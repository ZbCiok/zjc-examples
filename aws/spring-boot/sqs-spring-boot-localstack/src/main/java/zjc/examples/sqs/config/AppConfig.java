package zjc.examples.sqs.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import zjc.examples.sqs.domain.MessageConsumer;
import zjc.examples.sqs.domain.MessageProducer;
import zjc.examples.sqs.receivers.JMSMessageListener;
import zjc.examples.sqs.senders.JMSMessageSender;

import javax.jms.Session;

@Configuration
@EnableJms
public class AppConfig {

    @Value("${outcoming.queue.name}")
    private String outcomingQueueName;

    @Bean
    public SQSConnectionFactory connectionFactory() {
        AmazonSQSAsync build = AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("xxx", "yyy")))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
                .build();

        return new SQSConnectionFactory(new ProviderConfiguration(), build);
    }

    @Bean
    public MessageConverter mappingJackson2MessageConverter() {
        MappingJackson2MessageConverter jackson2MessageConverter = new MappingJackson2MessageConverter();
        jackson2MessageConverter.setTargetType(MessageType.TEXT);
        jackson2MessageConverter.setTypeIdPropertyName("_type");
        return jackson2MessageConverter;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(SQSConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(SQSConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setMessageConverter(mappingJackson2MessageConverter());
        return jmsTemplate;
    }

    @Bean
    public MessageProducer messageProducer(JmsTemplate jmsTemplate) {
        return new MessageProducer(new JMSMessageSender(jmsTemplate, outcomingQueueName));
    }

    @Bean
    public MessageConsumer messageConsumer() {
        return new MessageConsumer();
    }

    @Bean
    public JMSMessageListener jmsMessageListener() {
        return new JMSMessageListener(messageConsumer());
    }
}