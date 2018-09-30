/**
 * 
 */
package com.innovanon.rnd.ri.yfunctions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

import com.innovanon.rnd.func.predicates.InvariablePredicate;
import com.innovanon.rnd.rand.Randumb;
import com.innovanon.rnd.ri.functions.Instantiator;
import com.innovanon.rnd.ri.functions.YInstantiator;

/**
 * @author gouldbergstein
 *
 */
public class ObjectInstantiator2 implements YInstantiator<Class<?>, Object> {
	/**
	 * 
	 * @param random
	 * @param child
	 * @return
	 */
	private static Iterable<YInstantiator<Class<?>, Object>> getDelegates(Randumb random) {
		// TODO interfaces
		// TODO constructors
		// TODO factory methods
		Set<YInstantiator<Class<?>, Object>> firstPass = new HashSet<>();
		return firstPass;
	}

	/**
	 * 
	 */
	// private List<Set<Instantiator<Class<?>,Object>>> delegates;
	private Iterable<YInstantiator<Class<?>, Object>> delegates;
	private Instantiator<Class<?>, Object> child;

	/**
	 * 
	 * @param delegates
	 */
	public ObjectInstantiator2(Iterable<YInstantiator<Class<?>, Object>> delegates,
			Instantiator<Class<?>, Object> child) {
		this.delegates = delegates;
		this.child = child;
	}

	/**
	 * 
	 * @param random
	 * @param child
	 */
	public ObjectInstantiator2(Randumb random, Instantiator<Class<?>, Object> child) {
		this(getDelegates(random), child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		Iterator<YInstantiator<Class<?>, Object>> iterator = StreamSupport.stream(delegates.spliterator(), true)
				.filter(new InvariablePredicate<Class<?>>(t)).iterator();
		if (!iterator.hasNext())
			throw new Error("unsupported type");
		while (iterator.hasNext()) {
			Instantiator<Class<?>, Object> instantiator = iterator.next();
			return instantiator.apply(t);
		}
		if (child.test(t))
			return child.apply(t);
		throw new Error("failed");

		// if (t.isAnnotation())
		// if (t.isArray())
		// return arrays.apply(t);
		// if (t.isPrimitive())
		// return primitives.apply(t);
		// if (t.isEnum())
		// return enums.apply(t);
		// if (t.isInterface()) {
		// get random subclass

		// }
		//
		// if (t.isAnonymousClass() || t.isLocalClass()) {
		// Method m = t.getEnclosingMethod();
		// Constructor<?> c = t.getEnclosingConstructor();
		// Class<?> cls = t.getEnclosingClass();
		// }
		//
		// if (t.isMemberClass()) {
		// t.getDeclaringClass();
		// }
		// if (t.isSynthetic())

		// TODO generic declarations

		// goes on top of this one... don't wanna invoke a method with an uninit'd
		// collection/array
		// TODO factory methods
		// TODO constructors

		// specialized instantiator... goes on top of this one
		// if (t.isAssignableFrom(cls))
		// even more specialized instantiator... idk what to do with it
		// if (t.isAnnotationPresent(annotationClass))
		// TODO
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Class<?> t) {
		if (StreamSupport.stream(delegates.spliterator(), true).anyMatch(new InvariablePredicate<Class<?>>(t)))
			return true;
		return child.test(t);
	}

	// @Override
	// public YInstantiator<Class<?>, Object> getDelegate() {
	// // TODO Auto-generated method stub
	// return null;
//	}

	@Override
	public void setDelegate(YInstantiator<Class<?>, Object> delegate) {
		StreamSupport.stream(delegates.spliterator(), true)
				.forEachOrdered(new Consumer<YInstantiator<Class<?>, Object>>() {
					@Override
					public void accept(YInstantiator<Class<?>, Object> t) {
						t.setDelegate(delegate);
					}
				});
	}
}
