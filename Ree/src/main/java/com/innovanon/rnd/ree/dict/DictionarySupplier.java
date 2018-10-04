/**
 * 
 */
package com.innovanon.rnd.ree.dict;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.innovanon.rnd.io.DictionaryUtil;
import com.innovanon.rnd.struct.bag.BagUtil;

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
		Stream<String> list = DictionaryUtil.getDictionary("all");
		Stream<String>rList = BagUtil.getRandomStream(list, random);
		//Iterable<String> iter = new BagImpl<>(list, random);
		//dict = new Reiterator<>(iter);
		dict = rList.iterator();
	}

	/**
	 * 
	 */
	@Override
	public String get() {
		return dict.next();
	}
}
