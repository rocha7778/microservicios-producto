FROM openjdk:12
VOLUME /temp
EXPOSE 8880
ADD ./target/microservicios-producto-0.0.1-SNAPSHOT.jar producto.jar
ENTRYPOINT ["java", "-jar", "-Duser.timezone=America/Bogota",  "/producto.jar"]