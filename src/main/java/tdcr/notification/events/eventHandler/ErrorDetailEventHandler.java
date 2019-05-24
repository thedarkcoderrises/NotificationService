package tdcr.notification.events.eventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tdcr.notification.events.ErrorDetails;
import tdcr.notification.pojo.SnsNotification;
import tdcr.notification.service.PublisherService;

import static tdcr.notification.service.PublisherService.TOPIC_EMAIL;
import static tdcr.notification.service.PublisherService.TOPIC_SMS;

@Component
public class ErrorDetailEventHandler {


    @Autowired
    PublisherService publisherService;
    private static Logger LOG = LoggerFactory.getLogger(ErrorDetailEventHandler.class);

    @KafkaListener(topics = "RaiseIncTopic",containerFactory = "kafkaListenerIncContainerFactory")
    public void consume(ErrorDetails event) throws Exception {
        LOG.info("Notification event :{}",event.toString());
        SnsNotification notification =null ;
        notification = new SnsNotification("Incident Created for container"+ event.getImageId(),
                "Error occured with type :"+event.getIncDescription().toString());
        publisherService.publish(notification,TOPIC_SMS);
        publisherService.publish(notification,TOPIC_EMAIL);
        return;
    }
}
