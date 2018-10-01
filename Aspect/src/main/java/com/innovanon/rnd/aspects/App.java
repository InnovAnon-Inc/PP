package com.innovanon.rnd.aspects;

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
		//Objects.requireNonNull(args);
		if (args == null || args.length == 0)
			args = new String[] { "World" };
		Stream.of(args).map(s -> String.format("Hello, %s!", s)).forEach(System.out::println);
		//Stream.of(args).map(s -> new Greeting (s)).forEach(System.out::println);
		/*
		for (String arg: args) {
			System.out.println("arg="+arg);
			System.out.println(new Greeting(arg));
		}
		*/
	}
}
