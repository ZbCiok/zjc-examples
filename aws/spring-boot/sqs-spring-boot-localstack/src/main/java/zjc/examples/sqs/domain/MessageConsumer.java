package zjc.examples.sqs.domain;

public class MessageConsumer {

    private Message lastReceivedMessage = new Message("empty");

    public Message getLastReceivedMessage() {
        return lastReceivedMessage;
    }

    public void setLastReceivedMessage(Message lastReceivedMessage) {
        this.lastReceivedMessage = lastReceivedMessage;
    }
}