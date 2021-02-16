package weather.steps;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import weather.ApiEndPoints;

public class LocationApi {
	
	  @Step("Get the weather details base on location only")
	    public void getWeatherDetailBaseOnWoeId(String woeidvalue) {
		  setLocationApiConfig();
		  SerenityRest.when().get(ApiEndPoints.LOCATION+woeidvalue);	                

	    }
	  
	  @Step("Get the weather detail base on location and date")
	    public void getWeatherDetailBaseOnWoeIdAndDate(String woeidvalue,String datevalue) {
		  setLocationApiConfig();		  
		  SerenityRest.when().get(ApiEndPoints.LOCATION+woeidvalue+"/"+datevalue);	                

	    }
	  
	  private void setLocationApiConfig() {
		  SerenityRest.given()
                      .contentType("application/json")
                      .header("Content-Type", "application/json");	  
	  }
}


