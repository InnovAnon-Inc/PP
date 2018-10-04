/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public enum WordListsUtil {
	/* no instances */ ;

	private static Function<String, Collection<File>> userAgents = Memoizer
			.memoize(uax -> getDataHelper(uax));
	

	public static Collection<File> getData() {
		//String pathname = String.format("%1$svar%1$slib%1$sdictionaries-common%1$swordlist", File.separator);
		// TODO are these being found?
		String pathname = String.format("%1$susr%1$sshare%1$sdict", File.separator);
		//String pathname = String.format("%1$s%2$sdicts", System.getProperty("user.home"), File.separator);
		return getData(pathname);
	}

	public static Collection<File> getData(String filename) {
		return userAgents.apply(filename);
	}

	private static Collection<File> getDataHelper(String pathname) {
		File dir = new File(pathname);
		assert dir.isDirectory();
		File[] files = dir.listFiles();
		//return Stream.of(files);
		return Arrays.asList(files);
	}
}
