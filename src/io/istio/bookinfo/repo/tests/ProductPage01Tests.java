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

import io.istio.bookinfo.groups.GroupSanityAccept;
import io.istio.bookinfo.common.BookInfoCommon;
import io.istio.bookinfo.groups.GroupQuickTest;


public class ProductPage01Tests extends BookInfoCommon {

	@BeforeClass
	public static void setupBeforeClass(){
		setProductPageCommon(BOOK_INFO_URL);
	}

	@Test
	public void test01Products(){
		try {
			Response response = msgSdr.getProducts();
			assertTrue("calling getProducts(): ", response.getResponseCode()==HTTP_OK);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | ParseException e) {
			fail("Failed calling test01Products()");
		}
	}
	
	@Test
	@Category(GroupSanityAccept.class)
	public void test02ProductTitle(){
		try {
			Response response = msgSdr.getProducts();
			assertTrue("calling test02ProductTitle(): ", response.getResponseCode()==HTTP_OK);
			verifyProductTitle(response);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | ParseException e) {
			fail("Failed calling test02ProductTitle()");
		}
	}

	@Test
	@Category(GroupQuickTest.class)
	public void test03ProductDescriptionHtml(){
		try {
			Response response = msgSdr.getProducts();
			assertTrue("calling test03ProductDescriptionHtml(): ", response.getResponseCode()==HTTP_OK);
			verifyProductDescription(response);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | ParseException e) {
			fail("Failed calling test03ProductDescriptionHtml()");
		}
	}
	
	@Test
	@Category(GroupQuickTest.class)
	public void test04getProductById(){
		try {
			Response response = msgSdr.getProductById(productId);
			assertTrue("calling test04getProductById(): ", response.getResponseCode()==HTTP_OK);
			verifyProductDetails(response);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | ParseException e) {
			fail("Failed calling test04getProductById()");
		}
	}

	public static void main(String args[]){
		setupBeforeClass();
		ProductPage01Tests tp = new ProductPage01Tests();
		tp.test01Products();
	}

}
