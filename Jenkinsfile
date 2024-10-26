pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Earn-Burn/backend.git', branch: 'main'
            }
        }

       stage('Compile') {
           steps {
               // Utilisez le chemin absolu vers le pom.xml
               bat 'mvn -f "C:/Users/hp/Desktop/projet-stage/earn backend/backend/microservices/pom.xml" clean compile'
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
                            -Dsonar.sources="C:/Users/hp/Desktop/projet-stage/earn backend/backend/microservices/src" ^
                            -Dsonar.java.binaries=./backend/microservices/target/classes
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
