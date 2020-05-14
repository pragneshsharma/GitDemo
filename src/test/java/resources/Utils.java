package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.BodyKey;
import pojo.LocationData;

public class Utils {
	public static RequestSpecification req; // important to make it public static to get full log.
	public BodyKey payLoadData(String name, String language, String address) {
		
		BodyKey bk = new BodyKey();
		bk.setAccuracy(50);
		bk.setName(name);
		bk.setPhone_number("123456789");
		bk.setAddress(address);
		bk.setWebsite("http://google.com");
		bk.setLanguage(language);
		
		LocationData ld = new LocationData();
		ld.setLat(-38.383494);
		ld.setLng(33.427362);
		bk.setLocation(ld);
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		bk.setTypes(myList);
		return bk;
		
	}
	
	public RequestSpecification getRequestSpecification() throws IOException {
			
		if(req==null) 
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		req = new RequestSpecBuilder().setBaseUri(getGlobalProperties("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))				
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}
	
	public String getGlobalProperties(String key) throws IOException {
		Properties p = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\PragneshS\\eclipse-workspace\\APIFramework1\\src\\test\\java\\resources\\global.properties");
		p.load(fis);
		return p.getProperty(key);
		
	}
	
	public String getJsonPath(Response ress,String key)
	{
		String resp = ress.asString();
	    JsonPath js = new JsonPath(resp);
	    return js.get(key).toString();
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\r\n    \"place_id\":\""+placeId+"\"   \t \t\r\n}";
	}
 
}
