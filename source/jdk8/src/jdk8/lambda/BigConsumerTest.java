package jdk8.lambda;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class BigConsumerTest {

	public static void main(String[] args) throws Exception {

		HashMap<String, Integer> map = new HashMap<String, Integer>();

		map.put("Java", 8);
		map.put("Csharp", 5);

		BiConsumer<String, Integer> bic = new BiConsumer<String, Integer>() {
			public void accept(String t, Integer u) {
				System.out.println("t : " + t + ", u : " + u);
			}
		};

		BiConsumer<String, Integer> bic2 = (t, u) -> System.out.println("\t >> t : " + t + ", u : " + u);

		BiConsumer<String, Integer> bic3 = bic.andThen(bic2);

		map.forEach(bic3);
	}
}
