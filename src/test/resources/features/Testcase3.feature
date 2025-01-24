@Todo @Edittask
Feature: User has to edit task

    Scenario Outline: To edit Todo task 
    Given The user has to edit task
    When The user adds "Go to the gym"
    When The user double clicks on "Go to the Gym"
    Then The check the count of the task.
    And Close browser

     Examples:
      | task                |
      | Go to the gym       |
      