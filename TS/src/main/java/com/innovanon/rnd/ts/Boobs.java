/**
 * 
 */
package com.innovanon.rnd.ts;

/**
 * @author gouldbergstein
 *
 */
public class Boobs {

	/**
	 * 
	 */
	private static Boobs instance = new Boobs ();
	
	/**
	 * 
	 * @return
	 */
	public static Boobs getBoob () {
		return instance;
	}
	
	/**
	 * 
	 */
	private Boob left;
	/**
	 * 
	 */
	private Boob right;

	/**
	 * 
	 * @param left
	 * @param right
	 */
	public Boobs (Boob left, Boob right) {
		this.left = left;
		this.right=right;
	}
	
	public Boobs () { this (Boob.getBoob(),Boob.getBoob());}

	@Override
	public String toString() {
		return String.format("%s%s", left, right);
	}
}
