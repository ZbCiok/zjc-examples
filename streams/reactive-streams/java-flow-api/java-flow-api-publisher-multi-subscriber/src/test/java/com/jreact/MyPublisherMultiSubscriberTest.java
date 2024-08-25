package com.jreact;

import org.junit.Test;

public class MyPublisherMultiSubscriberTest {

    MyPublisherMultiSubscriber mySubscriber = new MyPublisherMultiSubscriber("");

    @Test
    public void flowPublisherMultiSubscriber() {
        mySubscriber.flowPublisherMultiSubscriber();
    }
}
