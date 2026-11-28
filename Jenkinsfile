@'
pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code from GitHub'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compiling Java project'
                bat 'mvn clean compile'
            }
        }

        stage('Run Automation Tests') {
            steps {
                echo 'Running Selenium, TestNG, Cucumber, API and Database tests'
                bat 'mvn test'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo 'Automation Pipeline Completed'
        }

        success {
            echo 'All automation tests passed successfully!'
        }

        failure {
            echo 'Pipeline failed. Please check Console Output.'
        }
    }
}
'@ | Set-Content -Path "Jenkinsfile" -Encoding UTF8