/**
 * 
 */
package com.innovanon.rnd.ri.consumers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.innovanon.rnd.func.predicates.InvariablePredicate;
import com.innovanon.rnd.ri.functions.Instantiator;
import com.innovanon.rnd.struct.pe.PluggableEngine;

/**
 * @author gouldbergstein
 *
 */
public class ObjectInitializer implements Initializer<Object> {
/**
 * 
 * @param delegate
 * @return
 */
	private static Collection<Initializer<Object>> getDelegates (Instantiator<Class<?>,Object> delegate) {
		Initializer<Object> arrays = new ArrayInitializer(delegate);
		Set<Initializer<Object>> firstPass = new HashSet<>();
		firstPass.add(arrays);
		List<Set<Initializer<Object>>> delegates = new ArrayList<>();
		delegates.add(firstPass);
		Collection<Initializer<Object>>ret = new PluggableEngine<>(delegates);
		return ret;
	}
	
	/**
	 * 
	 */
	private Collection<Initializer<Object>> delegates;
	
	

	/**
	 * @param delegates
	 */
	public ObjectInitializer(Collection<Initializer<Object>> delegates) {
		this.delegates = delegates;
	}
	
	/**
	 * 
	 * @param delegate
	 */
	public ObjectInitializer(Instantiator<Class<?>, Object> delegate) {
		this (getDelegates(delegate));
	}

	/* (non-Javadoc)
	 * @see java.util.function.Predicate#test(java.lang.Object)
	 */
	@Override
	public boolean test(Object t) {
	return	delegates.stream().anyMatch(new InvariablePredicate<Object>(t));
	}

	/* (non-Javadoc)
	 * @see java.util.function.Consumer#accept(java.lang.Object)
	 */
	@Override
	public void accept(Object t) {
		Iterator<Initializer<Object>> iterator = delegates.stream().filter(new InvariablePredicate<Object>(t)).iterator();
		if (! iterator.hasNext())throw new Error ("unsupported type");
		while (iterator.hasNext()) {
			Initializer<Object> instantiator = iterator.next();
			instantiator.accept(t);
			return;
		}
		throw new Error ("failed");
	}
}
