FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/recommendation-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} recommendation-service.jar

ENTRYPOINT ["java", "-jar", "recommendation-service.jar"]
