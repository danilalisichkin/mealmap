FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/user-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]
