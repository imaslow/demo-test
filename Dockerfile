FROM maven:3.6.0-jdk-11-slim AS build

WORKDIR /build
COPY demo/pom.xml .
COPY demo/src /build/src
RUN mvn -f /build/pom.xml clean package

FROM adoptopenjdk:11-jre-hotspot
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
COPY --from=build /build/target/demo.jar demo.jar
EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "demo.jar", "java $JAVA_OPTS -jar /demo.jar"]