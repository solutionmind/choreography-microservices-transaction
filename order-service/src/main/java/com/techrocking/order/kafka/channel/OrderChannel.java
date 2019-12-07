package com.techrocking.order.kafka.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderChannel {

	String INPUT = "order-in";
	String OUTPUT = "order-out";

	@Input(INPUT)
	SubscribableChannel inboundOrder();

	@Output(OUTPUT)
	MessageChannel outboundOrder();

}
