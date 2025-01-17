FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

COPY ./src/main/resources/key.json key.json

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
