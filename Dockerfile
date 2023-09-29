# Utiliza una imagen base de Maven
FROM maven:3.8.4-openjdk-17 AS build

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo pom.xml al contenedor
COPY pom.xml .

# Descarga las dependencias del proyecto sin compilar
RUN mvn dependency:go-offline -B

# Copia todo el contenido del proyecto al contenedor
COPY src src

# Construye la aplicación Maven
RUN mvn package -DskipTests

# Etapa final: Utiliza una imagen base de Java 17 (OpenJDK) para la imagen final
FROM openjdk:17-alpine

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR construido desde la etapa de construcción al contenedor
COPY --from=build /app/target/api-micro-reactive-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que tu aplicación Java escucha (ajusta el puerto según tu aplicación)
EXPOSE 8080

# Comando para ejecutar la aplicación Java al iniciar el contenedor
CMD ["java", "-jar", "app.jar"]
