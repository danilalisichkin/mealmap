FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/cart-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} cart-service.jar

ENTRYPOINT ["java", "-jar", "cart-service.jar"]
