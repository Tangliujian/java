package jdk8.lambda;

import java.lang.invoke.LambdaMetafactory;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

class MyClass {
	static void dosomething() {
		System.out.println("dosomething");
	}
}

public class ConsumerDemo3 {

	public static void main(String[] args) {
		// new Thread(System.out::println).start();
		new Thread(MyClass::dosomething).start();
		// new Thread((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V,
		// dosomething(), ()V)((MyClass)cls)).start();

		int[] nums = { 3, 45, 66, 33, 0, -4 };

		int min = Integer.MAX_VALUE;
		for (int i : nums) {
			if (i < min) {
				min = i;
			}
		}

		System.out.println(min);

		OptionalInt min2 = IntStream.of(nums).min();
		System.out.println(min2.getAsInt());
	}

}
