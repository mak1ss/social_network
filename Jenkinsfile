pipeline {

    agent any

    checkout scm

    tools {
        maven 'Maven.3.9.8'

    }

    stages {

        stage('Compile') {

            steps {
                sh 'mvn clean compile'
            }

        }

        stage('Build') {

            steps {
                sh 'mvn clean install -Dmaven.test.skip'
            }

        }

        stage('Test execution') {

            steps {
                sh 'mvn clean test'
            }

        }
    }

}