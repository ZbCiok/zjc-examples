package com.jreact.jmsConsumer;

import org.apache.activemq.ActiveMQConnectionFactory;
import java.io.Console;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.MessageConsumer;
import javax.jms.TextMessage;
import javax.jms.*;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static org.apache.activemq.ActiveMQConnection.DEFAULT_BROKER_URL;

public class Consumer {

    public static void main(String[] args) throws JMSException {

		// Create and start connection
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
		Connection connection = connectionFactory.createConnection("admin", "admin");
		connection.start();

		// Create Session
		Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);

		// Create topic
		Topic topic = session.createTopic("MyTopic");

		MessageConsumer consumer = session.createConsumer(topic);

		Console c = System.console();

		String response;

		do {
			// Receive the message
			Message msg = consumer.receive();
			response = ((TextMessage) msg).getText();

			System.out.println("Received = "+response);

		} while (!response.equalsIgnoreCase("Quit"));

        connection.close();
        System.exit(1);
    }
}