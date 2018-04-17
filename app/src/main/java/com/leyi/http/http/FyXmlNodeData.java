package com.leyi.http.http;

import java.util.HashMap;


public final class FyXmlNodeData extends HashMap<Object, Object>{

	private static final long serialVersionUID = -474930809860388732L;

	public FyXmlNodeData() {
		super();
	}

	public FyXmlNodeArray getList(String key) {
		return (FyXmlNodeArray) this.get(key);
	}

	public FyXmlNodeData getMap(String key) {
		return  (FyXmlNodeData) this.get(key);
	}
	
	public String getText(Object key) {
		Object result = super.get(key);
		if (result != null && result instanceof String) {
			return (String) result;
		}
		return "";
	}
}