package TestCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import Helper.RestActionController;
import Helper.RestSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import propertyReader.propertyReader;
import Utility.GetYAMLData;
import Utility.JacksonUtility;
import applicationPOJO.Inventory;
import applicationPOJO.Pet;

public class Add_Update_Pet_Request 
{
     Logger logger =Logger.getLogger(Add_Update_Pet_Request.class);
	RestSpecBuilder restSpecBuilder=new RestSpecBuilder();
	Response response;
	String jsonToPost;
	GetYAMLData getpersondata=new GetYAMLData();
	Inventory in=new Inventory();
	public static String petId;
	 
	
	@BeforeClass
	public void createData()
	{
		
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\pet.yaml";

		Map<String,Object> credentials = new HashMap<String,Object>();
		credentials=getpersondata.getPetData(new File(path));
		try{
			jsonToPost = JacksonUtility.buildParametrizedJsonToPostFromFile(
					new File (propertyReader.getInstance().getAddPetJson()), credentials);
			System.out.println(jsonToPost);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("request payload" + jsonToPost);
	}
	
	@Test
	public void addPetRequest() throws FileNotFoundException, IOException
	{
		
		RestActionController restActionController = new RestActionController(restSpecBuilder);
		System.out.println(in.getAvaialble());
		int status=restActionController.getPetStatus(in.getAvaialble())+1;
		try {
			response = restActionController.hitPostRequestwithBody(propertyReader.getInstance().getAddPetResource(),
					jsonToPost);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.then().statusCode(200);
		petId=response.jsonPath().getString("id");
		System.out.println("petid is "+petId);
		Assert.assertEquals(restActionController.getPetStatus(in.getAvaialble()),status);
		
		
	}
	
	@Test
	public void updatePetRequest() throws FileNotFoundException, IOException
	{
		
		RestActionController restActionController = new RestActionController(restSpecBuilder);
		int status=restActionController.getPetStatus(in.getSold())+1;
		try {
			response = restActionController.hitPutRequest(propertyReader.getInstance().getAddPetResource(),
					jsonToPost);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.then().statusCode(200);
		petId=response.jsonPath().get("Id");
		Assert.assertEquals(restActionController.getPetStatus(in.getSold()),status);
		}

	@Test
	public void deletPetRequest() throws FileNotFoundException, IOException
	{
		
		RestActionController restActionController = new RestActionController(restSpecBuilder);
		int status=restActionController.getPetStatus(in.getSold())-1;
		try {
			response = restActionController.hitDeleteRequest(propertyReader.getInstance().getDeletePetResource(),petId,"apiKey");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.then().statusCode(200);
		Assert.assertEquals(restActionController.getPetStatus(in.getSold()),status);
		
		
	}
	
}
