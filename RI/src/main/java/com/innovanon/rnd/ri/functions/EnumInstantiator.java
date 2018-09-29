/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.util.function.Function;

import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class EnumInstantiator implements Function<Class<?>, Object> {

	/**
	 * 
	 */
	private Randumb random;

	/**
	 * @param random
	 */
	public EnumInstantiator(Randumb random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		assert t.isEnum();
		Object[] instances = t.getEnumConstants();
		int index = random.nextInt(instances.length);
		return instances[index];
	}
}
