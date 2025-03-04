package com.challenge.ambevtech.order.service;

import com.challenge.ambevtech.kafka.OrderEvent;
import com.challenge.ambevtech.order.mapper.OrderMapper;
import com.challenge.ambevtech.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public void run(OrderEvent dto) {
        log.info("OrderEvent: {}", dto);
        final var order = orderMapper.toEntity(dto);
        log.info("Order Entity: {}", order);
        orderRepository.save(order);
    }
}
