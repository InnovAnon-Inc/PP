/**
 * 
 */
package com.innovanon.rnd.ri.suppliers;

import com.innovanon.rnd.func.ByteSupplier;
import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class RandomByteSupplier implements ByteSupplier {
	/**
	 * 
	 */
	private Randumb random;

	/**
	 * @param random
	 */
	public RandomByteSupplier(Randumb random) {
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.func.ByteSupplier#getAsByte()
	 */
	@Override
	public byte getAsByte() {
		byte[] bytes = new byte[1];
		random.nextBytes(bytes);
		return bytes[0];
	}
}
