pipeline {
  environment {
    registry = 'navyasurendran/telecom'
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent any/*{
    dockerfile true
  }*/
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
    }
    
  }
}
