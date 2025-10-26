# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working dirctory
WORKDIR /app

# Copy the application JAR file to the container
COPY target/metro-card-system-0.0.1-SNAPSHOT.jar /app/app.jar

#Expose the port the app runs on
EXPOSE 8080

#Run the jar file
CMD ["java", "-jar", "app.jar"]