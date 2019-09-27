package com.istio.bookinfo.rests;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Response {
	private int responseCode;
	private JSONObject jsonResponse;
	private JSONArray jsonArrResponse;
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public JSONObject getJsonResponse() {
		return jsonResponse;
	}
	public void setJsonResponse(JSONObject jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	public JSONArray getJsonArrResponse() {
		return jsonArrResponse;
	}
	public void setJsonArrResponse(JSONArray jsonArrResponse) {
		this.jsonArrResponse = jsonArrResponse;
	}
	@Override
	public String toString() {
		return "RESTResponse [responseCode=" + responseCode + ", jsonResponse=" + jsonResponse + ", jsonArrResponse="
				+ jsonArrResponse + "]";
	}
}