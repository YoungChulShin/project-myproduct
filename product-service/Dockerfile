FROM openjdk:17.0.1
EXPOSE 8080
ADD ./build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]