package io.istio.bookinfo.common;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.istio.bookinfo.rests.MessageGenerator;
import com.istio.bookinfo.rests.MessageSender;
import com.istio.bookinfo.rests.Response;

import sun.net.www.protocol.http.HttpURLConnection;

public class BookInfoCommon {

	protected static final String BOOK_INFO_URL = "http://104.211.49.92";
	protected static final long productId = 1;
	protected static final int HTTP_OK = HttpURLConnection.HTTP_OK;
	protected static final String product_descriptionHtml="<a href=\"https://en.wikipedia.org/wiki/The_Comedy_of_Errors\">"
			+"Wikipedia Summary</a>: The Comedy of Errors is one of <b>William Shakespeare's</b>"
			+ " early plays. It is his shortest and one of his most farcical comedies, with a major "
			+ "part of the humour coming from slapstick and mistaken identity, in addition to puns and word play.";

	protected static String product_test02_title = "The Comedy of Errors";
	
	protected static MessageSender msgSdr;
	protected static MessageGenerator msgGnr;

	public static void setProductPageCommon(String url){
		msgSdr = new MessageSender(url);
		msgGnr = new MessageGenerator();
	}
	
	
	
	public static void verifyReviewers(Response response) {
		JSONObject obj = response.getJsonResponse();
		JSONArray reviewersjson = (JSONArray) obj.get("reviews");
		assertTrue("check size:", reviewersjson.size()==2);
		for(int index=0; index < reviewersjson.size(); index++){
			switch(index){
			case 0:
				assertTrue(((JSONObject) reviewersjson.get(index)).get("reviewer").equals("Reviewer1"));
				assertTrue(((JSONObject) reviewersjson.get(index)).get("text").equals("An extremely entertaining play by Shakespeare. The slapstick humour is refreshing!"));
				break;
			case 1:
				assertTrue(((JSONObject) reviewersjson.get(index)).get("reviewer").equals("Reviewer2"));
				assertTrue(((JSONObject) reviewersjson.get(index)).get("text").equals("Absolutely fun and entertaining. The play lacks thematic depth when compared to other plays by Shakespeare."));
				break;
			default:
				fail();
			}
		}
	}

	public static boolean verifyReviewersRating(Response response, String version){
		boolean result = true;
		JSONObject obj = response.getJsonResponse();
		JSONArray reviewersjson = (JSONArray) obj.get("reviews");
		assertTrue("check size:", reviewersjson.size()==2);
		for(int index=0; index < reviewersjson.size(); index++){
			switch(index){
			case 0:
				result = result && ((JSONObject) reviewersjson.get(index)).get("reviewer").equals("Reviewer1");
				result = result && (((JSONObject) reviewersjson.get(index)).get("text").equals("An extremely entertaining play by Shakespeare. The slapstick humour is refreshing!"));
				break;
			case 1:
				result = result && (((JSONObject) reviewersjson.get(index)).get("reviewer").equals("Reviewer2"));
				result = result && (((JSONObject) reviewersjson.get(index)).get("text").equals("Absolutely fun and entertaining. The play lacks thematic depth when compared to other plays by Shakespeare."));
				break;
			default:
				result = result && false;
			}
			switch(version){
			case "v1" :
				result = result && !((JSONObject) reviewersjson.get(index)).containsKey("rating");
				break;
			case "v2" :
				boolean hasv2Key = ((JSONObject) reviewersjson.get(index)).containsKey("rating");
				if(hasv2Key){
					JSONObject jsonRating = (JSONObject) ((JSONObject) reviewersjson.get(index)).get("rating");
					result = result && jsonRating.get("color").equals("black");
				}else{
					result = result && hasv2Key;
				}
				break;
			case "v3" :
				boolean hasKey = ((JSONObject) reviewersjson.get(index)).containsKey("rating");
				if(hasKey){
					JSONObject jsonRating = (JSONObject) ((JSONObject) reviewersjson.get(index)).get("rating");
					result = result && jsonRating.get("color").equals("red");
				}else{
					result = result && hasKey;
				}
				break;

			}
		}
		return result;
	}

	protected static void verifyProductDescription(Response response) {
		JSONObject json = (JSONObject) response.getJsonArrResponse().get(0);
		assertTrue("matching product description :", json.get("descriptionHtml").equals(product_descriptionHtml));
	}
	
	protected static void verifyProductDetails(Response response) {
		JSONObject json = response.getJsonResponse();
		assertTrue("matching pages :", (long)json.get("pages") == 200 );
		assertTrue("matching pages :", (long)json.get("year") == 1595 );
		assertTrue("matching pages :", json.get("author").equals("William Shakespeare"));
		assertTrue("matching pages :", json.get("ISBN-13").equals("123-1234567890"));
		assertTrue("matching pages :", json.get("publisher").equals("PublisherA"));
		assertTrue("matching pages :", json.get("ISBN-10").equals("1234567890") );
		assertTrue("matching pages :", json.get("language").equals("English") );
		assertTrue("matching pages :", json.get("type").equals("paperback"));
		assertTrue("matching pages :", (long)json.get("id") == productId );
	}
	
	protected static void verifyProductTitle(Response response) {
		JSONObject json = (JSONObject) response.getJsonArrResponse().get(0);
		assertTrue("matching product title :", json.get("title").equals(product_test02_title));
	}

	protected static void verifyRatings(Response response) {
		JSONObject json = response.getJsonResponse();
		JSONObject rvwerJson = (JSONObject) json.get("ratings");
		assertTrue(rvwerJson.containsKey("Reviewer1"));
		assertTrue((long)rvwerJson.get("Reviewer1") == 5);
		assertTrue(rvwerJson.containsKey("Reviewer2"));
		assertTrue((long)rvwerJson.get("Reviewer2") == 4);
	}

}
