FROM openjdk:11-jre-slim
EXPOSE 9097
VOLUME /Product-Bidding-Service-App
COPY target/Product-Bidding-Service.jar Product-Bidding-Service.jar
ENTRYPOINT ["java","-jar","/Product-Bidding-Service.jar"]
