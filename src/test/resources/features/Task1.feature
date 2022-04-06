Feature: Introduction to cucumber test 1

  Scenario Outline: Error cases scenario outline
  Given I am on enter a number page
  When I enter number in enter a number page: "<number>"
  And I click submit number
  Then I see error message: "<error>"

Examples:
| number |  error                |
| 45     | Number is too small   |
| 150    | Number is too big     |
| text   | Please enter a number |

    Scenario: for correct number
      Given I am on enter a number page
      When I enter number in enter a number page: 64
      And I click submit number
      And I see popup with message: "Square root of 64 is 8.00"
