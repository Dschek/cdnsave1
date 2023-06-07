FROM openjdk:16-jdk-alpine

WORKDIR /home/save
COPY ./build/libs/app.jar app.jar

EXPOSE 8080

CMD java -jar app.jar
