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
	Consumer<Message<IntegerEntity>> myConsumer() {
		return message -> {
			System.out.println("Consumer received Header  : " + message.getHeaders());
			//Integer partKey = (Integer) message.getHeaders().get(KinesisHeaders);
			IntegerEntity payload = (IntegerEntity) message.getPayload();
			System.out.println(" ###### Consumer ###### received payload : " + payload);
		};
	}

}
