pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '4'))
        timeout(time: 30, unit: 'MINUTES')
    }

    tools {
        maven 'default-maven'
    }

    stages {
        stage('Compile') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    withChecks(name: 'Compilation', includeStage: true) {
                        sh 'mvn clean compile'
                    }
                }
            }
            post {
                success {
                    publishChecks name: 'Compilation', summary: 'Compilation Check', text: 'Compilation stage completed successfully.'
                }
                failure {
                    publishChecks name: 'Compilation', summary: 'Compilation Check', text: 'Compilation stage failed.'
                }
            }
        }
        stage('Build') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    withChecks(name: 'Build', includeStage: true) {
                        sh 'mvn clean install -Dmaven.test.skip'
                    }
                }
            }
            post {
                success {
                    publishChecks name: 'Build', summary: 'Build Check', text: 'Build stage completed successfully.'
                }
                failure {
                    publishChecks name: 'Build', summary: 'Build Check', text: 'Build stage failed.'
                }
            }
        }
        stage('Test execution') {
            steps {
                timeout(time: 15, unit: 'MINUTES') {
                    withChecks(name: 'Testing', includeStage: true) {
                        sh 'mvn clean test'
                    }
                }
            }
            post {
                success {
                    publishChecks name: 'Testing', summary: 'Testing Check', text: 'Testing stage completed successfully.'
                }
                failure {
                    publishChecks name: 'Testing', summary: 'Testing Check', text: 'Testing stage failed.'
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
