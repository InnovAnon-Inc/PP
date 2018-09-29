/**
 * 
 */
package com.innovanon.rnd.ri.consumers;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author gouldbergstein
 *
 */
public class ObjectInitializer implements Consumer<Object> {

	/**
	 * 
	 */
	private Consumer<Object> arrays;

	/**
	 * @param arrays
	 */
	public ObjectInitializer(Consumer<Object> arrays) {
		this.arrays = arrays;
	}

	/**
	 * 
	 * @param elements
	 */
	public ObjectInitializer(Function<Class<?>, Object> elements) {
		this(new ArrayInitializer(elements));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(Object t) {
		Class<?> tClass = t.getClass();
		if (tClass.isArray()) {
			arrays.accept(t);
			return;
		}

		throw new Error("unsupported object type");
	}
}
