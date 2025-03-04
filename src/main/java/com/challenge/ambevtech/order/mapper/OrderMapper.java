package com.challenge.ambevtech.order.mapper;

import com.challenge.ambevtech.kafka.OrderEvent;
import com.challenge.ambevtech.order.domain.dto.OrderDto;
import com.challenge.ambevtech.order.domain.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderEvent order);
    OrderEvent toEvent(OrderDto order);
}
