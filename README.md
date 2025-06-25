# Recipe Cart Application

### Stack

The project was created using https://start.spring.io/ for a quick setup.

- Maven for build, wrapper included here.
- Java 21 (latest version supported by Kotlin)
- Kotlin 1.9.25
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Flyway (migrations)
- Testcontainers (for integration tests with real PostgreSQL)

### How to build the application

The only dependency is Docker, you'll need it running first.

The Dockerfile will take care of the entire build.
So this command is enough:

```shell
docker-compose up
```

For this command to work properly, make sure the ports `9090` and `5432` are available.

### Debug the code

The application is also ready to run outside of Docker.
We can follow these steps:

* Stop the containers (if you already started them)
```shell
docker-compose down
```

* Then start only the database:

```shell
docker-compose up postgres
```

Now you can start the application in your IntelliJ (or any IDE) using the Debug mode.

### Sample data

In the migrations I've already created the products and recipes; in the tests I start with the cart and cart item parts.

## Quick Demo

https://www.loom.com/share/511bdd72ad7142edb57b14e9f912acca?sid=a8affef0-b832-4756-9653-4a8bf69e3e24


### Next steps

Some improvements or next steps I could do here, but I decided to not do for the sake of simplicity:

- Swagger to document the API.
- Cover the endpoints in E2E.
- More endpoints to create all the dependencies.