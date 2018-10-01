/**
 * 
 */
package com.innovanon.rnd.ree.dict;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gouldbergstein
 *
 */
public enum DictionaryUtil {
	/* no instances */ ;

	/**
	 * 
	 */
	private static Collection<String> dictionary;

	/**
	 * 
	 * @return
	 */
	public static Collection<String> getDictionary() {
		if (dictionary != null)
			return dictionary;
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("all");
		Reader r = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(r);
		Stream<String> stream = reader.lines();
		// TODO filter out comments
		List<String> temp = stream.collect(Collectors.toList());
		dictionary = Collections.unmodifiableList(temp);
		return dictionary;
	}
}
