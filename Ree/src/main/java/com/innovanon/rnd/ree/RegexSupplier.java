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
public class RegexSupplier implements Supplier<String>{
	/**
	 * 
	 */
	private Generex generex;
	private int minLength;
	//private List<String> strings;
	private IntSupplier sizes;

	/**
	 * @param generex
	 */
	public RegexSupplier(Generex generex) {
		this.generex = generex;
	}

	/* (non-Javadoc)
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public String get() {
		int maxLength = sizes.getAsInt();
		//if (strings ==null)
			//strings = generex.getAllMatchedStrings();
		return generex.random(minLength, maxLength );
	}
}
