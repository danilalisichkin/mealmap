FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/health-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} health-service.jar

ENTRYPOINT ["java", "-jar", "health-service.jar"]
