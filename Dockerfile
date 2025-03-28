#### CREACION DEL JAR ####
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY ./pom.xml .
RUN mvn -e -B dependency:go-offline
COPY ./src ./src
RUN mvn -e -B -D maven.test.skip=true package

#### FASE FINAL DE LA IMAGEN ####
FROM openjdk:21-slim
LABEL maintainer="sebashh26@gmail.com"
WORKDIR /workspace

ARG mypassword
ENV url_apipersona="apipersona"
ENV port_apipersona="8080"
ENV host="mysql_server"
ENV port="3306"
ENV database="demobd"
ENV username="root"
ENV password=$mypassword

COPY --from=builder /app/target/api*.jar app.jar

EXPOSE 8081
ENTRYPOINT exec java -jar /workspace/app.jar