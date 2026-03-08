# Stage 1: Build
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Copier le wrapper Gradle
COPY gradlew ./
COPY gradle ./gradle

# Copier les fichiers de build
COPY build.gradle settings.gradle ./

# Résoudre les dépendances avec retry
RUN gradle dependencies --no-daemon --refresh-dependencies --stacktrace --info || \
    sleep 5 && gradle dependencies --no-daemon --refresh-dependencies || \
    sleep 10 && gradle dependencies --no-daemon

# Copier le code source
COPY src ./src

# Build
RUN gradle build --no-daemon -x test

# Stage 2: Run
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]