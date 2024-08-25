package com.github.kothapet.scs.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClientBuilder;
import com.amazonaws.services.kinesis.producer.KinesisProducer;
import com.amazonaws.services.kinesis.producer.KinesisProducerConfiguration;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsync;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceAsyncClientBuilder;

import lombok.RequiredArgsConstructor;

@Profile("local")
@Configuration
@RequiredArgsConstructor
public class LocalStackClientConfiguration {

	@Value("${cloud.aws.region.static}")
	private String region;

	@Value("${spring.cloud.aws.sts.endpoint}")
	private String stsEndpoint;

	@Value("${spring.cloud.aws.kinesis.endpoint}")
	private String kinesisEndpoint;

	@Value("${spring.cloud.aws.cloudwatch.endpoint}")
	private String cloudWatchEndpoint;

	@Value("${spring.cloud.aws.dynamodb.endpoint}")
	private String dynamoDbEndpoint;

	private AWSCredentialsProvider getCredentialsProvider() {

		AWSCredentialsProvider credentialsProvider = new DefaultAWSCredentialsProviderChain();

		System.out.println("getAWSAccessKeyId : " + credentialsProvider.getCredentials().getAWSAccessKeyId());
		System.out.println("getAWSSecretKey   : " + credentialsProvider.getCredentials().getAWSSecretKey());
		return credentialsProvider;
	}

//	@Bean
//	public AWSSecurityTokenServiceAsync aWSSecurityTokenServiceAsync() {
//		System.out.println("**** AWSSecurityTokenServiceAsync ***");
//		return AWSSecurityTokenServiceAsyncClientBuilder.standard()
//				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(stsEndpoint, region)).build();
//	}

//	@Bean
//	public AmazonKinesisAsync amazonKinesisAsync() {
//		System.out.println("**** amazonKinesisAsync ***");
//		return AmazonKinesisAsyncClientBuilder.standard().withCredentials(getCredentialsProvider())
//				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(kinesisEndpoint, region)).build();
//	}

//	@Bean
//	public AmazonCloudWatchAsync amazonCloudWatchAsync() {
//		System.out.println("**** amazonCloudWatchAsync ***");
//		return AmazonCloudWatchAsyncClientBuilder.standard().withCredentials(getCredentialsProvider())
//				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(cloudWatchEndpoint, region))
//				.build();
//	}

	@Bean
	public KinesisProducerConfiguration kinesisProducerConfiguration() {
		System.out.println("**** KinesisProducerConfiguration ***");
		return new KinesisProducerConfiguration().setCredentialsProvider(getCredentialsProvider())
				.setKinesisEndpoint("localhost").setKinesisPort(4566)
				.setStsEndpoint("localhost").setStsPort(4566)
				.setCloudwatchEndpoint("localhost").setCloudwatchPort(4566)
				.setRegion(region).setVerifyCertificate(false);
	}

}