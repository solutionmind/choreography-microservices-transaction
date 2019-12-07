package com.techrocking.inventory.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techrocking.inventory.kafka.channel.InventoryChannel;
import com.techrocking.inventory.kafka.message.OrderEvent;
import com.techrocking.inventory.service.InventoryService;

@Component
public class OrderPlacedEventListener {
	
	@Autowired
	private InventoryService inventoryService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(OrderPlacedEventListener.class);
	
	@StreamListener(target = InventoryChannel.INPUT_ORDER)
	public void listenPaymentReceived(@Payload OrderEvent orderEvent) {
		
		if(orderEvent.getAction().equals(OrderEvent.OrderAction.ORDERPLACED)) {
			logger.info("Received an OrderPlacedEvent for order id: " + orderEvent.getOrderId());
			logger.info("Going to fetch item for order id : " + orderEvent.getOrderId());
			inventoryService.fetchItem(orderEvent.getOrderId());
		}
	}

}
