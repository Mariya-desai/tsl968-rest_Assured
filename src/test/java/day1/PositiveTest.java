package day1;

import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class PositiveTest {
	String id;
	
	@Test(enabled = false, description = "For Getting all contact List")
	public void getAllContactList() {
		given()
		.when()
			.get("http://3.13.86.142:3000/contacts")
		.then()
			.log()
			.body()
			.statusCode(200);
	}	

	@Test(enabled = true , description = "Adding Contact")
	public void addContact() {
		JSONObject loc = new JSONObject();
		loc.put("city", "Pune");
		loc.put("country", "India");

		JSONObject emp = new JSONObject();
		emp.put("jobTitle", "Automation Tester");
		emp.put("company", "LTI");

		JSONObject ob = new JSONObject();
		ob.put("firstName", "Mayank");
		ob.put("lastName", "Sharma");
		ob.put("email", "asmith@thinkingtester.com");
		ob.put("location", loc);
		ob.put("employer", emp);

		id= given()
		 .header("Content-Type", "application/json")
		 .body(ob.toJSONString()) // To convert object in json type																					// json type
				.when()
				.post("http://3.13.86.142:3000/contacts")
				.then()
				.log()
					.body()
						.statusCode(200)
						.extract()
						.jsonPath()
						.get("_id");
		System.out.println("ID is " + id);
	}

	@Test(enabled = true, dependsOnMethods="addContact" ,description = "Getting Specific Contact")
	public void getSpecificContact() {
		given()
		.when()
			.get("http://3.13.86.142:3000/contacts/"+id)
		.then()
			.log()
			.body()
			.statusCode(200);
	}

	@Test(enabled = true ,dependsOnMethods="getSpecificContact", description = "Adding Contact")
	public void updateContact() {
		JSONObject loc = new JSONObject();
		loc.put("city", "Pune");
		loc.put("country", "India");

		JSONObject emp = new JSONObject();
		emp.put("jobTitle", "Automation Tester");
		emp.put("company", "LTI");

		JSONObject ob = new JSONObject();
		ob.put("firstName", "Mayank");
		ob.put("lastName", "Sharma");
		ob.put("email", "asmith@thinkingtester.com");
		ob.put("location", loc);
		ob.put("employer", emp);

				given()
					.header("Content-Type", "application/json")
					.body(ob.toJSONString()) // To convert object in json type
				.when()
					.put("http://3.13.86.142:3000/contacts/"+id)
				.then()
					.log()
					.body()
					.statusCode(204);
	}
	
	@Test(enabled = true, dependsOnMethods="updateContact" ,description = "Deleting Specific Contact")
	public void deleteSpecificContact() {
		given()
		.when()
			.delete("http://3.13.86.142:3000/contacts/"+id)
		.then()
			.log()
			.body()
			.statusCode(204);
	}
}
