/**
 * 
 */
package com.innovanon.rnd.simon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Locale;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.innovanon.rnd.ri.suppliers.special.EnumSubsetSupplier;
import com.innovanon.rnd.ri.suppliers.special.EnumSupplier;
import com.innovanon.rnd.struct.pair.Pair;

enum QueryLang {
	CS("cs"), DA("da"), DE("de"), EN("en"), ES("es"), FR("fr"), ID("id"), IT("it"), HU("hu"), NL("nl"), NO("no"),
	PL("pl"), PT("pt"), RO("ro"), SK("sk"), FI("fi"), SV("sv"), TR("tr"), VI("vi"), TH("th"), BG("bg"), RU("ru"),
	EL("el"), JA("ja"), KO("ko"), ZH("zh");
	/**
	 * 
	 */
	private String value;
	
	private Locale locale;

	/**
	 * @param value
	 */
	QueryLang(String value) {
		this.value = value;
		this.locale = Locale.forLanguageTag(value);
	}

	public String getValue() {
		return value;
	}

	public Locale getLocale() {
		return locale;
	}
}

enum QueryType {
	ALL("all"),PHOTO("photo"),ILLUSTRATION("illustration"),VECTOR("vector");
	private String value;
	QueryType(String value){this.value=value;}

	public String getValue() {
		return value;
	}}

enum QueryOrientation {
	ALL("all"),HORIZONTAL("horizontal"),VERTICAL("vertical");
	private String value;
	QueryOrientation(String value){this.value=value;}

	public String getValue() {
		return value;
	}}

enum QueryCategory {
	FASHION("fashion"),NATURE("nature"),BACKGROUNDS("backgrounds"),
	SCIENCE("scince"),EDUCATION("education"),PEOPLE("people"),FEELINGS("feelings"),RELIGION("religion"),
	HEALTH("health"),PLACES("places"),ANIMALS("animals"),INDUSTRY("industry"),FOOD("food"), 
	COMPUTER("computer"),SPORTS("sports"),TRANSPORTATION("transportation"),TRAVEL("travel"),
	BUILDINGS("buildings"),BUSINESS("business"),MUSIC("music");
	private String value;
	QueryCategory(String value){this.value=value;}

	public String getValue() {
		return value;
	}}

enum QueryColor {
	GRAYSCALE("grayscale"), TRANSPARENT("transparent"), RED("red"), ORANGE("orange"), YELLOW("yellow"), 
	GREEN("green"), TURQUOISE("turquoise"), BLUE("blue"), LILAC("lilac"),
	PINK("pink"),WHITE( "white"), GRAY("gray"), BLACK("black"), BROWN("brown") 
	;private String value;
	QueryColor(String value){this.value=value;}

	public String getValue() {
		return value;
	}
}

enum QueryChoice {
	TRUE("true"),FALSE("false");
	;private String value;
	QueryChoice(String value){this.value=value;}

	public String getValue() {
		return value;
	}
}

enum QuerySafe {
	TRUE("true"),FALSE("false");
	;private String value;
	QuerySafe(String value){this.value=value;}

	public String getValue() {
		return value;
	}
}

enum QueryOrder {POPULAR("popular"),LATEST("latest");
	;private String value;
	QueryOrder(String value){this.value=value;}

	public String getValue() {
		return value;
	}
}

/**
 * @author gouldbergstein
 *
 */
public class PixabayURLSupplier implements Supplier<URL> {
	private String base = "https://pixabay.com/api";

	private String key;

	private Supplier<Pair<QueryLang, Collection<String>>> queries;

	private Supplier<QueryType> types;

	private Supplier<QueryOrientation> orientations;

	private Supplier<QueryCategory> categories;

	private IntSupplier minWidths;
	private IntSupplier minHeights;

	private Supplier<Collection<QueryColor>> colors;

	private Supplier<QueryChoice> choices;
	private Supplier<QuerySafe> safes;
	private Supplier<QueryOrder> orders;

	private IntSupplier pages;
	private IntSupplier perPages;

	private static String getKey() throws FileNotFoundException, IOException {
		return String.valueOf(HomeFileToCharArrayUtil.getData(".pixabay"));
	}

	/**
	 * @param base
	 * @param key
	 * @param queries
	 * @param langs
	 * @param types
	 * @param orientations
	 * @param categories
	 * @param minWidths
	 * @param minHeights
	 * @param colors
	 * @param choices
	 * @param safes
	 * @param orders
	 * @param pages
	 * @param perPages
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public PixabayURLSupplier(Supplier<Pair<QueryLang,Collection<String>> >queries,  Supplier<QueryType> types,
			Supplier<QueryOrientation> orientations, Supplier<QueryCategory> categories, IntSupplier minWidths,
			IntSupplier minHeights, Supplier<Collection<QueryColor>> colors, Supplier<QueryChoice> choices,
			Supplier<QuerySafe> safes, Supplier<QueryOrder> orders, IntSupplier pages, IntSupplier perPages)
			throws FileNotFoundException, IOException {
		this.key = getKey();
		this.queries = queries;
		this.types = types;
		this.orientations = orientations;
		this.categories = categories;
		this.minWidths = minWidths;
		this.minHeights = minHeights;
		this.colors = colors;
		this.choices = choices;
		this.safes = safes;
		this.orders = orders;
		this.pages = pages;
		this.perPages = perPages;
	}

	public PixabayURLSupplier(Supplier<Pair<QueryLang,Collection<String>>> queries,  IntSupplier minWidths,
			IntSupplier minHeights, IntSupplier pages, IntSupplier perPages, Random random) throws FileNotFoundException, IOException {
		this(queries, new EnumSupplier<QueryType>(QueryType.class, random),
				new EnumSupplier<QueryOrientation>(QueryOrientation.class, random),
				new EnumSupplier<QueryCategory>(QueryCategory.class, random), minWidths, minHeights,
				new EnumSubsetSupplier<QueryColor>(QueryColor.class, random),
				new EnumSupplier<QueryChoice>(QueryChoice.class, random),
				new EnumSupplier<QuerySafe>(QuerySafe.class, random),
				new EnumSupplier<QueryOrder>(QueryOrder.class, random), pages, perPages);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.function.Supplier#get()
	 */
	@Override
	public URL get() {
		String enc = StandardCharsets.UTF_8.name();
		try {
			Pair<QueryLang, Collection<String>> k = queries.get();
			String q = 
			String.join("+",  k.getCdr().stream().map(s -> {
				try {
					return URLEncoder.encode(s, enc);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					throw new Error(e);
				}
			}).collect(Collectors.toList()));
			String lang = URLEncoder.encode(k.getCar().getValue(), enc);
			String image_type = URLEncoder.encode(types.get().getValue(), enc);
			String orientation = URLEncoder.encode(orientations.get().getValue(), enc);
			String category = URLEncoder.encode(categories.get().getValue(), enc);
			int min_width = minWidths.getAsInt();
			int min_height = minHeights.getAsInt();
			String colors = 
					String.join(",",  this.colors.get().stream().map(QueryColor::getValue).map(s -> {
						try {
							return URLEncoder.encode(s, enc);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							throw new Error(e);
						}
					}).collect(Collectors.toList()));
			String editors_choice = URLEncoder.encode(choices.get().getValue(), enc);
			String safesearch = URLEncoder.encode(safes.get().getValue(), enc);
			String order = URLEncoder.encode(orders.get().getValue(), enc);
			int page = pages.getAsInt();
			int per_page = perPages.getAsInt();
			String s = String.format(
					"%s?key=%s&q=%s&lang=%s&image_type=%s&orientation=%s&category=%s&min_width=%s&min_height=%s&colors=%s&editors_choice=%s&safesearch=%s&order=%s&page=%s&per_page=%s",
					base, key, q, lang, image_type, orientation, category, min_width, min_height,
					colors, editors_choice, safesearch, order, page, per_page);
			//String encoded = URLEncoder.encode(s, "UTF-8");
			//return new URL(encoded);
			return new URL(s);
		} catch (UnsupportedEncodingException | MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new Error(e);
		}
	}

}
