/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class EnumInstantiator implements Instantiator<Class<?>, Object> {

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
	assert test(t);
		Object[] instances = t.getEnumConstants();
		int index = random.nextInt(instances.length);
		return instances[index];
	}

	/* (non-Javadoc)
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?> t) {
		return t.isEnum();
	}
}
