/**
 * 
 */
package com.innovanon.rnd.net.ua;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

import javax.xml.bind.JAXBException;

import com.innovanon.rnd.struct.bag.BagImpl;
import com.innovanon.rnd.struct.iter.Reiterator;

/**
 * @author gouldbergstein
 *
 */
public class UserAgentSupplier implements Supplier<String> {
	/**
	 * 
	 */
	private Iterator<String> iter;

	public UserAgentSupplier(Random random) throws JAXBException {
		Collection<String> agents = UserAgentUtil.getUserAgents();
		assert !agents.isEmpty();
		// this.userAgents = new BagImpl<>(agents,random);
		Iterable<String> iterable = new BagImpl<>(agents, random);
		iter = new Reiterator<>(iterable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public String get() {
		return iter.next();
	}
}
