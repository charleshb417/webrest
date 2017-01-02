package com.charlesbishop.webrest.util;

/**
 * @author charlie
 * This utility class offers helper methods that can be useful for doing queries.
 */
public class QueryUtil {

	public static int calculateLastPageNumber(long countResults, int perPage){
		if (countResults % perPage == 0){
			return (int) ((countResults / perPage));
		}
		else {
			return (int) ((countResults / perPage) + 1);
		}
	}
}
