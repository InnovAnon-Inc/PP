/**
 * 
 */
package com.innovanon.rnd.net.ua;

import java.net.URL;
import java.util.Collection;
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
	;
	public static Collection<String> getUserAgents() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(UserAgents.class);
		Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource("allagents.xml");

		UserAgents userAgents = (UserAgents) jaxbMarshaller.unmarshal(url);
		List<UserAgent> list = userAgents.userAgents;
		assert list != null;
		Collection<String> set = list.stream().map(ua -> ua.string).collect(Collectors.toList());
		return set;
	}
}
