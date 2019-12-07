package com.techrocking.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techrocking.order.payload.PlaceOrderRequest;
import com.techrocking.order.payload.PlaceOrderResponse;
import com.techrocking.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping()
	public PlaceOrderResponse placeOrder(@RequestBody PlaceOrderRequest request) {
		return orderService.createOrder(request);
	}

}
