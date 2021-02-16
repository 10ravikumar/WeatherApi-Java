package weather.stepdef;

import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import weather.data.DateField;
import weather.steps.LocationApi;
import weather.steps.SearchApi;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class WeatherApiStepDef {
	
	@Steps
	SearchApi search;
	
	@Steps
	LocationApi locationApi;
	
	@Steps
	DateField dateValue;
	
	private Map<String,String> dataMap= new HashMap<>();
	

	@Given("the location {string} for search earth Id is available")
	public void location_for_search_operation(String location) {
		String woeId= search.getWhereOnEarth(location);	
		dataMap.put(location, woeId);
	}
	
	@When("the weather forecast for a {string} on a {string} is retrieve")
	public void retrieve_weather_forecast(String location,String day) {
		
		String date=dateValue.getDateFormatValue(day);
		String woeId=dataMap.getOrDefault(location, "location not found");
		Serenity.recordReportData()
		        .withTitle("Input Test Data")
		        .andContents("Date :: "+date +" Where on earth Id :: "+woeId );
		
		locationApi.getWeatherDetailBaseOnWoeIdAndDate(woeId, date);
	}

	@Then("the weather api should return successfull response")
	public void the_weather_api_should_return_successfull_response() {
		 restAssuredThat(response -> response.statusCode(200));
	}
	
	@And("the weather api response should contains {int} fields")
	public void the_weather_api_response_field_count(int expectedSize) {
		assertEquals("weather fields mismatch",expectedSize, locationApi.verifyLocationResponse());
	}
		
	
}
