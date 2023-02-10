@WinnDixie
  Feature: Winn Dixie POC Test Suite

  @WinnDixieNewLogin
  Scenario: Login Scales to 6 Concurrent for Fastback
  Given I click on "android.whileUsing" and ignore errors
  Then I click on "allow" and ignore errors
  And I start device logging
  And I click on "getStarted" and ignore errors
  Then I switch to webview context
  When I login to Winn Dixie app
  Then I switch to native context
  Then I click on "list" and ignore errors
  And I click on "myShoppingList" and ignore errors
  And I click on "genericItem" and ignore errors
  Then I stop device logging


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
          Then I click on "whileUsing" and ignore errors
          And I wait for "10" seconds
          Then I click on "allow" and ignore errors
          Then I wait for "5" seconds
          And I click on "getStarted" and ignore errors
          Then I wait for "5" seconds
          Then I switch to webview context
          When I login to Winn Dixie app
          Then I switch to native context
          And I click on "savings" and ignore errors
          Then I wait for "2" seconds
          Then I click on "rewards" and ignore errors
          And I wait for "2" seconds
          Then I click on "list" and ignore errors
          And I wait for "2" seconds
          Then I click on "more" and ignore errors

          @2WinnDixieWebFlow
          Scenario: I use the web browser to update Winn-Dixie
            Given I switch to driver "perfectoRemote"
            And I open browser to webpage "https://www.winndixie.com/"
            Then I click on "winn.button.originallogin" and ignore errors
            And I click on "winn.button.login" and ignore errors
            Then I login to the Winn Dixie Web Application
            Then I click on "winn.savings.menu" and ignore errors
            Then I click on "winn.savings.browseproducts" and ignore errors
            And I click on "winn.savings.item" and ignore errors
            And I click on "winn.browse.item" and ignore errors
            Then I click on "winn.item.add" and ignore errors

    @AndroidCheckList
    Scenario: Login to Winn-Dixie App
      Given I wait for "10" seconds
      Then I click on "whileUsing" and ignore errors
      Then I wait for "5" seconds
      Then I click on "allow" and ignore errors
      Then I wait for "5" seconds
      And I start device logging
      And I click on "getStarted" and ignore errors
      Then I wait for "5" seconds
      Then I switch to webview context
      When I login to Winn Dixie app
      Then I wait for "2" seconds
      Then I switch to native context
      Then I click on "list" and ignore errors
      And I wait for "2" seconds
      And I click on "myShoppingList" and ignore errors
      Then I wait for "2" seconds
      And I click on "genericItem" and ignore errors
      Then I wait for "2" seconds
      Then I stop device logging

      @WinnDixieMultideviceFlow
      Scenario: I make changes in Web app and verify in Mobile app
        Given I switch to driver "perfectoRemote"
        Then I switch to driver "perfectodevii"
        Then I switch to driver "perfectoRemote"
        And I open browser to webpage "https://www.winndixie.com/"
        Then I click on "winn.button.originallogin" and ignore errors
        And I click on "winn.button.login" and ignore errors
        Then I login to the Winn Dixie Web Application
        Then I click on "winn.savings.menu" and ignore errors
        Then I click on "winn.savings.browseproducts" and ignore errors
        And I click on "winn.savings.item" and ignore errors
        And I click on "winn.browse.item" and ignore errors
        Then I click on "winn.item.add" and ignore errors
        Then I switch to driver "perfectodevii"
        Then I wait for "2" seconds
        Then I click on "android.whileUsing" and ignore errors
        Then I wait for "5" seconds
        Then I click on "allow" and ignore errors
        Then I wait for "5" seconds
        And I start device logging
        And I click on "getStarted" and ignore errors
        Then I wait for "5" seconds
        Then I switch to webview context
        When I login to Winn Dixie app
        Then I wait for "5" seconds
        Then I switch to native context
        Then I click on "list" and ignore errors
        And I wait for "2" seconds
        And I click on "myShoppingList" and ignore errors
        Then I wait for "2" seconds
        And I click on "genericItem" and ignore errors
        Then I wait for "2" seconds
        Then I stop device logging

    @WinnDixieNewLoginNetworkSimulation
    Scenario: Android Samsung Galaxy S22 Under 4G Good Network Conditions
      Given I click on "android.whileUsing" and ignore errors
      Then I click on "allow" and ignore errors
      Then I start network virtualization with "4g_lte_advanced_good" profile
      And I start device logging
      And I click on "getStarted" and ignore errors
      Then I switch to webview context
      When I login to Winn Dixie app
      Then I switch to native context
      Then I click on "list" and ignore errors
      # And I click on "myShoppingList" and ignore errors
      # And I click on "genericItem" and ignore errors
      Then I stop device logging
      And I stop network virtualization


      Scenario: I call phone A with phone B
        Given I start application by id "com.samsung.android.dialer"
        Then I click on "android.keypad.button" and ignore errors
        And I click on "six.button" and ignore errors
        Then I click on "seven.button" and ignore errors
        And I click on "eight.button" and ignore errors
        Then I click on "six.button" and ignore errors
         And I click on "six.button" and ignore errors
        Then I click on "two.button" and ignore errors
        And I click on "zero.button" and ignore errors
        Then I click on "zero.button" and ignore errors
        And I click on "zero.button" and ignore errors
        And I click on "three.button" and ignore errors
        Then I click on "call.button" and ignore errors
        Then I wait for "15" seconds










