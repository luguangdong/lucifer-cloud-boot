FROM java:17
MAINTAINER lucifer
ADD ums-service-1.0.1.jar /data/ums-service-1.0.1.jar
ENTRYPOINT ["java", "-jar", "/data/ums-service-1.0.1.jar"]
