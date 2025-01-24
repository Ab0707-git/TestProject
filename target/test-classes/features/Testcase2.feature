@Todo @Markcompleted
Feature: User has to complete a task

  Scenario: User has to complete a task
    Given User has to complete one task
    When User adds "task"
    When User clicks on "task" Todo checkbox
    Then Check the total number of taskcount
    And close browser

