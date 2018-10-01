/**
 * 
 */
package com.innovanon.rnd.ree.dict;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

import com.innovanon.rnd.struct.bag.BagImpl;
import com.innovanon.rnd.struct.iter.Reiterator;

/**
 * @author gouldbergstein
 *
 */
public class DictionarySupplier implements Supplier<String> {
	/**
	 * 
	 */
	private Iterator<String> dict;

	/**
	 * 
	 * @param random
	 */
	public DictionarySupplier(Random random) {
		Collection<String> list = DictionaryUtil.getDictionary();
		Iterable<String> iter = new BagImpl<>(list, random);
		dict = new Reiterator<>(iter);
	}

	/**
	 * 
	 */
	@Override
	public String get() {
		return dict.next();
	}
}
