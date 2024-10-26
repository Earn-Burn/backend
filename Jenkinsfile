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
                           -Dsonar.exclusions=**/*.gitignore ^
                           -Dsonar.modules=client,events,offers ^
                           -Dsonar.java.source=17 ^
                           -Dsonar.sourceEncoding=UTF-8
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