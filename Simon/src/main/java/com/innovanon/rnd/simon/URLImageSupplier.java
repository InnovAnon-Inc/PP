/**
 * 
 */
package com.innovanon.rnd.simon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

/**
 * @author gouldbergstein
 *
 */
public class URLImageSupplier implements Supplier<Image> {

	/**
	 * 
	 */
	private Supplier<URL> urls;
	/**
	 * 
	 */
	private Supplier<String> userAgents;

	/**
	 * @param urlspecs
	 * @param userAgents
	 */
	public URLImageSupplier(Supplier<URL> urlspecs, Supplier<String> userAgents) {
		this.urls = urlspecs;
		this.userAgents = userAgents;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Image get() {
		/*String urlspec = urlspecs.get();
		URL url;
		try {
			url = new URL(urlspec);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}*/
		URL url = urls.get();
		URLConnection connection;
		try {
			connection = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
		String userAgent = userAgents.get();
		connection.setRequestProperty("User-Agent", userAgent);
		BufferedImage c;
		try {
			c = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
		// ImageIcon image = new ImageIcon(c);
		// return image;
		return c;
	}
}
