package com.jreact;

import org.junit.Test;

public class MySubscriberTest {

    MySubscriber mySubscriber = new MySubscriber();

    @Test
    public void flowPublisher() {
        mySubscriber.flowSubscriber();
    }
}
