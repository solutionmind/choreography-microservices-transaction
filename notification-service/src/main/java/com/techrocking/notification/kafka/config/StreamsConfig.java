package com.techrocking.notification.kafka.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.techrocking.notification.kafka.channel.NotificationChannel;

@EnableBinding(NotificationChannel.class)
public class StreamsConfig {

}
