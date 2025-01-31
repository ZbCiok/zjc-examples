package com.amqp.exchanges;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Selective message broadcast with routingkey filter.
 */
public class DirectExchange {

  //Declare the exchange
  public static void declareExchange() throws IOException, TimeoutException {
    Channel channel = ConnectionManager.getConnection().createChannel();
    //Declare my-direct-exchange DIRECT exchange
    channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT, true);
    channel.close();
  }

  //Declare the Queues
  public static void declareQueues() throws IOException, TimeoutException {
    //Create a channel - do not share the Channel instance
    Channel channel = ConnectionManager.getConnection().createChannel();

    //Create the Queues
    channel.queueDeclare("MobileQ", true, false, false, null);
    channel.queueDeclare("ACQ", true, false, false, null);
    channel.queueDeclare("LightQ", true, false, false, null);

    channel.close();
  }

  //Create the Bindings
  public static void declareBindings() throws IOException, TimeoutException {
    Channel channel = ConnectionManager.getConnection().createChannel();
    //Create bindings - (queue, exchange, routingKey)
    channel.queueBind("MobileQ", "my-direct-exchange", "personalDevice");
    channel.queueBind("ACQ", "my-direct-exchange", "homeAppliance");
    channel.queueBind("LightQ", "my-direct-exchange", "homeAppliance");
    channel.close();
  }

  //Create the Subscribers
  public static void subscribeMessage() throws IOException {
    Channel channel = ConnectionManager.getConnection().createChannel();
    channel.basicConsume("LightQ", true, ((consumerTag, message) -> {
      System.out.println(consumerTag);
      System.out.println("LightQ:" + new String(message.getBody()));
    }), consumerTag -> {
      System.out.println(consumerTag);
    });

    channel.basicConsume("ACQ", true, ((consumerTag, message) -> {
      System.out.println(consumerTag);
      System.out.println("ACQ:" + new String(message.getBody()));
    }), consumerTag -> {
      System.out.println(consumerTag);
    });

    channel.basicConsume("MobileQ", true, ((consumerTag, message) -> {
      System.out.println(consumerTag);
      System.out.println("MobileQ:" + new String(message.getBody()));
    }), consumerTag -> {
      System.out.println(consumerTag);
    });
  }

  //Publish the messages
  public static void publishMessage() throws IOException, TimeoutException {
    Channel channel = ConnectionManager.getConnection().createChannel();
    String message = "Direct message - Turn on the Home Appliances ";
    channel.basicPublish("my-direct-exchange", "homeAppliance", null, message.getBytes());
    channel.close();
  }

  public static void main(String[] args) throws IOException, TimeoutException {
    DirectExchange.declareQueues();
    DirectExchange.declareExchange();
    DirectExchange.declareBindings();

    //Threads created to publish-subscribe asynchronously
    Thread subscribe = new Thread(){
      @Override
      public void run() {
        try {
          DirectExchange.subscribeMessage();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    };

    Thread publish = new Thread(){
      @Override
      public void run() {
        try {
          DirectExchange.publishMessage();
        } catch (IOException | TimeoutException e) {
          e.printStackTrace();
        }
      }
    };

    subscribe.start();
    publish.start();
  }
}
