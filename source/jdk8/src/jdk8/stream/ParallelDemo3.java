package jdk8.stream;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ParallelDemo3 {

	public static void main(String[] args) throws InterruptedException {
//		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
//		    "5");

		IntStream.rangeClosed(1, 100).parallel().peek(ParallelDemo3::peek)
		    //.sequential()
				.sequential()
		    .max();
	}

	public static void peek(int i) {
		System.out.println(Thread.currentThread().getName() + " peek:" + i);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void peek2(int i) {
		System.out.println(Thread.currentThread().getName() + " peek2:" + i);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
