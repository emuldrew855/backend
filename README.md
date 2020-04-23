# General Info
This is the backend server for a project which was created for eBay for Charity program. It manages the requests and responses from eBay and PayPal's APIs. 
In addition to this it serves up calls to the matching frontend and communicates to a local MongoDB instance. 

# How to set up Spring Boot server
1) You will have to download a Java Development kit and set you JAVA_HOME environmental variable to the JDK location https://www.oracle.com/java/technologies/javase-jdk8-downloads.html

2) Download Maven and add it to your systems environment variables. A good website to do this can be found here. https://www.baeldung.com/install-maven-on-windows-linux-mac

3) Step 4: Then navigate to the folder where you cloned the project and enter ‘mvn install’. This will build and package a Spring Boot app into a single executable Jar file with a Maven. 

4)  Then enter ‘mvn spring-boot:run’ and the backend server should be running

# How to integrate Spring Boot with MongoDB
Finally the system requires a local instance of MongoDB to manage the systems data. 
1) The user needs download a local instance of MongoDB so that the backend can connect to the database. You can download this here https://www.mongodb.com/download-center/community?tck=docs_server

2) Install the local instance of MongoDB and set it up to “Run service as Network Service User”

3) To double check the service is running type “tasklist /FI "IMAGENAME eq mongod.exe”

# Download the matching frontend
To get full use out of this server you should download my frontend here: https://github.com/emuldrew855/MarkoProject