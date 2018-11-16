package com.spring.kafka.TescoSpringWork.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListenerContainer;

@Configuration
@EnableKafka
public class ReceiverConfig {
	@Value("${kafka.bootstrapAddress}")
	private String bootstrapServer; 
	
	
	@Bean
	public Map<String, Object> consumerConfig(){
		Map<String, Object> properties = new HashMap<String, Object>();
		
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapServer); 
		
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
				StringDeserializer.class);
		
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
				StringDeserializer.class); 
		
		properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "mySprGrp"); 
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "mySprGrp"); 
		
		return properties; 
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfig()); 
	}

	@Bean
	public Reciver reciver() {
		return new Reciver(); 
	}
	
	@Bean
	public KafkaListenerContainerFactory
		<ConcurrentMessageListenerContainer<String, String>> 
			kafkaListenerContainerFactory(){

		ConcurrentKafkaListenerContainerFactory<String, String> factory = 
					new ConcurrentKafkaListenerContainerFactory<>(); 

		factory.setConsumerFactory(consumerFactory());
		
			return factory; 
	}
}










