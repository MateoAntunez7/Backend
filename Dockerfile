FROM amazoncorretto:11-alpine-jdk
MAINTAINER AEM
COPY target/aem-0.0.1-SNAPSHOT.jar  aem-app.jar
ENTRYPOINT ["java","-jar","/aem-app.jar"]


