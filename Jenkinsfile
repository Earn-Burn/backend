pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Earn-Burn/backend.git', branch: 'main'
            }
        }

        stage('Compile Microservices') {
            steps {
                bat '''
                    cd microservices/client && mvn clean compile
                    cd ../events && mvn clean compile
                    cd ../offers && mvn clean compile
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'sonar-scanner'
                    withSonarQubeEnv('SonarQube') {
                        bat """
                            ${scannerHome}\\bin\\sonar-scanner.bat ^
                            -Dsonar.projectKey=spring-test ^
                            -Dsonar.host.url=http://localhost:9000 ^
                            -Dsonar.login=sqp_021bddfe93bc35945f4f7a0b16838f44c96f1aea ^
                            -Dsonar.sources=microservices/client/src/main/java,microservices/events/src/main/java,microservices/offers/src/main/java ^
                            -Dsonar.java.binaries=microservices/client/target/classes,microservices/events/target/classes,microservices/offers/target/classes ^
                            -Dsonar.modules=client,events,offers ^
                            -Dsonar.java.source=17 ^
                            -Dsonar.sourceEncoding=UTF-8 ^
                            -Dsonar.client.sonar.sources=microservices/client/src/main/java ^
                            -Dsonar.client.sonar.java.binaries=microservices/client/target/classes ^
                            -Dsonar.events.sonar.sources=microservices/events/src/main/java ^
                            -Dsonar.events.sonar.java.binaries=microservices/events/target/classes ^
                            -Dsonar.offers.sonar.sources=microservices/offers/src/main/java ^
                            -Dsonar.offers.sonar.java.binaries=microservices/offers/target/classes
                        """
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}