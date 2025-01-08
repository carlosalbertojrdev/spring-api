FROM gradle:8.11-alpine AS build

WORKDIR /app

COPY src src
COPY build.gradle .
COPY settings.gradle .

RUN gradle clean build -x test

FROM amazoncorretto:21-alpine-jdk AS release

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]