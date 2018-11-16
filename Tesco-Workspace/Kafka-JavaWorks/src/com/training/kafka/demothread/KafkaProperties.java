package com.training.kafka.demothread;

public interface KafkaProperties {
	String TOPIC1 = "topic1"; 
	String TOPIC2 = "tesco-topic";
	
	String KAFKA_SERVER_URL="localhost";
	String KAFKA_SERVER_PORT="9092";
	int PRODUCER_BUFFER_SIZE= 64*1024;
	int TIME_OUT=100000; 
	
	String CLIENT_ID="TescoGroup"; 
}
