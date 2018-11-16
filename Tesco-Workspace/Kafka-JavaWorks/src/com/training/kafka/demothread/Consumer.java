package com.training.kafka.demothread;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import kafka.utils.ShutdownableThread;

public class Consumer extends ShutdownableThread{

	private KafkaConsumer<Integer, String> consumer; 
	private String topic; 
	
	public Consumer(String name, boolean isInterruptible) {
		super(name, isInterruptible);
		
		Properties properties = new Properties(); 
			properties.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.
						BOOTSTRAP_SERVERS_CONFIG , 
					KafkaProperties.KAFKA_SERVER_URL +":" + 
					KafkaProperties.KAFKA_SERVER_PORT); 
			
			
			properties.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.
					GROUP_ID_CONFIG, KafkaProperties.CLIENT_ID);
			
			properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
					IntegerDeserializer.class.getName()); 
			properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
					StringDeserializer.class.getName());
			
			properties.setProperty(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "30000"); 
			
			this.topic = name; 
			
			consumer = new KafkaConsumer<>(properties);
	}

	@Override
	public void doWork() {
		consumer.subscribe(Collections.singleton(this.topic));
		ConsumerRecords<Integer, String> consumerRecords = consumer.poll(1000); 
		
		consumerRecords.forEach(record -> {
			System.out.println
				("Key : " + record.key() +
				", Value : " + record.value()+ 
				", Offset : " + record.offset() +
				", Partition : " + record.partition());
		});
	}
}







