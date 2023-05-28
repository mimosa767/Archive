@WinnDixie
  Feature: Winn Dixie POC Test Suite
    @Arbeit
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

    @ArebeitTwoPhones
    Scenario: I call phone A with phone B and answer the phone call with phone B
      Given I switch to driver "perfectodeviiv"
      Then I start application by id "com.samsung.android.dialer"
      Then I switch to driver "perfectodeviv"
      Then I start application by id "com.samsung.android.dialer"
      Then I click on "android.keypad.button" and ignore errors
      And I click on "nine.button" and ignore errors
      Then I click on "seven.button" and ignore errors
      And I click on "eight.button" and ignore errors
      Then I click on "six.button" and ignore errors
      And I click on "three.button" and ignore errors
      Then I click on "five.button" and ignore errors
      And I click on "two.button" and ignore errors
      Then I click on "nine.button" and ignore errors
      And I click on "five.button" and ignore errors
      And I click on "eight.button" and ignore errors
      Then I click on "call.button" and ignore errors
      Then I wait for "15" seconds
      Given I switch to driver "perfectodeviiv"
      Then I wait for "15" seconds

    @ArbeitOnePhoneWait
    Scenario: I open up a phone and wait for a call
      Given I start application by id "com.samsung.android.dialer"
      Then I click on "android.keypad.button" and ignore errors
      Then I wait for "45" seconds

      @ArbeitSendReceiveSMS
      Scenario: I open up my messenger application and receive text messages
        Given I start application by id "com.samsung.android.messaging"
        Then I wait for "45" seconds

    @EverbridgeTwoSMSPhones
    Scenario: I text phone A with phone B
      Given I switch to driver "perfectodeviiv"
      Then I start application by id "com.google.android.apps.messaging"
      Then I close application by id "com.google.android.apps.messaging"
      Then I start application by id "com.google.android.apps.messaging"
      Then I switch to driver "perfectodeviv"
      Then I start application by id "com.google.android.apps.messaging"
      Then I wait for "15" seconds
      Given I switch to driver "perfectodeviiv"
      Then I wait for "15" seconds

    @EverbridgeTwoSMSPhonesA
    Scenario: I text phone A with phone B
      Given I switch to driver "perfectodeviiv"
      Then I start application by id "com.samsung.android.messaging"
      Then I close application by id "com.samsung.android.messaging"
      Then I start application by id "com.samsung.android.messaging"
      Then I switch to driver "perfectodeviv"
      Then I start application by id "com.samsung.android.messaging"
      Then I wait for "15" seconds
      Given I switch to driver "perfectodeviiv"
      Then I wait for "15" seconds


      @CMSReceiveSMS
      Scenario: I open up my messenger application and receive text messages
        Given I start application by id "com.samsung.android.messaging"
        Then I wait for "45" seconds

    @CMTwoPhones
    Scenario: I message phone A with phone B and see the request on phone B
      Given I switch to driver "perfectodeviiv"
      Then I start application by id "com.samsung.android.messaging"
      Then I switch to driver "perfectodeviv"
      Then I start application by id "com.samsung.android.messaging"
      Then I wait for "15" seconds
      Given I switch to driver "perfectodeviiv"
      Then I wait for "15" seconds






