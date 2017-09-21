# Internal Dashboard Services
This is the project that will contain all the web services for internal IBM USAA applications.

### Build Server
 - Jenkins URL: http://9.178.151.159:8080/  
 - Jenkins Job URL: TBD
   
### Database
 - Database: ibm-usaa  
 - URL: jdbc:mariadb://9.178.151.159:3306/  
 - Username: usaa  
 - Password: usaa  
 - Driver JAR: https://na.artifactory.swg-devops.com/artifactory/g2o-local-approved/MariaDB%20Client%20library%20for%20Java%20application%20-%20IUA/1.1.1/mariadb-java-client-1.1.1.zip
    - use ibm network credentials when asked for username and password when downloading the driver jar
 - Driver class: org.mariadb.jdbc.Driver  
   
### Maven Artifact Repository 
 - Repository URL: http://9.178.151.159:8081/  

### To run the project locally in eclipse
1. Right click project  
2. Click **Run As**  
3. Click **Maven Build...**  
4. In goals, put `spring-boot:run`
5. Click **Run**
    
### To run the project locally in command line
1. Run **cmd.exe**
2. Type `mvn spring-boot:run`

### To run the compiled war file locally
1. Run **cmd.exe**
2. Type `java -jar <name of war file here>.war`

### Source Package Structure 
`com.ibm.usaa.configuration` - Will contain classes for bean configurations  
`com.ibm.usaa.mapper` - Will contain classes that transfers data from representation classes to other classes(entity, custom data object, etc.) and vice versa  
`com.ibm.usaa.resource` - Will contain classes that exposes rest apis  
`com.ibm.usaa.resource.representation` - Will contain classes that acts as the request/response objects of a rest api  
`com.ibm.usaa.service` - Will contain services   
`com.ibm.usaa.exception` - Will contain custom exception classes   
`com.ibm.usaa.repository` - Will contain spring-data-jpa repository classes  
`com.ibm.usaa.repository.entity` - Will contain jpa entity classes  
