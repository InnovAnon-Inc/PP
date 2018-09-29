/**
 * 
 */
package com.innovanon.rnd.rand;

import java.security.Provider;
import java.util.SplittableRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author gouldbergstein
 *
 */
public interface Randumb {
	default void setSeed(long seed) {
		throw new Error("incomplete");
	}

	void nextBytes(byte[] bytes);

	int nextInt();

	int nextInt(int bound);

	long nextLong();

	boolean nextBoolean();

	default float nextFloat() {
		throw new Error("incomplete");
	}

	double nextDouble();

	default double nextGaussian() {
		throw new Error("incomplete");
	}

	IntStream ints(long streamSize);

	IntStream ints();

	IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound);

	IntStream ints(int randomNumberOrigin, int randomNumberBound);

	LongStream longs(long streamSize);

	LongStream longs();

	LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound);

	LongStream longs(long randomNumberOrigin, long randomNumberBound);

	DoubleStream doubles(long streamSize);

	DoubleStream doubles();

	DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound);

	DoubleStream doubles(double randomNumberOrigin, double randomNumberBound);

	default Provider getProvider() {
		throw new Error("incomplete");
	}

	default String getAlgorithm() {
		throw new Error("incomplete");
	}

	default void setSeed(byte[] seed) {
		throw new Error("incomplete");
	}

	default byte[] generateSeed(int numBytes) {
		throw new Error("incomplete");
	}

	default SplittableRandom split() {
		throw new Error("incomplete");
	}

	default int nextInt(int origin, int bound) {
		return origin + nextInt(bound);
	}

	default long nextLong(long bound) {
		throw new Error("incomplete");
	}

	default long nextLong(long origin, long bound) {
		return origin + nextLong(bound);
	}

	default double nextDouble(double bound) {
		throw new Error("incomplete");
	}

	default double nextDouble(double origin, double bound) {
		return origin + nextDouble(bound);
	}
}
