package Helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import Helper.RestSpecBuilder;
import propertyReader.propertyReader;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
 

public class RestActionController 
{
	Logger log=Logger.getLogger(RestActionController.class);
	private RestSpecBuilder restSpecBuilder;

	public RestActionController(RestSpecBuilder restSpecBuilder)
	{
		this.restSpecBuilder = restSpecBuilder;
	}

	public Response hitGetRequest(String resourceUrl) {
		Response response = given().spec(getRequestSpecification()).get(resourceUrl);
		log.info("response of Post request" + response.prettyPrint());
       return response;

	}

	public Response hitPostRequestwithBody(String resourceUrl, String requestBody) {
		log.info(resourceUrl);
		Response response = given().spec(getRequestSpecificationforLogin()).body(requestBody).post(resourceUrl);
		log.info("response of Post request" + response.prettyPrint());
		return response;
	}
	
	

	public Response hitPutRequest(String resourceUrl, String requestBody) {
		log.info(resourceUrl);
		Response response = given().spec(getRequestSpecification()).body(requestBody).put(resourceUrl);
		log.info("response of Put request" + response.prettyPrint());
		return response;
	}
	
	
	public Response hitDeleteRequest(String resourceUrl,String petID,String apiKey) {
		log.info(resourceUrl);
		resourceUrl=resourceUrl.replace("{petId}", petID);
		Response response = given().header("api_key",apiKey).spec(getRequestSpecification()).when().delete(resourceUrl);
		log.info("response of delete request" + response.prettyPrint());
		return response;
	}

	private RequestSpecification getRequestSpecificationforLogin() 
	{
		RequestSpecification resq=null;
		try{
			restSpecBuilder.SetBaseURI(propertyReader.getInstance().getBaseUri());
			restSpecBuilder.setContentTypeJson();
			resq=restSpecBuilder.getRequestSpecification();
			
		}
		catch(IOException e)
		{
			log.error("Exception occured"+e.getStackTrace());
			
		}
		return resq;
		}
	
	private RequestSpecification getRequestSpecification() 
	{
		HashMap<String,String>hm=new HashMap<String,String>();
		hm.put("Content-Type", "application/json");
		
		RequestSpecification resq=null;
		try{
			restSpecBuilder.SetBaseURI(propertyReader.getInstance().getBaseUri());
			restSpecBuilder.setHeaders(hm);
			resq=restSpecBuilder.getRequestSpecification();
			
		}
		catch(IOException e)
		{
			log.error("Exception occured"+e.getStackTrace());
			
		}
		return resq;
		}
		
	public int  getPetStatus(String status) throws FileNotFoundException, IOException
	{
   Response resp=hitGetRequest(propertyReader.getInstance().getStoreInventory());
		
		log.info("status is"+resp.jsonPath().get(status));
		return resp.jsonPath().get(status);

	}

}
