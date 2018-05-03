package jdk8.lambda;

import java.util.function.Function;
import java.util.function.Predicate;

import beans.Gender;
import beans.Person;

public class PredicateTest {
	public static void main(String[] args) {

		Predicate<Person> predicate = p -> p.getAge() < 18;

		System.out.println(predicate);
		
		Person person = new Person("xwjie", 33, Gender.MALE);
		System.out.println(predicate.test(person));
		
		Function<Person, Boolean> funtion =  p -> p.getAge() < 18;
		System.err.println(funtion);
	}
}
