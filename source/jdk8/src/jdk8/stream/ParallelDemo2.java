package jdk8.stream;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ParallelDemo2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");

		
		Thread thread = new Thread(() -> {
			 IntStream.rangeClosed(1, 200).parallel()
			    .peek(ParallelDemo2::peek).count();
		});
		thread.setName("my thread 1");
		thread.start();

		Thread.sleep(100);

		Thread thread2 = new Thread(() -> {
			Callable<Long> task = () -> {
				long count = IntStream.rangeClosed(300, 500).parallel()
				    .peek(ParallelDemo2::peek2).count();

				return count;
			};

			try {
				task.call();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			ForkJoinPool forkJoinPool = new ForkJoinPool(4);
//			long count;
//			try {
//				count = forkJoinPool.submit(task).get();
//				System.out.println(count);
//			} catch (InterruptedException | ExecutionException e) {
//				e.printStackTrace();
//			}
		});
		thread2.setName("my thread 2");
		thread2.start();

		// Stream<SomeClass> stream =
		// list.parallelStream().map(this::veryLongProcessing);
		// Callable<List<Integer>> task = () -> stream.collect(toList());
		// ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		// List<SomeClass> newList = forkJoinPool.submit(task).get()
	}

	public static void peek(int i) {
		System.out.println(Thread.currentThread().getName() + " peek:" + i);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void peek2(int i) {
		System.out.println(Thread.currentThread().getName() + " peek:" + i);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
