pipeline {
    agent any
    triggers {
        githubPush()
    }

    environment {
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_TOKEN = credentials('sonar-token')
    }

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'github-token', branch: 'lab4', url: 'https://github.com/vladnovoren/dnd.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=integral-calculator'
                }
            }
        }

        // stage('Docker Build & Push') {
        //     steps {
        //         sh '''
        //         docker build -t your-dockerhub-user/integral-calculator:latest .
        //         docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
        //         docker push your-dockerhub-user/integral-calculator:latest
        //         '''
        //     }
        // }
    }
}
