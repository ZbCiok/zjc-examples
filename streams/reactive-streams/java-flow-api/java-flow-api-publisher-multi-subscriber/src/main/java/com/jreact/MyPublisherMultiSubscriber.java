package com.jreact;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class MyPublisherMultiSubscriber implements java.util.concurrent.Flow.Subscriber<String> {

    private Flow.Subscription subscription;

    public void flowPublisherMultiSubscriber() {
        System.out.printf("main (tid=%d)%n", Thread.currentThread().threadId());

        try (final var submission = new SubmissionPublisher<String>()) {

            System.out.println("-- subscribe --");

            final Flow.Publisher<String> publisher = submission;
            publisher.subscribe(new MyPublisherMultiSubscriber("A"));
            publisher.subscribe(new MyPublisherMultiSubscriber("B"));
            publisher.subscribe(new MyPublisherMultiSubscriber("C"));

            TimeUnit.SECONDS.sleep(1);
            System.out.println("-- submit --");

            submission.submit("abc");
            submission.submit("XYZ");

            TimeUnit.SECONDS.sleep(1);
            System.out.println("-- close --");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private final String name;

    MyPublisherMultiSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println(name + " : onSubscribe");
        subscription.request(2);
    }

    @Override
    public void onNext(String item) {
        System.out.println(name + " : onNext item = " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + " : onError");
    }

    @Override
    public void onComplete() {
        System.out.println(name + " : onComplete");
    }

    @Override
    public String toString() {
        return "name = " + name;
    }
}
