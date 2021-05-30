package Utility;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.*;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class JacksonUtility 
{
	public static <T> T getJsonFileMappedtoPojoClass(String jsonFileName,Class<T>pojoClassName)throws IllegalAccessException,IOException, InstantiationException
	{
	T object=pojoClassName.newInstance();
	ObjectMapper mapper=new ObjectMapper();
	object=mapper.readValue(new File(jsonFileName),pojoClassName);
	return object;
	}

	public static <T> T getJsonStringMappedtoPojoClass(String responseString,Class<T>pojoClassName)throws IllegalAccessException,IOException, InstantiationException
	{
		T object=pojoClassName.newInstance();
		ObjectMapper mapper=new ObjectMapper();
		object=mapper.readValue(responseString,pojoClassName);
		return object;	
		
	}
	
	public static String buildParametrizedJsonToPostFromFile(File file,Map<String,Object>valuesToChange)throws IOException
	{
		//String JsonFileAsString=FileUtils.readFileToString(file,"UTF-8");
		String JsonFileAsString=Files.toString(file, Charsets.UTF_8);
		for(String key:valuesToChange.keySet())
		{
			JsonFileAsString=StringUtils.replace(JsonFileAsString,"$"+key,String.valueOf(valuesToChange.get(key)));
			
		}
		return JsonFileAsString;
		
	}

	public static String buildParametrizedJsonToPostFromString(String jsonBodyToPost,Map<String,String>valuesToChange)throws IOException
	{
		String JsonAsString=jsonBodyToPost;
		for(String key:valuesToChange.keySet())
		{
			JsonAsString=StringUtils.replace(JsonAsString,"$"+key,valuesToChange.get(key));
		}
		return JsonAsString;
	}

}
