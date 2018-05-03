package jdk8.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import beans.Gender;
import beans.Person;

public class StreamTest2 {

	public static void main(String[] args) {

		List<Person> persons = new ArrayList<>();

		persons.add(new Person("Albert", 80, Gender.MALE));
		persons.add(new Person("Ben", 15, Gender.MALE));
		persons.add(new Person("Charlote", 20, Gender.FEMALE));
		persons.add(new Person("Dean", 6, Gender.MALE));
		persons.add(new Person("Elaine", 17, Gender.FEMALE));

		Map<Gender, Long> map = persons.stream().collect(
		    Collectors.groupingBy(p -> p.getGender(), Collectors.counting()));
		System.out.println(map);
		System.out.println(map.getClass());

		TreeMap<Gender, Long> map2 = persons.stream().collect(Collectors
		    .groupingBy(p -> p.getGender(), TreeMap::new, Collectors.counting()));
		System.out.println(map2);
		System.out.println(map2.getClass());

		Map<Gender, Long> map3 = persons.stream().collect(
		    Collectors.groupingBy(p -> p.getGender(), Collectors.counting()));
		System.out.println(map3);
		System.out.println(map3.getClass());

		Long collect222 = persons.stream().collect(Collectors.counting());
		System.out.println(collect222);

		IntSummaryStatistics stats1 = persons.stream().mapToInt((x) -> x.getAge())
		    .summaryStatistics();

		System.out.println(stats1);

		IntSummaryStatistics stats2 = persons.stream()
		    .collect(Collectors.summarizingInt(o -> o.getAge()));

		System.out.println(stats2);

		// 求和
		Integer collect2 = persons.stream()
		    .collect(Collectors.summingInt(o -> o.getAge()));

		System.out.println(collect2);

		IntSummaryStatistics istats2 = persons.stream().collect(
		    () -> new IntSummaryStatistics(), (i, o) -> i.accept(o.getAge()),
		    (i1, i2) -> i1.combine(i2));

		System.out.println(istats2);

		IntSummaryStatistics istats = IntStream.of(51, 22, 50, 27, 35).collect(
		    IntSummaryStatistics::new, IntSummaryStatistics::accept,
		    IntSummaryStatistics::combine);

		System.out.println(istats);

		//
		Predicate<Person> predicate = p -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return p.getAge() > 15;
		};

		{
			long start1 = System.currentTimeMillis();

			printNames(persons, predicate);

			System.out.println("use time:" + (System.currentTimeMillis() - start1));
		}

		{
			long start1 = System.currentTimeMillis();

			parallelPrintNames(persons, predicate);

			System.out.println("use time:" + (System.currentTimeMillis() - start1));
		}

		// 收集
		{
			List<Person> collect = persons.stream().filter(predicate)
			    .collect(Collectors.toList());

			System.out.println(collect);
			System.out.println(collect.getClass()); // java.util.ArrayList
		}

		// 先并行在串行
		{
			List<String> studentNames = persons.stream().parallel()
			    .filter(p -> p.getAge() > 18)// 并发地执行过滤操作
			    .sequential().map(p -> p.getName())
			    .collect(Collectors.toCollection(ArrayList::new));

			System.out.println(studentNames);
		}

		// 分组
		{
			Map<Boolean, List<Person>> collect = persons.stream().collect(
			    Collectors.partitioningBy(p -> p.getGender() == Gender.MALE));

			System.out.println("分组partitioningBy：男：" + collect.get(true));
			System.out.println("分组partitioningBy：女：" + collect.get(false));
		}

		// 分組2
		{
			Map<Gender, List<Person>> collect = persons.stream()
			    .collect(Collectors.groupingBy(p -> p.getGender()));

			System.out.println(collect.getClass());

			System.out.println("分组groupingBy：男：" + collect.get(Gender.MALE));
			System.out.println("分组groupingBy：女：" + collect.get(Gender.FEMALE));
		}

		// 分组3
		{
			Map<Gender, Long> collect = persons.stream().collect(
			    Collectors.groupingBy(p -> p.getGender(), Collectors.counting()));

			System.out.println("分组groupingBy：男的个数：" + collect.get(Gender.MALE));
			System.out.println("分组groupingBy：女的个数：" + collect.get(Gender.FEMALE));
		}

		// 拼接字符串
		{
			String names = persons.stream().map(Person::getName)
			    .collect(Collectors.joining(",", "{", "}"));
			System.out.println(names);
		}

	}

	private static void printNames(List<Person> persons,
	    Predicate<Person> predicate) {
		// Intermediate operations are lazy。 其中filter和map方法是lazy的
		persons.stream().filter(predicate).map(p -> p.getName())
		    .forEach(name -> System.out.println(name));
	}

	// parallelStream在阻塞场景下优势更明显，其线程池个数默认为
	// Runtime.getRuntime().availableProcessors() - 1
	// 如果需修改则需设置-Djava.util.concurrent.ForkJoinPool.common.parallelism=8
	private static void parallelPrintNames(List<Person> persons,
	    Predicate<Person> predicate) {
		// Intermediate operations are lazy。 其中filter和map方法是lazy的
		persons.parallelStream().filter(predicate).map(p -> p.getName())
		    .forEach(name -> System.out.println(name));
	}

}
