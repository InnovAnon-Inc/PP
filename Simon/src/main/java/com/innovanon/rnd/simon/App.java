package com.innovanon.rnd.simon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import com.innovanon.rnd.rand.random.RandomRandumb;
import com.innovanon.rnd.ree.DiscreteSubsetSupplier;
import com.innovanon.rnd.ree.EnumSupplier;
import com.innovanon.rnd.ri.functions.special.BoundedIntFunction;
import com.innovanon.rnd.ri.suppliers.special.RangedIntSupplier;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		System.out.println("Hello World!");

		Random random = new Random();
		Supplier<String> userAgents = new UserAgentSupplier(random);
		for (int k = 1; k <= 10; k++)
			System.out.println(userAgents.get());

		String[] array;
		IntSupplier nterm = new RangedIntSupplier(random, 1,array.length);
		Supplier<Collection<String>> searchTerms = new DiscreteSubsetSupplier<String>(random, nterm, array);
		Supplier<QueryLang> langs = new EnumSupplier<QueryLang>(QueryLang.class, random);
		IntSupplier widths = new RangedIntSupplier(new RandomRandumb(random), 0, 100);
		IntSupplier heights = new RangedIntSupplier(new RandomRandumb(random), 0, 100);
		IntSupplier page = new RangedIntSupplier(new RandomRandumb(random), 0, 10);
		IntSupplier pageAmt = new RangedIntSupplier(new RandomRandumb(random), 3, 200);
		Supplier<URL> urls = new URLSupplier(userAgents, langs, widths, heights, page, pageAmt, random);
		for (int k = 1; k <= 10; k++)
			System.out.println(urls.get());

	}
}
