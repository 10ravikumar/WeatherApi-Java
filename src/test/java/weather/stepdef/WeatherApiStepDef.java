package weather.stepdef;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import weather.data.DateField;
import weather.steps.LocationApi;
import weather.steps.SearchApi;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

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
	

	@Given("fetch the where on earth id of {string} location")
	public void fetch_the_where_on_earth_id(String location) {
		String woeId= search.getWhereOnEarth(location);	
		dataMap.put(location, woeId);
	}

	@When("retrieve the weather info for a particular day {string} & location {string}")
	public void user_retrieve_the_weather_forecast_info(String day,String location) {
		
		String date=dateValue.getDateFormatValue(day);
		String woeId=dataMap.getOrDefault(location, "location not found");
		Serenity.recordReportData()
		        .withTitle("Input Test Data")
		        .andContents("Date :: "+date +" Where on earth Id :: "+woeId );
		
		locationApi.getWeatherDetailBaseOnWoeIdAndDate(woeId, date);
	}

	@Then("the weather api should return successfully response")
	public void the_weather_api_should_return_successfully_response() {
		 restAssuredThat(response -> response.statusCode(200));
	}
		
	
}
