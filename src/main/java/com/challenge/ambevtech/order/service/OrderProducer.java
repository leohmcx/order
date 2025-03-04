package com.challenge.ambevtech.order.service;

import com.challenge.ambevtech.kafka.OrderEvent;
import com.challenge.ambevtech.order.domain.dto.OrderDto;
import com.challenge.ambevtech.order.mapper.OrderEventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private static final String ORDER_TOPIC_INPUT = "order-topic-input";

    private final OrderEventMapper orderEventMapper;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Async
    public CompletableFuture<SendResult<String, OrderEvent>> produceOrder(OrderDto orderDto) {
        return kafkaTemplate.send(MessageBuilder.withPayload(orderEventMapper.toEntity(orderDto))
                .setHeader(KafkaHeaders.KEY, orderDto.id())
                .setHeader(KafkaHeaders.TOPIC, ORDER_TOPIC_INPUT)
                .build());
    }
}