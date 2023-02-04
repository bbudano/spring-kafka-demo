package com.example.springkafkademo.producer;

import com.example.springkafkademo.integration.ExampleMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class KafkaMessageScheduler {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public KafkaMessageScheduler(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(initialDelay = 2000L, fixedRate = 1000L)
    public void sendMessage() {
        kafkaTemplate.send(KafkaConfiguration.SCHEDULER_TOPIC,
                new ExampleMessage(UUID.randomUUID().toString(), "kafka-template-scheduled"));
    }

}
