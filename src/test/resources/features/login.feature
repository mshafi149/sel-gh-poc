Feature: Login

  Scenario: Successful login
    Given user is on login page
    When user logs in with valid credentials
    Then user should be logged in successfully
