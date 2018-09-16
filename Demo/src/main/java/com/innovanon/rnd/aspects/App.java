package com.innovanon.rnd.aspects;

import java.io.PrintStream;

import com.innovanon.rnd.ts.HelloWorld;

/**
 * Hello world!
 *
 */
public enum App implements Runnable {
	/**
	 * 
	 */
	INSTANCE(System.out, "hello", "world", false);

	/**
	 * 
	 */
	private PrintStream out;
	/**
	 * 
	 */
	private HelloWorld hw;

	/**
	 * 
	 * @param out
	 * @param hello
	 * @param world
	 * @param isProperNoun
	 */
	App(PrintStream out, String hello, String world, boolean isProperNoun) {
		this.out = out;
		this.hw = new HelloWorld(hello, world, isProperNoun);
	}

	/**
	 * 
	 */
	@Override
	public void run() {
		out.println(hw);
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		App.INSTANCE.run();
	}
}
