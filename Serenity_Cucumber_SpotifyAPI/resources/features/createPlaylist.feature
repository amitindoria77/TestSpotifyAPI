Feature: Create Playlist
  As an user
  I should be able to create the playlist

  Scenario: Get user Access Token and create the Song PlayList
  
    Given Request the Access Token for user authroization 
    When  Hit the Spotify create playlist API endpoint to create playlist
    Then  Playlist should be created
    