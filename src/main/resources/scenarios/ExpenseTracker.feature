@ExpenseTracker
Feature: Expense Tracker Demo Scenarios

  @ExpenseTrackerLogExpense
    Scenario: Add expense after Login
    Given I login to Expense Tracker app with username and password
    And I add expense with head "Meal" , amount "23.45" , "USD" currency and category "Business"
    And I attach receipt
    Then I logout of application













