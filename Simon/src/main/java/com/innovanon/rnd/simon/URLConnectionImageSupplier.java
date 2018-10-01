/**
 * 
 */
package com.innovanon.rnd.simon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.function.Supplier;

import javax.imageio.ImageIO;

/**
 * @author gouldbergstein
 *
 */
public class URLConnectionImageSupplier implements Supplier<Image> {

	/**
	 * 
	 */
	private Supplier<URL>urls;
	

	/**
	 * @param connections
	 */
	public URLConnectionImageSupplier(Supplier<URL> connections) {
		this.urls = connections;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public Image get() {
		URL url = urls.get();
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
