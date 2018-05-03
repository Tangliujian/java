package jdk8.lambda;

import java.util.function.Consumer;

public class ConsumerTest {

	public static void main(String[] args) {
		// 必须带<String>否则编译不过
		Consumer<String> consumer = ConsumerTest::printNames;

		// jdk8.ConsumerTest$$Lambda$1/135721597@6ce253f1
		System.out.println(consumer);

		print(ConsumerTest::printNames);

		consumer.accept("aaaaaaaaa");

		// error
		// System.out.println(ConsumerTest::printNames instanceof Object);
	}

	private static void printNames(String name) {
		System.out.println("printNames:" + name);
	}

	private static void print(Consumer<String> consumer) {
		System.out.println(consumer);
	}
}