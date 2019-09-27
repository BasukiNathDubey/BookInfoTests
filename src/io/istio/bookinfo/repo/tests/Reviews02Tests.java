package io.istio.bookinfo.repo.tests;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.json.simple.parser.ParseException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.istio.bookinfo.rests.Response;

import io.istio.bookinfo.common.BookInfoCommon;
import io.istio.bookinfo.groups.GroupQuickTest;


public class Reviews02Tests extends BookInfoCommon {
	
	@BeforeClass
	public static void setupBeforeClass(){
		setProductPageCommon(BOOK_INFO_URL);
	}

	@Test
	public void test01_getReviewsByProductId(){
		try {
			Response response = msgSdr.getReviewsByProductId(productId);
			assertTrue("calling test01_getReviewsByProductId(): ", response.getResponseCode()==HTTP_OK);
			System.out.println(response);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | ParseException e) {
			fail("Failed calling test01_getReviewsByProductId()");
		}
	}

	@Test
	public void test02_validateReviewers(){
		try {
			Response response = msgSdr.getReviewsByProductId(productId);
			assertTrue("calling test02_validateReviewers(): ", response.getResponseCode()==HTTP_OK);
			verifyReviewers(response);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | ParseException e) {
			fail("Failed calling test02_validateReviewers()");
		}
	}
	
	@Test
	@Category(GroupQuickTest.class)
	public void test03_validateRating(){
		try {
			Response response1 = msgSdr.getReviewsByProductId(productId);
			Response response2 = msgSdr.getReviewsByProductId(productId);
			Response response3 = msgSdr.getReviewsByProductId(productId);
			assertTrue("calling test03_validateRating(): ", response1.getResponseCode()==HTTP_OK);
			assertTrue("calling test03_validateRating(): ", response2.getResponseCode()==HTTP_OK);
			assertTrue("calling test03_validateRating(): ", response3.getResponseCode()==HTTP_OK);
			boolean v1Matched = verifyReviewersRating(response1, "v1") || verifyReviewersRating(response1, "v2") || verifyReviewersRating(response1, "v3");
			boolean v2Matched = verifyReviewersRating(response2, "v1") || verifyReviewersRating(response2, "v2") || verifyReviewersRating(response2, "v3");
			boolean v3Matched = verifyReviewersRating(response3, "v1") || verifyReviewersRating(response3, "v2") || verifyReviewersRating(response3, "v3");
			assertTrue("rating matched ", v1Matched && v2Matched && v3Matched);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | ParseException e) {
			fail("Failed calling test03_validateRating()");
		}
	}


	public static void main(String args[]){
		setupBeforeClass();
		Reviews02Tests tp = new Reviews02Tests();
		tp.test01_getReviewsByProductId();
	}

}
