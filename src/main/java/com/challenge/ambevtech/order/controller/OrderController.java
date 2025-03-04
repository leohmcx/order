package com.challenge.ambevtech.order.controller;

import com.challenge.ambevtech.order.domain.dto.OrderDto;
import com.challenge.ambevtech.order.service.OrderProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class OrderController {

    private OrderProducer orderProducer;

    @PostMapping("/orders")
    public void create(@RequestBody OrderDto orderDto) {
        orderProducer.produceOrder(orderDto)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Order sent to topic: {}", result.getProducerRecord().value());
                    } else {
                        log.error("Failed to send order", ex);
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex);
                    }
                });
    }
}