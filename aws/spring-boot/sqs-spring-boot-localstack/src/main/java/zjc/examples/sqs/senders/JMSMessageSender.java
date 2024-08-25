package zjc.examples.sqs.senders;

import org.springframework.jms.core.JmsTemplate;
import zjc.examples.sqs.domain.MessageSender;
import zjc.examples.sqs.domain.Message;

public class JMSMessageSender implements MessageSender {

    private final JmsTemplate template;
    private final String queueName;

    public JMSMessageSender(JmsTemplate template, String queueName) {
        this.template = template;
        this.queueName = queueName;
    }

    @Override
    public void send(Message message) {
        template.convertAndSend(queueName, message);
    }

}