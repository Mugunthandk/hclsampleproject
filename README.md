\# E-Commerce Automation Testing Framework



\## Project Overview

An end-to-end E-Commerce Automation Testing Framework developed using Java, Selenium WebDriver, TestNG, Maven, and Jenkins.



The framework automates website testing, validates application functionality, performs API testing, and executes performance testing using JMeter.



\## Technologies Used

\- Java

\- Selenium WebDriver

\- TestNG

\- Maven

\- Python

\- REST API Testing

\- Cucumber

\- SQL

\- Apache JMeter

\- Jenkins

\- Git and GitHub



\## Key Features

\- Web UI automation using Selenium WebDriver

\- Test execution and validation using TestNG

\- Page Object Model (POM) structure

\- API testing

\- Performance testing using JMeter

\- Automated CI/CD pipeline using Jenkins

\- TestNG HTML reports

\- JMeter HTML performance reports



\## Test Execution



Run automation tests:



```bash

mvn clean test

```



\## JMeter Performance Testing



Execute the JMeter test plan in non-GUI mode and generate an HTML report:



```bash

jmeter -n -t "jmeter/ECommerce Load Test.jmx" -l "jmeter/results/jenkins-run.jtl" -e -o "jmeter/results/jenkins-html-report"

```



\## Jenkins Pipeline

The Jenkins pipeline automates:

1\. Source code checkout

2\. Maven build

3\. TestNG automation execution

4\. Test report publishing

5\. JMeter performance testing

6\. JMeter HTML report publishing



\## Reports

\- TestNG HTML Report

\- JMeter HTML Performance Report



\## Repository

https://github.com/Mugunthandk/hclsampleproject



\## Author

Mugunthan DK



