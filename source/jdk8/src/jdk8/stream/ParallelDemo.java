package jdk8.stream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelDemo {

	public static void main(String[] args) {

		ArrayList<Integer> arrayList1 = IntStream.range(0, 10000).mapToObj(i -> i)
				.collect(Collectors.toCollection(ArrayList::new));

		LinkedList<Integer> listList1 = IntStream.range(0, 10000).mapToObj(i -> i)
				.collect(Collectors.toCollection(LinkedList::new));

		//parallesSumList(arrayList1);
		parallesSumList(listList1);
	}

	private static void parallesSumList(List<Integer> list) {
		long start = System.currentTimeMillis();
		int sum = list.stream().parallel().mapToInt(i -> i).sum();
		System.out.println(sum + ", use:" + (System.currentTimeMillis() - start));
	}

}
