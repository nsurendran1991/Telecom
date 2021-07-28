pipeline {
  environment {
    registry = 'navyasurendran/telecom'
    registryCredential = 'dockerhub'
    dockerImage = ''
  }
  agent {
    dockerfile true
  }
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
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          
          withDockerRegistry(credentialsId: 'dockerhub', toolName: 'docker') {
    dockerImage.push()
}
         /* docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
          }*/
        }
         /*withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockerhub',
        usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
      sh 'docker login -u "$USERNAME" -p "$PASSWORD"'
      
    }
        sh 'docker push $dockerImage'*/
      }
    }
    /*stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }*/
  }
}
