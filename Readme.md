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
* Azure CLI - https://docs.microsoft.com/en-us/cli/azure/install-azure-cli
* A free account of Azure

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
mvn clean package spring-boot:run
```

### Dockerized Application
Create with docker a local container for testing the image. In this example we are going to name the repository: jpworks/ws-employee-az. Everytime you update your code and create an image be sure to run 'mvn clean package'  
```bash
docker build -t jpworks/ws-employee-az:1.0.3 .

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
docker run -p 80:80 ^
--env DATABASE_URL=jdbc:postgresql://ec2-54.compute-1.amazonaws.com:5432/dcfcb4766bbcc ^
--env DATABASE_USER=myuser ^
--env DATABASE_PASSWORD=mypassword ^
jpworks/ws-employee-az:1.0.3


  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.3.3.RELEASE)

2021-04-27 06:24:57.032  INFO 1 --- [           main] c.j.d.employee.EmployeeApplication       : Starting EmployeeApplication v1.0.3 on 94702582d48e with PID 1 (/app.jar started by spring in /)
2021-04-27 06:24:57.034 DEBUG 1 --- [           main] c.j.d.employee.EmployeeApplication       : Running with Spring Boot v2.3.3.RELEASE, Spring v5.2.8.RELEASE
2021-04-27 06:24:57.034  INFO 1 --- [           main] c.j.d.employee.EmployeeApplication       : The following profiles are active: local
2021-04-27 06:24:58.048  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JDBC repositories in DEFAULT mode.
2021-04-27 06:24:58.117  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 64ms. Found 1 JDBC repository interfaces.
2021-04-27 06:24:58.635  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 80 (http)
..
..
2021-04-27 06:25:01.658  INFO 1 --- [           main] c.j.d.employee.EmployeeApplication       : Started EmployeeApplication in 5.481 seconds (JVM running for 6.11)
2021-04-27 06:25:01.660  INFO 1 --- [           main] c.j.d.employee.EmployeeApplication       : Application version: com.jpworks:ws-employee-az:ws-employee-az:1.0.3, 2021-04-27T05:36:32.583Z

```

### Deploy image to Azure Container registry

```bash
az acr login -n myloginserver.azurecr.io

The login server endpoint suffix '.azurecr.io' is automatically omitted.
Login Succeeded
```
