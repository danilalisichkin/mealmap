FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/promo-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} promo-service.jar

ENTRYPOINT ["java", "-jar", "promo-service.jar"]
