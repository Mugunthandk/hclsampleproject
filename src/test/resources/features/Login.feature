Feature: E-Commerce Login

  Scenario: Successful login with valid credentials
    Given user opens the login page
    When user enters valid credentials
    And clicks the login button
    Then user should reach the products page

  Scenario: Login with invalid credentials
    Given user opens the login page
    When user enters invalid credentials
    And clicks the login button
    Then an error message should be displayed