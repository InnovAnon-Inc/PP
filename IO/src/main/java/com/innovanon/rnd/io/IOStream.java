/**
 * 
 */
package com.innovanon.rnd.io;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

/**
 * @author gouldbergstein
 *
 */
public abstract class IOStream<T, R> extends IO<T, Supplier<Stream<R>>, Stream<R>> {

	@Override
	protected Stream<R> outputHelper(Supplier<Stream<R>> input) {
		return input.get();
	}

	@Override
	protected Supplier<Stream<R>> inputHelper(T input) {
		return Replayer.replay(helper(input));
	}

	protected abstract Stream<R> helper(T input);
}
