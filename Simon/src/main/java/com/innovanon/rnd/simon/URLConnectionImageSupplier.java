/**
 * 
 */
package com.innovanon.rnd.simon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBException;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.net.ua.UserAgentSupplier;
import com.innovanon.rnd.simon.pixabay.PixabayImageURLSupplier;
import com.innovanon.rnd.struct.bag.BagUtil;
import com.innovanon.rnd.struct.stream.StreamUtil;

/**
 * @author gouldbergstein
 *
 */
public class URLConnectionImageSupplier implements Supplier<Image> {

	/**
	 * 
	 */
	private Supplier<URL>urls;
	private Supplier<String> userAgents;
	

	/**
	 * @param connections
	 * @param userAgents 
	 */
	public URLConnectionImageSupplier(Supplier<URL> connections, Supplier<String> userAgents) {
		this.urls = connections;
		this.userAgents=userAgents;
	}
	
	private static Supplier<URL>getConnections(Random random) throws IOException, JAXBException{
		Supplier<Stream<URL>> s = new PixabayImageURLSupplier(random);
		Supplier<Stream<URL>> s2 = () -> {
			Stream<URL> temp ;
			do temp = s.get();
			while (temp == null);
			return temp;
		};
		Supplier<Stream<URL>> s3 = () -> BagUtil.getRandomStream(s2.get(), random);
		// TODO seems a bit wasteful
		@Todo("wasteful")
		Supplier<URL> s4 = () -> s3.get().findFirst().get();
		return s4;
		}
	
	public URLConnectionImageSupplier(Random random) throws JAXBException, IOException {
		this(
		getConnections(random)	, 
				new UserAgentSupplier(random));
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
			URLConnection u = url.openConnection();
			String userAgent = userAgents.get();
			u.setRequestProperty("User-Agent", userAgent );
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
