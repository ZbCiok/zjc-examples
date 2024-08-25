package zjc.examples.sqs.webaccess;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zjc.examples.sqs.domain.Message;
import zjc.examples.sqs.domain.MessageConsumer;
import zjc.examples.sqs.domain.MessageProducer;

@RestController
public class MessageController {

    private final MessageConsumer messageConsumer;
    private final MessageProducer messageProducer;

    public MessageController(MessageConsumer messageConsumer, MessageProducer messageProducer) {
        this.messageConsumer = messageConsumer;
        this.messageProducer = messageProducer;
    }

    @GetMapping("/last-message")
    Message getLastMessage() {
        return messageConsumer.getLastReceivedMessage();
    }

    @PostMapping("/send-message/{text}")
    String sendMessage(@PathVariable String text) {
        messageProducer.sendMessage(text);
        return "message " + text + " sent";
    }
}