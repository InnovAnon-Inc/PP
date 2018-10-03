/**
 * 
 */
package com.innovanon.rnd.ree.words;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.ri.functions.Instantiator;
import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * @author gouldbergstein
 *
 */
public class WordSupplier implements Instantiator<Locale, Collection<Word>> {

	/**
	 * 
	 */
	private Function<Locale, Stream<Word>> langs;

	/**
	 * 
	 */
	private Map<Locale, Collection<Word>> dicts;

	/**
	 * 
	 */
	private Random random;

	/**
	 * 
	 * @param random
	 */
	public WordSupplier(Random random) {
		Function<Locale, Collection<File>> files = new WordListFileFunction();
		Function<Locale, Stream<Word>> words = new WordListFunction(files);
		// Collection<String> list = words.apply(t);
		// Iterable<String> iter = new BagImpl<>(list, random);
		// dict = new Reiterator<>(iter);
		langs = words;
		dicts = new HashMap<>();
		this.random = random;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Locale t) {
		if (dicts.containsKey(t))
			return true;
		Stream<Word> s = langs.apply(t);
		Collection<Word> list = s.collect(Collectors.toList());
		if (list.isEmpty())
			return false;
		Collection<Word> iter = new BagImpl<>(list, random);
		//Iterator<Word> dict = new Reiterator<>(iter);
		//dicts.put(t, dict);
		dicts.put(t, iter);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Collection<Word> apply(Locale param) {
		Collection<Word> sub;
		if (dicts.containsKey(param))
			sub = dicts.get(param);
		else {
			Stream<Word> s = langs.apply(param);
			Collection<Word> list = s.collect(Collectors.toList());
			if (list.isEmpty())
				throw new Error();
			Collection<Word> iter = new BagImpl<>(list, random);
			sub = iter;
			//sub = new Reiterator<>(iter);
			//dicts.put(param, sub);
			dicts.put(param, iter);
		}

		//return sub.next();
		return sub;
	}
}
