FROM amazoncorretto:21-alpine AS runtime

VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=target/telegram-bot-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} telegram-bot.jar

ENTRYPOINT ["java", "-jar", "telegram-bot.jar"]
