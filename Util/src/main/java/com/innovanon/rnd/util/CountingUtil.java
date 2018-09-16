/**
 * 
 */
package com.innovanon.rnd.util;

/**
 * @author gouldbergstein
 *
 */
public enum CountingUtil {
	;
	public static int inclusiveRange(int min, int max) {
		return exclusiveRange(min, max) + 1;
	}

	public static int exclusiveRange(int min, int max) {
		return max - min;
	}
}
