Feature: User API Feature

  @api
  Scenario: Get all users
    Given I set GET endpoint for all users
    When I send GET HTTP request
    Then I receive valid HTTP response code 200
    And Response body should contain list of users
