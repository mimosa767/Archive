@MultiDevice
Feature: Multi Device Demo Scenarios

  @2DevicesDemo
  Scenario: 2 Devices Demo
    Given I switch to driver "perfectoRemote"
    And I switch to driver "perfectodeviiRemote"
    And I switch to driver "perfectoRemote"
    And I open browser to webpage "https://www.perfecto.io"
    And I switch to driver "perfectodeviiRemote"
    And I start application by name "Tide-staging"

  @3DevicesDemo
  Scenario: 3 Devices Demo
    Given I switch to driver "perfectoRemote"
    And I switch to driver "perfectodevii"
    And I switch to driver "perfectodeviii"
    And I switch to driver "perfectoRemote"
    And I open browser to webpage "https://www.perfecto.io"
    And I switch to driver "perfectodevii"
    And I start application by name "Settings"
    And I switch to driver "perfectodeviii"
    And I start application by name "Settings"
