/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public enum WordListUtil {
	/* no instances */ ;

	private static Function<String, Stream<File>> userAgents = Memoizer
			.memoize(Replayer.replay(uax -> getDataHelper(uax)));

	public static Stream<File> getData() {
		String pathname = String.format("%1$svar%1$slib%1$sdictionaries-common%1$swordlist", File.separator);
		return getData(pathname);
	}

	public static Stream<File> getData(String filename) {
		return userAgents.apply(filename);
	}

	private static Stream<File> getDataHelper(String pathname) {
		File dir = new File(pathname);
		assert dir.isDirectory();
		File[] files = dir.listFiles();
		return Stream.of(files);
	}
}
