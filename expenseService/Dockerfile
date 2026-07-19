# ---- Build stage ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle
COPY src ./src

RUN chmod +x gradlew
RUN ./gradlew bootJar -x test --no-daemon

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

COPY --from=build /workspace/build/libs/*.jar app.jar

EXPOSE 9820

HEALTHCHECK --interval=15s --timeout=5s --start-period=40s --retries=6 \
    CMD curl -f http://localhost:9820/expense/v1/health || exit 1

ENTRYPOINT ["java", "-jar", "/app/app.jar"]