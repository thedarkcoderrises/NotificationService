#getting base image
FROM java:8
VOLUME /tmp/notification
ADD /target/NotificationService-1.0-SNAPSHOT.jar notification.jar
EXPOSE 8082
#ENTRYPOINT ["java","-Dkafka.url=kafka:9092","-jar","/notification.jar"]
ENTRYPOINT ["java","-jar","-DAWS_ACCESS_KEY_ID=AKIAJ5WRUHMYNU5FL42Q","-DAWS_SECRET_ACCESS_KEY=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","/notification.jar"]
RUN pwd
RUN ls -ltr