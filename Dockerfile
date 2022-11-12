FROM openjdk:17.0.1-jdk-slim
ADD build/libs/telegram-reminder-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]