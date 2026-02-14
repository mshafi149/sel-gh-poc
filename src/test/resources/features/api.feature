Feature: JSONPlaceholder API Testing

  Background:
    Given the API base URL is "https://jsonplaceholder.typicode.com"

  @api @smoke
  Scenario: Get a single user by ID
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should contain field "name" with value "Leanne Graham"
    And the response should contain field "email" with value "Sincere@april.biz"

  @api @smoke
  Scenario: Get all users
    When I send a GET request to "/users"
    Then the response status code should be 200
    And the response should be a JSON array
    And the response array should have 10 items

  @api
  Scenario: Get user posts
    When I send a GET request to "/users/1/posts"
    Then the response status code should be 200
    And the response should be a JSON array
    And the response array should not be empty

  @api
  Scenario: Create a new user
    Given I have the following user data:
      | name     | John Doe              |
      | email    | john.doe@example.com  |
      | username | johndoe               |
    When I send a POST request to "/users" with the user data
    Then the response status code should be 201
    And the response should contain field "name" with value "John Doe"
    And the response should contain field "id"

  @api
  Scenario: Update a user
    Given I have the following update data:
      | name  | Jane Doe Updated      |
      | email | jane.updated@test.com |
    When I send a PUT request to "/users/1" with the update data
    Then the response status code should be 200
    And the response should contain field "name" with value "Jane Doe Updated"

  @api
  Scenario: Partially update a user
    Given I have the following patch data:
      | email | patched@example.com |
    When I send a PATCH request to "/users/1" with the patch data
    Then the response status code should be 200
    And the response should contain field "email" with value "patched@example.com"

  @api
  Scenario: Delete a user
    When I send a DELETE request to "/users/1"
    Then the response status code should be 200

  @api @negative
  Scenario: Get non-existent user
    When I send a GET request to "/users/999999"
    Then the response status code should be 404

  @api
  Scenario: Verify user address structure
    When I send a GET request to "/users/1"
    Then the response status code should be 200
    And the response should have nested field "address.city" with value "Gwenborough"
    And the response should have nested field "address.geo.lat" with value "-37.3159"

  @api
  Scenario Outline: Get multiple users by ID
    When I send a GET request to "/users/<userId>"
    Then the response status code should be 200
    And the response should contain field "name" with value "<expectedName>"

    Examples:
      | userId | expectedName        |
      | 1      | Leanne Graham       |
      | 2      | Ervin Howell        |
      | 3      | Clementine Bauch    |
