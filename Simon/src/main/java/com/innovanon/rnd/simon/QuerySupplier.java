/**
 * 
 */
package com.innovanon.rnd.simon;

import java.util.Collection;
import java.util.Locale;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.innovanon.rnd.ree.words.Word;
import com.innovanon.rnd.ri.functions.special.BoundedIntFunction2;
import com.innovanon.rnd.ri.functions.special.DiscreteSubsetFunction;
import com.innovanon.rnd.ri.suppliers.special.EnumSupplier;
import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;

/**
 * @author gouldbergstein
 *
 */
public class QuerySupplier implements Supplier<Pair<QueryLang, Collection<String>>> {

	private Supplier<QueryLang> langs;
	private IntFunction<Integer> lengths;
	private Function<Locale, DiscreteSubsetFunction<Word>> lists;

	public QuerySupplier(Random random, Function<Locale, DiscreteSubsetFunction<Word>> lists) {
		langs = new EnumSupplier<QueryLang>(QueryLang.class, random);
		this.lists = lists;
		//lengths = new RangedIntSupplier(1, 5, random);
		lengths = new BoundedIntFunction2(random, 1, 4);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Pair<QueryLang, Collection<String>> get() {
		// TODO
		while (true) try{
		QueryLang ql = langs.get();
		Locale l = ql.getLocale ();
		DiscreteSubsetFunction<Word> words = lists.apply(l);
		int length = lengths.apply(words.size());
		Collection<String> strings = words.apply(length).stream().map(w -> w.getString()).collect(Collectors.toList());
		return new ImmutablePairImpl<QueryLang, Collection<String>>(ql, strings);
		}catch (Error e) {
			System.err.println(e);
		}
	}

}
