package com.innovanon.rnd.ree;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

    	Random random = new Random();
		Supplier<String>dict = new DictionarySupplier(random );
		for (int k = 1; k <= 10; k ++)
			System.out.println(dict.get());

	
    }
}
