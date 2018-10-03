/**
 * 
 */
package com.innovanon.rnd.ree.words;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author gouldbergstein
 *
 */
public enum WordListUtil {
	/* no instances */ ;

	/**
	 * 
	 */
	private static Collection<File> dictionary;

	/**
	 * 
	 * @return
	 */
	public static Collection<File> getDictionary() {
		if (dictionary != null)
			return dictionary;

		// TODO

		String pathname = String.format("%1$svar%1$slib%1$sdictionaries-common%1$swordlist", File.separator);
		File dir = new File(pathname);
		assert dir.isDirectory();
		File[] files = dir.listFiles();
		Collection<File> dictionaries = Arrays.asList(files);
		return dictionaries;
	}
}
