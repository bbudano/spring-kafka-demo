package com.example.springkafkademo.consumer;

import com.example.springkafkademo.integration.ExampleMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.Message;

@Slf4j
@Configuration
public class KafkaConfiguration {

    private static final String EXAMPLE_TOPIC = "example_topic";

    @Bean
    JsonMessageConverter jsonMessageConverter() {
        return new JsonMessageConverter();
    }

    @KafkaListener(topics = EXAMPLE_TOPIC, groupId = "example_topic_group_1")
    public void onNewExampleMessage(Message<ExampleMessage> exampleMessage) {
        log.info("Payload - {}", exampleMessage.getPayload());
        log.info("Headers - {}", exampleMessage.getHeaders());
    }

}
