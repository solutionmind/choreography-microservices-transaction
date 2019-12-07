package com.techrocking.shipping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techrocking.shipping.kafka.source.GoodsShippedEventSource;

@Service
public class ShippingService {
	
	@Autowired
	private GoodsShippedEventSource goodsShippedEventSource;
	
	public void processShippment(Long orderId) {
		
		goodsShippedEventSource.publishGoodsShippedEvent(orderId);

	}

}
