/**
 * 
 */
package com.innovanon.rnd.ri;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import com.innovanon.rnd.util.function.uf.UnitedFunctions;

/**
 * @author gouldbergstein
 *
 */
public class RandomObjectInstantiator extends UnitedFunctions<Class<?>, Object> {

	/**
	 * 
	 */
	private static final Map<Predicate<Class<?>>, Function<Class<?>, Object>> delegates;
	
	static {
		delegates = new HashMap<>();
		delegates.put(Class::isAnnotation, AnnotationInstantiator.INSTANCE);
	}

	/**
	 * 
	 */
	public RandomObjectInstantiator() {
		super(delegates);
	}
}
