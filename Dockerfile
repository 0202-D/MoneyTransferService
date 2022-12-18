FROM openjdk:17

EXPOSE 5500

ADD target/MoneyTransferService-0.0.1-SNAPSHOT.jar transfer_service.jar

ENTRYPOINT ["java", "-jar", "transfer_service.jar"]