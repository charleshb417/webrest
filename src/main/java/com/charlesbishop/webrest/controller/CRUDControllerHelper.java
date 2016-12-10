package com.charlesbishop.webrest.controller;

import java.util.List;

import com.charlesbishop.webrest.model.ObjModel;

public class CRUDControllerHelper {
	
	// Generate a JSON array from a list of JSON Objects
    public static String generateJSONArray(List<? extends ObjModel> list) {
		StringBuilder builder = new StringBuilder();
		
    	builder.append("[");
		for(ObjModel m : list){
			builder.append(m.getObjectAsJsonString());
			builder.append(",");
		}
		
		// Replace last comma with a terminating delimiter
		builder.replace(builder.length()-1, builder.length(), "]");

        return builder.toString();
    }
	
}
