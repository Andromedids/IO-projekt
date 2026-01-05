## Prerequisites:
- Docker installed (`docker run hello-world` works for you)
- Java17 installed

## How to run the project
1. build and run `docker build` and `docker run` (just as for other databases on Docker)
2. run the application either via IntelliJ or commandline: `chmod +x ./gradlew && ./gradlew bootJar && java -jar -Dspring.datasource.username=<username> -Dspring.datasource.password=<password>  build/libs/SFI-POP-2023-0.0.1-SNAPSHOT.jar`
3. open swagger UI http://localhost:8080/swagger-ui/index.html 