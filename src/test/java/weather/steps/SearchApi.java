package weather.steps;

import java.util.List;
import java.util.Optional;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import weather.ApiEndPoints;

public class SearchApi {
	
	private final String QUERY_PARAM = "query";
	private final String LATTITUDE_LONGITUDE_PARAM = "lattlong";
	
	@Step("Get the where on earth id value")
	public String getWhereOnEarth(String input) {	
		return input.contains(",") ? getWhereOnEarthIDByCoordinates(input) : getWhereOnEarthIDByLocation(input);
			
	}

    @Step("Get the woeid value in search api by location")
    public String getWhereOnEarthIDByLocation(String location) {	
    	Response locationResponse = getLocationApiResponse(QUERY_PARAM,location);
    	locationResponse.then().statusCode(200);
    	
    	List<Integer> woeidList =locationResponse.body().jsonPath().get("woeid");
    	
    	Optional<String> woeid = woeidList.stream().map(String::valueOf).findFirst(); 
    	return woeid.isPresent() ? woeid.get() : "Where on earth id not found";
    }
    
    @Step("Get the woeid value in search api by coordinates")
    public String getWhereOnEarthIDByCoordinates(String coordinates) {
    	Response locationResponse = getLocationApiResponse(LATTITUDE_LONGITUDE_PARAM,coordinates);
    	locationResponse.then().statusCode(200);
        
    	List<Integer> woeidList =locationResponse.body().jsonPath().get("woeid");
		
    	Optional<String> woeid = woeidList.stream().map(String::valueOf).findFirst(); 
    	return woeid.isPresent() ? woeid.get() : "Where on earth id not found";
    }
    
    
    private Response getLocationApiResponse(String query,String value) {
    	return SerenityRest.given()
        .contentType("application/json")
        .header("Content-Type", "application/json")
        .queryParam(query, value)
        .when()
        .get(ApiEndPoints.LOCATION_SEARCH);
    	
    }
    

}