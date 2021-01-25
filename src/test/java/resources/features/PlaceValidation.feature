Feature: Validating Place API's

  @AddPlace	@Regression
  Scenario Outline: Verify if Place is being added successfully using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples: 
      | name | language | address |
      | Thakran | en-US    | H.No 2070, FF, Sector-46,Gurgaon |
  # | DCBA  	| en-GB    | Hyderbad |
  
  @DeletePlace	@Regression
  Scenario Outline: Verify if Place is being removed successfully using DeletePlaceAPI
    Given Delete Place Payload with "<place_id>"
    When User calls "DeletePlaceAPI" with "POST" http request
    Then The API call got success with status code 200
    And "status" in response body is "OK"