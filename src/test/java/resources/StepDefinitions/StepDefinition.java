package resources.StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.DeletePlace;
import pojo.SetPlaceDetails;
import resources.APIResources;
import resources.Utils;

public class StepDefinition {

	RequestSpecification reqSpecification;
	public RequestSpecification req;
	ResponseSpecification resSpecification;

	SetPlaceDetails placeDetailsObj = new SetPlaceDetails();
	DeletePlace delPlaceObj = new DeletePlace();

	Utils util = new Utils();
	Response resp;
	static String place_id;
	static ArrayList<String> placeids = new ArrayList<String>();

	public void createRequest(String name, String language, String address) throws FileNotFoundException {

		req = util.requestSpecification();

		resSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqSpecification = given().log().all().spec(req).body(placeDetailsObj.getPlaceDetails(name, language, address));
	}

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws FileNotFoundException {

		createRequest(name, language, address);
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String httpmethod) {

		APIResources apiresource = APIResources.valueOf(resource);
		String strResource = apiresource.getResource();

		if (httpmethod.equalsIgnoreCase("Post"))
			resp = reqSpecification.when().post(strResource);
		else if (httpmethod.equalsIgnoreCase("Get"))
			resp = reqSpecification.when().get(strResource);
		else if (httpmethod.equalsIgnoreCase("Delete"))
			resp = reqSpecification.when().delete(strResource);
	}

	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {

		assertEquals(resp.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {

		assertEquals(util.getJsonPath(resp, key), value);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_creaetd_maps_to_using(String name, String resource) throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions

		place_id = util.getJsonPath(resp, "place_id");
		Utils.addPlaceIds(place_id);
		req = util.requestSpecification();

		reqSpecification = given().spec(req).queryParam("place_id", place_id);
		user_calls_with_post_http_request(resource, "GET");
		String name_in_resp = util.getJsonPath(resp, "name");
		assertEquals(name_in_resp, name);
	}

	@Given("Delete Place Payload with {string}")
	public void delete_place_payload_with(String placeid) throws FileNotFoundException {
		// Write code here that turns the phrase above into concrete actions

		placeid = place_id;
		System.out.println("Place to be deleted id is: " + placeid);

		req = util.requestSpecification();
		reqSpecification = given().spec(req).body(delPlaceObj.getDeletePlace(placeid));
	}
}