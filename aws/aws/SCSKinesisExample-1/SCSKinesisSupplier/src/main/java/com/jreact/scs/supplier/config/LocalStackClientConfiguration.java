package com.jreact.scs.supplier.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;

import lombok.RequiredArgsConstructor;

@Profile("local")
@Configuration
@RequiredArgsConstructor
public class LocalStackClientConfiguration {
	Logger logger = LoggerFactory.getLogger(LocalStackClientConfiguration.class);

	@Value("${cloud.aws.region.static}")
	private String region;

	private AWSCredentialsProvider getCredentialsProvider() {

		AWSCredentialsProvider credentialsProvider = new DefaultAWSCredentialsProviderChain();

		logger.info("getAWSAccessKeyId : " + credentialsProvider.getCredentials().getAWSAccessKeyId());
		logger.info("getAWSSecretKey   : " + credentialsProvider.getCredentials().getAWSSecretKey());
		return credentialsProvider;
	}

	@Bean
	public KinesisProducerConfiguration kinesisProducerConfiguration() {
		logger.info("###### KinesisProducerConfiguration ######");
		return new KinesisProducerConfiguration().setCredentialsProvider(getCredentialsProvider())
				.setKinesisEndpoint("localhost").setKinesisPort(4566)
				.setStsEndpoint("localhost").setStsPort(4566)
				.setCloudwatchEndpoint("localhost").setCloudwatchPort(4566)
				.setRegion(region).setVerifyCertificate(false);
	}

}