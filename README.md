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
```bash
mvc spring-boot:run
```

### Dockerized Application
Create with docker a local container for testing the image. In this example we are going to name the repository: jpworks/ws-employee-az  
```bash
docker build -t jpworks/ws-employee-az .

[+] Building 2.1s (8/8) FINISHED
 => [internal] load build definition from Dockerfile                                                                            0.0s
 => => transferring dockerfile: 32B                                                                                             0.0s
 => [internal] load .dockerignore                                                                                               0.0s
 => => transferring context: 2B                                                                                                 0.0s
 => [internal] load metadata for docker.io/adoptopenjdk/openjdk14:alpine-jre                                                    1.8s
 => [1/3] FROM docker.io/adoptopenjdk/openjdk14:alpine-jre@sha256:3fc4afe28bb469b7b1915bc936fe7daa57b9b85d9bceaadecd09e12315a4  0.0s
 => [internal] load build context                                                                                               0.0s
 => => transferring context: 90B                                                                                                0.0s
 => CACHED [2/3] RUN addgroup -S spring && adduser -S spring -G spring                                                          0.0s
 => CACHED [3/3] COPY target/*.jar app.jar                                                                                      0.0s
 => exporting to image                                                                                                          0.0s
 => => exporting layers                                                                                                         0.0s
 => => writing image sha256:2321b647c96847147d7eff285e94ea38f5efd19c9afc8eb2b7aadc2279e08371                                    0.0s
 => => naming to docker.io/jpworks/ws-employee-az                                                                               0.0s

Use 'docker scan' to run Snyk tests against images to find vulnerabilities and learn how to fix them
```

Run the image, replace the values of the environmental variables with your database connection:
```bash
docker run jpworks/ws-employee-az -d -p 80:80 -e DATABASE_URL='jdbc:postgresql://ec2-54.compute-1.amazonaws.com:5432/dcfcb4766bbcc' -e DATABASE_USER='user' -e DATABASE_PASSWORD='password'
```