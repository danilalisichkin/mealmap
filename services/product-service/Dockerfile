FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/product-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} product-service.jar

ENTRYPOINT ["java", "-jar", "product-service.jar"]
