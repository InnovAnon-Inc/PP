/**
 * 
 */
package com.innovanon.rnd.ri.consumers;

import java.lang.reflect.Array;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author gouldbergstein
 *
 */
public class ArrayInitializer implements Consumer<Object> {

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
		Class<?> tClass = array.getClass();
		assert tClass.isArray();
		Class<?> eClass = tClass.getComponentType();
		//if (eClass.isPrimitive())
		int length = Array.getLength(array);
		for (int index = 0; index < length; index++) {
			Object value = elements.apply(eClass);
			Array.set(array, index, value);
		}
	}
}
