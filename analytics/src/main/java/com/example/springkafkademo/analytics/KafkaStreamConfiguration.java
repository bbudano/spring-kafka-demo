package com.example.springkafkademo.analytics;

import com.example.springkafkademo.integration.ExampleMessage;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class KafkaStreamConfiguration {

    @Bean
    Function<KStream<String, ExampleMessage>, KStream<String, Long>> counter() {
        return messages -> messages
                .filter((s, m) -> !m.source().equalsIgnoreCase("kafka-template-scheduled"))
                .map((s, em) -> new KeyValue<>(em.source(), 0L))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                .count(Materialized.as("source_counts"))
                .toStream();
    }

}
