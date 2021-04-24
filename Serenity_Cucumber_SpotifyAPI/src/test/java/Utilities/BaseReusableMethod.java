package Utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BaseReusableMethod {
	
	static String responseStr;
	public static String baseUri = "https://accounts.spotify.com";
	
    public JsonPath rawTojson(Response res) {
		
		  responseStr =  res.asString();
		 
		 JsonPath js = new JsonPath(responseStr);
		 
		return js;
	}

}
