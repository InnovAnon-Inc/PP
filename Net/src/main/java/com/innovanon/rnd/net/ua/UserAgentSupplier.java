/**
 * 
 */
package com.innovanon.rnd.net.ua;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;

import javax.xml.bind.JAXBException;

import com.innovanon.rnd.struct.bag.Bag;
import com.innovanon.rnd.struct.bag.BagImpl;

/**
 * @author gouldbergstein
 *
 */
public class UserAgentSupplier implements Supplier<String> {
	/**
	 * 
	 */
	private Bag<String> userAgents;
	private Iterator<String>delegate;

	public UserAgentSupplier(Random random) throws JAXBException {
		Collection<String> agents =UserAgentUtil.getUserAgents();
		assert !agents.isEmpty();
		this.userAgents = new BagImpl<>(agents,random);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public String get() {
		if (delegate==null||!delegate.hasNext())
			delegate = userAgents.iterator();
		assert delegate.hasNext();
		return delegate.next();
	}
}
