FROM openjdk:11-jre-slim
EXPOSE 8888
VOLUME /discovery-service-app.
#WORKDIR ./auction-discovery-service
ARG JAR_FILE=target/discovery-service.jar
COPY ${JAR_FILE} discovery-service.jar

ENTRYPOINT ["java","-jar","/discovery-service.jar"]
