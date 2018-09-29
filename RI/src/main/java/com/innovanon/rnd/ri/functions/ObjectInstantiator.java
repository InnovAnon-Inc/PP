/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.util.Random;
import java.util.function.Function;

/**
 * @author gouldbergstein
 *
 */
public class ObjectInstantiator implements Function<Class<?>, Object> {

	private Function<Class<?>, Object> arrays;
	private Function<Class<?>, Object> primitives;
	private Function<Class<?>, Object> enums;

	/**
	 * @param arrays
	 * @param primitives
	 * @param enums
	 */
	public ObjectInstantiator(Function<Class<?>, Object> arrays, Function<Class<?>, Object> primitives,
			Function<Class<?>, Object> enums) {
		this.arrays = arrays;
		this.primitives = primitives;
		this.enums = enums;
	}

	/**
	 * 
	 * @param random
	 */
	public ObjectInstantiator(Randumb random) {
		this(new ArrayInstantiator(random), new PrimitiveInstantiator(random), new EnumInstantiator(random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		// if (t.isAnnotation())
		if (t.isArray())
			return arrays.apply(t);
		if (t.isPrimitive())
			return primitives.apply(t);
		if (t.isEnum())
			return enums.apply(t);
		// if (t.isInterface())
		// if (t.isAnonymousClass())
		// if (t.isLocalClass())
		// if (t.isMemberClass())
		// if (t.isSynthetic())

		// TODO factory methods
		// TODO constructors

		// specialized instantiator... goes on top of this one
		// if (t.isAssignableFrom(cls))
		// even more specialized instantiator... idk what to do with it
		// if (t.isAnnotationPresent(annotationClass))
		// TODO
		throw new Error("unsupported class type");
	}
}
