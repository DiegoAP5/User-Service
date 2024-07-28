FROM amazoncorretto:17-alpine3.19

WORKDIR /app

# install dependencies with pom and then create final fat jar
RUN apk add --no-cache maven
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package -DskipTests=true

# copy the final jar to the image
RUN cp target/*.jar app.jar

# run the jar
CMD ["java", "-jar", "app.jar"]