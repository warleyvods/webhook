FROM openjdk:8

WORKDIR /app

EXPOSE 8050

ENV SPRING_CLOUD_CONFIG="http://config-server:8083"

COPY target/kaizen-discord-webhook.jar /app/kaizen-discord-webhook.jar

ENTRYPOINT ["java", "-jar", "kaizen-discord-webhook.jar"]
