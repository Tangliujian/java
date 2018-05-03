package jdk8.lazy;

import java.util.function.IntUnaryOperator;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * 自己尝试实现方法惰性求值
 * 
 * @author 晓风轻
 * @github https://github.com/xwjie/jdk8/blob/master/src/src/jdk8/lazy/LazyDemo2.java
 * @date 2018.3.8
 *
 */
class MyClass {

	private final int result;

	public MyClass(int t) {
		this.result = t;
	}

	public MyClass some(IntUnaryOperator f) {
		return new MyClass(f.applyAsInt(this.result));
	}

	public int value() {
		return this.result;
	}
}

/**
 * 带惰性求值的类，不调用终点方法value的，中间的方法都不会执行
 *
 */
class MyClassLazy {

	private final Supplier<Integer> resultSupplier;

	public MyClassLazy(int t) {
		this(() -> t);
	}

	public MyClassLazy(Supplier<Integer> t) {
		this.resultSupplier = t;
	}

	/**
	 * intermediate Method，中间方法
	 * 
	 * @param f
	 * @return
	 */
	public MyClassLazy some(IntUnaryOperator f) {
		return new MyClassLazy(() -> f.applyAsInt(this.resultSupplier.get()));
	}

	/**
	 * terminal Method 结束方法
	 * 
	 * @return
	 */
	public int value() {
		return this.resultSupplier.get();
	}
}

public class LazyDemo2 {

	public static void main(String[] args) {

		IntStream nums = IntStream.rangeClosed(1, 3);
		
		nums.map(LazyDemo2::doubleNumber);
		
		System.out
		    .println(nums.map(LazyDemo2::doubleNumber).sum());

		// map是中间函数，后面没有结束函数的时候不会调用
		IntStream.range(0, 2).map(LazyDemo2::doubleNumber);

		System.out.println("----------上面的日志不会打印------");

		// 最后的map函数是结束函数的时候，中间的日志才会打印
		IntStream.range(0, 2).map(LazyDemo2::doubleNumber).count();

		System.out.println("\t------------测试普通的方法--------");
		// 没有做惰性求值
		testClass();

		System.out.println("\n\t------------测试实现了惰性的方法--------");
		// 实现了惰性求值
		testLazyClass();
	}

	private static void testClass() {
		MyClass c = new MyClass(10);

		System.out.println("---------------------下面的日志我希望不需要打印的，但【实际上】打印了");

		// 没有调用终点方法，期待中间的日志不打印
		c.some(LazyDemo2::doubleNumber).some(LazyDemo2::doubleNumber);

		System.out.println("---------------------");

		// 执行中间方法
		int value = c.some(LazyDemo2::doubleNumber).some(LazyDemo2::doubleNumber)
		    .value();
		System.out.println("计算结果:" + value);
	}

	private static void testLazyClass() {
		MyClassLazy c = new MyClassLazy(10);

		System.out.println("---------------------下面的日志由于没有调用终点方法，【没有】打印");

		// 没有调用终点方法，期待中间的日志不打印
		c.some(LazyDemo2::doubleNumber).some(LazyDemo2::doubleNumber);

		System.out.println("---------------------");

		// 执行中间方法
		int value = c.some(LazyDemo2::doubleNumber).some(LazyDemo2::doubleNumber)
		    .value();
		System.out.println("计算结果:" + value);
	}

	static int doubleNumber(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("乘以2");
		return i * 2;
	}
}
