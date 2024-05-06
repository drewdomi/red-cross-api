# CVA Log
## Requirements

For building and running the application you need:

- [JDK 21](https://www.oracle.com/java/technologies/downloads/)
- [Docker](https://www.docker.com/)
## Running the Spring API

```bash
# Perform a clean build no tests needed
$ ./gradlew clean build -x test

# Copy the JAR file from the 'libs' directory to 'src/main/docker'
$ cp build/libs/*-SNAPSHOT.jar src/main/docker/

# Navigate to 'src/main/docker' 
$ cd src/main/docker

# Create and run Docker image for the Spring application
$ docker compose up

# If there are no errors, navigate to the root of the project and run it:
$ ./gradlew bootRun --continuous
```
