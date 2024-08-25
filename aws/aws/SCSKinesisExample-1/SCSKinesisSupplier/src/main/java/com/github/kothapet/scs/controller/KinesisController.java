package com.github.kothapet.scs.controller;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

import com.github.kothapet.scs.model.IntegerEntity;
import com.google.common.base.Supplier;

@Configuration
@Controller
public class KinesisController {
	
	@Autowired
	StreamBridge streamBridge;
	
	@Bean
	Supplier<Message<IntegerEntity>> mySupplier() {
		return() -> {
			Integer partKey =  (int) (Math.random() * 1000);
			IntegerEntity payload = new IntegerEntity(partKey);
			Message<IntegerEntity> msg11 = MessageBuilder.withPayload(payload)
												.setHeader("partitionKey", partKey)
												.build();
			System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSS mySupplier creating payload " + msg11);
			return msg11;
		};
	}
	
//	@Bean
//	Consumer<Message<IntegerEntity>> myConsumer() {
//		return message -> {
//			System.out.println("Consumer received Header  : " + message.getHeaders());
//			//Integer partKey = (Integer) message.getHeaders().get(KinesisHeaders);
//			IntegerEntity payload = (IntegerEntity) message.getPayload();
//			System.out.println("CCCCCCCCCCCCCCCCCCCCCCCC Consumer received payload : " + payload);
//		};
//	}

}
