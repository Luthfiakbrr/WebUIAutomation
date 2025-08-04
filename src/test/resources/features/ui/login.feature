Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given I open the login page
    When I enter valid username and password
    And I click the login button
    Then I should be redirected to the dashboard

  Scenario: Login fails with invalid credentials
    Given I open the login page
    When I enter invalid username and password
    And I click the login button
    Then I should see an error message

  Scenario: Login with empty fields
    Given I open the login page
    When I leave username and password empty
    And I click the login button
    Then I should see a validation warning
