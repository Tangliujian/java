package jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.collections4.MapUtils;

class Student {
	private String name;
	private int age;
	private Gender sex;
	private Grade grade;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Student(String name, int age, Gender sex, Grade grade) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.grade = grade;
	}
}

enum Grade {
	ONE, TWO, THREE, FOUR;
}

public class CoreStream {

	public static void main(String[] args) {
		List<Student> students = Arrays.asList(
		    new Student("小明", 10, Gender.FEMALE, Grade.ONE),
		    new Student("小明2", 8, Gender.MALE, Grade.TWO),
		    new Student("小明3", 23, Gender.FEMALE, Grade.TWO),
		    new Student("小明4", 12, Gender.MALE, Grade.ONE),
		    new Student("小明5", 33, Gender.FEMALE, Grade.ONE),
		    new Student("小明6", 3, Gender.MALE, Grade.ONE));

		//

		Object collect = IntStream.rangeClosed(1, 10).boxed().collect(
		    Collectors.groupingBy(i -> i > 5, Collectors.summingInt(i -> i)));

		MapUtils.verbosePrint(System.out, "顶顶顶", (Map<?, ?>) collect);

		IntStream.rangeClosed(1, 10).boxed()
		    .collect(Collectors.toMap((i) -> i, (i) -> i));

		Stream<String> stream = Stream.of("1", "443", "62", "143", "1343", "12", "3")
		    .peek(s -> System.out.println("peek:" + s)).filter(s -> {
			    System.out.println("filter: " + s);
			    return s.startsWith("1");
		    });
		stream.sorted((i1, i2) -> {
			System.err.println("排序:" + i1 + ", " + i2);
			return i1.compareTo(i2);
		}).peek(s -> System.out.println("peek2:" + s)).count();
	}

}
