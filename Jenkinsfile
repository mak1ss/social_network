pipeline {

    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr:'4'))
        timeout(time: 30, unit: 'MINUTES')
    }

    tools {
        maven 'default-maven'
    }
    stages {
        stage ('Compile') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    withChecks(name: 'Compilation') {
                        sh 'mvn clean compile'
                    }
                }
            }
        }
        stage ('Build') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    sh 'mvn clean install -Dmaven.test.skip'
                }
            }
        }
        stage ('Test execution') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
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