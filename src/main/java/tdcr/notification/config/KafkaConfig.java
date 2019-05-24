package tdcr.notification.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import tdcr.notification.events.ErrorDetails;
import tdcr.notification.events.UserCreatedEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaConfig {

    @Value("${kafka.url}")
    String kafkaURL;
    private static Logger LOG = LoggerFactory.getLogger(KafkaConfig.class);

    private Map<String, Object> consumerConfig() {
        Map<String, Object> config = new HashMap();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaURL);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return  config;
    }

    public ConsumerFactory<String, UserCreatedEvent> cosnumerCreateFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfig(),
                new StringDeserializer(),new JsonDeserializer(UserCreatedEvent.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,UserCreatedEvent> kafkaListenerCreateContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,UserCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<String, UserCreatedEvent>();
        factory.setConsumerFactory(cosnumerCreateFactory());
        LOG.info("UserCreatedEvent listener initialised..");
        return factory;
    }

    public ConsumerFactory<String, ErrorDetails> incidentDetailsConsumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfig(),
                new StringDeserializer(),new JsonDeserializer(ErrorDetails.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ErrorDetails> kafkaListenerIncContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, ErrorDetails> factory =
                new ConcurrentKafkaListenerContainerFactory<String, ErrorDetails>();
        factory.setConsumerFactory(incidentDetailsConsumerFactory());
        LOG.info("IncDetailEvent listener initialised..");
        return factory;
    }

    public ConsumerFactory<String, String> defaultConsumerFactory() {
        return new DefaultKafkaConsumerFactory(consumerConfig(),
                new StringDeserializer(),new JsonDeserializer(String.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> defaultKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory factory =
                new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(defaultConsumerFactory());
        LOG.info("Default listener initialised..");
        return factory;
    }
}
