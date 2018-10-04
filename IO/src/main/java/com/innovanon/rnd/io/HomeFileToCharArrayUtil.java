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
import com.innovanon.rnd.util.CountingUtil;

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

		long length = file.length();
		assert length <= Integer.MAX_VALUE;
		// cbuf = CharBuffer.allocate((int) length);
		int len = (int) length;
		int off;
		char[] cbuf = new char[len];

		try {
			input = new BufferedReader(fis);
			off = IOUtil.r_read(input, cbuf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
		}
		// if (off != 0 && Character.isWhitespace(cbuf[off-1]))
		// off--;
		// char[] ret = new char[off];
		// System.arraycopy(cbuf, 0, ret, 0, off);
		//// assert !String.valueOf(ret).contains("\n");
		//// assert !String.valueOf(ret).contains("\0");
		// System.out.println((int)(ret[off-1]));
		// return ret;
		return trim(cbuf, 0, off - 1);
	}

	public static char[] trim(char[] buf) {
		return trim(buf, 0, buf.length - 1);
	}

	public static char[] trim(char[] buf, int off, int len) {
		// System.out.println(off);
		while (off < len && (Character.isWhitespace(buf[off]) /*|| buf[off] == '\0'||buf[off]==0*/))
			off++;
		while (len > off && (Character.isWhitespace(buf[len]) /*|| buf[len] == '\0'||buf[len]==0*/))
			len--;
		assert buf[len]!='\0';
		assert buf[len]!=0;
		assert len >= 0;
		assert off <= buf.length;
		assert off <= len;
		char[] ret = new char[CountingUtil.inclusiveRange(off, len)];
		assert ret.length <= buf.length;
		assert ret.length == len - off + 1;
		// System.out.println(Arrays.toString(buf));
		// System.out.println(buf.length);
		// System.out.println(off);
		// System.out.println(len);
		// System.out.println(ret.length);
		System.arraycopy(buf, off, ret, 0, len + 1);
		//for (int i = 0; i < ret.length; i++) 
		//System.out.println((int)ret[i]);
		return ret;
	}
}
