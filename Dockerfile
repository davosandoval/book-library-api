FROM openjdk:14-alpine

ARG WAR_FILE=./target/*.war

COPY ${WAR_FILE} library-api.war

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "library-api.war"]