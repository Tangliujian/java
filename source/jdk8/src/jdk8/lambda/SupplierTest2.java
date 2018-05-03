package jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SupplierTest2 {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();

		names.add("David");
		names.add("Sam");
		names.add("Ben");

		names.stream().forEach((x) -> {
			System.out.println(x);
			printNames(() -> x);
		});

	}

	static void printNames(Supplier<String> arg) {
		System.out.println(arg);
		System.out.println(arg.get());
	}
}