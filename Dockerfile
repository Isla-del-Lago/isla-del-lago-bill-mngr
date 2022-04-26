FROM amazoncorretto:11

EXPOSE 9003

WORKDIR /app

COPY ./target/billmanager-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "billmanager-0.0.1-SNAPSHOT.jar"]