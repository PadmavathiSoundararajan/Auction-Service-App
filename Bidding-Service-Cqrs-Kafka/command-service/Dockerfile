FROM openjdk:11-jre-slim
EXPOSE 8099
VOLUME /command-service-App
COPY target/command-service.jar command-service.jar
ENTRYPOINT ["java","-jar","/command-service.jar"]
