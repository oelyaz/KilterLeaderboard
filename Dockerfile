# Step 1: Build the Spring Boot app
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /app
COPY backend/pom.xml backend/
COPY backend/src backend/src
RUN mvn -f backend/pom.xml clean package -DskipTests

# Step 2: Create a lightweight container with the JAR
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/backend/target/*.jar app.jar

# Expose port and run
EXPOSE 8080
ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "-jar", "app.jar"]
