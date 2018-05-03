package jdk8.lambda;

import java.util.function.Consumer;

public class Deep {

	public static void main(String[] args) {
		Consumer<String> consumer = s -> System.out.println(s);
		// Consumer<String> consumer = Deep::printStr;
		consumer.accept("输入的数据");
		System.out.println(consumer);
		
		Deep deep = new Deep();
		deep.some();
		deep.some();
	}
	public static void printStr(String str) {

	}
	Consumer<String> consumerLast;
	public void some() {
		Consumer<String> consumer = s -> {
			System.out.println(s + this.toString());
		};
		consumer.accept("输入的数据");
	}

}
