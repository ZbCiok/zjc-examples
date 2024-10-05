package com.jreact.sns;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishResult;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Log
@SpringBootApplication
@RequiredArgsConstructor
public class SNSApp implements ApplicationRunner {
    private final AmazonSNS amazonSNS = AmazonSNSClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
            .build();

    @Value("${sns.topic.arn}")
    private String snsTopicARN;

    public static void main(String[] args) throws ParseException {
        SpringApplication.run(SNSApp.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<PersonRequest> personRequests = new ArrayList<>();

        PersonRequest personRequest = new PersonRequest();
        personRequest.setFirstName("John");
        personRequest.setLastName("Doe");
        personRequest.setAge(26);
        personRequest.setAddress("Address01");
        personRequests.add(personRequest);

        personRequest = new PersonRequest();
        personRequest.setFirstName("Jack");
        personRequest.setLastName("Smith");
        personRequest.setAge(30);
        personRequest.setAddress("Address02");
        personRequests.add(personRequest);

        for (PersonRequest personRequest2 : personRequests) {
            publishSNSMessage(new Gson().toJson(personRequest2));
        }
    }

    public void publishSNSMessage(String message) {
        log.info("snsTopicARN: " + snsTopicARN);

        log.info("Publishing SNS message: " + message);
        PublishResult result = this.amazonSNS.publish(this.snsTopicARN, message);
        log.info("SNS Message ID: " + result.getMessageId());
    }
}
