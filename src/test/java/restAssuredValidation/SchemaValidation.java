package restAssuredValidation;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SchemaValidation {
	
	public void schemaValidator() {
		
		try {
			
			File schema = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\PayLoad.json");
			
			RestAssured.baseURI = "https://api.picsart.com/localizations/en/messages?project=reusable_components,photo_editor";
			RequestSpecification reqSpec = RestAssured.given();
			Response response = reqSpec.get();
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody().asString());
			
			response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
	}

}
