package com.techrocking.shipping.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.techrocking.shipping.kafka.channel.ShippingChannel;
import com.techrocking.shipping.kafka.message.PaymentEvent;
import com.techrocking.shipping.service.ShippingService;

@Component
public class PaymentReceivedEventListener {

	@Autowired
	private ShippingService shippingService;

	private static final Logger logger = LoggerFactory.getLogger(PaymentReceivedEventListener.class);

	@StreamListener(ShippingChannel.INPUT)
	public void listenItemFetchedEvent(@Payload PaymentEvent paymentReceivedMessage) {
		
		if (PaymentEvent.PaymentAction.PAYMENTRECEIVED.equals(paymentReceivedMessage.getAction())) {
			logger.info("Received an PaymentReceivedEvent for order id: " + paymentReceivedMessage.getOrderId());

			if (paymentReceivedMessage.getOrderId() != null) {
				logger.info("Going to start shipment for order id : " + paymentReceivedMessage.getOrderId());
				shippingService.processShippment(paymentReceivedMessage.getOrderId());
			}
		}

	}

}
