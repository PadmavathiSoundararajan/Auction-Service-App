FROM openjdk:11-jre-slim
EXPOSE 9096
VOLUME /query-service-App
COPY target/query-service.jar query-service.jar
ENTRYPOINT ["java","-jar","/query-service.jar"]
