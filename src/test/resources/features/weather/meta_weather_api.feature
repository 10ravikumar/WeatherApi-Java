Feature: As a MetaWeather API client, I want to retrieve weather information for location.

 # Scenario: User want to retrieve the weather detail base on location/coordinates.
 #   When user retrieve "tomorrows" weather for "Nottingham"
 #   Then the API should return successfully response

#  Scenario: retrieve the weather detail base on and date.
#    When user retrieve "tomorrows" weather for "52.949219,-1.143920"
#    Then the API should return successfully response

  #Scenario: retrieve the weather forecast history for a particular day & location.
    #Given fetch the where on earth id of "London" location
    #When retrieve the weather info for a particular day "tomorrows" & location "London"
    #Then the weather api should return successfully response

  Scenario Outline: retrieve the weather forecast history for a particular day & location.
    Given fetch the where on earth id of "<location>" location
    When retrieve the weather info for a particular day "<day>" & location "<location>"
    Then the weather api should return successfully response

    Examples: 
      | location   | day      |
      | London     | todays    |
      | Nottingham | tomorrows |
