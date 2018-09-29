/**
 * 
 */
package com.innovanon.rnd.ri.suppliers.special;

import java.util.function.DoubleSupplier;

import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class RandomGaussianSupplier implements DoubleSupplier {
	/**
	 * 
	 */
	private Randumb random;

	/**
	 * @param random
	 */
	public RandomGaussianSupplier(Randumb random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.DoubleSupplier#getAsDouble()
	 */
	@Override
	public double getAsDouble() {
		return random.nextGaussian();
	}
}
