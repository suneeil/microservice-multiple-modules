FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY *.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dport=8100", "/app.jar"]