package com.charlesbishop.webrest.util;

import org.junit.Test;
import static org.junit.Assert.fail;

public class QueryUtilTest {

	@Test
	public void testModZeroResultsCorrect(){
		int res = QueryUtil.calculateLastPageNumber(100, 25);
		compareNumbers(4, res);
	}
	
	@Test
	public void testOverflowResultsCorrect(){
		int res = QueryUtil.calculateLastPageNumber(101, 25);
		compareNumbers(5, res);
	}
	
	@Test
	public void testOnePageExpected(){
		int res = QueryUtil.calculateLastPageNumber(25, 250);
		compareNumbers(1, res);
	}
	
	public void compareNumbers(int expected, int actual){
		if (expected != actual)
			fail();
	}
}
