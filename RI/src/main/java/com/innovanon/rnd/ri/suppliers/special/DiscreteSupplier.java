/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * @author gouldbergstein
 *
 */
public class DiscreteSupplier<T> implements Supplier<T> {
	/**
	 * 
	 */
	private Collection<T> delegates;
	private Iterator<T> delegate;

	/**
	 * 
	 * @param random
	 * @param array
	 */
	@SafeVarargs
	public DiscreteSupplier(Random random, T... array) {
		// TODO Auto-generated constructor stub
		delegates = new BagImpl<T>(random, array);
		assert array.length!=0;
		assert ! delegates.isEmpty();
		delegate = delegates.iterator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public T get() {
		if (/* repeat && */delegate == null || !delegate.hasNext())
			delegate = delegates.iterator();
		assert delegate.hasNext();
		return delegate.next();
	}

}