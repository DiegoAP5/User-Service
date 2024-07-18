FROM amazonlinux:2 AS base
WORKDIR /app

RUN yum install -y java-21-amazon-corretto-devel

RUN yum install -y maven

RUN java -version
RUN mvn -version

FROM base AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM amazoncorretto:21-al2-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
