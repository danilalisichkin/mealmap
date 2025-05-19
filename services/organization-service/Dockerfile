FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/organization-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} organization-service.jar

ENTRYPOINT ["java", "-jar", "organization-service.jar"]
