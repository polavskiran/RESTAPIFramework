package resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.ArrayList;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	private String baseUri;
	private ContentType contentType;
	private String keyParam;
	private String valueParam;
	public static RequestSpecification req;
	public static PrintStream log;
	public JsonPath js;
	private static ArrayList<String> placeIds;
	public static File places;
	public static FileWriter fout;

	public String getBaseUri() {
		return baseUri;
	}

	public void setBaseUri(String baseUri) {
		this.baseUri = baseUri;
	}

	public ContentType getContentType() {
		return contentType;
	}

	public void setContentType(ContentType json) {
		this.contentType = json;
	}

	public String getKeyParam() {
		return keyParam;
	}

	public void setKeyParam(String keyParam) {
		this.keyParam = keyParam;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public String getJsonPath(Response resp, String key) {

		String response = resp.asString();
		js = new JsonPath(response);

		return js.getString(key).toString();
	}

	/*
	 * public ArrayList<String> getPlaceIds(){ return new
	 * ArrayList<String>(this.placeIds); }
	 * 
	 * public void setPlaceIds(String placeid) { this.placeIds = new
	 * ArrayList<String>(placeid); }
	 */

	/*
	 * @method requestSpecification
	 *
	 * @return RequestSpecification object
	 */
	public RequestSpecification requestSpecification() throws FileNotFoundException {

		setBaseUri("https://rahulshettyacademy.com");
		String baseUri = getBaseUri();

		setContentType(ContentType.JSON);
		String contentType = getContentType().toString();

		if (req == null) {
			log = new PrintStream(new FileOutputStream("Logging.txt"));

			req = new RequestSpecBuilder().setBaseUri(baseUri).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(contentType).build();

			return req;
		}

		return req;
	}

	public static ArrayList<String> addPlaceIds(String placeid) {
		String placeidspath = System.getProperty("user.dir") + "//src//test//resources//PlaceIds.txt";

		try {
			placeIds.add(placeid);
			places = new File(placeidspath);
			fout = new FileWriter(places);
			fout.write(placeid);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * System.out.println("Place Ids Are:"); for (int i = 0; i < placeIds.size();
		 * i++) { System.out.println(placeIds.get(i)); }
		 */

		return placeIds;
	}
}