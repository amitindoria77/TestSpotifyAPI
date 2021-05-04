node {
	stage ('SCM checkout'){
		git "https://github.com/amitindoria77/TestSpotifyAPI.git"
		}
	stage ('Build'){
    	dir("Serenity_Cucumber_SpotifyAPI") {
	   sh "mvn clean install"
       }
       	dir("Serenity_Cucumber_SpotifyAPI/target") {
	   sh "java -jar Serenity_Cucumber_SpotifyAPI-1.0-SNAPSHOT.jar"
       }
		}
}
