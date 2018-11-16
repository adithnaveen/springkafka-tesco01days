package com.spring.kafka.TescoSpringWork.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Sender {
	private static final Logger logger = LoggerFactory.getLogger(Sender.class); 
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate; 
	
	public void send(String topic, String payload) {
		logger.info("Sending Message ='{}' to topic '{}'", payload, topic);
		kafkaTemplate.send(topic, payload); 
	}
	
}
