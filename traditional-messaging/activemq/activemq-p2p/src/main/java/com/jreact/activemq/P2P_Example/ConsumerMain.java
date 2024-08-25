package com.jreact.activemq.P2P_Example;

public class ConsumerMain {

	private static final String QUEUE_NAME = "MyFirstActiveMQ";

	public static void main(String[] args) throws Exception {
		SimpleQueue queue = new SimpleQueue(QUEUE_NAME);
		queue.receive();
		queue.close();
	}

}
