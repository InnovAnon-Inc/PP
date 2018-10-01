/**
 * 
 */
package com.innovanon.rnd.ree;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author gouldbergstein
 *
 */
public class EnumSubsetSupplier<T > implements Supplier<Collection<T>> {

	private Supplier<Collection<T>> delegate;

	/**
	 * @param random
	 * @param delegate
	 */
	public EnumSubsetSupplier(Class<T> t, Random random) {
		assert t.isEnum();
		this.delegate = new DiscreteSubsetSupplier<T>(random, t.getEnumConstants());
	}

	@Override
	public Collection<T> get() {
		return delegate.get();
	}

}
