package com.spring.kafka.TescoSpringWork;

public class Message {
	private String msg; 
	private String name;
	
	public Message() {}
	
	public Message(String msg, String name) {
		super();
		this.msg = msg;
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Message [msg=" + msg + ", name=" + name + "]";
	} 
	
	
	
}
