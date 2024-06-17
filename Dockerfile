FROM gradle:jdk-21-and-22-alpine AS build

COPY build.gradle settings.gradle ./
COPY src ./src
RUN gradle clean build --refresh-dependencies -x test

FROM eclipse-temurin:21

RUN mkdir /app
WORKDIR /app
COPY --from=build /home/gradle/build/libs/*-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]
