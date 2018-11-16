package com.spring.kafka.TescoSpringWork.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class SenderConfig {

	// 
	@Value("${kafka.bootstrapAddress}")
	private String bootstrapServer; 
	
	@Bean
	public Map<String, Object> producerConfig(){
		
		Map<String, Object> properties = new HashMap<String, Object>(); 
		// all configuration properties go here 
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer); 
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
					StringSerializer.class.getName()); 
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
					StringSerializer.class.getName());
		return properties; 
	}
	
	@Bean
	public ProducerFactory<String, String> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfig()); 
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory()); 
	}
	
	@Bean
	public Sender sender() {
		return new Sender(); 
	}
	
}










