FROM openjdk:17-jdk
WORKDIR /app
EXPOSE 9000
COPY build/libs/Backend-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
