FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/notification-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} notification-service.jar

ENTRYPOINT ["java", "-jar", "notification-service.jar"]
