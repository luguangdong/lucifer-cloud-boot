FROM luguangdong/jdk:v17.0.9
MAINTAINER lucifer
ADD ums-service-1.0.1-SNAPSHOT.jar /data/ums-service-1.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Dproject.name=ums", "-jar", "/data/ums-service-1.0.1-SNAPSHOT.jar", "--spring.profiles.active=dev"]

