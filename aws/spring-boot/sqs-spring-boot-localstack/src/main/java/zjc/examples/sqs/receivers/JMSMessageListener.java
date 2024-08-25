package zjc.examples.sqs.receivers;

import org.springframework.jms.annotation.JmsListener;
import zjc.examples.sqs.domain.MessageConsumer;
import zjc.examples.sqs.domain.Message;

public class JMSMessageListener {

    private final MessageConsumer messageConsumer;

    public JMSMessageListener(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @JmsListener(destination = "${incoming.queue.name}")
    void receiveMessage(Message message) {
        messageConsumer.setLastReceivedMessage(message);
    }
}