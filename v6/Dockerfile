# Используем официальный образ Java
FROM openjdk:17-jdk-slim

# Указываем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем .jar файл (предполагается, что он уже собран)
COPY target/pollapp2-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
