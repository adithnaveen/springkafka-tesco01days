package com.training.kafka.demothread;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class TescoCallBack implements Callback{
	private long startTime; 
	private int key; 
	private String message; 
	
	public TescoCallBack(long startTime, int key, String message) {
		this.startTime = startTime; 
		this.key = key; 
		this.message = message; 
	}

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		long timeElapsed = System.currentTimeMillis() - this.startTime; 
		
		if(metadata != null) {
			System.out.println("Message Got :" + message +
					", Time Taken :" + timeElapsed + 
					", offSet : " + metadata.offset());
		}else {
			System.out.println(exception);
		}
	}
	
	
}










