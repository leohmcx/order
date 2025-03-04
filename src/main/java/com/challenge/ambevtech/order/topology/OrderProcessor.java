package com.challenge.ambevtech.order.topology;

import com.challenge.ambevtech.kafka.OrderEvent;
import com.challenge.ambevtech.order.service.OrderService;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.math.BigInteger.ZERO;

@Component
@AllArgsConstructor
public class OrderProcessor {

    private static final String ORDER_TOPIC_INPUT = "order-topic-input";
    private static final String ORDER_TOPIC_OUTPUT = "order-topic-output";

    private OrderService orderService;

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KTable<String, OrderEvent> orders = streamsBuilder
                .table(ORDER_TOPIC_INPUT, Consumed.with(Serdes.String(), order()));
        //.filter((key, order) -> order.getPrice() > ZERO.intValue()
          //              && order.getQuantity() > ZERO.intValue());

        orders.toStream()
                .peek((key, order) -> orderService.run(order))
                .to(ORDER_TOPIC_OUTPUT, Produced.with(Serdes.String(), order()));
    }

    public Serde<OrderEvent> order() {
        return new SpecificAvroSerde<>();
    }
}
