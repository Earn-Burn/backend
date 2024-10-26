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
                            -Dsonar.projectKey=Earn-and-Burn ^
                            -Dsonar.host.url=http://localhost:9000 ^
                            -Dsonar.login=sqp_021bddfe93bc35945f4f7a0b16838f44c96f1aea ^
                            -Dsonar.projectBaseDir=microservices ^
                            -Dsonar.sources=. ^
                            -Dsonar.modules=client,events,offers ^
                            -Dsonar.java.source=17 ^
                            -Dsonar.sourceEncoding=UTF-8 ^
                            -Dsonar.client.projectBaseDir=microservices/client ^
                            -Dsonar.client.sources=src/main/java ^
                            -Dsonar.client.java.binaries=target/classes ^
                            -Dsonar.events.projectBaseDir=microservices/events ^
                            -Dsonar.events.sources=src/main/java ^
                            -Dsonar.events.java.binaries=target/classes ^
                            -Dsonar.offers.projectBaseDir=microservices/offers ^
                            -Dsonar.offers.sources=src/main/java ^
                            -Dsonar.offers.java.binaries=target/classes
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