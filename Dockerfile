FROM openjdk:17-alpine
MAINTAINER haa
COPY build/libs/takehome-1.0.2.jar takehome-1.0.2.jar
ENTRYPOINT ["java","-jar","/takehome-1.0.2.jar"]