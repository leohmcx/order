package com.challenge.ambevtech.order.mapper;

import com.challenge.ambevtech.kafka.OrderEvent;
import com.challenge.ambevtech.order.domain.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Order toEntity(OrderEvent order);
}
