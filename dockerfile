FROM openjdk:11
MAINTAINER Treboder
COPY target/SuperDuperDrive-0.0.1-SNAPSHOT.jar SuperDuperDrive-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/SuperDuperDrive-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080