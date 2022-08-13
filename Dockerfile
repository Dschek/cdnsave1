FROM openjdk:16-jdk-alpine

WORKDIR /home/cdn
COPY ./build/libs/save-0.0.1-SNAPSHOT-plain.jar save.jar

EXPOSE 8080

CMD java -jar save.jar
