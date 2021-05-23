FROM openjdk:14-alpine

RUN apk upgrade --update && apk add freetype

RUN apk --no-cache add msttcorefonts-installer fontconfig && update-ms-fonts && fc-cache -f

ARG WAR_FILE=./target/*.war

COPY ${WAR_FILE} library-api.war

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "library-api.war"]