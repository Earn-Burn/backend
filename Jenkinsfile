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
                        -Dsonar.token=sqa_0ce905e2790e1a6f26e271ce93035f7ce579b69c ^
                        -Dsonar.projectBaseDir=microservices ^
                        -Dsonar.sources=. ^
                        -Dsonar.exclusions=**/*.gitignore,**/.mvn/wrapper/maven-wrapper.properties,**/mvnw,**/*.cmd,**/data/journal/** ^
                        -Dsonar.java.source=17 ^
                        -Dsonar.sourceEncoding=UTF-8 ^
                        -Dsonar.java.binaries=client/target/classes,events/target/classes,offers/target/classes ^
                        -Dsonar.client.projectBaseDir=microservices/client ^
                        -Dsonar.client.sources=. ^
                        -Dsonar.events.projectBaseDir=microservices/events ^
                        -Dsonar.events.sources=. ^
                        -Dsonar.offers.projectBaseDir=microservices/offers ^
                        -Dsonar.offers.sources=.
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
