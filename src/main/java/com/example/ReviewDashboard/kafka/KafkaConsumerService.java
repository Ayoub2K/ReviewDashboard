package com.example.ReviewDashboard.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "example_topic", groupId = "group_id")
    public void consumeMessage(String message) {
        System.out.println("Received message from Kafka: " + message);
    }
}