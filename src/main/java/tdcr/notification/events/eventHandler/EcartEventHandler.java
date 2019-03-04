package tdcr.notification.events.eventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tdcr.notification.events.UserCreatedEvent;
import tdcr.notification.pojo.SnsNotification;
import tdcr.notification.service.PublisherService;

import static tdcr.notification.service.PublisherService.TOPIC_EMAIL;
import static tdcr.notification.service.PublisherService.TOPIC_SMS;

@Component
public class EcartEventHandler {

    @Autowired
    PublisherService publisherService;
    private static Logger LOG = LoggerFactory.getLogger(EcartEventHandler.class);

    @KafkaListener(topics = "CreateUserTopic",containerFactory = "kafkaListenerCreateContainerFactory")
    public void consume(UserCreatedEvent event) throws Exception {
        LOG.info("Notification event :{}",event.toString());
        SnsNotification notification =null ;
            notification = new SnsNotification("AccountCreated" ,
                    "UserId :"+event.getUserId());
            publisherService.publish(notification,TOPIC_SMS);
            publisherService.publish(notification,TOPIC_EMAIL);
            return;
    }
}
