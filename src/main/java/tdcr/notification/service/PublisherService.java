package tdcr.notification.service;

import tdcr.notification.pojo.SnsNotification;


public interface PublisherService {
    public static final String TOPIC_SMS = "SMS";
    public static final String TOPIC_EMAIL="EMAIL";
    void publish(SnsNotification notification, String topic) throws Exception;
}

