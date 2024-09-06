
# instructions or commands to build docker image

FROM eclipse-temurin:17

LABEL maintainer="solomonotoo74@gmail.com"

# createa a working directory named app in the container
WORKDIR /app

COPY target/springboot-resful-webservices-0.0.1-SNAPSHOT.jar /app/springboot-resful-webservices.jar

ENTRYPOINT ["java", "-jar", "springboot-resful-webservices.jar"]