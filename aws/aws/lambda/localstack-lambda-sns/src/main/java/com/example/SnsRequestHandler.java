package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;

import java.util.List;
import com.amazonaws.services.lambda.runtime.events.SNSEvent.SNSRecord;
import java.util.Iterator;
import java.util.List;

public class SnsRequestHandler implements RequestHandler<SNSEvent, Boolean> {
    LambdaLogger logger;

    @Override
    public Boolean handleRequest(SNSEvent event, Context context) {
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
            logger.log("message: " + message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


//public class SnsRequestHandler implements RequestHandler<SNSEvent, List<String>> {
//
//    @Override
//    public List<String> handleRequest(SNSEvent event, Context context) {
//        final LambdaLogger logger = context.getLogger();
//        final List<String> messages = event.getRecords().stream()
//                .map(SNSEvent.SNSRecord::getSNS)
//                .map(SNSEvent.SNS::getMessage)
//                .toList();
//        messages.forEach(System.out::println);
//        return messages;
//    }
//}


//public class SnsRequestHandler {
//
//    public String handleSNSEvent(SNSEvent event, Context context) {
////        LambdaLogger logger = context.getLogger();
////        for (SNSEvent.SNSRecord record : event.getRecords()) {
////            SNSEvent.SNS sns = record.getSNS();
////            logger.log("handleSNSEvent received SNS message " + sns.getMessage());
////        }
//        return ">>>>>>>>>>>>>>>>>>>>> handleSNSEvent finished";
//    }
//
//}
