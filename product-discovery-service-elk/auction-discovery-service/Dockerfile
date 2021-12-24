FROM openjdk:11-jre-slim
EXPOSE 8761
VOLUME /discovery-service-app.
ARG JAR_FILE=target/product-discovery-service.jar
COPY ${JAR_FILE} product-discovery-service.jar

ENTRYPOINT ["java","-jar","/product-discovery-service.jar"]
