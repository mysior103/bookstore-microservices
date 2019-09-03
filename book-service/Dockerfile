FROM openjdk:11-jdk

ARG name
ENV name=book-service

WORKDIR /usr/src/${name}

COPY . .

RUN ./gradlew build -x test

MAINTAINER Rafal Podles

ENTRYPOINT java -jar /usr/src/${name}/build/libs/${name}-0.0.1-SNAPSHOT.jar

EXPOSE 8082
