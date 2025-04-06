# Убедитесь, что есть явное объявление стадии
FROM gradle:8.5-jdk21-alpine AS builder
WORKDIR /app
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN gradle --no-daemon dependencies
COPY src ./src
RUN gradle bootJar --parallel --no-daemon

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]