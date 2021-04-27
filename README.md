# ws-employee
*Employee Services*

Spring Boot Microservices and AWS Cloud development. Domain driven design approach using a root aggregate. 

The tech stack for this POC is:
* Spring Boot 2.3.3
* Java 14
* Spring Data JDBC 2
* Postgresql or MySQL on Cloud 
* OpenAPI 3 (swagger 3) with UI
* Azure Platform: Web App service for container
 
### Local environment requirements
* Java 14 JDK - https://openjdk.java.net/projects/jdk/14/
* Maven - https://maven.apache.org/download.cgi
* Docker - https://www.docker.com/products/docker-desktop
* Postgresql - https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

### Database
Next is the database entity model:

![Database Diagram](/doc/EntityModel.png)

The database script is here [script](/doc/database.sql)

### SwaggerUI
The Swagger UI is available at - http://localhost/swagger-ui.html

### Running the application locally
In order to run the application in your computer, three environment variables must be provided
for database connection:
| Variable      | Description |
| ----------- | ----------- |
| DATABASE_URL      | URL connection, example: jdbc:postgresql://ec2-54.compute-1.amazonaws.com:5432/dcfcb4766bbcc       |
| DATABASE_USER      | Database user       |
| DATABASE_PASSWORD      | Database password       |
 
On Windows, set the environment variables with:
```
set DATABASE_URL=jdbc:postgresql://ec2-54.compute-1.amazonaws.com:5432/dcfcb4766bbcc
set DATABASE_USER=myuser
set DATABASE_PASSWORD=mypassword
```

Run the Spring Boot application:
```
mvc spring-boot:run
```
