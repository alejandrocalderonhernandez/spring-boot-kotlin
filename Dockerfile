FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /img
ADD ./target/spring-kotlin-example-0.0.1-SNAPSHOT.jar  app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]