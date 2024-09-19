FROM alpine/java:22.0.2-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /AssistantBot.jar
CMD ["java","-jar","/AssistantBot.jar"]