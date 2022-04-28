FROM openjdk
EXPOSE 8081
ADD /target/Conditional-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "/app.jar"]