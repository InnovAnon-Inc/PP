/**
 * 
 */
package com.innovanon.rnd.simon.pixabay;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBException;

import com.innovanon.rnd.net.ua.UserAgentSupplier;

/**
 * @author gouldbergstein
 *
 */
public class PixabayImageURLSupplier implements Supplier<Stream<URL>> {

	private Supplier<URL> urls;
	private Supplier<String> userAgents;

	
	/**
	 * @param urls
	 */
	public PixabayImageURLSupplier(Supplier<URL> urls, Supplier<String>userAgents) {
		this.urls = urls;
		this.userAgents=userAgents;
	}
	

	public PixabayImageURLSupplier(Random random) throws IOException, JAXBException {
		this(new PixabayURLSupplier(random), new UserAgentSupplier(random) );
	}

	@Override
	public Stream<URL> get() {
		Stream<URL> ret;
		do {
			URL url = urls.get();
			String userAgent = userAgents.get();
			ret = PixabayURLUtil.getImageURLs(url, userAgent);
		} while (ret == null);
		return ret;
	}
}
