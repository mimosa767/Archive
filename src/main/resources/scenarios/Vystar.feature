@Vystar
Feature: Vystar Test Suite

@VystarNewLogin
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

@VystarWebFlow
Scenario: I use the web browser to update Winn-Dixie
Given I switch to driver "perfectoRemote"
And I open browser to webpage "https://vystarcu.org/"
Then I click on "winn.button.originallogin" and ignore errors
And I click on "winn.button.login" and ignore errors
Then I login to the Winn Dixie Web Application
Then I click on "winn.savings.menu" and ignore errors
Then I click on "winn.savings.browseproducts" and ignore errors
And I click on "winn.savings.item" and ignore errors
And I click on "winn.browse.item" and ignore errors
Then I click on "winn.item.add" and ignore errors