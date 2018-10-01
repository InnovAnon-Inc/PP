/**
 * 
 */
package com.innovanon.rnd.net.ua;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author gouldbergstein
 *
 */
public enum UserAgentUtil {
	/* no instances */ ;

	/**
	 * 
	 */
	private static Collection<String> userAgents;

	/**
	 * 
	 * @return
	 * @throws JAXBException
	 */
	public static Collection<String> getUserAgents() throws JAXBException {
		if (userAgents != null)
			return userAgents;
		JAXBContext jaxbContext = JAXBContext.newInstance(UserAgents.class);
		Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource("allagents.xml");

		UserAgents ua = (UserAgents) jaxbMarshaller.unmarshal(url);
		List<UserAgent> list = ua.userAgents;
		assert list != null;
		List<String> temp = list.stream().map(u -> u.string).collect(Collectors.toList());
		userAgents = Collections.unmodifiableList(temp);
		return userAgents;
	}
}
