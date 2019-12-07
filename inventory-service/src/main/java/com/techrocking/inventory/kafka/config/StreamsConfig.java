package com.techrocking.inventory.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.techrocking.inventory.kafka.channel.InventoryChannel;

@EnableBinding(InventoryChannel.class)
public class StreamsConfig {

}
