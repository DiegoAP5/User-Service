# Usar Amazon Corretto 21 como base
FROM amazoncorretto:21 AS base
WORKDIR /app

# Instalar Maven
RUN yum update -y && \
    yum install -y wget && \
    wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo && \
    yum install -y apache-maven && \
    yum clean all

# Crear una etapa de construcción con Maven
FROM base AS build
WORKDIR /app

# Copiar el archivo pom.xml y descargar las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente y construir el proyecto
COPY src ./src
RUN mvn package -DskipTests

# Crear una etapa final para ejecutar la aplicación
FROM amazoncorretto:21-al2-jdk
WORKDIR /app

# Copiar el archivo .jar desde la fase de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto 8080 y definir el punto de entrada
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
