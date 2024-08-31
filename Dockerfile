FROM alpine/java:22.0.2-jre
MAINTAINER user-name
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /AssistantBot-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/AssistantBot-0.0.1-SNAPSHOT.jar"]