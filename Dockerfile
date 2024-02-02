FROM maven:3.6.0-jdk-11-slim as build

WORKDIR /build
COPY /pom.xml .
COPY /src /build/src
RUN mvn -f /build/pom.xml clean install -D skipTests

FROM adoptopenjdk:11-jre-hotspot

COPY --from=build /build/target/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]
