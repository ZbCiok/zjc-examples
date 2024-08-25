package com.github.kothapet.scs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class ScsKinesisConsumerApplication {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(ScsKinesisConsumerApplication.class);
		logger.info("ScsKinesisConsumerApplication Start");
		SpringApplication.run(ScsKinesisConsumerApplication.class, args);
	}

}
