package authorization;
//import java.util.Arrays;
//import java.util.Base64;
//import io.restassured.RestAssured.*;
//import io.restassured.http.ContentType;
//import io.restassured.path.xml.XmlPath;
//import io.restassured.specification.ProxySpecification;
//import static io.restassured.RestAssured.baseURI;
//import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import java.util.ArrayList;
import net.serenitybdd.rest.SerenityRest;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import java.util.List;
import Utilities.BaseReusableMethod;



//import static io.restassured.RestAssured.*;

//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//import org.testng.asserts.Assertion;


public class AccessToken extends BaseReusableMethod{

	
//	 Response response;
	private List<String> authCode;

  
@Step("Request the Authorization code to get the Access Token")
public  List<String>  getAuthorization() {
	
	
	String response =   SerenityRest.given().baseUri(baseUri).
				        queryParam("response_type","code")
				       .queryParam("state","1600af1cc0a2490f8f31afb057f37c8f")
				       .queryParam("client_id","5e2653b1ac8d4a39a85d2f78bf2ea568")
				       .queryParam("scope","playlist-read-private%20playlist-modify-private")
				       .queryParam("redirect_uri","https://oauth.pstmn.io/v1/browser-callback")
			    	   .header("Cookie","__Secure-TPASESSION=AQCJFtB0+gRFblGT/gJ7BPTZsydcS3WWJRIYJmu3TeojRfBJ/lOTvDbuRwwdFpCKgWgFJXK3skXZYbC1rOofe4AokvrhVwBvwmw=; ; __bon=MHwwfDUwOTMxMzk4MnwyMTM5MTE4NzI0NHwxfDF8MXwx; remember=amitindoria4%40gmail.com; ; sp_ac=AQDka3yusTYZEnUOcasb3KifoRt09L_SZ6YAyhibFrjPbLoroy8yPT5pFTBs6o65_EFBShs6qW2L21PMDazmhc4qPheBDMcDFqY6HlWEprPFjBGMdw-ryWY78RnBxA-kX3UXtbQ0foZY6aH6358QEBUs_KbMwHmZsR1Rr50LM6PKPR2Z_igZX3B83Uu1ZYvaTpYr6ryj08CuPGA1jJTv_QfIcdkf3McZMmBUPQ;; sp_dc=AQCPjuxMCFD4IPYFdauubsEw3YP9UncYvwwhOMFdaOiXZ6RWY3tQXNqHZ6ST1GfKS857wEjsa5ZHtUZ5cqEMNPOrfeb56phfT5TmHSRk4lZw;; csrf_token=AQC3s-PLYno2a8d6rb4pjh7Flu5y0cIGFYXow-3E_yZbDLiSPrNjvwpEJqybpIfauFeJrPsRtStOgPzw;; sp_key=ac9cf16c-4c25-49ff-b4ab-7fb9064de2b0; Cookie_1=value; __Host-device_id=AQBz6bG_3djyXHBBIUEVarkJruwasfTI3E4dNt6ujW7tuFv9Q0biIao0_3Ld1fXsUsVoUofAqfmp2Sw1qzD6VvMCdBH_Gxe6dj0; __Secure-TPASESSION=AQBdNgazrKm5DUVC526TQFMZVLraGMD3uv0VooHCP6y44Y5pglmJMtyYDlb08GZI4lxwIn2Ns4hLMqBx9EtgBXGjDfrG7YQS71U=; csrf_token=AQASUVNfw_wEu_XjuIByWURRiGTWroTIF6EgkhUlXHqYYV8KhN6MtH2cO4-2VQBZ-BnaiYmT3a9hSycb; inapptestgroup=; sp_ac=AQDjK_ZSgec0sA0nU5xKc36DxVeh9GPlfVHwmazV4BM9Z7ifsra0U2FvKBK990PN20BdGzzO3_3pv6xW_TWFuqmDsCLJPhTmWjAnwS7TeG_oS57xGGmTzoeNcoCdhnpZ0EaDdMXCGd2O_tjxomblIfYtXZxj5xAyGAmy9zup5nhp6R2pYxgS4LuhHbsnJcfCtr3eYBJllZUMzxpB46jB0R65wNNvpDVBomBOCw")
			    	   .redirects().follow(false).get("/authorize")
			    	   .then()
			    	   .statusCode(302)
//				    	   .statusCode(302).log().all()
				       .extract()
				       .header("location").toString();
					    
	            authCode =  getAuthCode(response);
	             
	            return authCode;

}
    
   
private static List<String> getAuthCode(String response) {
	
	String[] codeL;
	String getCode,getState;
	List<String> authList = new ArrayList<String>();

    if(response.contains("code")) {
	
			System.out.println("Print String : "+response);
			codeL=response.split("code=",2);
			
		//	for (String a :codeL) {
		//	System.out.println("Print code : "+a);
	
			getCode=codeL[1].split("&state=")[0];
			getState=codeL[1].split("&state=")[1];
		
		    authList.add(getCode);
		    authList.add(getState);
			
			System.out.println();
			System.out.println("Here is the Authorization Code : "+getCode+" \n"+" and the State : "+getState);

           }
    return authList;
}

  @Step("Now we have the Authorization code then get the Access Token ")
    public  String  getAccessToken() {
	   
    	
    	Response res =  SerenityRest.given().baseUri(baseUri)
				       .header("Authorization","Basic NWUyNjUzYjFhYzhkNGEzOWE4NWQyZjc4YmYyZWE1Njg6MTYwMGFmMWNjMGEyNDkwZjhmMzFhZmIwNTdmMzdjOGY=")
				       .header("ConetentType","application/x-www-form-urlencoded")
				       .formParam("grant_type","authorization_code")
				       .queryParam("client_id", "5e2653b1ac8d4a39a85d2f78bf2ea568")
				     // Instead of calling authroization function 
				       .formParam("code",getAuthorization().get(0))
				       .formParam("state",getAuthorization().get(1))
				       .formParam("redirect_uri","https://oauth.pstmn.io/v1/browser-callback")
				       .post("/api/token").then()
				       .statusCode(200)
				       .extract()
				       .response();

    	System.out.println("response "+ res.jsonPath().prettify());
    	
        JsonPath jsonP = rawTojson(res);
    
	    String AccessToken = jsonP.getString("access_token");
//	    String AccessTokenType = jsonP.getString("token_type");
    
//    String Token = AccessTokenType.concat(" "+AccessToken); 
//    System.out.println("AccessTokenType along with AccessToken are : "+Token);
    
    return AccessToken;
    
  }
    
    
}


