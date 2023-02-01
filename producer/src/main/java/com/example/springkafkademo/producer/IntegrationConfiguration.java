package com.example.springkafkademo.producer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;

@Configuration
public class IntegrationConfiguration {

    @Bean
    MessageChannel messageChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    IntegrationFlow integrationFlow(MessageChannel messageChannel, KafkaTemplate<Object, Object> kafkaTemplate) {
        var kafkaHandler = Kafka
                .outboundChannelAdapter(kafkaTemplate)
                .topic(KafkaConfiguration.EXAMPLE_TOPIC)
                .get();

        return IntegrationFlow
                .from(messageChannel)
                .handle(kafkaHandler)
                .get();
    }

}
