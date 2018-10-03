package com.innovanon.rnd.ree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test <T,R>implements Runnable {

	private Set<T> inputSet;
		
	private Stream<T> streamT = inputSet.stream();
	
	private Function<T,R> function;
	
	private Set<R> outputSet = streamT.map(function).collect(Collectors.toSet());
	
	
	
	
	
	private Set<Locale> supported1;
	private Set<Locale> supported2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Set<Locale> intersection = intersect();
		
	}
	
	public Set<Locale> intersect () {
		Set<Locale> intersections = new HashSet<>();
		intersections.addAll(supported1);
		intersections.retainAll(supported2);
		return intersections;
	}

	public static void main(String[] args) {

	}
}
