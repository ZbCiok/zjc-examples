package com.github.kothapet.scs.controller;

import java.util.function.Consumer;

import com.github.kothapet.scs.ScsKinesisConsumerApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	Logger logger = LoggerFactory.getLogger(KinesisController.class);

	@Autowired
	StreamBridge streamBridge;
	
	@Bean
	Consumer<Message<IntegerEntity>> myConsumer() {
		return message -> {
			logger.info("Consumer received Header  : " + message.getHeaders());
			//Integer partKey = (Integer) message.getHeaders().get(KinesisHeaders);
			IntegerEntity payload = (IntegerEntity) message.getPayload();
			logger.info(" ###### Consumer ###### received payload : " + payload);
		};
	}

}
