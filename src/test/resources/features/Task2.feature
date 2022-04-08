@regression @part4
Feature: Introduction to cucumber Test 2

  Scenario Outline: Add person to the list
    Given I am on list of people with jobs page
    When I click on add person button
    And I enter input in add person page
      |name   | <name> |
      | job   | <job>  |
    And I click add button
    And I can see name 4 "<name>" in list of people
    And I can see job 4 "<job>" in list of people
    Examples:
      | name | job  |
      | John | Tester|
      |Ann   | Analyst|

  Scenario: Edit person in the list

    Given I am on list of people with jobs page
    When I click on edit pencil for person 3
    And I enter values in edit person page
      | name | John |
      | job | Developer|
    Then I click on edit button
    And I can see name 3 "John" in list of people
    And I can see job 3 "Developer" in list of people

  Scenario: Remove a person from the list

    Given I am on list of people with jobs page
    When I click on remove sign for person 3
    And I can not see name Jane in list of people
    And I can not see job Accountant in list of people

  Scenario: Reset original list after adding a person
    Given I am on list of people with jobs page
    When I click on add person button
    And I enter values in add person page
      | name | John |
      | job | Developer|
    And I click add button
    And I can see name 4 "John" in list of people
    And I can see job 4 "Developer" in list of people
    And I click on reset button
    And I see original list of names without added one

  Scenario: Reset original list after removing a person
    Given I am on list of people with jobs page
    When I click on remove sign for person 3
    And I click on reset button
    And I see original list of names including removed one

  Scenario: Reset original list after editing a person
    Given I am on list of people with jobs page
    When I click on edit pencil for person 3
    And I enter values in edit person page
      | name | John |
      | job | Developer|
    Then I click on edit button
    And I click on reset button
    And I see original list of names without inserted changes

    Scenario: clear button on adding a user works correctly
      Given I am on list of people with jobs page
      When I click on add person button
      And I enter values in add person page
        | name | John |
        | job | Developer|
      Then I click on clear button
      Then I see clear fields of the form

  Scenario: Reset original list after editing a person (second version)
    Given I am on list of people with jobs page
    When I click on edit pencil for person 3
    And I enter values in edit person page
      | name | John |
      | job | Developer|
    Then I click on edit button
    And I check the list, click on reset button, and check the new list
