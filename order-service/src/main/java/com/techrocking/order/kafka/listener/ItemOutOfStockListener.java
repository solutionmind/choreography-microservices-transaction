package com.techrocking.order.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techrocking.order.kafka.channel.OrderChannel;
import com.techrocking.order.kafka.message.ItemEvent;
import com.techrocking.order.service.OrderService;

@Component
public class ItemOutOfStockListener {

	@Autowired
	private OrderService orderService;

	private static final Logger logger = LoggerFactory.getLogger(ItemOutOfStockListener.class);

	@StreamListener(OrderChannel.INPUT)
	public void listenOutOfStockItem(@Payload ItemEvent itemEvent) {
		if (itemEvent.getAction().equals(ItemEvent.Action.ITEMOUTOFSTOCK)) {
			logger.info("ItemOutOfStock event received for item id: " + itemEvent.getItemId());

			logger.info("Going to compensate order for id " + itemEvent.getOrderId());
			orderService.compensateOrder(itemEvent.getOrderId());
		}
	}
}
