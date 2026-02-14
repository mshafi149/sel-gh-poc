package stepdefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ApiSteps {

    private Response response;
    private RequestSpecification request;
    private Map<String, String> requestData;

    @Given("the API base URL is {string}")
    public void the_api_base_url_is(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        RestAssured.useRelaxedHTTPSValidation();
        request = given().log().all();
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = request
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @When("I send a POST request to {string} with the user data")
    public void i_send_a_post_request_with_data(String endpoint) {
        response = request
                .contentType("application/json")
                .body(requestData)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @When("I send a PUT request to {string} with the update data")
    public void i_send_a_put_request_with_data(String endpoint) {
        response = request
                .contentType("application/json")
                .body(requestData)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @When("I send a PATCH request to {string} with the patch data")
    public void i_send_a_patch_request_with_data(String endpoint) {
        response = request
                .contentType("application/json")
                .body(requestData)
                .when()
                .patch(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        response = request
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @Given("I have the following user data:")
    public void i_have_the_following_user_data(DataTable dataTable) {
        requestData = new HashMap<>();
        dataTable.asMap().forEach((key, value) -> requestData.put(key, value));
    }

    @Given("I have the following update data:")
    public void i_have_the_following_update_data(DataTable dataTable) {
        requestData = new HashMap<>();
        dataTable.asMap().forEach((key, value) -> requestData.put(key, value));
    }

    @Given("I have the following patch data:")
    public void i_have_the_following_patch_data(DataTable dataTable) {
        requestData = new HashMap<>();
        dataTable.asMap().forEach((key, value) -> requestData.put(key, value));
    }

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode.intValue(),
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());
    }

    @Then("the response should contain field {string} with value {string}")
    public void the_response_should_contain_field_with_value(String fieldName, String expectedValue) {
        String actualValue = response.jsonPath().getString(fieldName);
        Assert.assertEquals(actualValue, expectedValue,
                "Expected field '" + fieldName + "' to be '" + expectedValue + "' but got '" + actualValue + "'");
    }

    @Then("the response should contain field {string}")
    public void the_response_should_contain_field(String fieldName) {
        Object fieldValue = response.jsonPath().get(fieldName);
        Assert.assertNotNull(fieldValue, "Field '" + fieldName + "' should not be null");
    }

    @Then("the response should be a JSON array")
    public void the_response_should_be_a_json_array() {
        Assert.assertTrue(response.jsonPath().getList("$").size() >= 0,
                "Response should be a JSON array");
    }

    @Then("the response array should have {int} items")
    public void the_response_array_should_have_items(Integer expectedCount) {
        int actualCount = response.jsonPath().getList("$").size();
        Assert.assertEquals(actualCount, expectedCount.intValue(),
                "Expected " + expectedCount + " items but got " + actualCount);
    }

    @Then("the response array should not be empty")
    public void the_response_array_should_not_be_empty() {
        int count = response.jsonPath().getList("$").size();
        Assert.assertTrue(count > 0, "Response array should not be empty");
    }

    @Then("the response should have nested field {string} with value {string}")
    public void the_response_should_have_nested_field_with_value(String fieldPath, String expectedValue) {
        String actualValue = response.jsonPath().getString(fieldPath);
        Assert.assertEquals(actualValue, expectedValue,
                "Expected nested field '" + fieldPath + "' to be '" + expectedValue + "' but got '" + actualValue + "'");
    }
}
