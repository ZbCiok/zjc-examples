package com.jreact;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class MySubscriber implements java.util.concurrent.Flow.Subscriber<String> {

    private Flow.Subscription subscription;

    public void flowSubscriptionCancel() {
        System.out.printf("main (tid=%d)%n", Thread.currentThread().threadId());


        try (final var publisher = new SubmissionPublisher<String>()) {


            System.out.println("-- subscribe --");

            final var subscriber = new MySubscriber();
            publisher.subscribe(subscriber);

            TimeUnit.SECONDS.sleep(1);

            System.out.println("-- submit (a1, a2) --");

            publisher.submit("a1");
            publisher.submit("a2");

            TimeUnit.SECONDS.sleep(1);

            System.out.println("-- request 3 --");
            subscriber.request(3);

            TimeUnit.SECONDS.sleep(1);

            System.out.println("-- cancel --");
            subscriber.cancel();

            TimeUnit.SECONDS.sleep(1);

            System.out.println("-- submit (a3, a4) --");

            publisher.submit("a3");
            publisher.submit("a4");

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

    void request(long n) {
        if (subscription != null) {
            subscription.request(n);
        }
    }

    void cancel() {
        if (subscription != null) {
            subscription.cancel();
        }
    }
}
