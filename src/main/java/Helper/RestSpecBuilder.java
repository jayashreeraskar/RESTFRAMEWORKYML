package Helper;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;

public class RestSpecBuilder 
{
	RequestSpecification requestSpecification;
	RequestSpecBuilder build=new RequestSpecBuilder();
	
	public static RestSpecBuilder getInstance()
	{
		RestSpecBuilder resp=new RestSpecBuilder();
		return resp;
		
	}
	public void SetBaseURI(String url)
	{
		build.setBaseUri(url);
	}
	
	public void setPort(int value)
	{
		build.setPort(value);
	}
	public void setContentTypeJson()
	{
		build.setContentType(ContentType.JSON);
	}
	public RequestSpecification getRequestSpecification()
	{
		return build.build();
		
	}
	
	public void setParam(String key,String value)
	{
		build.addQueryParam(key,value);
		
	}
	public void setHeaders(Map<String,String>headerMap)
	{
		build.addHeaders(headerMap);
	}
	public void setCookies(Map<String,?>cookies)
	{
		build.addCookies(cookies);
	}


}
