/**
 * 
 */
package com.innovanon.rnd.util.function.uf;

import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 
 * @author gouldbergstein
 *
 * @param <T>
 * @param <R>
 */
public class UFPredicate<T, R> implements Predicate<Entry<Predicate<T>, Function<T, R>>> {
	/**
	 * 
	 */
	private final T t;

	/**
	 * 
	 * @param t
	 */
	public UFPredicate(T t) {
		this.t = t;
	}

	/**
	 * 
	 */
	@Override
	public boolean test(Entry<Predicate<T>, Function<T, R>> entry) {
		Predicate<T> p = entry.getKey();
		return p.test(t);
	}
}