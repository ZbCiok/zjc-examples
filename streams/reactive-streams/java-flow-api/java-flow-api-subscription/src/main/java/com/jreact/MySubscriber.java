package com.jreact;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class MySubscriber implements java.util.concurrent.Flow.Subscriber<String> {

    private Flow.Subscription subscription;

    public void flowSubscription() {
        System.out.printf("main (tid=%d)%n", Thread.currentThread().threadId());


        try (final var publisher = new SubmissionPublisher<String>()) {

            System.out.println("-- subscribe --");

            publisher.subscribe(new MySubscriber());

            TimeUnit.SECONDS.sleep(1);
            System.out.println("-- submit --");

            publisher.submit("abc");
            publisher.submit("123");
            publisher.submit("XYZ");

            TimeUnit.SECONDS.sleep(1);
            System.out.println("-- close --");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.printf("  onSubscribe (tid=%d)%n",
                Thread.currentThread().threadId());

        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        System.out.printf("  onNext (tid=%d) : %s%n",
                Thread.currentThread().threadId(), item);

        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.printf("  onError (tid=%d) : %s%n",
                Thread.currentThread().threadId(), throwable);
    }

    @Override
    public void onComplete() {
        System.out.printf("  onComplete (tid=%d)%n",
                Thread.currentThread().threadId());
    }
}
