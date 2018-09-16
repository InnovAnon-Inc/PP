/**
 * 
 */
package com.innovanon.simon.util.function;

/**
 * @author gouldbergstein
 *
 */
@FunctionalInterface
public interface ByteSupplier {
	/**
	 * Gets a result.
	 *
	 * @return a result
	 */
	byte getAsByte();
}