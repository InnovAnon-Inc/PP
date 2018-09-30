/**
 * 
 */
package com.innovanon.rnd.ree;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.struct.bag.Bag;
import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * @author gouldbergstein
 *
 */
public class DictionarySupplier implements Supplier<String>{
	private Bag<String> dict;
	
	public DictionarySupplier(Random random) {
		// TODO Auto-generated constructor stub
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("all");
		Reader r = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader (r);
		Stream<String> stream = reader.lines();
		// TODO filter out comments
		Set<String>set = stream.collect(Collectors.toSet());
		dict =new BagImpl<>(set, random);
	}

	@Override
	public String get() {
		// TODO with or without replacement?
return dict.remove();
	}
}
