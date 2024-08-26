package com.jreact.scs.supplier.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

import com.jreact.scs.supplier.model.IntegerEntity;
import com.google.common.base.Supplier;

@Configuration
@Controller
public class KinesisController {
	Logger logger = LoggerFactory.getLogger(KinesisController.class);
	
	@Autowired
	StreamBridge streamBridge;
	
	@Bean
	Supplier<Message<IntegerEntity>> mySupplier() {
		return() -> {
			Integer partKey =  (int) (Math.random() * 1000);
			IntegerEntity payload = new IntegerEntity(partKey);
			Message<IntegerEntity> msg = MessageBuilder.withPayload(payload)
												.setHeader("partitionKey", partKey)
												.build();
			logger.info("###### Supplier ###### creating payload " + msg);
			return msg;
		};
	}
}
