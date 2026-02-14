Feature: Database Testing with H2

  Background:
    Given the database connection is established

  @database @smoke
  Scenario: Create and verify users table
    When I create a users table
    Then the users table should exist

  @database @smoke
  Scenario: Insert and retrieve a user
    Given I create a users table
    When I insert a user with id 1, name "John Doe", and email "john@example.com"
    Then I should be able to retrieve user with id 1
    And the user name should be "John Doe"
    And the user email should be "john@example.com"

  @database
  Scenario: Insert multiple users and count
    Given I create a users table
    When I insert the following users:
      | id | name          | email                |
      | 1  | Alice Smith   | alice@example.com    |
      | 2  | Bob Johnson   | bob@example.com      |
      | 3  | Carol White   | carol@example.com    |
    Then the users table should have 3 records

  @database
  Scenario: Update user information
    Given I create a users table
    And I insert a user with id 1, name "John Doe", and email "john@example.com"
    When I update user with id 1 to have email "john.updated@example.com"
    Then the user with id 1 should have email "john.updated@example.com"

  @database
  Scenario: Delete a user
    Given I create a users table
    And I insert a user with id 1, name "John Doe", and email "john@example.com"
    When I delete user with id 1
    Then the user with id 1 should not exist

  @database
  Scenario: Query users by email domain
    Given I create a users table
    And I insert the following users:
      | id | name          | email                |
      | 1  | Alice Smith   | alice@example.com    |
      | 2  | Bob Johnson   | bob@test.com         |
      | 3  | Carol White   | carol@example.com    |
    When I query users with email domain "example.com"
    Then I should get 2 users

  @database @negative
  Scenario: Attempt to insert duplicate user ID
    Given I create a users table
    And I insert a user with id 1, name "John Doe", and email "john@example.com"
    When I attempt to insert a user with id 1, name "Jane Doe", and email "jane@example.com"
    Then a duplicate key exception should be thrown
