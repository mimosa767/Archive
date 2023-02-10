@BecktonDickson
  Feature: BD inject video use case validation


    @BDInjectVideo
    Scenario: I inject video using Snapchat
    Given I start application by id "com.snapchat.android"
    And I wait for "1" seconds
    Then I start network virtualization with HAR capture
    Then I click on "addVideoButton" and ignore errors
    And I wait for "1" seconds
    Then I click on "cameraRollButton" and ignore errors
    And I wait for "1" seconds
    Then I click on "videoSelection" and ignore errors
    And I wait for "1" seconds
    Then I click on "editButton" and ignore errors
    And I wait for "1" seconds
    Then I click on "snapConatainer" and ignore errors
    And I wait for "2" seconds
    Then I swipe right a lot
    And I wait for "5" seconds
    Then I swipe right a lot
    And I wait for "5" seconds
    Then I close application by id "com.snapchat.android"
