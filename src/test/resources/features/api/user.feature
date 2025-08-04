@api @feature:UserAPI @story:GetUsers
Feature: API Testing for User Endpoint

  @get @severity:normal
  Scenario: Get a user by ID
    Given I set GET endpoint for user with ID "..."
    When I send GET HTTP request
    Then I receive valid HTTP response code 200