package com.techrocking.inventory.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techrocking.inventory.kafka.channel.InventoryChannel;
import com.techrocking.inventory.kafka.message.PaymentEvent;
import com.techrocking.inventory.service.InventoryService;

@Component
public class PaymentFailedEventListener {

	@Autowired
	private InventoryService inventoryService;

	private static final Logger logger = LoggerFactory.getLogger(PaymentFailedEventListener.class);

	@StreamListener(InventoryChannel.INPUT_PAYMENT)
	public void listenPaymentFailed(@Payload PaymentEvent paymentFailedMessage) {
		if (paymentFailedMessage.getAction().equals(PaymentEvent.PaymentAction.PAYMENTNOTRECEIVED)) {
			logger.info("Received an PaymentFailedEvent for order id: " + paymentFailedMessage.getOrderId());

			if (paymentFailedMessage.getOrderId() != null) {
				logger.info("Going compensate item for order id : " + paymentFailedMessage.getOrderId());
				inventoryService.compensateItem(paymentFailedMessage.getOrderId());
			}
		}

	}

}
