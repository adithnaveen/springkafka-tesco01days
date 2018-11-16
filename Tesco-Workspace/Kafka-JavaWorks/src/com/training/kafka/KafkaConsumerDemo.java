package com.training.kafka;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaConsumerDemo {
	public static void main(String[] args) {
		Properties properties = new Properties(); 
		
		properties.setProperty("key.deserializer", StringDeserializer.class.getName()); 
		properties.setProperty("value.deserializer", StringDeserializer.class.getName()); 
		properties.setProperty("bootstrap.servers", "localhost:9092"); 
		properties.setProperty("session.timeout.ms", "30000"); 
		properties.setProperty("group.id", "myGroup"); 
		
		KafkaConsumer<String, String> kafkaConsumer = new 
				KafkaConsumer<>(properties); 
		
		kafkaConsumer.subscribe(Arrays.asList("topic1")); 
		
		// we may have to keep listening if any new message has come 
		// but ensure you will have some delay for getting the data 
		
		while(true) {
			ConsumerRecords<String, String> consumerRecords = 
				kafkaConsumer.poll(1000); 
			
			System.out.println("Number of Messages Got :" + consumerRecords.count());
			
			for(ConsumerRecord<String, String> record : consumerRecords) {
				
				System.out.println("--------------------------------------------------");
				System.out.println("Offset Value : " + record.offset());
				System.out.println("Topic : " + record.topic());
				System.out.println("Timestamp : " + record.timestamp());
				System.out.println("Partition : " + record.partition());
				System.out.println("Key : " + record.key());
				System.out.println("Value : " + record.value());
			}
		}
		
	}
}













