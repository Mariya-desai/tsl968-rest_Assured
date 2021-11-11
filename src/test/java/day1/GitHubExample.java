package day1;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GitHubExample {
  @Test(enabled=false , description="Getting all respositories")
  public void getAllRepo() {
	  given()
	  	.auth()   //Specifying Authentication needed
	  	.oauth2("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")
	  .when()
	  	.get("https://api.github.com/user/repos")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(200)
	  	.time(Matchers.lessThan(2000L),TimeUnit.MILLISECONDS);
	  	
  }
  
  @Test(enabled=true , description="Adding respository")
  public void addRepository() {
	  JSONObject js=new JSONObject();
	  js.put("name", "tsl968-restAssured1");
	  js.put("description", "I am created by restAssured");
	  js.put("homepage", "https://github.com/Mariya-desai");
	  
	  given()
	  	.auth()   //Specifying Authentication needed
	  	.oauth2("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")
	  	 .header("Content-Type","application/json")
	  	 .body(js.toJSONString())
	  .when()
	      .post("https://api.github.com/user/repos")
	  .then()
	  	.log()
	  	.body()
	  	.statusCode(201)
	  	.time(Matchers.lessThan(5000L),TimeUnit.MILLISECONDS);
	  	
  }
}
