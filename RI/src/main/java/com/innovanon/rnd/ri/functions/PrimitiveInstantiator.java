/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

import com.innovanon.rnd.func.ByteSupplier;
import com.innovanon.rnd.func.CharSupplier;
import com.innovanon.rnd.func.FloatSupplier;
import com.innovanon.rnd.func.ShortSupplier;
import com.innovanon.rnd.rand.Randumb;
import com.innovanon.rnd.ri.suppliers.RandomBooleanSupplier;
import com.innovanon.rnd.ri.suppliers.RandomByteSupplier;
import com.innovanon.rnd.ri.suppliers.RandomCharSupplier;
import com.innovanon.rnd.ri.suppliers.RandomDoubleSupplier;
import com.innovanon.rnd.ri.suppliers.RandomFloatSupplier;
import com.innovanon.rnd.ri.suppliers.RandomIntSupplier;
import com.innovanon.rnd.ri.suppliers.RandomLongSupplier;
import com.innovanon.rnd.ri.suppliers.RandomShortSupplier;

/**
 * @author gouldbergstein
 *
 */
public class PrimitiveInstantiator implements Function<Class<?>, Object> {
	/**
	 * 
	 */
	private IntSupplier ints;
	/**
	 * 
	 */
	private BooleanSupplier booleans;
	/**
	 * 
	 */
	private DoubleSupplier doubles;
	/**
	 * 
	 */
	private ShortSupplier shorts;
	/**
	 * 
	 */
	private LongSupplier longs;
	/**
	 * 
	 */
	private FloatSupplier floats;
	/**
	 * 
	 */
	private ByteSupplier bytes;
	/**
	 * 
	 */
	private CharSupplier chars;

	/**
	 * @param ints
	 * @param booleans
	 * @param doubles
	 * @param shorts
	 * @param longs
	 * @param floats
	 * @param bytes
	 * @param chars
	 */
	public PrimitiveInstantiator(IntSupplier ints, BooleanSupplier booleans, DoubleSupplier doubles,
			ShortSupplier shorts, LongSupplier longs, FloatSupplier floats, ByteSupplier bytes, CharSupplier chars) {
		this.ints = ints;
		this.booleans = booleans;
		this.doubles = doubles;
		this.shorts = shorts;
		this.longs = longs;
		this.floats = floats;
		this.bytes = bytes;
		this.chars = chars;
	}

	/**
	 * @param random
	 */
	public PrimitiveInstantiator (Randumb random) {
		this (new RandomIntSupplier (random), new RandomBooleanSupplier (random), new RandomDoubleSupplier (random),
				new RandomShortSupplier(random),new RandomLongSupplier(random),new RandomFloatSupplier(random),new RandomByteSupplier(random), new RandomCharSupplier(random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		assert t.isPrimitive();
		if (t.equals(int.class))
			return Integer.valueOf(ints.getAsInt());
		if (t.equals(boolean.class))
			return Boolean.valueOf(booleans.getAsBoolean());
		if (t.equals(double.class))
			return Double.valueOf(doubles.getAsDouble());
		if (t.equals(short.class))
			return Short.valueOf(shorts.getAsShort());
		if (t.equals(long.class))
			return Long.valueOf(longs.getAsLong());
		if (t.equals(float.class))
			return Float.valueOf(floats.getAsFloat());
		if (t.equals(byte.class))
			return Byte.valueOf(bytes.getAsByte());
		if (t.equals(char.class))
			return Character.valueOf(chars.getAsChar());
		// TODO
		throw new Error("unsupported primitive type");
	}
}