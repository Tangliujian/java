package flow.demo3;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class LongSubscriber<T> implements Subscriber<T> {

	private Subscription subscription;

	@Override
	public void onComplete() {
		System.out.println("LongSubscriber.onComplete()");
	}

	@Override
	public void onError(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNext(T item) {
		// DEBUG this line
		System.out.println(item);

		this.subscription.request(1);
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		System.out.println("onSubscribe...");
		this.subscription = subscription;
		subscription.request(5);
	}

}
