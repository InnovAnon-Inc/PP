/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import com.innovanon.rnd.func.FloatSupplier;
import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class RandomFloatSupplier implements FloatSupplier {
	/**
	 * 
	 */
	private Randumb random;

	/**
	 * @param random
	 */
	public RandomFloatSupplier(Randumb random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.func.FloatSupplier#getAsFloat()
	 */
	@Override
	public float getAsFloat() {
		return random.nextFloat();
	}
}
