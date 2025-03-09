# 1️⃣ Usamos la imagen oficial de Gradle con Java 17
FROM gradle:8.5-jdk17 AS build

# 2️⃣ Establecemos el directorio de trabajo
WORKDIR /app

# 3️⃣ Copiamos el código fuente
COPY . .

# 4️⃣ Construimos el proyecto (genera el JAR)
RUN gradle build --no-daemon

# 5️⃣ Usamos una imagen más liviana de OpenJDK 17 para ejecutar el JAR
FROM openjdk:17-jdk-slim

WORKDIR /app

# 6️⃣ Copiamos el JAR generado en la fase de compilación
COPY --from=build /app/build/libs/*.jar app.jar

# 7️⃣ Exponemos el puerto 8080
EXPOSE 8080

# 8️⃣ Definimos variables de entorno para la conexión a PostgreSQL (opcional)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://dpg-cv6fop8gph6c73dmosq0-a:5432/dbtecnify
