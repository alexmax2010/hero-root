FROM openjdk:8-jdk-alpine

MAINTAINER Kenny Lopez "klopez@ec.krugercorp.com"

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

EXPOSE 8080

WORKDIR /api

#COPY build/libs/*.jar /api/app.jar
ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ENTRYPOINT exec java -Dspring.profiles.active=${SPRING_PROFILE} -Xms64m -Xmx256m -jar app.jar