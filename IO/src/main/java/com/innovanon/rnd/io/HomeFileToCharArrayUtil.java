/**
 * 
 */
package com.innovanon.rnd.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

import com.innovanon.rnd.struct.memo.Memoizer;

/**
 * @author gouldbergstein
 *
 */
public enum HomeFileToCharArrayUtil {
	/* no instances */ ;

	private static Function<String, char[]> userAgents = Memoizer.memoize(uax -> getUserAgentsHelper(uax));

	public static char[] getData(String filename) {
		return userAgents.apply(filename);
	}

	private static char[] getUserAgentsHelper(String filename) {
		return getDataHelper(new File(System.getProperty("user.home"), filename));
	}

	private static char[] getDataHelper(File file) {
		FileReader fis;
		try {
			fis = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
		BufferedReader input = null;
		char[] cbuf;
		int off;
		try {
			input = new BufferedReader(fis);
			long length = file.length();
			assert length <= Integer.MAX_VALUE;
			// cbuf = CharBuffer.allocate((int) length);
			int len = (int) length;
			off = 0;
			cbuf = new char[len];
			try {
				int rd;
				do {
					rd = input.read(cbuf, off, len);
					off += rd;
					len -= rd;
				} while (rd > 0);
				// assert rd == -1;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new Error(e);
			}
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
		}
		char[] ret = new char[off];
		System.arraycopy(cbuf, 0, ret, 0, off);
		return ret;
	}
}
