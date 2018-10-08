/**
 * 
 */
package com.innovanon.rnd.simon.pixabay;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.javasync.streams.Replayer;

import com.innovanon.rnd.at.Todo;
import com.innovanon.rnd.struct.memo.Memoizer;
import com.innovanon.rnd.struct.pair.ImmutablePairImpl;
import com.innovanon.rnd.struct.pair.Pair;

/**
 * @author gouldbergstein
 *
 */
public enum PixabayURLUtil {
	/* no instances */ ;

	/**
	 * 
	 */
	@Todo("specialized errors")
	private static Function<Pair<URL,String>, Supplier<Stream<URL>>> userAgents = Memoizer.memoize(uax -> {
		try {
			return Replayer.replay(getUserAgentsHelper (uax));
		} catch (JAXBException | IOException e) {
			// TODO Auto-generated catch block
			throw new Error (e);
		}
	});
	
	public static Stream<URL> getImageURLs (URL url, String userAgent) {
		return userAgents.apply(new ImmutablePairImpl<URL, String>(url,userAgent)).get();
	}

	/**
	 * 
	 * @return
	 * @throws JAXBException
	 * @throws IOException 
	 */
	private static Stream<URL> getUserAgentsHelper(Pair<URL,String> p) throws JAXBException, IOException {
		URL url = p.getCar();
		String userAgent = p.getCdr();
		
		JAXBContext jaxbContext = JAXBContext.newInstance(PixabayResult.class);
		Unmarshaller jaxbMarshaller = jaxbContext.createUnmarshaller();

		URLConnection c = url.openConnection();
		c.setRequestProperty("User-Agent", userAgent);
		
		PixabayResult ua = (PixabayResult) jaxbMarshaller.unmarshal(c.getInputStream());
		if (ua.total == 0)return null;
		List<Hit> list = ua.hits;
		assert list != null;
		//List<String> temp = list.stream().map(u -> u.string).collect(Collectors.toList());
		//userAgents = Collections.unmodifiableList(temp);
		//return userAgents;
		//Stream<URL> s = list.stream().map(u -> u.largeImageURL);
		Stream<URL> s = list.stream().map(u -> u.largeImageURL).map(u -> {
			try {
				return new URL(u);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				throw new Error(e);
			}
		});
		//return Replayer.replay(s);
		return s;
	}
}
