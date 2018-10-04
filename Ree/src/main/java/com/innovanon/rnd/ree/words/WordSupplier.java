/**
 * 
 */
package com.innovanon.rnd.ree.words;

import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.io.WordListUtil;
import com.innovanon.rnd.ri.functions.Instantiator;
import com.innovanon.rnd.struct.ss.SetUtil;

/**
 * @author gouldbergstein
 *
 */
public class WordSupplier implements Instantiator<Locale, Stream<Word>> {

	// supplier of random subsets
	// supplier of random strings
	private Function<Locale, Iterator<Supplier<Stream<String>>>> subsets;

	/**
	 * 
	 * @param random
	 */
	public WordSupplier(Random random, IntSupplier ns) {
		Function<Locale, Stream<String>> words = lang -> WordListUtil.getData(lang);
		subsets = l -> SetUtil.randomSubsetsSupplier(words.apply(l).collect(Collectors.toList()), ns, random)
				.iterator();
		// Function<Locale,Supplier<Supplier<Stream<Supplier<Stream<String>>>>>>
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Locale param) {
		Iterator<Supplier<Stream<String>>> i = subsets.apply(param);
		return i != null && i.hasNext();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Stream<Word> apply(Locale param) {
		Iterator<Supplier<Stream<String>>> i = subsets.apply(param);
		Supplier<Stream<String>> s = i.next();
		return s.get().map(S -> Word.valueOf(S, param));
	}
}
