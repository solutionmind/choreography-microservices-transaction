package com.techrocking.payment.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techrocking.payment.kafka.channel.PaymentChannel;
import com.techrocking.payment.kafka.message.ItemEvent;
import com.techrocking.payment.service.PaymentService;

@Component
public class ItemFetchedEventListener {

	@Autowired
	private PaymentService paymentService;

	private static final Logger logger = LoggerFactory.getLogger(ItemFetchedEventListener.class);

	@StreamListener(PaymentChannel.INPUT)
	public void listenItemFetchedEvent(@Payload ItemEvent itemFetchedMessage) {

		if (ItemEvent.Action.ITEMFETCHED.equals(itemFetchedMessage.getAction())) {
			logger.info("Received an ItemFetchedEvent for ITEM id: " + itemFetchedMessage.getItemId());

			if (itemFetchedMessage.getOrderId() != null && itemFetchedMessage.getItemId() != null) {
				logger.info("Going to make a payment for order id : " + itemFetchedMessage.getOrderId());
				paymentService.makePayment(itemFetchedMessage.getOrderId());
			}
		}

	}

}
