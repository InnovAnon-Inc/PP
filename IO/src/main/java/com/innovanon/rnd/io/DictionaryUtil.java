/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public enum DictionaryUtil {
	/* no instances */ ;

	private static Function<String, Supplier<Stream<String>>> userAgents = Memoizer
			.memoize(uax -> Replayer.replay(getDictionaryHelper(uax)));

	public static Stream<String> getDictionary(String resource) {
		return userAgents.apply(resource).get();
	}

	/**
	 * 
	 * @return
	 */
	@Todo("filter out comments")
	private static Stream<String> getDictionaryHelper(String resource) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream(resource);
		Reader r = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(r);
		Stream<String> stream = reader.lines();
//		dictionary=stream;
		// TODO filter out comments
		// List<String> temp = stream.collect(Collectors.toList());
		// dictionary = Collections.unmodifiableList(temp);
		// return dictionary;
		return stream;
	}
}
