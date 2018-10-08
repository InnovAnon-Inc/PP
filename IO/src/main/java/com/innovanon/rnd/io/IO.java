/**
 * 
 */
package com.innovanon.rnd.io;

import java.util.function.Function;

import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public abstract class IO<T, C, R> implements Function<T,R>{
	private Function<T, C> cache = Memoizer
			.memoize(uax -> inputHelper(uax));

	public R apply(T input) {
		return outputHelper (cache.apply(input));
	}
	
	protected abstract R outputHelper (C input);
	
	protected abstract C inputHelper (T input) ;
}
