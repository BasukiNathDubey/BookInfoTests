package io.istio.bookinfo.repo.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.istio.bookinfo.rests.Response;

import io.istio.bookinfo.common.BookInfoCommon;
import io.istio.bookinfo.groups.GroupQuickTest;

public class Ratings03Tests extends BookInfoCommon{
	
	@BeforeClass
	public static void setupBeforeClass(){
		setProductPageCommon(BOOK_INFO_URL);
	}
	
	@Test
	@Category(GroupQuickTest.class)
	public void test01_getRatingsByProductId(){
		try {
			Response response = msgSdr.getRatingsByProductId(productId);
			assertTrue("calling test01_getRatingsByProductId(): ", response.getResponseCode()==HTTP_OK);
			verifyRatings(response);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | ParseException e) {
			fail("Failed calling test01_getRatingsByProductId()");
		}
	}

}
