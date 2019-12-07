package com.techrocking.shipping.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.techrocking.shipping.kafka.channel.ShippingChannel;

@EnableBinding(ShippingChannel.class)
public class StreamsConfig {

}
