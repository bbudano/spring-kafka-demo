package com.example.springkafkademo.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaConfiguration {

    public static final String EXAMPLE_TOPIC = "example_topic";
    public static final String SCHEDULER_TOPIC = "scheduler_topic";

    @Bean
    NewTopic exampleTopic() {
        return new NewTopic(EXAMPLE_TOPIC, 1, (short) 1);
    }

    @Bean
    NewTopic schedulerTopic() {
        return new NewTopic(SCHEDULER_TOPIC, 1, (short) 1);
    }

    @Bean
    KafkaTemplate<Object, Object> kafkaTemplate(ProducerFactory<Object, Object> producerFactory) {
        return new KafkaTemplate<>(
                producerFactory,
                Map.of(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
        );
    }

}
