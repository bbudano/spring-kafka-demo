package com.example.springkafkademo.analytics;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnalyticsController {

    private final InteractiveQueryService interactiveQueryService;

    @GetMapping("/source-counts")
    List<SourceCountView> sourceCounts() {
        ReadOnlyKeyValueStore<String, Long> countsStore =
                this.interactiveQueryService.getQueryableStore("source_counts", QueryableStoreTypes.keyValueStore());

        KeyValueIterator<String, Long> countsIterator = countsStore.all();

        var sourceCountViews = new ArrayList<SourceCountView>();
        while (countsIterator.hasNext()) {
            KeyValue<String, Long> kv = countsIterator.next();
            sourceCountViews.add(new SourceCountView(kv.key, kv.value));
        }

        return sourceCountViews;
    }

}
