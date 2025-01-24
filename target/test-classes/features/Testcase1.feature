Feature: ToDo App Testing

  @Todo @addTasks
  Scenario: To add items to the to-do list
    Given User is ready to add Todo items
    When User adds "Go for a walk" in one
    And User adds "Visit doctor" in one
    And User adds "Meeting" in one
    Then The total number of tasks should be 3 in one
    And close browser one
