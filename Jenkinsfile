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
        script {
          dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Deploy Image') {
      steps{
        /*script {
          docker.withRegistry( 'https://hub.docker.com/repository/docker', registryCredential ) {
            dockerImage.push()
          }
        }*/
         withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'dockerhub',
        usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
      sh 'docker login -u "$USERNAME" -p "$PASSWORD"'
      sh 'dockerImage.push()'
    }
      }
    }
    /*stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
      }
    }*/
  }
}
