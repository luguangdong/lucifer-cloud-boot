FROM openjdk:17-jdk-alpine
MAINTAINER lucifer
ADD ums-service-1.0.1-SNAPSHOT.jar /data/ums-service-1.0.1.jar
ENTRYPOINT ["java", "-jar", "/data/ums-service-1.0.1.jar"]
