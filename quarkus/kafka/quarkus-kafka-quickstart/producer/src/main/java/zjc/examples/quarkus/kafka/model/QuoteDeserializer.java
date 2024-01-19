package zjc.examples.quarkus.kafka.model;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class QuoteDeserializer extends ObjectMapperDeserializer<Quote> {
    public QuoteDeserializer() {
        super(Quote.class);
    }
}
