package jdk8.lambda;

import java.util.function.Consumer;

/*
 *  javap -p .\jdk8\ConsumerTest2.class
Compiled from "ConsumerTest2.java"

public class jdk8.ConsumerTest2 {
  public jdk8.ConsumerTest2();
  public static void main(java.lang.String[]);
  private static void lambda$0(java.lang.String);
}
 */
public class ConsumerTest2 {

	public static void main(String[] args) {

		Consumer<String> consumer = s -> System.out.println(s);

		System.out.println(consumer);

		consumer.accept("Jeremy");
		consumer.accept("Paul");
		consumer.accept("Richard");
	}

	/*
	// ---------------------------
	Java Virtual Machine Launcher
	---------------------------
	Error: A JNI error has occurred, please check your installation and try again
	---------------------------
	确定   
	---------------------------
	*/
	// Exception in thread "main" java.lang.ClassFormatError: Duplicate method name
	// "lambda$0" with signature "(Ljava.lang.String;)V" in class file
	// jdk8/ConsumerTest2
	public static void lambda$0(String s) {
	}

}