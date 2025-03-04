package com.challenge.ambevtech.order.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderDto(UUID id,
                       String name,
                       Integer quantity,
                       BigDecimal price) {
}
