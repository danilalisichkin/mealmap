FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/preference-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} preference-service.jar

ENTRYPOINT ["java", "-jar", "preference-service.jar"]
