package zjc.examples.sqs.domain;

public class MessageProducer {

    private final MessageSender messageSender;

    public MessageProducer(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String text) {
        Message message = new Message(text);
        messageSender.send(message);
    }
}