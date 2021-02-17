package weather.steps;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import weather.ApiEndPoints;

public class LocationApi {

	private Map<String, Response> responseMap = new HashMap<>();

	@Step("Get the weather details base on location only")
	public void getWeatherDetailBaseOnWoeId(String woeidvalue) {
		setLocationApiConfig();
		Response response = SerenityRest.when().get(ApiEndPoints.LOCATION + woeidvalue);
		response.then().statusCode(200);

	}

	@Step("Get the weather detail base on location and day")
	public void getWeatherDetailBaseOnWoeIdAndDate(String woeidvalue, String datevalue) {
		setLocationApiConfig();
		Response response = SerenityRest.when().get(ApiEndPoints.LOCATION + woeidvalue + "/" + datevalue);
		responseMap.put("locationByDay", response);
	}

	@Step("Verify location day api status code response")
	public void verifyLocationDayApiStatusCode() {
		responseMap.get("locationByDay").then().assertThat().statusCode(200);

	}

	@Step("Get the number of fields present in weather api response")
	public int getLocationDayResponseFields() {
		return responseMap.get("locationByDay").then().assertThat().extract().body().jsonPath().getInt("[0].size()");

	}

	private void setLocationApiConfig() {
		SerenityRest.given().contentType("application/json").header("Content-Type", "application/json");
	}
}
