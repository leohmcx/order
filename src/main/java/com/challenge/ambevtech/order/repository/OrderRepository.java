package com.challenge.ambevtech.order.repository;

import com.challenge.ambevtech.order.domain.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}
