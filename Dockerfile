# Utilizar Amazon Corretto 21 como base
FROM amazoncorretto:21-al2-jdk AS base
WORKDIR /app

# Instalar Maven
RUN yum install -y maven

# Crear una etapa de construcción con Maven
FROM base AS build
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .

# Copiar el código fuente y construir el proyecto
COPY src ./src
RUN mvn package -DskipTests

# Crear una etapa final para ejecutar la aplicación
FROM base
WORKDIR /app

# Copiar el archivo .jar desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto 8080 y definir el punto de entrada
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
