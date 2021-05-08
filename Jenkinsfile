node {

    git url: 'https://github.com/amitindoria77/TestSpotifyAPI.git', branch: 'master'

 // set up Java:
   
    //env.JAVA_HOME="C:/Program Files/Java/jdk1.8.0_291"
    //env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
 // set up Maven:
   
    env.M2_HOME  = "opt/apache-maven-3.8.1"
    def mvn_home = tool "maven-3.8"
 // execute tests and produce reports:
   
    sh "${mvn_home}/bin/mvn -B -e -q clean verify"
 // publish the Serenity report
   
    publishHTML(target: [
        reportName : 'Serenity',
        reportDir:   'target/site/serenity',
        reportFiles: 'index.html',
        keepAll:     true,
        alwaysLinkToLastBuild: true,
        allowMissing: false
    ])
}

