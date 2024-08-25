package com.jreact.jmsPublisher;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;

import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import java.util.Scanner;
import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static org.apache.activemq.ActiveMQConnection.DEFAULT_BROKER_URL;


public class Producer {

    public static void main(String[] args) throws Exception {

        // Create and start connection
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
        Connection connection = connectionFactory.createConnection("admin", "admin");
        connection.start();

        // Create Session
        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);

        // Create topic
        Topic topic = session.createTopic("MyTopic");

        MessageProducer producer = session.createProducer(topic);

         /* This section of code simply reads input from the console and then sends that
         * input as JMS Message to the ActiveMQ broker.
         */
        Scanner input = new Scanner(System.in);
        String response;

        do {
            System.out.println("Enter message: ");
            response = input.nextLine();

            // Create a message object
            TextMessage msg = session.createTextMessage(response);

            // Send the message to the queue
            producer.send(msg);

        } while (!response.equalsIgnoreCase("Quit"));
        input.close();

        connection.close();
        System.exit(1);
    }
}