/**
 * 
 */
package com.innovanon.rnd.struct.ss;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.struct.bag.BagUtil;
import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public enum SetUtil {
	/* no instances */ ;

	public static <E> Collection<E> union(Collection<E> a, Collection<E> b) {
		Predicate<? super E> predicate = o -> !a.contains(o);
		Stream<E> as = a.stream();
		Stream<E> bs = b.stream().filter(predicate);
		Stream<E> ab = Stream.concat(as, bs);
		Supplier<Stream<E>> s = Replayer.replay(ab);
		return new ImmutableStreamCollectionImpl<>(s);
	}

	public static <E> Collection<E> intersection(Collection<E> a, Collection<E> b) {
		Predicate<? super E> predicate = o -> a.contains(o);
		// Stream<E> as = a.stream();
		Stream<E> bs = b.stream().filter(predicate);
		// Stream<E> ab = Stream.concat(as, bs);
		Stream<E> ab = bs;
		Supplier<Stream<E>> s = Replayer.replay(ab);
		return new ImmutableStreamCollectionImpl<>(s);
	}

	public static <E> Collection<E> difference(Collection<E> a, Collection<E> b) {
		Predicate<? super E> predicate = o -> !b.contains(o);
		Stream<E> as = a.stream();
		// Stream<E> bs = b.stream();
		Stream<E> ab = as.filter(predicate);
		Supplier<Stream<E>> s = Replayer.replay(ab);
		return new ImmutableStreamCollectionImpl<>(s);
	}

	public static <E> Collection<E> antiIntersection(Collection<E> a, Collection<E> b) {
		Predicate<? super E> aPredicate = o -> b.contains(o);
		Predicate<? super E> bPredicate = o -> a.contains(o);
		Stream<E> as = a.stream().filter(aPredicate);
		Stream<E> bs = b.stream().filter(bPredicate);
		Stream<E> ab = Stream.concat(as, bs);
		Supplier<Stream<E>> s = Replayer.replay(ab);
		return new ImmutableStreamCollectionImpl<>(s);
	}

	public static <E> Collection<Collection<E>> subsets(Collection<E> c, int n) {
		//if (c.size() < n)return null;
		if (c.size() < n)return Collections.emptyList();
		// Stream<E>s = c.stream();
		// Supplier<Stream<E>> r = Replayer.replay(s);
		// Function<Integer, Collection<E>> mapper = i ->
		// r.get().skip(i).limit(n).collect(Collectors.toList());
		// Stream<Collection<E>> ss = Stream.iterate(0, x -> x + 1).limit
		// (c.size()-n).map(mapper );
		// return ss.collect(Collectors.toList());
		return collectTheCollectors(substreams(c, n));
	}

	public static <E> Collection<Collection<E>> superset(Collection<E> c) {
		return collectTheCollectors(superstream(c));
	}

	public static <E> Collection<Collection<E>> collectTheCollectors(Stream<Stream<E>> ss) {
		return ss.map(s -> s.collect(Collectors.toList())).collect(Collectors.toList());
	}

	/**
	 * https://stackoverflow.com/questions/25311535/generate-one-stream-from-multiple-suppliers
	 * 
	 * @param c
	 * @param n
	 * @return
	 */
	public static <E> Stream<Stream<E>> substreams(Collection<E> c, int n) {
		if (n == 0 || c.isEmpty() || c.size() < n)
			//return Optional.empty();
			return null;
		Stream<E> s = c.stream();
		Supplier<Stream<E>> r = Replayer.replay(s);
		Function<Integer, Stream<E>> mapper = i -> r.get().skip(i).limit(n);
		Stream<Stream<E>> ss = Stream.iterate(0, x -> x + 1).limit(Math.max(c.size() - n,0)).map(mapper);
		//return Optional.of(ss);
		return ss;
	}

	public static <E> Stream<Stream<E>> superstream(Collection<E> c) {
		//Function<Integer, Optional<Stream<Stream<E>>>> mapper = n -> substreams(c, n);
		//return Stream.iterate(0, x -> x + 1).limit(c.size()).flatMap(mapper);
		if (c.isEmpty())return null;
		Function<Integer, Stream<Stream<E>>> mapper = n -> substreams(c, n);
		return Stream.iterate(0, x -> x + 1).limit(c.size()).flatMap(mapper);
	}

	public static <E> Supplier<Stream<Supplier<Stream<E>>>> randomSubstreams(Collection<E> c, int n, Random random) {
		//if (c.size() < n)return Optional.empty();
		if (n == 0 || c.isEmpty() || c.size() < n)
return null;
		Stream<Supplier<Stream<E>>> sss = substreams(c, n).map(s -> BagUtil.getRandomStreamSupplier(s, random));
		//return Optional.of(BagUtil.getRandomStreamSupplier(sss, random));
		return BagUtil.getRandomStreamSupplier(sss, random);
	}

	public static <E> Supplier<Stream<Supplier<Stream<E>>>> randomSuperstream(Collection<E> c, Random random) {
		if (c.isEmpty())return null;
		Stream<Supplier<Stream<E>>> sss = superstream(c).map(s -> BagUtil.getRandomStreamSupplier(s, random));
		return BagUtil.getRandomStreamSupplier(sss, random);
	}

	public static <E> Supplier<Supplier<Stream<Supplier<Stream<E>>>>> randomSubsetSupplier(Collection<E> c,
			IntSupplier ns, Random random) {
		if (c.isEmpty())return null;
		//System.out.println("randomSubsetSupplier 0");
		Function<Integer, Supplier<Stream<Supplier<Stream<E>>>>> f0 = n -> randomSubstreams(c, n, random);
		//Function<Integer, Supplier<Stream<Supplier<Stream<E>>>>> f0 = n -> n < c.size() ? Optional.empty() : Optional.ofNullable(randomSubstreams(c, n, random));
		//Function<Integer, Supplier<Stream<Supplier<Stream<E>>>>> f0 = n -> randomSubstreams(c, Math.min(n, c.size()), random);
		//System.out.println("randomSubsetSupplier 1");
		Function<Integer, Supplier<Stream<Supplier<Stream<E>>>>> f = Memoizer.memoize(f0);
		//System.out.println("randomSubsetSupplier 2");
		return () -> f.apply(ns.getAsInt());
	}

	public static <E> Stream<Supplier<Stream<E>>> randomSubsetsSupplier (Collection<E>c,IntSupplier ns,Random random){
	//	System.out.println("randomSubsetsSupplier 0");
		if (c.isEmpty())return null;
		Stream<Supplier<Stream<Supplier<Stream<E>>>>> stream = Stream.generate(randomSubsetSupplier(c, ns, random));
		Function<? super Supplier<Stream<Supplier<Stream<E>>>>, ? extends Stream<Supplier<Stream<E>> >> mapper=s->s.get();
		//System.out.println(c.size());
		//stream.forEach(s -> s.get().forEach(ss -> ss.get().forEach(System.out::println)));
		//System.out.println("randomSubsetsSupplier 1");
		Stream<Supplier<Stream<E>>> stream2 = stream.flatMap( mapper);
		//System.out.println("randomSubsetsSupplier 2");
		return stream2;
	}
}
