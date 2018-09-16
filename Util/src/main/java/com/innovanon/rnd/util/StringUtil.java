/**
 * 
 */
package com.innovanon.rnd.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

/**
 * @author gouldbergstein
 *
 */
public enum StringUtil {
	;
	/**
	 * https://stackoverflow.com/questions/19198048/how-to-repeat-string-n-times-in-java
	 * 
	 * @param str
	 * @param n
	 * @return
	 */
	public static String repeatv1(String str, int n) {
		return String.valueOf(new char[n]).replace("\0", str);
	}

	/**
	 * @param str
	 * @param n
	 * @return
	 */
	public static String repeatv2(String str, int n) {
		CharSequence delimiter = "";
		Iterable<CharSequence> elements = Collections.nCopies(n, str);
		return String.join(delimiter, elements);
	}

	public static String toString(Object object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (object == null)
			return null;
		if (object instanceof String)
			return object.toString();
		Class<?> cls = object.getClass();
		if (cls.isArray()) {
			Class<?> ct = cls.getComponentType();
			if (ct.equals(int.class))
				return Arrays.toString((int[]) object);
			if (ct.equals(short.class))
				return Arrays.toString((short[]) object);
			if (ct.equals(long.class))
				return Arrays.toString((long[]) object);
			if (ct.equals(double.class))
				return Arrays.toString((double[]) object);
			if (ct.equals(float.class))
				return Arrays.toString((float[]) object);
			if (ct.equals(boolean.class))
				return Arrays.toString((boolean[]) object);
			if (ct.equals(byte.class))
				return Arrays.toString((byte[]) object);
			if (ct.equals(char.class))
				return Arrays.toString((char[]) object);
			// TODO
			return Arrays.deepToString((Object[]) object);
		}
		return toString(object, new Method[0]);
	}

	public static String toString(Object object, Method[] accessors) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if (object == null)
			return null;
		StringBuilder ret = new StringBuilder();

		Class<?> cls = object.getClass();
		ret.append(cls.getName());
		ret.append('[');
		ret.append(invocationToString("toString", object));
		for (int index = 0; index < accessors.length; index++) {
			Method accessor = accessors[index];
			// if (index > 0)
			ret.append(", ");
			ret.append(invocationToString(accessor, object));
		}
		ret.append(']');
		return ret.toString();
	}

	public static String invocationToString(Method accessor, Object object)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		StringBuilder ret = new StringBuilder();
		Object returnValue;
		try {
			returnValue = accessor.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// throw new MethodInvocationError(e);
			throw e;
		}
		// if (index > 0)
		// ret.append(", ");
		ret.append(accessor.getName());
		ret.append('=');
		String returnValueStr = toString(returnValue);
		ret.append(returnValueStr);
		return ret.toString();
	}

	public static String invocationToString(String accessorName, Object object) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> cls = object.getClass();
		Method accessor;
		try {
			accessor = cls.getMethod(accessorName);
		} catch (NoSuchMethodException | SecurityException e) {
			// throw new GetMethodError(e);
			throw e;
		}
		return invocationToString(accessor, object);
	}
	
	public static String toString2(Object object) {
		if (object == null)
			return null;
		Class<?> cls = object.getClass();
		if (cls.isArray()) {
			Class<?> ct = cls.getComponentType();
			if (ct.equals(int.class))
				return Arrays.toString((int[]) object);
			if (ct.equals(short.class))
				return Arrays.toString((short[]) object);
			if (ct.equals(long.class))
				return Arrays.toString((long[]) object);
			if (ct.equals(double.class))
				return Arrays.toString((double[]) object);
			if (ct.equals(float.class))
				return Arrays.toString((float[]) object);
			if (ct.equals(boolean.class))
				return Arrays.toString((boolean[]) object);
			if (ct.equals(byte.class))
				return Arrays.toString((byte[]) object);
			if (ct.equals(char.class))
				return Arrays.toString((char[]) object);
			//return Arrays.deepToString((Object[]) object);
			return arrayToString ((Object[])object, StringUtil::toString2);
		}
		return object.toString();
	}

	public static <T> String arrayToString(T[] array, Function<T,String> toString) {
		StringBuilder ret = new StringBuilder();
		ret.append('[');
		boolean flag = true;
		for (T elem : array) {
			if (flag) {
				ret.append(", ");
				flag = false;
			}
			String append = toString.apply (elem);
			ret.append(append);
		}
		ret.append(']');
		return ret.toString();
	}
}
