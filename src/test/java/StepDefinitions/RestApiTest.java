
package StepDefinitions;
import org.hamcrest.Matchers;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class RestApiTest {

    RequestSpecification request;
    ValidatableResponse response;
    Response response1;

    @Given("the user wants to create a booking")
    public void prepareCreateBookingRequest() {
        String jsonString = "{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        request = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(jsonString);
    }

    @When("the user sends a POST request to create a booking")
    public void sendCreateBookingRequest() {
        response = request.when().post("/booking").then().log().all();
    }

    @Then("the user should receive a response with status code 200 for create booking")
    public void validateResponseStatusCode() {
        response.assertThat().statusCode(200);
    }
    
    
    @Given("the user wants to get a booking")
    public void prepareGetBookingRequest() {
        request = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com");
    }

    @When("the user sends a GET request to get a booking with ID {int}")
    public void sendGetBookingRequest(int bookingId) {
        response = request.when().get("/booking/" + bookingId).then().log().all();
    }

    @Then("the user should receive a response with status code 200 for Get Request")
    public void validateResponseStatusCode1() {
        response.assertThat().statusCode(200);
    }
    
    String token = "abc123"; // 

    @Given("the user wants to update a booking")
    public void prepareUpdateBookingRequest() {
        String jsonString = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        request = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token) 
                .body(jsonString);
    }

    @When("the user sends a PUT request to update a booking with ID {int}")
    public void sendUpdateBookingRequest(int bookingId) {
        response = request.when().put("/booking/" + bookingId).then().log().all();
    }

    @Then("the user should receive a response with status code 403 for update booking")
    public void validateResponseStatusCode3() {
        response.assertThat().statusCode(403);
    }
    
    @Given("the user wants to delete a booking with valid authentication")
    public void prepareDeleteBookingRequestWithAuthentication() {
        request = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .cookie("token", "abc123") // Replace with your valid token
                .contentType(ContentType.JSON);
    }

    @When("the user sends a DELETE request to delete the booking")
    public void sendDeleteBookingRequest() {
        response1 = request.delete("/booking/1");
    }

    @Then("the user should receive a response with status code 403")
    public void validateResponseStatusCode4() {
        response1.then().statusCode(403);
    }
    
    } 

    
    

