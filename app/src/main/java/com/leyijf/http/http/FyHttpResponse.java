package com.leyijf.http.http;

import java.io.InputStream;

import org.json.JSONObject;

/**
 * User: MengHX Date: 1/26/13 Time: 1:23 PM
 */
public final class FyHttpResponse {

	
	private FyXmlNodeData xml;
	private int httpStatus;
	private String uri = "";
	private JSONObject json;
	private String text;
	private String action = "";
	private InputStream inputStream = null;
	private String tag; //加入一个标志，用于获取到的流归属

	public FyHttpResponse() {
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public FyXmlNodeData getXml() {
		return xml;
	}

	public void setXml(FyXmlNodeData xml) {
		this.xml = xml;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	

}
