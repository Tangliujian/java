package jdk8.stream;

import java.util.stream.IntStream;

public class StreamTest3 {

	public static void main(String[] args) {
		
		//A Stream将仅在有终端操作时被遍历，如 count()，collect() 或forEach()。否则，不会对Stream 执行任何操作。
		//在下面的示例中，没有将终端操作添加到Stream，因此 filter() 操作不会调用，并且不会产生输出，因为peek()不是终端操作。
		
		IntStream.range(1, 10).filter(a -> a % 2 == 0).peek(System.out::println);


	}

}
