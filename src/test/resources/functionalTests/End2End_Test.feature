Feature: End to End Tests for Country Information API
  Description: The purpose of this test is to authenticate the validation of data and properties.
  Country Info API URL : https://restcountries.eu/rest/v2/alpha/USA

  Background: User Input Country Code for Country Info
    Given The Base URL is Accessible

    Scenario: The Master URL Should Return ALl Countries in the List
      Given The Base URL is Accessible
      Then All Countries Info are Returned

    Scenario: Input Two Letters Country Code in URL Should Return Matched Country Info
      Given A List of Countries are Available
      When Input Two Letters Country Code
      Then A Country Info is Returned

    Scenario: Input Three Letters Country Code in URL Should Return Matched Country Info
      Given A List of Countries are Available
      When Input Three Letters Country Code
      Then A Country Info is Returned

    Scenario: Each Countries Should Have 24 Key-Value Pairs Properties
      Given A List of Countries are Available
      When Input Three Letters Country Code
      Then Total of 24 Key Value Pairs Should Be Returned

    Scenario: All Properties of a Country Should Not Be Null
      Given A List of Countries are Available
      When Input Three Letters Country Code
      Then Properties Returned Should All Have A Key
