package com.istio.bookinfo.rests;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpClientAux {

	protected final int HTTP_CONNECTION_TIMEOUT = 60000;
	protected String BASE_URL;
	
	protected HttpClientAux() {
		super();
	}

	protected int getResponseCode(HttpURLConnection conn) {
		int code = 408;
		try {
			code = conn.getResponseCode();
		} catch (Exception e) {
			//gracefully handling stale / blocked connection with timeout
			System.out.println(Thread.currentThread().getName()+" establishing connection time-out, hence retrying ...");
			code = 408;
		}
		return code;
	}
	
	protected String getSuccessResponse(HttpURLConnection conn, int size){
		final char[] buf = new char[size];
		int read;
		final StringBuffer sb = new StringBuffer();
		try {
			final InputStream is = conn.getInputStream();
			final Reader reader = new InputStreamReader(is);
			while((read = reader.read(buf)) > 0) {
				sb.append(buf, 0, read);
			}
			reader.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return sb.toString();
	}


	protected String getSuccessResponse(HttpURLConnection conn){
		final char[] buf = new char[4096];
		int read;
		final StringBuffer sb = new StringBuffer();
		try {
			final InputStream is = conn.getInputStream();
			final Reader reader = new InputStreamReader(is);
			while((read = reader.read(buf)) > 0) {
				sb.append(buf, 0, read);
			}
			reader.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return sb.toString();
	}

	protected JSONObject stringToJson(String response) throws ParseException {
		JSONObject jsonObject;
		JSONParser parser = new JSONParser();
		if(response != null && response.trim() != "" && (!response.isEmpty())){
			jsonObject = (JSONObject)parser.parse(response);
			return jsonObject;
		} else {
			return null;
		}
	}

	protected JSONArray stringToJsonArray(String response) throws ParseException {
		JSONArray jsonObject;
		JSONParser parser = new JSONParser();
		if(response != null && response.trim() != "" && (!response.isEmpty())){
			jsonObject = (JSONArray)parser.parse(response);
			return jsonObject;
		} else {
			return null;
		}
	}

	protected String getErrorResponse(HttpURLConnection conn){
		final char[] buf = new char[2048];
		int read;
		final StringBuffer sb = new StringBuffer();
		try {
			final InputStream is = conn.getErrorStream();
			final Reader reader = new InputStreamReader(is);
			while((read = reader.read(buf)) > 0) {
				sb.append(buf, 0, read);
			}
			reader.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			conn.disconnect();
		}
		return sb.toString();
	}
	
}