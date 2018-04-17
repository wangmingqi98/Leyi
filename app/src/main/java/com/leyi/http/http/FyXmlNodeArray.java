package com.leyi.http.http;

import java.util.ArrayList;


public final class FyXmlNodeArray extends ArrayList<Object> {


	private static final long serialVersionUID = 6623000096444218849L;
	
	public FyXmlNodeArray(){
		super();
	}
	
	public FyXmlNodeData getNode(int index){
		return (FyXmlNodeData) this.get(index);
	}
}
