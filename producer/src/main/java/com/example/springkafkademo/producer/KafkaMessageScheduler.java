package com.example.springkafkademo.producer;

import com.example.springkafkademo.integration.ExampleMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class KafkaMessageScheduler {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Scheduled(initialDelay = 2000L, fixedRate = 1000L)
    public void sendMessage() {
        kafkaTemplate.send(KafkaConfiguration.EXAMPLE_TOPIC,
                new ExampleMessage(UUID.randomUUID().toString(), "producer-scheduled"));
    }

}
