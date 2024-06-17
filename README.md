# CVA Log

## Requirements

For building and running the application you need:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/)
- [Docker](https://www.docker.com/)

## Running the Spring API

```bash

# Run docker image for the Spring application & Postgres database
$ docker compose up

# If there are no errors, navigate to the root of the project and run it:
$ ./gradlew bootRun --continuous
```
