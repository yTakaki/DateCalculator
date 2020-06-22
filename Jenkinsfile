pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        withGradle() {
          build 'bootRun'
        }

      }
    }

  }
}