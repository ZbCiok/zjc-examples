package com.jreact.scs.kinesis.controller;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import com.jreact.scs.kinesis.model.IntegerEntity;

@Configuration
@Controller
public class KinesisController {
	Logger logger = LoggerFactory.getLogger(KinesisController.class);

	@Autowired
	StreamBridge streamBridge;
	
	@Bean
	Consumer<Message<IntegerEntity>> myConsumer() {
		return message -> {
			logger.info(" ###### Consumer ###### received Header  : " + message.getHeaders());
			//Integer partKey = (Integer) message.getHeaders().get(KinesisHeaders);
			IntegerEntity payload = (IntegerEntity) message.getPayload();
			logger.info(" ###### Consumer ###### received payload : " + payload);
		};
	}

}
