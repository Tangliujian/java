package jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class StreamCore {

	public static void main(String[] args) {
		Stream<Integer> filter =
		    // Stream.of("111", "222", "222", "333", "555")
		    Stream.generate(() -> new Random().nextInt() + "").limit(100).parallel()
		        .map(i -> {
			        System.out
			            .println(Thread.currentThread().getName() + " 转换为数字:" + i);
			        return Integer.parseInt(i);
		        }).distinct().filter(i -> {
			        // Thread.dumpStack();
			        System.out.println(Thread.currentThread().getName() + "过滤:" + i);
			        return i < 1000;
		        }).sorted((i1, i2) -> {
			        System.out.println(Thread.currentThread().getName() + "排序");
			        return i1 - i2;
		        });

		System.out.println(Stream.of("111", "222", "222333"));
		System.out.println(filter);

		long count2 = filter.count();

		System.out.println(count2);

	}

}
