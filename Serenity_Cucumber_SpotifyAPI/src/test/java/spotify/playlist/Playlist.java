package spotify.playlist;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
//import java.util.ArrayList;
import java.io.IOException;
import Utilities.BaseReusableMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;


public class Playlist  extends BaseReusableMethod {

Response response;

@Step("Create the List")
public int createPlayList(String aToken) throws IOException {
	
	String accessToken=aToken;
	String UserId="wdqj41v0jgau6umpdv3h20iug";
	baseURI  ="https://api.spotify.com";
	
response =  SerenityRest.given().baseUri(baseURI).
			header("Authorization", "Bearer "+accessToken).
			header("Content-Type","application/json").
			pathParam("user_id", UserId).	
			body("{\r\n" + 
					"   \"name\":\"My New Playlist7\",\r\n" + 
					"   \"description\":\"Second playlist description\",\r\n" + 
					"   \"public\":false\r\n" + 
					"}").log().all().
	        when().
			        post("v1/users/{user_id}/playlists")
	        
	        .then().
			        statusCode(201).extract().response();
	    
	int responseCode= response.getStatusCode();
	
	System.out.println("Printing the res Codde = "+ responseCode);
	
	return responseCode;
	
}

@Step
public String getPlaylistId() {
	
	    JsonPath js = rawTojson(response);
	 
	//  System.out.println("Response"+v.get("uri"));
	  
	   String getPlayList = js.get("uri").toString();
	  
	   String playlistId= getPlayList.split("playlist:", 2)[1];
	 
	   System.out.println("PlayListLid : "+playlistId);
	 
		
	   return playlistId;
	
}
	
}

