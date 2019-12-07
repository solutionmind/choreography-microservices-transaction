package com.techrocking.order.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.techrocking.order.kafka.channel.OrderChannel;

@EnableBinding(OrderChannel.class)
public class StreamsConfig {

}
