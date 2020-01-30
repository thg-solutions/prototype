FROM openjdk:11.0.5-jre
COPY build/libs/prototype-0.0.1-SNAPSHOT.jar .
CMD java -jar prototype-0.0.1-SNAPSHOT.jar
EXPOSE 8080
