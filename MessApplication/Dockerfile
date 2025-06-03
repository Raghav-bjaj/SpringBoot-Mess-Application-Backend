# Use Maven 3.9.6 with Java 17 for building
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

# Use a slim Java 17 image for running
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/MessSecond-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (Render assigns $PORT)
EXPOSE $PORT

# Run the JAR
CMD ["java", "-jar", "app.jar"]
