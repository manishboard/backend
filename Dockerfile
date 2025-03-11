# Stage 1: Build Stage
FROM maven:3-amazoncorretto-21-alpine AS build

WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests
RUN ls /app

# Stage 2: Run Stage
FROM openjdk:21-jdk

ARG PROFILE
ARG DB_ENDPOINT
ARG DB_USERNAME
ARG DB_PASSWORD

ENV PROFILE=${PROFILE}
ENV DB_ENDPOINT=${DB_ENDPOINT}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
