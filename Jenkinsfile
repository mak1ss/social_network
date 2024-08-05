pipeline {

    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr:'4'))
    }

    tools {
        maven 'default-maven'
    }
    stages {
        stage ('Compile') {
            steps {
                withChecks(name: 'Compilation') {
                    sh 'mvn clean compile'
                }
            }
        }
        stage ('Build') {
            steps {
                withChecks(name: 'Build') {
                    sh 'mvn clean install -Dmaven.test.skip'
                }
            }
        }
        stage ('Test execution') {
            steps {
                withChecks(name: 'Testing') {
                    sh 'mvn clean test'
                }
            }
        }
    }
    post {
        always {
            deleteDir()
        }
    }
}