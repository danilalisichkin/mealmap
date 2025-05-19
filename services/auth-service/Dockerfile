FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/auth-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} auth-service.jar

ENTRYPOINT ["java", "-jar", "auth-service.jar"]
