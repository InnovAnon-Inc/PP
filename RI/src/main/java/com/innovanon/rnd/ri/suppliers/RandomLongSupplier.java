/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.function.LongSupplier;

import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class RandomLongSupplier implements LongSupplier {
	/**
	 * 
	 */
	private Randumb random;

	/**
	 * @param random
	 */
	public RandomLongSupplier(Randumb random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.LongSupplier#getAsLong()
	 */
	@Override
	public long getAsLong() {
		return random.nextLong();
	}
}
