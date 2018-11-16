package com.training.kafka.demothread;

public class Main {
	public static void main(String[] args) {
//		Producer producerThread = new Producer(true); 
//		producerThread.setName("Bengaluru");
//		producerThread.start(); 
//		
//		Producer producerThread1 = new Producer(true); 
//		producerThread1.setName("Delhi"); 
//		producerThread1.start();
		
		
		Consumer consumer = new Consumer(KafkaProperties.TOPIC1, false); 
		
		
	}
}
