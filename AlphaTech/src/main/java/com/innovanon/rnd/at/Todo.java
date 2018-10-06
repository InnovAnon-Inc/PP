/**
 * 
 */
package com.innovanon.rnd.at;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;

/**
 * @author gouldbergstein
 *
 */
@Documented
@Retention(SOURCE)
@Repeatable(Todos.class)
public @interface Todo {
	String message ();
	String issueTrackerId () default "N/A";
}
