package com.example.springkafkademo.producer;

import com.example.springkafkademo.integration.ExampleMessage;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@Configuration
public class RunnerConfiguration {

    @Bean
    ApplicationListener<ApplicationReadyEvent> applicationReadyEventListener(MessageChannel messageChannel) {
        return event -> {
            messageChannel.send(MessageBuilder
                    .withPayload(new ExampleMessage(UUID.randomUUID().toString(), "integration"))
                    .build());
        };
    }

}
