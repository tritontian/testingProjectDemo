package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import java.util.List;
import java.util.Map;

/**
 * @Author Tavin
 * @Date 08/22/2021
 * */
public class Steps {

    private static final String BASE_URL = "https://restcountries.eu";

    private static Response response;
    private static String jsonString;

    /**
     * This will test the accessibility of the master URL
     * True if 200 response is returned
     * False Otherwise
     * */
    @Given("The Base URL is Accessible")
    public void the_base_url_is_accessible() {
        response = RestAssured.get(BASE_URL);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200,statusCode);
    }

    /**
     * This will test if total of 250 countries info will be displayed
     * True if the size of total countries matches 250
     * False Otherwise
     * */
    @Then("All Countries Info are Returned")
    public void all_countries_info_are_returned() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/rest/v2");
        jsonString = response.asString();
        List<Map<String,String>> countries = JsonPath.from(jsonString).get();
        Assert.assertEquals(250,  countries.size());
    }
    /**
     * This will test if there is at least one country can be displayed
     * True if at least one country can be displayed
     * False Otherwise
     * */
    @Given("A List of Countries are Available")
    public void a_list_of_countries_are_available() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/rest/v2");
        jsonString = response.asString();
        List<Map<String,String>> countries = JsonPath.from(jsonString).get();
        Assert.assertTrue(countries.size() > 0);
    }
    /**
     * This will test if we can get the country info by searching for first two letters of country name
     * True if the matched country displayed
     * False Otherwise
     * */
    @When("Input Two Letters Country Code")
    public void input_two_letters_country_code() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/rest/v2/alpha/US");
        jsonString = response.asString();
        JsonPath jsonPath = new JsonPath(jsonString);
        String name = jsonPath.getString("name");
        Assert.assertEquals("United States of America",name);

    }
    /**
     * This will check the response code of the current webpage
     * True if 200 response code returned
     * False Otherwise
     * */
    @Then("A Country Info is Returned")
    public void a_country_info_is_returned() {
        Assert.assertEquals(200,response.getStatusCode());
    }

    /**
     * This will test if we can get the country info by searching for three letters of country name
     * True if the matched country displayed
     * False Otherwise
     * */
    @When("Input Three Letters Country Code")
    public void input_three_letters_country_code() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/rest/v2/alpha/USA");
        jsonString = response.asString();
        JsonPath jsonPath = new JsonPath(jsonString);
        String name = jsonPath.getString("name");
        Assert.assertEquals("United States of America",name);
    }

    /**
     * This will check total of 24 key-value pair properties should be return for any country
     * True if the country has 24 key-value pair properties
     * False Otherwise
     * */
    @Then("Total of {int} Key Value Pairs Should Be Returned")
    public void total_of_key_value_pair_properties_should_be_returned(Integer int1) {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/rest/v2/alpha/JPN");
        jsonString = response.asString();
        Map<String,String> countryMap = JsonPath.from(jsonString).get();
        Assert.assertEquals(24,countryMap.size());
    }

    /**
     * This will check all key should be non-null and non-empty for any country
     * True if the country's key has a value
     * False Otherwise
     * */
    @Then("Properties Returned Should All Have A Key")
    public void properties_returned_should_all_have_a_value() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        response = request.get("/rest/v2/alpha/USA");
        jsonString = response.asString();
        Map<String,String> countryMap = JsonPath.from(jsonString).get();
        for(String countryKey : countryMap.keySet()){
            Assert.assertNotNull(countryKey);
            Assert.assertFalse(countryKey.equals(""));
        }
    }

}
