FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY /target/Telecom-0.0.1-SNAPSHOT.jar Telecom-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Telecom-0.0.1-SNAPSHOT.jar"]
EXPOSE 4000