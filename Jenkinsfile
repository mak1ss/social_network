pipeline {

    agent any

    stages {
        stage('Build') {

            sh 'mvn clean install -Dmaven.test.skip'

        }

        stage('Test execution') {

            sh 'mvn clean test'

        }
    }

}