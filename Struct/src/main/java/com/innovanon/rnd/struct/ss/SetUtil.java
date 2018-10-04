/**
 * 
 */
package com.innovanon.rnd.struct.ss;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

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

	public static <E> Stream<Stream<E>> substreams(Collection<E> c, int n) {
		Stream<E> s = c.stream();
		Supplier<Stream<E>> r = Replayer.replay(s);
		Function<Integer, Stream<E>> mapper = i -> r.get().skip(i).limit(n);
		Stream<Stream<E>> ss = Stream.iterate(0, x -> x + 1).limit(c.size() - n).map(mapper);
		return ss;
	}

	public static <E> Stream<Stream<E>> superstream(Collection<E> c) {
		Function<Integer, Stream<Stream<E>>> mapper = n -> substreams(c, n);
		return Stream.iterate(0, x -> x + 1).limit(c.size()).flatMap(mapper);
	}
}
