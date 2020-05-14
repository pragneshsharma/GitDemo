package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.BodyKey;
import resources.APIResource;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;


public class StepDefination extends Utils {
	Utils ut = new Utils();
	RequestSpecification res;
	Response ress;
	static String place_id;
	
	@Given("payload is provided with {string} {string} {string}")
	public void payload_is_provided_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	    res = given().spec(getRequestSpecification())
	    		//queryParam("key", "qaclick123").header("Content-Type", "application/json")
	    .body(ut.payLoadData(name, language, address));
	    
	}

	@When("user sends request using {string} with {string} http method")
	public void user_sends_request_using_with_http_method(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		APIResource resourceAPI = APIResource.valueOf(resource);
		if(method.equalsIgnoreCase("POST"))
		ress = res.when().post(resourceAPI.getAPIResource());
	    
		else if(method.equalsIgnoreCase("GET"))
			ress = res.when().get(resourceAPI.getAPIResource());
	    
	}

	@Then("the request is successfull with status code {int}")
	public void the_request_is_successfull_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		//.then().log().all().extract().response();
		assertEquals(ress.getStatusCode(),200);
	}

	@Then("the response contains {string} is {string}")
	public void the_response_contains_is(String keyValue, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    
	    
	    assertEquals(getJsonPath(ress,keyValue),string2);
	}
	
	@Then("verify place_Id created maps to {string} to {string}")
	public void verify_place_Id_created_maps_to_to(String name, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	place_id = getJsonPath(ress,"place_id");
		res = given().spec(getRequestSpecification()).queryParam("place_id",place_id);
		user_sends_request_using_with_http_method(resource, "GET");
		String responseName = getJsonPath(ress,"name");
	    	assertEquals(responseName,name);
	    }
	
	@Given("deletePlaceAPI payload provided")
	public void deleteplaceapi_payload_provided() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	   
		res = given().spec(getRequestSpecification())	    		
	    .body(ut.deletePlacePayload(place_id));
	}

}
