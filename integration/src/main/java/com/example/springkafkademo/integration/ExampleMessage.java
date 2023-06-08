package com.example.springkafkademo.integration;

import java.time.Instant;

public record ExampleMessage(String data, Instant timestamp) {
}
