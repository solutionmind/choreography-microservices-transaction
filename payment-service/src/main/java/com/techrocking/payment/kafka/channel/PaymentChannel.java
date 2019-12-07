package com.techrocking.payment.kafka.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PaymentChannel {

	String INPUT = "payment-in";
	String OUTPUT = "payment-out";

	@Input(INPUT)
	SubscribableChannel inboundPayment();

	@Output(OUTPUT)
	MessageChannel outboundPayment();

}
