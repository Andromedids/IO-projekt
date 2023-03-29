1. In `application-local.properties` replace similar line with the following: `#spring.datasource.url=jdbc:postgresql://localhost:5555/pop_demo_db`
2. In terminal run: `docker build -t pop_demo_db_docker . `
3. In terminal run: `docker run -d -e POSTGRES_PASSWORD=<your password here> -e POSTGRES_USER=<your username here> -i --name pop_demo_db_docker-container -p 5555:5432 pop_demo_db_docker`