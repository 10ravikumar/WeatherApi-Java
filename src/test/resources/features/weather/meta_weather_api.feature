Feature: As a MetaWeather API client, I want to retrieve weather forecast history for a particular location & day.

  Scenario Outline: Retrieve the weather forecast history for a particular location & day.
    Given the location "<location>" for search earth Id is available
    When the weather forecast for a "<location>" on a "<day>" is retrieve
    Then the weather api should return successfull response

    Examples: 
      | location            | day        |
      | London              | today      |
      | Nottingham          | tomorrows  |
      | 51.506321,-0.12714  | tomorrows  |
      | 52.949219,-1.143920 | today      |
      | London              | 2010/01/01 |
