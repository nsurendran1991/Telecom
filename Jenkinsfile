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
    
    stage('Pull Docker Image') {
      steps{
        script {
          
          withDockerRegistry(credentialsId: 'dockerhub', toolName: 'docker') {
    
         }
          sh 'docker pull registry'
         
        }        
        
      }
      post {
        always {
            jiraSendDeploymentInfo site: 'nsurendran1991.atlassian.net', enableGating: false, environmentId: 'jenkins-testing-prod-1', environmentName: 'staging', environmentType: 'staging'

        }
      }
    }
    
  }
}
