@AudioQuality
Feature: Multi Device Demo Scenarios

  @AudioCalls @TwoNativeDrivers
  Scenario: Audio Calls Demo
    Given I switch to driver "perfecto"
    And I switch to driver "perfectodevii"
#    When I switch to driver "perfecto"
#    And I turn volume up
#    And I make audio call "+447818436459"
##    And I make audio call "+447786363722"
#    And I switch to driver "perfectodevii"
#    And I wait for "3" seconds
#    And I pick up the phone
#    When I switch to driver "perfecto"
##    And I inject an audio from "PUBLIC:ENG_F.wav" into the device
##    And I switch to driver "perfectodevii"
##    And I start audio recording
    And I wait for "5" seconds
##    And I stop audio recording
#    And I switch to driver "perfecto"
#    And I hang up the phone
#    And I switch to driver "perfectodevii"
#    And I hang up the phone
#    Then I validate audio "PUBLIC:ENG_F.wav"


  @AudioCallsWebAndMobile
  Scenario: Audio Calls Demo
    Given I switch to driver "perfectoRemote"
    And I switch to driver "perfectodevii"
    When I switch to driver "perfecto"
    And I turn volume up
    And I make audio call "+447818436459"
    And I make audio call "+447786363722"
    And I switch to driver "perfectodevii"
    And I wait for "3" seconds
    And I pick up the phone
    When I switch to driver "perfecto"
    And I inject an audio from "PUBLIC:ENG_F.wav" into the device
    And I switch to driver "perfectodevii"
    And I start audio recording
    And I wait for "5" seconds
    And I stop audio recording
    And I switch to driver "perfecto"
    And I hang up the phone
    And I switch to driver "perfectodevii"
    And I hang up the phone
    Then I validate audio "PUBLIC:ENG_F.wav"