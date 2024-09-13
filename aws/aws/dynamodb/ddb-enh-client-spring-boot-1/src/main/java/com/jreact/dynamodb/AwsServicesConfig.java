package com.jreact.dynamodb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class AwsServicesConfig {

    Region region = Region.US_EAST_1;
    String accessKeyId = "test";
    String secretAccessKey = "test";

    @Bean
    public DynamoDbClient dynamoDbClient(AwsCredentialsProvider awsCredentialsProvider) {
        return DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(awsCredentialsProvider)
                //.endpointOverride(URI.create("http://localhost:4566"))
                .endpointOverride(URI.create("https://localhost.localstack.cloud:4566"))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    public AwsCredentialsProvider awsCredentialsProvider() {
        AwsCredentials awsCredentials = AwsBasicCredentials.create(
                accessKeyId,
                secretAccessKey
        );
        return StaticCredentialsProvider.create(awsCredentials);
    }

}