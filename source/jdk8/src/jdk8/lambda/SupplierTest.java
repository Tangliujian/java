package jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import beans.Gender;
import beans.Person;

public class SupplierTest {

	public static void main(String[] args) {

		String str = "some";

		Supplier<String> supplier = () -> str;

		System.out.println(supplier);
		System.out.println(supplier.get());

		System.out.println("----------------");

		Supplier<String> supplier2 = new Supplier<String>() {

			@Override
			public String get() {
				return str;
			}
		};

		System.out.println(supplier2);

		Person p = new Person("name", 22, Gender.FEMALE);
		Supplier<Person> s = () -> p;
		System.out.println(s);
		System.out.println(s.get());
	}

}