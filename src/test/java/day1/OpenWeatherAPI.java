package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpenWeatherAPI {
	@Test
	public void getWheatherInfo() {
		/*
		 * Given > pre-condition like content type, Authentication When > User perform
		 * something Then > expected outcome from system
		 */

		RestAssured.given().when()
				.get("http://api.openweathermap.org/data/2.5/weather?q=mumbai&appid=928dd5c4dda79b4f71e51dab779a8c98")
				.then()
				.log()
				.body()
				.statusCode(200);
	}

	@Test(enabled=false, description="Getting weather API information Generally")
  public void getWheatherInfo2() { 
   Response res=RestAssured.given()
             .when()
             .get("http://api.openweathermap.org/data/2.5/weather?q=mumbai&appid=928dd5c4dda79b4f71e51dab779a8c98");
             
             System.out.println(res.prettyPrint());
   			 System.out.println(res.getTime());
   			 System.out.println(res.getStatusCode());
   			 System.out.println(res.getContentType());
}
	@Test(enabled=true, description="Getting weather API information Generally")
	  public void getWheatherInfo3() { 
		Map<String, String> param=new HashMap<String, String>();
		param.put("q", "Mumbai");
		param.put("appid", "928dd5c4dda79b4f71e51dab779a8c98");

	   RestAssured.given()
	   				//.queryParam("q", "Mumbai")
	   				//.queryParam("appid", "928dd5c4dda79b4f71e51dab779a8c98")
	   				    .params(param)
	   				.when()
	                .get("http://api.openweathermap.org/data/2.5/weather")
	   				.then()
	   				    .log()
	   				    .body()
	   				    .statusCode(200);
	   
}
}