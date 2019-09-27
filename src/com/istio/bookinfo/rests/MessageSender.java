package com.istio.bookinfo.rests;

import java.io.IOException;

//import static org.junit.Assert.fail;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import org.json.simple.parser.ParseException;

public class MessageSender extends HttpClientAux{
	
	public MessageSender(String url){
		super();
		setBaseURL(url);
	}
	

	public void setBaseURL(String url){
		BASE_URL=url;
	}

	public String getBaseURL(){
		return BASE_URL;
	}
	
	//http get() to <base-url>/api/v1/products"
	public Response getProducts() throws KeyManagementException, NoSuchAlgorithmException, IOException, ParseException {
		long sTime;
		String urlString=getBaseURL()+"/api/v1/products";
		SSLconnection.trustAll();
		sTime = System.currentTimeMillis();
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try{
			conn.setDoOutput(true);
			conn.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
			conn.setReadTimeout(HTTP_CONNECTION_TIMEOUT);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Host", url.getHost()+":"+url.getPort());
			conn.setRequestProperty("Content-Length", String.valueOf(urlString.toString().getBytes().length));
			String response;
			int responseCode = getResponseCode(conn);
			Response rsp = new Response();
			rsp.setResponseCode(responseCode);
			if(responseCode == 408){
				System.out.println(Thread.currentThread().getName()+" retrying "+urlString+" as got response "+responseCode);
				NeededTools.waitForSometime(15000);
				return getProducts();
			}
			
			if (responseCode>=HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode==HttpURLConnection.HTTP_UNAUTHORIZED || responseCode==HttpURLConnection.HTTP_BAD_REQUEST) {
				System.out.println(Thread.currentThread().getName()+" retrying "+urlString+" as got response "+responseCode);
				NeededTools.waitForSometime(15000);
				if(responseCode == 401){
					//TODO -- @basuki: placeholder for future implementation.
				}
				if(responseCode  == HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode == HttpURLConnection.HTTP_BAD_GATEWAY){
					System.out.println(Thread.currentThread().getName()+ " retrying "+urlString+" as got response "+responseCode);
				}
				//TODO -- @basuki: placeholder for future implementation.
			}
			if (responseCode < HttpURLConnection.HTTP_OK || responseCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
				response = getErrorResponse(conn);
				rsp.setJsonResponse(stringToJson(response));
			}
			response = getSuccessResponse(conn);
			rsp.setJsonArrResponse(stringToJsonArray(response));
			return rsp;
		}
		finally{
			conn.disconnect();
			System.out.println(Thread.currentThread().getName()+" "+urlString+" took time "+(System.currentTimeMillis()-sTime)+" msec.");
		}
	}
	
	
	//http get() to <base-url>/api/v1/products/"+productId;"
	public Response getProductById(long productId) throws KeyManagementException, NoSuchAlgorithmException, IOException, ParseException {
		long sTime;
		String urlString=getBaseURL()+"/api/v1/products/"+productId;
		SSLconnection.trustAll();
		sTime = System.currentTimeMillis();
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try{
			conn.setDoOutput(true);
			conn.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
			conn.setReadTimeout(HTTP_CONNECTION_TIMEOUT);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Host", url.getHost()+":"+url.getPort());
			conn.setRequestProperty("Content-Length", String.valueOf(urlString.toString().getBytes().length));
			String response;
			int responseCode = getResponseCode(conn);
			Response rsp = new Response();
			rsp.setResponseCode(responseCode);
			if(responseCode == 408){
				System.out.println(Thread.currentThread().getName()+" retrying "+urlString+" as got response "+responseCode);
				NeededTools.waitForSometime(15000);
				return getProductById(productId);
			}
			if (responseCode>=HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode==HttpURLConnection.HTTP_UNAUTHORIZED || responseCode==HttpURLConnection.HTTP_BAD_REQUEST) {
				System.out.println(Thread.currentThread().getName()+" retrying "+urlString+" as got response "+responseCode);
				NeededTools.waitForSometime(15000);
				if(responseCode == 401){
					//TODO -- @basuki: placeholder for future implementation.
				}
				if(responseCode  == HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode == HttpURLConnection.HTTP_BAD_GATEWAY){
					System.out.println(Thread.currentThread().getName()+ " retrying "+urlString+" as got response "+responseCode);
				}
				//TODO -- @basuki: placeholder for future implementation.
			}
			if (responseCode < HttpURLConnection.HTTP_OK || responseCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
				response = getErrorResponse(conn);
				rsp.setJsonResponse(stringToJson(response));
			}
			response = getSuccessResponse(conn);
			rsp.setJsonResponse(stringToJson(response));
			return rsp;
		}
		finally{
			conn.disconnect();
			System.out.println(Thread.currentThread().getName()+" "+urlString+" took time "+(System.currentTimeMillis()-sTime)+" msec.");
		}
	}

	//http get() to <base-url>//api/v1/products/"+productId+"/reviews"
	public Response getReviewsByProductId(long productId) throws KeyManagementException, NoSuchAlgorithmException, IOException, ParseException {
		long sTime;
		String urlString=getBaseURL()+"/api/v1/products/"+productId+"/reviews";
		SSLconnection.trustAll();
		sTime = System.currentTimeMillis();
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try{
			conn.setDoOutput(true);
			conn.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
			conn.setReadTimeout(HTTP_CONNECTION_TIMEOUT);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Host", url.getHost()+":"+url.getPort());
			conn.setRequestProperty("Content-Length", String.valueOf(urlString.toString().getBytes().length));
			String response;
			int responseCode = getResponseCode(conn);
			Response rsp = new Response();
			rsp.setResponseCode(responseCode);
			if(responseCode == 408){
				System.out.println(Thread.currentThread().getName()+" retrying "+urlString+" as got response "+responseCode);
				NeededTools.waitForSometime(15000);
				return getReviewsByProductId(productId);
			}
			if (responseCode>=HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode==HttpURLConnection.HTTP_UNAUTHORIZED || responseCode==HttpURLConnection.HTTP_BAD_REQUEST) {
				//System.out.println(Thread.currentThread().getName()+" retrying "+urlString+" as got response "+responseCode);
				NeededTools.waitForSometime(15000);
				if(responseCode == 401){
					//TODO -- @basuki: placeholder for future implementation.
				}
				if(responseCode  == HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode == HttpURLConnection.HTTP_BAD_GATEWAY){
					System.out.println(Thread.currentThread().getName()+ " retrying "+urlString+" as got response "+responseCode);
				}
				//TODO -- @basuki: placeholder for future implementation.
			}
			if (responseCode < HttpURLConnection.HTTP_OK || responseCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
				response = getErrorResponse(conn);
				rsp.setJsonResponse(stringToJson(response));
			}
			response = getSuccessResponse(conn);
			rsp.setJsonResponse(stringToJson(response));
			return rsp;
		}
		finally{
			conn.disconnect();
			System.out.println(Thread.currentThread().getName()+" "+urlString+" took time "+(System.currentTimeMillis()-sTime)+" msec.");
		}
	}

	//http get() to <base-url>//api/v1/products/"+productId+"/ratings"
	public Response getRatingsByProductId(long productId) throws KeyManagementException, NoSuchAlgorithmException, IOException, ParseException {
		long sTime;
		String urlString=getBaseURL()+"/api/v1/products/"+productId+"/ratings";
		SSLconnection.trustAll();
		sTime = System.currentTimeMillis();
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		try{
			conn.setDoOutput(true);
			conn.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
			conn.setReadTimeout(HTTP_CONNECTION_TIMEOUT);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Host", url.getHost()+":"+url.getPort());
			conn.setRequestProperty("Content-Length", String.valueOf(urlString.toString().getBytes().length));
			String response;
			int responseCode = getResponseCode(conn);
			Response rsp = new Response();
			rsp.setResponseCode(responseCode);
			if(responseCode == 408){
				System.out.println(Thread.currentThread().getName()+" retrying "+urlString+" as got response "+responseCode);
				NeededTools.waitForSometime(15000);
				return getRatingsByProductId(productId);
			}
			if (responseCode>=HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode==HttpURLConnection.HTTP_UNAUTHORIZED || responseCode==HttpURLConnection.HTTP_BAD_REQUEST) {
				System.out.println(Thread.currentThread().getName()+" retrying "+urlString+" as got response "+responseCode);
				NeededTools.waitForSometime(15000);
				if(responseCode == 401){
					//TODO -- @basuki: placeholder for future implementation.
				}
				if(responseCode  == HttpURLConnection.HTTP_INTERNAL_ERROR || responseCode == HttpURLConnection.HTTP_BAD_GATEWAY){
					System.out.println(Thread.currentThread().getName()+ " retrying "+urlString+" as got response "+responseCode);
				}
				//TODO -- @basuki: placeholder for future implementation.
			}
			if (responseCode < HttpURLConnection.HTTP_OK || responseCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
				response = getErrorResponse(conn);
				rsp.setJsonResponse(stringToJson(response));
			}
			response = getSuccessResponse(conn);
			rsp.setJsonResponse(stringToJson(response));
			return rsp;
		}
		finally{
			conn.disconnect();
			System.out.println(Thread.currentThread().getName()+" "+urlString+" took time "+(System.currentTimeMillis()-sTime)+" msec.");
		}
	}

	
}

