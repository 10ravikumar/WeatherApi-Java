package weather.steps;

import java.util.List;
import java.util.Optional;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import weather.ApiEndPoints;

public class SearchApi {
	
	@Step("Get the where on earth id value")
	public String getWhereOnEarth(String input) {	
		return input.contains(",") ? getWhereOnEarthIDByCoordinates(input) : getWhereOnEarthIDByLocation(input);
			
	}

    @Step("Get the woeid value in search api by location")
    public String getWhereOnEarthIDByLocation(String location) {
    	
    	List<Integer> woeidList = SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .queryParam("query", location)
                .when()
                .get(ApiEndPoints.LOCATION_SEARCH).body().jsonPath().get("woeid");
    	
    	Optional<String> woeid = woeidList.stream().map(String::valueOf).findFirst(); 
    	return woeid.isPresent() ? woeid.get() : "Where on earth id not found";
    }
    
    @Step("Get the woeid value in search api by coordinates")
    public String getWhereOnEarthIDByCoordinates(String coordinates) {
    	List<Integer> woeidList = SerenityRest.given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .queryParam("lattlong", coordinates)
                .when()
                .get(ApiEndPoints.LOCATION_SEARCH).body().jsonPath().get("woeid");
		
    	Optional<String> woeid = woeidList.stream().map(String::valueOf).findFirst(); 
    	return woeid.isPresent() ? woeid.get() : "Where on earth id not found";
    }
    
}