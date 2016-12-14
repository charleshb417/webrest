package com.charlesbishop.webrest.controller;

import java.util.List;
import java.util.regex.Matcher;

import com.charlesbishop.webrest.model.ObjModel;

/*
 * This utility class provides helper methods for REST api classes
 */
public class CRUDControllerHelper {
	
	public static final String EMPTY_JSON_OBJECT = "{}";
	public static final String EMPTY_JSON_ARRAY = "[]";
	public static final String MSG_CREATE_FAIL = generateErrorMessage("The create operation has failed.");
	public static final String MSG_UPDATE_FAIL = generateErrorMessage("The update operation has failed.");
	public static final String MSG_DELETE_FAIL = generateErrorMessage("The delete operation has failed.");
	public static final String MSG_GENERIC_ERROR = generateErrorMessage("There has been an error communicating with the database.");

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

	public static String generateErrorMessage(String msg) {
		return "{\"error\":\"" + msg.replaceAll("\"", Matcher.quoteReplacement("\\\"")) + "\"}";
	}
	
}
