FROM adoptopenjdk:latest
ARG JAR_FILE=*.jar
WORKDIR /demo
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
