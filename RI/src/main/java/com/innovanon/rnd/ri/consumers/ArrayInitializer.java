/**
 * 
 */
package com.innovanon.rnd.ri.consumers;

import java.lang.reflect.Array;
import java.util.function.Function;

/**
 * @author gouldbergstein
 *
 */
public class ArrayInitializer implements Initializer<Object> {

	/**
	 * 
	 */
	private Function<Class<?>, Object> elements;

	/**
	 * @param elements
	 */
	public ArrayInitializer(Function<Class<?>, Object> elements) {
		this.elements = elements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(Object array) {
		assert test(array);
		Class<?> tClass = array.getClass();
		Class<?> eClass = tClass.getComponentType();
		//if (eClass.isPrimitive())
		int length = Array.getLength(array);
		for (int index = 0; index < length; index++) {
			Object value = elements.apply(eClass);
			Array.set(array, index, value);
		}
	}

	/* (non-Javadoc)
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Object t) {
		if (t == null) return false;
		Class<?>tClass=t.getClass();
		return tClass.isArray();
	}
}
