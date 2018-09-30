/**
 * 
 */
package com.innovanon.rnd.ri.functions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.innovanon.rnd.func.predicates.InvariablePredicate;
import com.innovanon.rnd.rand.Randumb;
import com.innovanon.rnd.struct.pe.PluggableEngine;

/**
 * @author gouldbergstein
 *
 */
public class ObjectInstantiator implements Instantiator<Class<?>, Object> {
	/**
	 * 
	 * @param random
	 * @return
	 */
	private static Collection<Instantiator<Class<?>, Object>> getDelegates (Randumb random) {
		Instantiator<Class<?>,Object> arrays = new ArrayInstantiator(random);
		Instantiator<Class<?>,Object> primitives = new PrimitiveInstantiator(random);
		Instantiator<Class<?>,Object> enums = new EnumInstantiator(random);
		Set<Instantiator<Class<?>,Object>> firstPass = new HashSet<>();
		firstPass.add(arrays);
		firstPass.add(primitives);
		firstPass.add(enums);
		List<Set<Instantiator<Class<?>,Object>>> delegates = new ArrayList<>();
		delegates.add(firstPass);
		Collection<Instantiator<Class<?>,Object>>ret = new PluggableEngine<>(delegates);
		return ret;
	}

	/**
	 * 
	 */
	//private List<Set<Instantiator<Class<?>,Object>>> delegates;
	private Collection<Instantiator<Class<?>,Object>> delegates;

	/**
	 * 
	 * @param delegates
	 */
	public ObjectInstantiator(Collection<Instantiator<Class<?>,Object>> delegates) {
		this.delegates=delegates;
	}

	/**
	 * 
	 * @param random
	 */
	public ObjectInstantiator(Randumb random) {
		this(getDelegates(random));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Function#apply(java.lang.Object)
	 */
	@Override
	public Object apply(Class<?> t) {
		Iterator<Instantiator<Class<?>,Object>> iterator = delegates.stream().filter(new InvariablePredicate<Class<?>>(t)).iterator();
		if (! iterator.hasNext())throw new Error ("unsupported type");
		while (iterator.hasNext()) {
			Instantiator<Class<?>,Object> instantiator = iterator.next();
			return instantiator.apply(t);
		}
		throw new Error ("failed");
			

		// if (t.isAnnotation())
		//if (t.isArray())
	//		return arrays.apply(t);
	//	if (t.isPrimitive())
	//		return primitives.apply(t);
	//	if (t.isEnum())
	//		return enums.apply(t);
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
	return	delegates.stream().anyMatch(new InvariablePredicate<Class<?>>(t));
	}
}
