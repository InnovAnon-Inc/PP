/**
 * 
 */
package com.innovanon.rnd.rand.splittablerandom;

import java.security.Provider;
import java.util.SplittableRandom;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import com.innovanon.rnd.rand.Randumb;

/**
 * @author gouldbergstein
 *
 */
public class SplittableRandumb implements Randumb {
	/**
	 * 
	 */
	private SplittableRandom delegate;

	/**
	 * @param delegate
	 */
	public SplittableRandumb(SplittableRandom delegate) {
		this.delegate = delegate;
	}

	/**
	 * 
	 */
	public SplittableRandumb() {
		this(new SplittableRandom());
	}

	/**
	 * @return
	 * @see java.util.SplittableRandom#split()
	 */
	@Override
	public SplittableRandom split() {
		return delegate.split();
	}

	/**
	 * @return
	 * @see java.util.SplittableRandom#nextInt()
	 */
	@Override
	public int nextInt() {
		return delegate.nextInt();
	}

	/**
	 * @param bound
	 * @return
	 * @see java.util.SplittableRandom#nextInt(int)
	 */
	@Override
	public int nextInt(int bound) {
		return delegate.nextInt(bound);
	}

	/**
	 * @param origin
	 * @param bound
	 * @return
	 * @see java.util.SplittableRandom#nextInt(int, int)
	 */
	@Override
	public int nextInt(int origin, int bound) {
		return delegate.nextInt(origin, bound);
	}

	/**
	 * @return
	 * @see java.util.SplittableRandom#nextLong()
	 */
	@Override
	public long nextLong() {
		return delegate.nextLong();
	}

	/**
	 * @param bound
	 * @return
	 * @see java.util.SplittableRandom#nextLong(long)
	 */
	@Override
	public long nextLong(long bound) {
		return delegate.nextLong(bound);
	}

	/**
	 * @param origin
	 * @param bound
	 * @return
	 * @see java.util.SplittableRandom#nextLong(long, long)
	 */
	@Override
	public long nextLong(long origin, long bound) {
		return delegate.nextLong(origin, bound);
	}

	/**
	 * @return
	 * @see java.util.SplittableRandom#nextDouble()
	 */
	@Override
	public double nextDouble() {
		return delegate.nextDouble();
	}

	/**
	 * @param bound
	 * @return
	 * @see java.util.SplittableRandom#nextDouble(double)
	 */
	@Override
	public double nextDouble(double bound) {
		return delegate.nextDouble(bound);
	}

	/**
	 * @param origin
	 * @param bound
	 * @return
	 * @see java.util.SplittableRandom#nextDouble(double, double)
	 */
	@Override
	public double nextDouble(double origin, double bound) {
		return delegate.nextDouble(origin, bound);
	}

	/**
	 * @return
	 * @see java.util.SplittableRandom#nextBoolean()
	 */
	@Override
	public boolean nextBoolean() {
		return delegate.nextBoolean();
	}

	/**
	 * @param streamSize
	 * @return
	 * @see java.util.SplittableRandom#ints(long)
	 */
	@Override
	public IntStream ints(long streamSize) {
		return delegate.ints(streamSize);
	}

	/**
	 * @return
	 * @see java.util.SplittableRandom#ints()
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
	 * @see java.util.SplittableRandom#ints(long, int, int)
	 */
	@Override
	public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
		return delegate.ints(streamSize, randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.SplittableRandom#ints(int, int)
	 */
	@Override
	public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
		return delegate.ints(randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param streamSize
	 * @return
	 * @see java.util.SplittableRandom#longs(long)
	 */
	@Override
	public LongStream longs(long streamSize) {
		return delegate.longs(streamSize);
	}

	/**
	 * @return
	 * @see java.util.SplittableRandom#longs()
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
	 * @see java.util.SplittableRandom#longs(long, long, long)
	 */
	@Override
	public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
		return delegate.longs(streamSize, randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.SplittableRandom#longs(long, long)
	 */
	@Override
	public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
		return delegate.longs(randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param streamSize
	 * @return
	 * @see java.util.SplittableRandom#doubles(long)
	 */
	@Override
	public DoubleStream doubles(long streamSize) {
		return delegate.doubles(streamSize);
	}

	/**
	 * @return
	 * @see java.util.SplittableRandom#doubles()
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
	 * @see java.util.SplittableRandom#doubles(long, double, double)
	 */
	@Override
	public DoubleStream doubles(long streamSize, double randomNumberOrigin, double randomNumberBound) {
		return delegate.doubles(streamSize, randomNumberOrigin, randomNumberBound);
	}

	/**
	 * @param randomNumberOrigin
	 * @param randomNumberBound
	 * @return
	 * @see java.util.SplittableRandom#doubles(double, double)
	 */
	@Override
	public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
		return delegate.doubles(randomNumberOrigin, randomNumberBound);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.innovanon.rnd.rand.Randumb#nextBytes(byte[])
	 */
	@Override
	public void nextBytes(byte[] bytes) {
		for (int index = 0; index < bytes.length; index++) {
			int temp = nextInt(Byte.MIN_VALUE, Byte.MAX_VALUE - Byte.MIN_VALUE + 1);
			assert Byte.MIN_VALUE <= temp;
			assert Byte.MAX_VALUE >= temp;
			byte b = (byte) temp;
			bytes[index] = b;
		}
	}

	/* (non-Javadoc)
	 * @see com.innovanon.rnd.rand.Randumb#getProvider()
	 */
	@Override
	public Provider getProvider() {
		// TODO Auto-generated method stub

		throw new UnsupportedOperationException();}

	/* (non-Javadoc)
	 * @see com.innovanon.rnd.rand.Randumb#getAlgorithm()
	 */
	@Override
	public String getAlgorithm() {
		// TODO Auto-generated method stub

		throw new UnsupportedOperationException();}

	/* (non-Javadoc)
	 * @see com.innovanon.rnd.rand.Randumb#setSeed(byte[])
	 */
	@Override
	public void setSeed(byte[] seed) {
		// TODO Auto-generated method stub

		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.innovanon.rnd.rand.Randumb#generateSeed(int)
	 */
	@Override
	public byte[] generateSeed(int numBytes) {
		// TODO Auto-generated method stub

		throw new UnsupportedOperationException();	}
}
