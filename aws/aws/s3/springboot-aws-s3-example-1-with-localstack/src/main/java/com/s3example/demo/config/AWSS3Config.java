package com.s3example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

@Configuration
public class AWSS3Config {
    Logger logger = LoggerFactory.getLogger(AWSS3Config.class);

    @Autowired
    private Environment env;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .overrideConfiguration(ClientOverrideConfiguration.builder().build())
                .credentialsProvider(getCredentialsProvider())
                .endpointOverride(URI.create(env.getProperty("aws.serviceEndpoint", "")))
                .region(Region.of(env.getProperty("aws.signingRegion", "")))
                .forcePathStyle(true)
                .build();
    }

    private StaticCredentialsProvider getCredentialsProvider() {
        var credentials = StaticCredentialsProvider.create(AwsBasicCredentials.create(
                env.getProperty("aws.accessKey", ""),
                env.getProperty("aws.secretKey", ""))
        );

        logger.info(">>>>>>>>> accessKey " + credentials.resolveCredentials().accessKeyId());
        logger.info(">>>>>>>>> secretKey " + credentials.resolveCredentials().secretAccessKey());

        return credentials;
    }
}
