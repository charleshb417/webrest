package com.charlesbishop.webrest.model;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public abstract class ObjModel {
	
	public String getObjectAsJsonString(){
	    try {
	        new JsonParser().parse(this.toString());
	        return this.toString();
	    } catch (JsonSyntaxException jse) {
	        return "{}";
	    }
	}
}
