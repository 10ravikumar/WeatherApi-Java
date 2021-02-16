package weather.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import weather.ApiEndPoints;

public class LocationApi {
	
	   private Response locationByDate;
	
	  @Step("Get the weather details base on location only")
	    public void getWeatherDetailBaseOnWoeId(String woeidvalue) {
		  setLocationApiConfig();
		  Response response= SerenityRest.when().get(ApiEndPoints.LOCATION+woeidvalue);			  
		  response.then().statusCode(200);	                

	    }
	  
	  @Step("Get the weather detail base on location and date")
	    public void getWeatherDetailBaseOnWoeIdAndDate(String woeidvalue,String datevalue) {
		  setLocationApiConfig();		  
		  Response response= SerenityRest.when().get(ApiEndPoints.LOCATION+woeidvalue+"/"+datevalue);
		  response.then().statusCode(200);
		  locationByDate = response;
	    }
	  
	  @Step("Return the number of fields present in weather api response")
	    public int verifyLocationResponse() {
		 return locationByDate.then().assertThat().extract().body().jsonPath().getInt("[0].size()"); 	
		
		 
	  }
	  private void setLocationApiConfig() {
		  SerenityRest.given()
                      .contentType("application/json")
                      .header("Content-Type", "application/json");	  
	  }
}


