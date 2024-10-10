package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;
import java.text.ParseException;
import java.util.List;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;
import lombok.extern.java.Log;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.Iterator;

@Log
public class SnsRequestHandler implements RequestHandler<SNSEvent, Boolean> {
    
    LambdaLogger logger;
    private SNSEvent event;

    //----- DynamodDB

    private static final String ACCESS_KEY = "test";
    private static final String SECRET_KEY = "test";
    private static String TABLE_NAME = "person";
    private static AwsCredentialsProvider credentials = StaticCredentialsProvider.create(
            AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY));

    private static Region region = Region.US_EAST_1;

    private static DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
            .region(region)
            .credentialsProvider(credentials)
            .endpointOverride(URI.create("https://localhost.localstack.cloud:4566"))
            .build();

    // -----

    @Override
    public Boolean handleRequest(SNSEvent event, Context context) {
        log.info(">>> start handleRequest...");
        logger = context.getLogger();
        List<SNSRecord> records = event.getRecords();

        if (!records.isEmpty()) {
            Iterator<SNSRecord> recordsIter = records.iterator();
            while (recordsIter.hasNext()) {
                processRecord(recordsIter.next());
            }
        }
        return Boolean.TRUE;
    }

    public void processRecord(SNSRecord record) {
        try {
            String message = record.getSNS().getMessage();
            logger.log(">>> message: " + message);

            //--- message to DynamoDB
            ObjectMapper objectMapper = new ObjectMapper();
            Person person = objectMapper.readValue(message, Person.class);
            log.info("JSON to POJO >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + person.getFirstName());
            String personId = "000000001";
            person.setId(Integer.parseInt(personId));
            addEntryToDynamoDB(person);
            //---

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void addEntryToDynamoDB(Person person) throws ParseException {
        try {
            DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                    .dynamoDbClient(dynamoDbClient)
                    .build();

            // use the enhanced client to interact with the table
            DynamoDbTable<Person> table = enhancedClient.table(TABLE_NAME,
                    TableSchema.fromBean(Person.class));
            table.putItem(person);

            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Add Entry successfully");
        } catch (DynamoDbException exception) {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Add Entry an error occurred: " + exception.getMessage());
        }
    }
}

