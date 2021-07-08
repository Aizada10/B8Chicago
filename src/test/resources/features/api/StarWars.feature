Feature: StarWars API

  Scenario: Get characters test
    Given I have a valid StarWars characters endpoint
    When  I sent GET request
    Then I should get 200 Status Code
    And I should get response in json format
    And I should get Luke Skywalker in the response

