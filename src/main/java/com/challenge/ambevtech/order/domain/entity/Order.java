package com.challenge.ambevtech.order.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order")
public class Order {

    @Id
    private UUID id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal total;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
}
