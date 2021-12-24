FROM openjdk:11-jre-slim
EXPOSE 8095
VOLUME /product-service-app
ARG JAR_FILE=target/product-service.jar
COPY ${JAR_FILE} product-service.jar

ENTRYPOINT ["java","-jar","/product-service.jar"]