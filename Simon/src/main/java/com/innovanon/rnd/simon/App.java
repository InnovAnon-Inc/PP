package com.innovanon.rnd.simon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

import com.innovanon.rnd.ree.DiscreteSubsetSupplier;
import com.innovanon.rnd.ree.EnumSupplier;
import com.innovanon.rnd.ri.suppliers.special.RangedIntSupplier;
import com.innovanon.rnd.simon.ua.UserAgentSupplier;

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

		String[] array= {"a","b","c"};
		//IntSupplier nterm = new RangedIntSupplier(random, 1,array.length - 1);
		Supplier<Collection<String>> searchTerms = new DiscreteSubsetSupplier<String>(random,1, array);
		/*
		  Supplier<String>queries = new Supplier<String>() {
		 

			@Override
			public String get() {
Collection<String>searchTerm = searchTerms.get();
				return String.join("+", searchTerm);
			}
			
		};*/
		Supplier<QueryLang> langs = new EnumSupplier<QueryLang>(QueryLang.class, random);
		IntSupplier widths = new RangedIntSupplier(random, 0, 100);
		IntSupplier heights = new RangedIntSupplier(random, 0, 100);
		IntSupplier page = new RangedIntSupplier(random, 0, 10);
		IntSupplier pageAmt = new RangedIntSupplier(random, 3, 200);
		Supplier<URL> urls = new PixabayURLSupplier(searchTerms, langs, widths, heights, page, pageAmt, random);
		for (int k = 1; k <= 10; k++)
			System.out.println(urls.get());

	}
}
