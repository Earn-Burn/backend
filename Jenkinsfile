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
                // Compile chaque microservice
                bat '''
                    cd microservices
                    mvn -f client/pom.xml clean compile
                    mvn -f events/pom.xml clean compile
                    mvn -f offers/pom.xml clean compile
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
                            -Dsonar.sources=microservices/client/src,microservices/events/src,microservices/offers/src ^
                            -Dsonar.java.binaries=microservices/client/target/classes,microservices/events/target/classes,microservices/offers/target/classes ^
                            -Dsonar.modules=client,events,offers ^
                            -Dsonar.java.source=17 ^
                            -Dsonar.sourceEncoding=UTF-8 ^
                            -Dsonar.coverage.exclusions=**/test/**,**/target/** ^
                            -Dsonar.exclusions=**/test/**,**/target/**
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