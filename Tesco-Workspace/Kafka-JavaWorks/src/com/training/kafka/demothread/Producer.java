package com.training.kafka.demothread;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer extends Thread{
	
	private KafkaProducer<Integer, String> producer; 
	private boolean isAsync; 
	
	public Producer(boolean isAsync) {
		Properties properties = new Properties(); 
		
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , 
				KafkaProperties.KAFKA_SERVER_URL +":" + 
				KafkaProperties.KAFKA_SERVER_PORT); 
		
		properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, 
				KafkaProperties.CLIENT_ID); 
		
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, 
				IntegerSerializer.class.getName());
		
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
				StringSerializer.class.getName());
		
		producer = new KafkaProducer<Integer, String>(properties); 
		
		this.isAsync = isAsync;
	}
	
	
	
	public void run() {
		int messageId =1; 
		
		while(true) {
			sleep();
			
			String message = Thread.currentThread().getName() 
					+" - Message : " + messageId; 
			long currentMillis = System.currentTimeMillis(); 
			
			if(this.isAsync) {
				// Asynchronously 
				ProducerRecord<Integer, String> producerRecord = 
						new ProducerRecord<Integer, String>(KafkaProperties.TOPIC1, 
								messageId, message); 
				
				this.producer.send(producerRecord, 
						new TescoCallBack(currentMillis, messageId, message)); 
			}else {
				// synchronously 
				try {
					ProducerRecord<Integer, String> producerRecord = 
							new ProducerRecord<Integer, String>
							(KafkaProperties.TOPIC1, 
									messageId, message); 
					
					producer.send(producerRecord).get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				} 
			}
			messageId ++; 
			
		}
	}



	private void sleep() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}












