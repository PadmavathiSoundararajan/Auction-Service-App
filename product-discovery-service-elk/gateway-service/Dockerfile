FROM openjdk:11-jre-slim
EXPOSE 8098
VOLUME /gateway-service-app
ARG JAR_FILE=target/gateway-service.jar
COPY ${JAR_FILE} gateway-service.jar

ENTRYPOINT ["java","-jar","/gateway-service.jar"]