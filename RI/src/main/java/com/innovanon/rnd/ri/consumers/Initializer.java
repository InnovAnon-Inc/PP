/**
 * 
 */
package com.innovanon.rnd.ri.consumers;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author gouldbergstein
 *
 */
public interface Initializer<T> extends Predicate<T>, Consumer<T>{

}
