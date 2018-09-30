/**
 * 
 */
package com.innovanon.rnd.simon;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

	public UserAgentSupplier(Random random) {
		// TODO Auto-generated constructor stub
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UserAgents.class);
			Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			URL url = classloader.getResource("allagents.xml");
			UserAgents userAgents = (UserAgents) jaxbMarshaller.unmarshal(url);
			List<UserAgent> list = userAgents.userAgents;
			assert list!=null;
			Set<String> set = list.stream().map(ua -> ua.string).collect(Collectors.toSet());
			this.userAgents = new BagImpl<String>(set, random);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new Error(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public String get() {
		return userAgents.remove();
	}
}
