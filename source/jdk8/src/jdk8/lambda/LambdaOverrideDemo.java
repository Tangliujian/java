package jdk8.lambda;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

interface MyInt {
	boolean test(int i);

	// boolean test2();
}

interface MyInt2 {
	boolean test2(int i);

	boolean test2();
}

public class LambdaOverrideDemo {

	public static void main(String[] args) {

		UnaryOperator<Integer> u = i -> i * 2;

		String name = "dddd";

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread:" + name);
			}
		}).start();

		LambdaOverrideDemo demo = new LambdaOverrideDemo();

		// Predicate<Integer> p = i -> i > 0;
		// demo.check(p, 1);

		demo.check(i -> i > 0, 1);
	}

	boolean check2(Predicate<Integer> predicate, int i) {
		printCls(predicate);
		return predicate.test(i);
	}

	boolean check(MyInt my, int i) {
		printCls(my);

		return my.test(i);
	}

	private void printCls(Object my) {
		System.out.println(my.getClass());
		Class<?>[] interfaces = my.getClass().getInterfaces();

		for (Class<?> class1 : interfaces) {
			System.out.println(class1);
		}
	}

	boolean check(MyInt2 my, int i) {
		return my.test2(i);
	}
}
