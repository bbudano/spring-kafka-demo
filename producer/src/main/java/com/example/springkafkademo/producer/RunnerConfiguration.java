package com.example.springkafkademo.producer;

import com.example.springkafkademo.integration.ExampleMessage;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@Configuration
public class RunnerConfiguration {

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventListener(KafkaTemplate<Object, Object> kafkaTemplate,
                                                                             MessageChannel messageChannel,
                                                                             StreamBridge streamBridge) {
        return event -> {
            for (int i = 0; i < 100; i++) {
                kafkaTemplate.send(KafkaConfiguration.EXAMPLE_TOPIC,
                        new ExampleMessage(UUID.randomUUID().toString(), "kafka-template"));

                messageChannel.send(MessageBuilder
                        .withPayload(new ExampleMessage(UUID.randomUUID().toString(), "integration"))
                        .build());

                streamBridge.send("exampleMessages-out-0", new ExampleMessage(UUID.randomUUID().toString(), "stream"));
            }
        };
    }

}
