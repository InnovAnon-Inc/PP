/**
 * 
 */
package com.innovanon.rnd.simon;

import java.net.URL;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author gouldbergstein
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="user-agents")
 class UserAgents {
	//@XmlList
	@XmlElement(name="user-agent",type=UserAgent.class)
	List<UserAgent> userAgents;
}

@XmlAccessorType(XmlAccessType.FIELD)
class UserAgent {
	@XmlElement(name="ID")
	String ID;
	@XmlElement(name="String")
	String string;
	@XmlElement(name="Description")
	String description;
	@XmlElement(name="Type")
	String type;
	@XmlElement(name="Link1")
	URL link1;
	@XmlElement(name="Link2")
	URL link2;

}
