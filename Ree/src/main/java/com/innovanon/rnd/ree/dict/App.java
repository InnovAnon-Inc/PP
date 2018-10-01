package com.innovanon.rnd.ree.dict;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public enum App {
	/* no instances */ ;

	/**
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		Random random = new Random();
		Supplier<String> dict = new DictionarySupplier(random);
		long maxSize = 10;
		Stream.generate(dict).limit(maxSize).forEach(System.out::println);
	}
}
