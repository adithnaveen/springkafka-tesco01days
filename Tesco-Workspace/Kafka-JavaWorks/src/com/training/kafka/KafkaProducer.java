package com.training.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducer {
	public static void main(String[] args) {
		Properties properties = new Properties(); 
		
		properties.setProperty("bootstrap.servers", "localhost:9092"); 
		properties.setProperty("key.serializer", StringSerializer.class.getName()); 
		properties.setProperty("value.serializer", StringSerializer.class.getName()); 
		properties.setProperty("acks", "1"); 
		properties.setProperty("retries", "2"); 
		
		Producer<String, String> producer = new 
				org.apache.kafka.clients.producer.KafkaProducer<String, String>
					(properties); 

		for(int i=0; i<100; i++) {
		ProducerRecord<String, String> record1 = 
			new ProducerRecord<String, String>
			("topic1", "Hello From Java " + i); 

		ProducerRecord<String, String> record2 = 
			new ProducerRecord<String, String>
			("topic1", "Hello From Java again " + i); 
		
		producer.send(record1); 
		producer.send(record2); 
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		producer.flush(); 
		producer.close(); 
		
		System.out.println("Data Sent... ");
		
		
	}
}













