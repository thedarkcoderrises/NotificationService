package tdcr.notification.events.eventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tdcr.notification.pojo.SnsNotification;
import tdcr.notification.service.PublisherService;

import static tdcr.notification.service.PublisherService.TOPIC_EMAIL;
import static tdcr.notification.service.PublisherService.TOPIC_SMS;

@Component
public class CuriousEventsHandler {

    @Autowired
    PublisherService publisherService;
    private static Logger LOG = LoggerFactory.getLogger(ErrorDetailEventHandler.class);

    @KafkaListener(topics = "CuriousNotifyTopic",containerFactory = "defaultKafkaListenerContainerFactory")
    public void consume(String message) throws Exception {
        LOG.info("Notification event :{}",message);
        SnsNotification notification =null ;
        notification = new SnsNotification("CuriousMonitor",
                message);
        publisherService.publish(notification,TOPIC_SMS);
        publisherService.publish(notification,TOPIC_EMAIL);
        return;
    }
}
