FROM maven:3.6.0-jdk-11-slim AS build

WORKDIR /build
COPY pom.xml .
COPY src /build/src
RUN mvn -f /build/pom.xml clean package

FROM adoptopenjdk:11-jre-hotspot
COPY --from=build /build/target/demo-test.jar demo-test.jar
EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["java", "-jar", "demo-test.jar", "java $JAVA_OPTS -jar /demo-test.jar"]