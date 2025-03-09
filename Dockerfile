# Usar una imagen base con JDK 17
FROM eclipse-temurin:17-jdk

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copiar el archivo JAR generado por Gradle
COPY build/libs/*.jar app.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
