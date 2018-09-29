/**
 * 
 */
package com.innovanon.rnd.rand.securerandom;

import java.security.Provider;
import java.security.SecureRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class SecureRandumb implements Randumb {
	/**
	 * 
	 */
	private SecureRandom delegate;

	/**
	 * @param delegate
	 */
	public SecureRandumb(SecureRandom delegate) {
		this.delegate = delegate;
	}

	/**
	 * 
	 */
	public SecureRandumb() {
		this(new SecureRandom());
	}

	/**
	 * @return
	 * @see java.util.Random#nextInt()
	 */
	@Override
	public int nextInt() {
		return delegate.nextInt();
	}

	/**
	 * @param bound
	 * @return
	 * @see java.util.Random#nextInt(int)
	 */
	@Override
	public int nextInt(int bound) {
		return delegate.nextInt(bound);
	}

	/**
	 * @return
	 * @see java.security.SecureRandom#getProvider()
	 */
	@Override
	public final Provider getProvider() {
		return delegate.getProvider();
	}

	/**
	 * @return
	 * @see java.security.SecureRandom#getAlgorithm()
	 */
	@Override
	public String getAlgorithm() {
		return delegate.getAlgorithm();
	}

	/**
	 * @param seed
	 * @see java.security.SecureRandom#setSeed(byte[])
	 */
	@Override
	public void setSeed(byte[] seed) {
		delegate.setSeed(seed);
	}

	/**
	 * @return
	 * @see java.util.Random#nextLong()
	 */
	@Override
	public long nextLong() {
		return delegate.nextLong();
	}

	/**
	 * @param seed
	 * @see java.security.SecureRandom#setSeed(long)
	 */
	@Override
	public void setSeed(long seed) {
		delegate.setSeed(seed);
	}

	/**
	 * @return
	 * @see java.util.Random#nextBoolean()
	 */
	@Override
	public boolean nextBoolean() {
		return delegate.nextBoolean();
	}

	/**
	 * @param bytes
	 * @see java.security.SecureRandom#nextBytes(byte[])
	 */
	@Override
	public void nextBytes(byte[] bytes) {
		delegate.nextBytes(bytes);
	}

	/**
	 * @return
	 * @see java.util.Random#nextFloat()
	 */
	@Override
	public float nextFloat() {
		return delegate.nextFloat();
	}

	/**
	 * @param numBytes
	 * @return
	 * @see java.security.SecureRandom#generateSeed(int)
	 */
	@Override
	public byte[] generateSeed(int numBytes) {
		return delegate.generateSeed(numBytes);
	}

	/**
	 * @return
	 * @see java.util.Random#nextDouble()
	 */
	@Override
	public double nextDouble() {
		return delegate.nextDouble();
	}

	/**
	 * @return
	 * @see java.util.Random#nextGaussian()
	 */
	@Override
	public double nextGaussian() {
		return delegate.nextGaussian();
	}

	/**
	 * @param streamSize
	 * @return
	 * @see java.util.Random#ints(long)
	 */
	@Override
	public IntStream ints(long streamSize) {
		return delegate.ints(streamSize);
	}

	/**
	 * @return
	 * @see java.util.Random#ints()
	 */
	@Override
	public IntStream ints() {
		return delegate.ints();
	}

	/**
	 * @param streamSize
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.Random#ints(long, int, int)
	 */
	@Override
	public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
		return delegate.ints(streamSize, randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.Random#ints(int, int)
	 */
	@Override
	public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
		return delegate.ints(randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param streamSize
	 * @return
	 * @see java.util.Random#longs(long)
	 */
	@Override
	public LongStream longs(long streamSize) {
		return delegate.longs(streamSize);
	}

	/**
	 * @return
	 * @see java.util.Random#longs()
	 */
	@Override
	public LongStream longs() {
		return delegate.longs();
	}

	/**
	 * @param streamSize
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.Random#longs(long, long, long)
	 */
	@Override
	public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
		return delegate.longs(streamSize, randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.Random#longs(long, long)
	 */
	@Override
	public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
		return delegate.longs(randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param streamSize
	 * @return
	 * @see java.util.Random#doubles(long)
	 */
	@Override
	public DoubleStream doubles(long streamSize) {
		return delegate.doubles(streamSize);
	}

	/**
	 * @return
	 * @see java.util.Random#doubles()
	 */
	@Override
	public DoubleStream doubles() {
		return delegate.doubles();
	}

	/**
	 * @param streamSize
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.Random#doubles(long, double, double)
	 */
	@Override
	public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
		return delegate.doubles(streamSize, randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.Random#doubles(double, double)
	 */
	@Override
	public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
		return delegate.doubles(randomNumberOrigin, randomNumberBound);
	}

	@Override
	public int nextInt(int origin, int bound) {
		return origin + nextInt(bound);
	}
}
