# Etapa 1: Construcción con Maven y JDK 17
FROM maven:3.9.0-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos el pom.xml para aprovechar el cache de dependencias
COPY pom.xml .

# Descargamos las dependencias para que quede en caché
RUN mvn dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Construimos el paquete (saltamos los tests para agilizar)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con JRE Temurin 17 Alpine (más ligera)
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiamos el jar construido desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080 (por defecto de Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
