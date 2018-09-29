package com.innovanon.rnd.ri;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Supplier;

import com.innovanon.rnd.rand.Randumb;
import com.innovanon.rnd.rand.random.RandomRandumb;
import com.innovanon.rnd.ri.consumers.ObjectInitializer;
import com.innovanon.rnd.ri.functions.ObjectInstantiator;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Randumb random = new RandomRandumb();
		ObjectInstantiator objects = new ObjectInstantiator(random);
		// TODO
		Supplier<Class<?>> classes = new Supplier<Class<?>>() {

			@Override
			public Class<?> get() {
				int bound = 2;
				switch (random.nextInt(bound)) {
				case 0:
					return int[].class;
				case 1:
					return double[].class;
				}
				throw new Error("oops");
			}

		};
		ObjectInitializer initializer = new ObjectInitializer(objects);
		for (int k = 0; k <= 10; k++) {
			Object object = objects.apply(classes.get());
			System.out.println(Array.getLength(object));
			initializer.accept(object);
			Class<?> type = object.getClass();
			if (type.equals(int[].class))
				System.out.println(Arrays.toString((int[]) object));
			if (type.equals(double[].class))
				System.out.println(Arrays.toString((double[]) object));
		}
		System.out.println("Hello World!");
	}
}
