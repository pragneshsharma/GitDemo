Feature: Validating place API

@AddPlace
Scenario Outline: Checking if place is created successfully
  Given payload is provided with "<name>" "<language>" "<address>"
  When user sends request using "addPlaceAPI" with "POST" http method
  Then the request is successfull with status code 200
  And the response contains "status" is "OK"
  And the response contains "scope" is "APP"
  And verify place_Id created maps to "<name>" to "getPlaceAPI"
   
Examples:
	|name         |language |address |
	|Sharma house |English  |12 aven |
#	|Arora house  |Hindi    |parle close|

@DeletePlace
Scenario:
	Given deletePlaceAPI payload provided
	When user sends request using "deletePlaceAPI" with "POST" http method
	Then the request is successfull with status code 200
	And the response contains "status" is "OK"