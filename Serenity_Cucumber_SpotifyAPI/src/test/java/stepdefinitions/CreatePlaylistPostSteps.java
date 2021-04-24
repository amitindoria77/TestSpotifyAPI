package stepdefinitions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

import org.junit.Assert;
import org.junit.runner.RunWith;

import authorization.AccessToken;
import net.thucydides.core.annotations.Steps;
import spotify.playlist.Playlist;

@RunWith(Cucumber.class)
public class CreatePlaylistPostSteps {

@Steps
AccessToken token;

@Steps
Playlist thePlaylist;

String accessToken;

@Given("^Request the Access Token for user authroization$")
public void request_the_access_token_for_user_authroization() throws Throwable {
	accessToken=token.getAccessToken();
	
}

@When("^Hit the Spotify create playlist API endpoint to create playlist$")
public void hit_the_spotify_create_playlist_api_endpoint_to_create_playlist() throws Throwable {	
	int resCode = thePlaylist.createPlayList(accessToken);
	Assert.assertEquals(201,resCode);
	
  }

@Then("^Playlist should be created$")
public void playlist_should_be_created() throws Throwable {
   String playlistId = thePlaylist.getPlaylistId();
	System.out.println("This is the playlist Id"+playlistId);
	  Assert.assertNotNull(playlistId);;
  }


}