package com.challenge.ambevtech.order.mapper;

import com.challenge.ambevtech.kafka.OrderEvent;
import com.challenge.ambevtech.order.domain.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEventMapper {

    OrderEvent toEntity(OrderDto order);
}
