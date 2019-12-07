package com.techrocking.inventory.kafka.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface InventoryChannel {

	String INPUT_ORDER = "inventory-in-order";
	String INPUT_PAYMENT = "inventory-in-payment";
	String OUTPUT = "inventory-out";

	@Input(INPUT_ORDER)
	SubscribableChannel inboundInventoryFromOrder();
	
	@Input(INPUT_PAYMENT)
	SubscribableChannel inboundInventoryFromPayment();

	@Output(OUTPUT)
	MessageChannel outboundInventory();

}
