/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import java.util.Random;
import java.util.function.BooleanSupplier;

/**
 * @author gouldbergstein
 *
 */
public class RandomBooleanSupplier implements BooleanSupplier {

	/**
	 * 
	 */
	private Randumb random;

	/**
	 * @param random
	 */
	public RandomBooleanSupplier(Randumb random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.BooleanSupplier#getAsBoolean()
	 */
	@Override
	public boolean getAsBoolean() {
		return random.nextBoolean();
	}
}
