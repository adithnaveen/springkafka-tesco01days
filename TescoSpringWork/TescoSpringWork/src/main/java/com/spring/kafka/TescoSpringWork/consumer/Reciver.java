package com.spring.kafka.TescoSpringWork.consumer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Reciver {
	private static final Logger LOGGER = LoggerFactory.getLogger(Reciver.class); 
	
	private CountDownLatch latch = new CountDownLatch(1); 
	
	public CountDownLatch getLatch() {
		return latch; 
	}
	
	@KafkaListener(topics="${message.topic}")
	public void receive(String payload) {
		LOGGER.info("Recived Message '{}'", payload);
		// when the value become zero(0) all the resources are released 
		// else awaits for payload 
		latch.countDown(); 
	}
	
	
}
