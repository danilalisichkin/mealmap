FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/order-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} order-service.jar

ENTRYPOINT ["java", "-jar", "order-service.jar"]
