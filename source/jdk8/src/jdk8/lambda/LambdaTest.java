package jdk8.lambda;

import java.util.Arrays;
import java.util.List;

public class LambdaTest {

	private static void print(String str) {
		System.out.println(str);
	}

	public static void main(String[] args) {
		List<String> list = Arrays.asList("aa", "bb", "cc");

		list.stream().forEach(s -> print(s));
		list.stream().forEach(s -> System.out.println(s));
		list.stream().forEach(LambdaTest::print);
		
		
	}

}
