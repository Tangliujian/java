package jdk8.lambda;

interface A {
	default void foo() {
		System.out.println("Calling A.foo()");
	}
}

interface B {
	default void foo() {
		System.out.println("Calling B.foo()");
	}
}

interface C extends A, B {

	// 不重新会报错
	@Override
	default void foo() {
		B.super.foo();
	}

	// 静态方法
	public static void testStaticMethod() {
		System.out.println("JDK8 Static Method!");
	}

}

class Class1 implements A, B {

	// java: class Clazz inherits unrelated defaults for foo() from types A and B

	// 2个接口有重复的时候，需要手工重写，否则报错
	// 必须显式覆盖，否则编译不通过
	@Override
	public void foo() {
		A.super.foo();
	}
}

public class DefenderMethodTest {

	public static void main(String[] args) {
		C.testStaticMethod();
	}

}
