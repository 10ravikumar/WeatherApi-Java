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
      | London              | 2020/02/01 |
      | Nottingham          | 2021/01/01 |
  
  # As per the weather api document it should contains 14 fields in response.
  # But it is returning 15 fields
  # "created" field is not present in api document.
     
   Scenario: Verify the weather api response field aganist json schema.
    Given the location "Nottingham" for search earth Id is available
    When the weather forecast for a "Nottingham" on a "tomorrows" is retrieve
    Then the weather api should return successfull response
    And the weather api response fields should same as weather schema fields

#  it looks like location json response fields are not matching as per the location API document.
# few fields like ""slug", "crawl_rate"
