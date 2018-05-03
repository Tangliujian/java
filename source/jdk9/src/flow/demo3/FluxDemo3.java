package flow.demo3;

import java.util.concurrent.SubmissionPublisher;
import java.util.stream.LongStream;

public class FluxDemo3 {

	public static void main(String[] args) {

		SubmissionPublisher<Long> publisher = new SubmissionPublisher<>();

		LongSubscriber<Long> longSubscriber = new LongSubscriber<>();

		publisher.subscribe(longSubscriber);

		// LongStream.range(Long.MIN_VALUE, Long.MAX_VALUE).forEach(publisher::submit);
		LongStream.range(Long.MIN_VALUE, Long.MAX_VALUE).parallel().forEach(item ->{
			System.out.println("publish item:" + item);
			publisher.submit(item);
		});

		publisher.close();
	}

}
