@Cafe24
Feature: Cafe24 Demo Scenarios

  @Cafe24SignUpOrder
  Scenario: Signup New Account and Order product
    Given I am on "https://vestworks24.cafe24shop.com/"
    And I audit accessibility on page "Home"
#    When I click on hamburger menu and signup
#    And I enter details and signup
#    Then User should be created
#    And I order product
#    And I checkout providing all details
#    Then Order should be successfully placed

  @Cafe24SignUpProcessOrder
  Scenario: Signup and Order Product, Process and Cancel the Order
    Given I switch to driver "perfectoRemote"
    And I am on "https://vestworks24.cafe24shop.com/"
    And I switch to driver "perfectodeviiRemote"
    And I am on "https://cafe24shop.com/login"
    And I switch to driver "perfectoRemote"
    When I click on hamburger menu and signup
    And I enter details and signup
    Then User should be created
    #When I login with signuped user
    And I order product
    And I checkout providing all details
    Then Order should be successfully placed
    When I switch to driver "perfectodeviiRemote"
    And I am on "https://cafe24shop.com/login"
    And I login as admin
    And I search for the created order
    Then I cancel the order