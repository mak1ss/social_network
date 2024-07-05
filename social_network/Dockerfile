FROM amazoncorretto:17.0.11

RUN mkdir /app

COPY target/network.war /app/network.war

EXPOSE 8080

WORKDIR /app

CMD java -jar network.war

