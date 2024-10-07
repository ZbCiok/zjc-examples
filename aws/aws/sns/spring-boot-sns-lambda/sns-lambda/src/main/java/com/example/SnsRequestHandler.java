package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

import java.util.List;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;
import lombok.extern.java.Log;

import java.util.Iterator;

@Log
public class SnsRequestHandler implements RequestHandler<SNSEvent, Boolean> {
    LambdaLogger logger;
    private SNSEvent event;

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

