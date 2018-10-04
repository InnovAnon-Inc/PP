/**
 * 
 */
package com.innovanon.rnd.simon;

import java.util.Collection;
import java.util.Locale;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.innovanon.rnd.ree.words.Word;
import com.innovanon.rnd.ree.words.WordSupplier;
import com.innovanon.rnd.ri.suppliers.special.EnumSupplier;
import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;

/**
 * @author gouldbergstein
 *
 */
public class QuerySupplier implements Supplier<Pair<QueryLang, Collection<String>>> {

	private Supplier<QueryLang> langs;
	private Function<Locale, Stream<Word>> words;

	public QuerySupplier(Random random, IntSupplier ns) {
		langs = new EnumSupplier<QueryLang>(QueryLang.class, random);
		words = new WordSupplier(random, ns);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Pair<QueryLang, Collection<String>> get() {
		QueryLang ql = langs.get();
		Locale l = ql.getLocale();
		Stream<Word> words = this.words.apply(l);
		Collection<String> c = words.map(w -> w.getString()).collect(Collectors.toList());
		return new ImmutablePairImpl<QueryLang, Collection<String>>(ql, c);
	}
}
