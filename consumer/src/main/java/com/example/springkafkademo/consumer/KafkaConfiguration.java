package com.example.springkafkademo.consumer;

import com.example.springkafkademo.integration.ExampleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.Message;

@Configuration
public class KafkaConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConfiguration.class);

    private static final String EXAMPLE_TOPIC = "example_topic";

    @Bean
    JsonMessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }

    @KafkaListener(topics = EXAMPLE_TOPIC, groupId = "example_topic_group")
    public void onNewExampleMessage(Message<ExampleMessage> exampleMessage) {
        LOGGER.info("Payload - {}", exampleMessage.getPayload());
        LOGGER.info("Headers - {}", exampleMessage.getHeaders());
    }

}
