/**
 * 
 */
package com.innovanon.rnd.ree;

import java.util.function.IntSupplier;
import java.util.function.Supplier;

import com.mifmif.common.regex.Generex;

/**
 * @author gouldbergstein
 *
 */
public class RegexSupplier implements Supplier<String> {
	/**
	 * 
	 */
	private Generex generex;
	private int minLength;
	// private List<String> strings;
	private IntSupplier sizes;

	/**
	 * @param generex
	 * @param minLength
	 * @param sizes
	 */
	public RegexSupplier(Generex generex, int minLength, IntSupplier sizes) {
		this.generex = generex;
		this.minLength = minLength;
		this.sizes = sizes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public String get() {
		// TODO must get min from stream
		// TODO must specify min bound
		int maxLength = sizes.getAsInt();
		// if (strings ==null)
		// strings = generex.getAllMatchedStrings();
		return generex.random(minLength, maxLength);
	}
}
