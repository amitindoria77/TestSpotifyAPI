node {
	stage ('SCM checkout'){
		git "https://github.com/amitindoria77/TestSpotifyAPI.git"
		mvnHome = tool 'M3'
		}
	stage ('Build'){
    	dir("Serenity_Cucumber_SpotifyAPI") {
		bat (/"${mvnHome}/bin/mvn" clean package/"
       }
	stage ('Results')
       	dir("Serenity_Cucumber_SpotifyAPI/target") {
	   junit '**/target/surefire-reports/Test-*.xml'
           archive  'target/*.jar'
       }
		}
}
