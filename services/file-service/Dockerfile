FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/file-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} file-service.jar

ENTRYPOINT ["java", "-jar", "file-service.jar"]
