package com.spring.kafka.TescoSpringWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.spring.kafka.TescoSpringWork.consumer.Reciver;
import com.spring.kafka.TescoSpringWork.producer.Sender;

@SpringBootApplication
public class TescoSpringWorkApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context = 
			SpringApplication.run(TescoSpringWorkApplication.class, args);
	
//	
//	Sender sender = context.getBean(Sender.class); 
//	
//	sender.send("topic1", "hello from Spring");
//	sender.send("topic1", new Message("From Message - Spring", "Sanjeev").toString());
//	
	
	
	Reciver reciver = context.getBean(Reciver.class); 
	
	reciver.receive("---- DUMMY  ----");
	
	}
}
