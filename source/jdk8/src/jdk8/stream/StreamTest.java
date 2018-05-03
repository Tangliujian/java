package jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import beans.Gender;
import beans.Person;

public class StreamTest {

	public static void main(String[] args) {

		List<Person> persons = new ArrayList<>();

		persons.add(new Person("Albert", 80, Gender.MALE));
		persons.add(new Person("Ben", 15, Gender.MALE));
		persons.add(new Person("Charlote", 20, Gender.FEMALE));
		persons.add(new Person("Dean", 6, Gender.MALE));
		persons.add(new Person("Elaine", 17, Gender.FEMALE));
		
		Object collect = persons.stream().collect(Collectors.maxBy((p1, p2) -> p1.getAge() - p2.getAge()));

		System.out.println(collect);

		// How much code would you need to do the following without Lambdas?
		System.out.println("----------Printing Persons with age less than 18----------");
		printNames(persons, p -> p.getAge() < 18);

		System.out.println("\n--------Printing all Males-------------");
		printNames(persons, p -> ((Person) p).getGender() == Gender.MALE);

		System.out.println("\n---------Printing Persons with Names starting With C------------");
		printNames(persons, p -> ((Person) p).getName().startsWith("C"));

	}

	private static void printNames(List<Person> persons, Predicate<Person> predicate) {
		// Intermediate operations are lazy。 其中filter和map方法是lazy的
		persons.stream().filter(predicate).map(p -> p.getName()).forEach(name -> System.out.println(name));
	}

}
