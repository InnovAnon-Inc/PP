/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public enum WordListUtil {
	/* no instances */ ;

	private static Function<Locale, Supplier<Stream<String>>> userAgents = Memoizer
			.memoize(uax -> Replayer.replay(getDataHelper(uax)));

	public static Stream<String> getData(Locale lang) {
		return userAgents.apply(lang).get();
	}

	private static Stream<String> getDataHelper(Locale lang) {
	Collection<File> files =	WordListsUtil.getData();
	Predicate<? super File> predicate=f -> f.getName().toLowerCase().contains(lang.getDisplayLanguage().toLowerCase());
	//System.out.println(lang.getDisplayLanguage());
	//files.stream().forEach(System.out::println);
	//files.stream().filter(predicate).forEach(System.out::println);
	return files.stream().filter(predicate).flatMap(f -> getLines (f));
	}
	
	private static Stream<String> getLines (File file) {
		assert file.exists();
		assert file.isFile();
		InputStream is;
		try {
			is = new FileInputStream (file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
		Reader r = new InputStreamReader(is);
		BufferedReader reader=null;
		try {reader= new BufferedReader(r);
		Stream<String> stream = reader.lines();
		return stream.collect(Collectors.toList()
				).stream();
		//return stream;
		} finally {
			try {
				if (reader != null)
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new Error(e);
			}
		}
	}
}
