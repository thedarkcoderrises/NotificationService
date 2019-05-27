package tdcr.notification.service.impl;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdcr.notification.pojo.SnsNotification;
import tdcr.notification.service.PublisherService;

@Service
public class AmazonSNSPublisherService implements PublisherService {
    @Autowired
    private AmazonSNS amazonSNS;

    private static Logger LOG = LoggerFactory.getLogger(AmazonSNSPublisherService.class);
    @Override
    public void publish(SnsNotification notification, String topic) throws Exception {
        String snsTopic = getTopicARN(topic);
        amazonSNS.setRegion(Region.getRegion(Regions.US_EAST_1));
        PublishRequest publishRequest = new PublishRequest(snsTopic, notification.getMessage(),notification.getSubject());
        PublishResult publishResult = amazonSNS.publish(publishRequest);
        LOG.info("MessageId - {} for chosen topic {} ", publishResult.getMessageId(),topic);

    }

    private String getTopicARN(String topic) throws Exception {
        switch(topic) {
            case TOPIC_SMS:
//                return "arn:aws:sns:us-east-1:763880534281:JB-TDCR-SNS-SMS-TOPIC";
                return "arn:aws:sns:us-east-1:793439442352:test_topic";
            case TOPIC_EMAIL:
//                return  "arn:aws:sns:us-east-1:763880534281:JB-TDCR-SNS-EMAIL-TOPIC";
                return "arn:aws:sns:us-east-1:793439442352:test_topic_email";
            default:
                throw new Exception("No matching topic supported!");
        }
    }
}
