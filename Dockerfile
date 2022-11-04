FROM openjdk:17.0.1-jdk-slim
ADD build/libs/telegram-reminder-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]