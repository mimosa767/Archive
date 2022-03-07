@directions
  Feature: requesting directions in Google Maps

    @directions_cycling
    Scenario: Cycling through locations
      Given I try to close application by name "Maps" and ignore error
      And I start application by name "Maps"
      And I set the device location to the address "Windsor Castle, UK"
      And I wait for "2" seconds
      And I set the device location to the address "Great Wall of China, China"
      And I wait for "2" seconds
      And I set the device location to the address "Kiyomizutera, Kyoto, Japan"
      And I wait for "2" seconds
      And I set the device location to the address "Pretoria, South Africa"
      And I wait for "2" seconds
      And I set the device location to the address "Machu Picchu, Peru"
      And I try to close application by name "Maps" and ignore error

    @directions_cycling_multi
    Scenario: Cycling through locations in parallel
      Given I switch to driver "perfectoRemote"
      And I start application by name "Maps"
      And I switch to driver "perfectodeviiRemote"
      And I start application by name "Maps"
      And I switch to driver "perfectodeviiiRemote"
      And I start application by name "Maps"
      And I switch to driver "perfectoRemote"
      And I set the device location to the address "Windsor Castle, UK"
      And I switch to driver "perfectodeviiRemote"
      And I set the device location to the address "Windsor Castle, UK"
      And I switch to driver "perfectodeviiiRemote"
      And I set the device location to the address "Windsor Castle, UK"
      And I switch to driver "perfectoRemote"
      And I set the device location to the address "Great Wall of China, China"
      And I switch to driver "perfectodeviiRemote"
      And I set the device location to the address "Great Wall of China, China"
      And I switch to driver "perfectodeviiiRemote"
      And I set the device location to the address "Great Wall of China, China"
      And I switch to driver "perfectoRemote"
      And I set the device location to the address "Kiyomizutera, Kyoto, Japan"
      And I switch to driver "perfectodeviiRemote"
      And I set the device location to the address "Kiyomizutera, Kyoto, Japan"
      And I switch to driver "perfectodeviiiRemote"
      And I set the device location to the address "Kiyomizutera, Kyoto, Japan"
      And I switch to driver "perfectoRemote"
      And I set the device location to the address "Pretoria, South Africa"
      And I switch to driver "perfectodeviiRemote"
      And I set the device location to the address "Pretoria, South Africa"
      And I switch to driver "perfectodeviiiRemote"
      And I set the device location to the address "Pretoria, South Africa"
      And I switch to driver "perfectoRemote"
      And I set the device location to the address "Machu Picchu, Peru"
      And I switch to driver "perfectodeviiRemote"
      And I set the device location to the address "Machu Picchu, Peru"
      And I switch to driver "perfectodeviiiRemote"
      And I set the device location to the address "Machu Picchu, Peru"