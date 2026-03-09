# Stage 1: Build
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

RUN apt-get update && apt-get install -y maven

COPY . .

RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]