/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.function.IntSupplier;

import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class RandomIntSupplier implements IntSupplier {
	/**
	 * 
	 */
	private Randumb random;

	/**
	 * @param random
	 */
	public RandomIntSupplier(Randumb random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.IntSupplier#getAsInt()
	 */
	@Override
	public int getAsInt() {
		return random.nextInt();
	}
}
