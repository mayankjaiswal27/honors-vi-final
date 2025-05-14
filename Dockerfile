# Use an OpenJDK 17 base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the target directory
COPY target/airline-management-system-0.0.1-SNAPSHOT.jar /app/airline-management-system.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/airline-management-system.jar"]