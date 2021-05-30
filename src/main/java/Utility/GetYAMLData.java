package Utility;

import java.io.File;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import applicationPOJO.Pet;


public class GetYAMLData 
{
ObjectMapper mapper=new ObjectMapper(new YAMLFactory());
	
	public  Map<String,Object> getPetData(File file)
	{
		Map<String,Object> reqdata=null;

		try
		{
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
			reqdata=mapper.readValue(file,Map.class);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return reqdata;
	}


}
