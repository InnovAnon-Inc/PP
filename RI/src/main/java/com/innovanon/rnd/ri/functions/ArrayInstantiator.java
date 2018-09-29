/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.lang.reflect.Array;
import java.util.function.Function;
import java.util.function.IntSupplier;

import com.innovanon.rnd.rand.Randumb;
import com.innovanon.rnd.ri.suppliers.special.SizeSupplier;

/**
 * @author gouldbergstein
 *
 */
public class ArrayInstantiator implements Function<Class<?>, Object> {

	/**
	 * 
	 */
	private IntSupplier lengths;

	/**
	 * @param lengths
	 */
	public ArrayInstantiator(IntSupplier lengths) {
		this.lengths = lengths;
	}

	/**
	 * 
	 * @param random
	 */
	public ArrayInstantiator(Randumb random) {
		this(new SizeSupplier(random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		assert t.isArray();
		Class<?> componentType = t.getComponentType();
		int length = lengths.getAsInt();
		Object array = Array.newInstance(componentType, length);
		return array;
	}
}
