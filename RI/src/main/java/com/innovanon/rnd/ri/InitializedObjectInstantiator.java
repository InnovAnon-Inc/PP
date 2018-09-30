/**
 * 
 */
package com.innovanon.rnd.ri;

import com.innovanon.rnd.ri.consumers.Initializer;
import com.innovanon.rnd.ri.functions.Instantiator;

/**
 * @author gouldbergstein
 *
 */
public class InitializedObjectInstantiator implements Instantiator<Class<?>, Object> {

	/**
	 * 
	 */
	private Instantiator<Class<?>, Object> function;
	/**
	 * 
	 */
	private Initializer<Object> consumer;

	/**
	 * @param function
	 * @param consumer
	 */
	public InitializedObjectInstantiator(Instantiator<Class<?>, Object> function, Initializer<Object> consumer) {
		this.function = function;
		this.consumer = consumer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		assert test(t);
		Object ret = function.apply(t);
		assert consumer.test(ret);
		consumer.accept(ret);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?> t) {
		return function.test(t);
	}
}
