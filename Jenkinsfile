pipeline {
    environment {
        registry = 'navyasurendran/telecom'
        registryCredential = 'dockerhub'
        dockerImage = ''
    }
    agent any
    tools {
        maven 'maven'
        dockerTool 'docker'       
    }
    stages {
        stage('Cloning Git') {
            steps {
                git 'https://github.com/nsurendran1991/Telecom.git'
            }
        }
        stage('Building image') {
            steps{
                sh 'mvn clean install'
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
            post {
                always {
                    
                    jiraSendBuildInfo site: 'nsurendran1991.atlassian.net'
                }
            }
        }
        
        stage('Deploy Image') {
            steps{
                script {          
                    withDockerRegistry(credentialsId: 'dockerhub', toolName: 'docker') {
                        dockerImage.push()
                    }         
                }        
            }
        }
    }    
}
