pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Checking out source code'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building Maven project'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running Automation Tests'
                bat 'mvn test'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish TestNG HTML Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'index.html',
                    reportName: 'TestNG HTML Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: true
                ])
            }
        }

        stage('Run JMeter Performance Test') {
            steps {
                bat '''
                    if not exist "jmeter\\results" mkdir "jmeter\\results"

                    if exist "jmeter\\results\\jenkins-html-report" rmdir /s /q "jmeter\\results\\jenkins-html-report"

                    "C:\\Users\\CSE LAB - 2\\Downloads\\apache-jmeter-5.6.3\\apache-jmeter-5.6.3\\bin\\jmeter.bat" -n -t "jmeter\\ECommerce Load Test.jmx" -l "jmeter\\results\\jenkins-run.jtl" -e -o "jmeter\\results\\jenkins-html-report"
                '''
            }
        }

        stage('Publish JMeter Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'jmeter/results/jenkins-html-report',
                    reportFiles: 'index.html',
                    reportName: 'JMeter HTML Report',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: false
                ])
            }
        }
    }

    post {
        always {
            echo 'Automation Pipeline Completed'
        }

        success {
            echo 'All tests passed!'
        }

        failure {
            echo 'Pipeline failed. Check Console Output.'
        }
    }
}
