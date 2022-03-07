@NHSBSA
Feature: NHS Demo

  @NHSBSA_Web
  Scenario Outline: Cost exemption status checks
    Given I am on "https://services.nhsbsa.nhs.uk/check-my-nhs-exemption/start"
    And I check patient first name "<firstName>" last name "<lastName>" with DOB "<dob>" at postcode "<postcode>" is exempt from costs
    Examples:
      | recId | firstName | lastName  | dob         | postcode  |
      | 1     | Joe	      | Bloggs    | 22/11/1980  | NE99 8AL  |
#      | 2     | Jim	      | King      | 23/12/1981  | BS1 3AG   |
