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
import weather.steps.model.ConsolidatedWeather;
import static org.junit.Assert.assertEquals;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class WeatherApiStepDef {

	@Steps
	SearchApi search;

	@Steps
	LocationApi locationApi;

	@Steps
	DateField dateValue;

	private Map<String, String> dataMap = new HashMap<>();

	@Given("the location {string} for search earth Id is available")
	public void location_for_search_operation(String location) {
		String woeId = search.getWhereOnEarth(location);
		dataMap.put(location, woeId);
	}

	@When("the weather forecast for a {string} on a {string} is retrieve")
	public void retrieve_weather_forecast(String location, String day) {

		String date = dateValue.getDateFormatValue(day);
		String woeId = dataMap.getOrDefault(location, "location not found");

		Serenity.recordReportData().withTitle("Input Test Data")
				.andContents("Date :: " + date + " Where on earth Id :: " + woeId);
		
		locationApi.getWeatherDetailBaseOnWoeIdAndDate(woeId, date);
	}

	@Then("the weather api should return successfull response")
	public void the_weather_api_should_return_successfull_response() {
		locationApi.verifyLocationDayApiStatusCode();
	}

	@And("the weather api response fields should same as weather schema fields")
	public void verify_weather_api_response_field() {
		assertEquals("weather fields mismatch", consolidatedWeatherSchemaFieldCount(),
				locationApi.getLocationDayResponseFields());
	}

	private int consolidatedWeatherSchemaFieldCount() {
		Field[] allfields = ConsolidatedWeather.class.getDeclaredFields();
		return allfields.length;

	}

}
