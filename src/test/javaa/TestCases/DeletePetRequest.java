package TestCases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import Helper.RestActionController;
import Helper.RestSpecBuilder;
import Utility.GetYAMLData;
import applicationPOJO.Inventory;
import io.restassured.response.Response;
import propertyReader.propertyReader;

public class DeletePetRequest 
{
	Logger logger =Logger.getLogger(Add_Update_Pet_Request.class);
	RestSpecBuilder restSpecBuilder=new RestSpecBuilder();
	Response response;
	GetYAMLData getpersondata=new GetYAMLData();
	Inventory in=new Inventory();
	 
	@Test
	public void deletPetRequest() throws FileNotFoundException, IOException
	{
		
		RestActionController restActionController = new RestActionController(restSpecBuilder);
		int status=restActionController.getPetStatus(in.getSold())-1;
		System.out.println("pet id is"+Add_Update_Pet_Request.petId);
		try {
			response = restActionController.hitDeleteRequest(propertyReader.getInstance().getDeletePetResource(),Add_Update_Pet_Request.petId,"apiKey");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.then().statusCode(200);
		Assert.assertEquals(restActionController.getPetStatus(in.getSold()),status);
		
		
	}
	
}
