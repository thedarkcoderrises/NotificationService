#getting base image
FROM java:8
VOLUME /tmp/notification
ADD /target/NotificationService-1.0-SNAPSHOT.jar notification.jar
EXPOSE 8081
ENTRYPOINT ["java","-Dkafka.url=kafka:9092","-jar","/notification.jar"]
RUN pwd
RUN ls -ltr