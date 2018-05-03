package jdk8.stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class RunStream {

	public static void main(String[] args) {
		// String[] strs = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
		// "Sep", "Oct", "Nov", "Dec" };

		String[] strs = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
		    "Friday", "Saturday" };

		// String[] strs = { "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌",
		// "亥" };
		Random random = new Random();
		Stream<String> parallel = Stream.generate(() -> random.nextInt()).limit(500).map(i -> "" + i)
		    // 第1个无状态操作
		    .peek(s -> print("peek: " + s))
		    // 第2个无状态操作
		    .filter(s -> {
			    print("filter: " + s);
			    return s.startsWith("1");
		    })
		    // 有状态操作
		    .sorted((i1, i2) -> {
			    print("排序: " + i1 + ", " + i2);
			    return i1.compareTo(i2);
		    })
		    // 又一个无状态操作
		    .peek(s -> {
			    print("peek2: " + s);
		    }).parallel();
		parallel.count();
	}

	public static void print(String s) {
		System.out.println(Thread.currentThread().getName() + " > " + s);
		try {
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
