@WinnDixie
  Feature: Winn Dixie POC

    @WinnDixieLogin
      Scenario: Login to Winn Dixie
        Given I start application by id "com.biloholdings.mywinndixie.prod"
        Then I wait "1000" seconds
        And I start network virtualization with HAR capture
        Then I stop network virtualization

      @AndroidLogin
      Scenario: Login to Winn-Dixie App
        Given I click on "whileUsing" and ignore errors
        Then I wait for "5" seconds
        Then I click on "allow" and ignore errors
        Then I wait for "5" seconds
        And I start device logging
        And I click on "getStarted" and ignore errors
        Then I wait for "5" seconds
        Then I switch to webview context
        When I login to Winn Dixie app
        Then I wait for "20" seconds
        Then I stop device logging

        @AndroidLoggedIn
        Scenario: I verify all menu buttons are working as expected
          Given I start application by id "com.biloholdings.mywinndixie.prod"
          Then I click on "home" and ignore errors
          And I click on "savings" and ignore errors
          Then I click on "rewards" and ignore errors
          Then I click on "list" and ignore errors
          Then I click on "more" and ignore errors

          @2WinnDixieDevicesDemo
          Scenario: I register with Winn-Dixie
            Given I start application by id "com.biloholdings.muywinndixie.prod"








