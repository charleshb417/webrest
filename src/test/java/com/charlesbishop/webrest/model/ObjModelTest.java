package com.charlesbishop.webrest.model;

import static org.junit.Assert.assertNotEquals;
import java.sql.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class ObjModelTest {

	private static Crime crime = new Crime();
	private static Vacant vacant = new Vacant();
	private static Date nowDate = new Date(System.currentTimeMillis());
	
	@BeforeClass
	public static void setup(){
		// Setup Crime object
		crime.setCrimeID(0);
		crime.setCrimeCode("000");
		crime.setCrimeDate(nowDate);
		crime.setDescription("Who killed Mr. Body?");
		crime.setDistrict("SOUTHEASTERN");
		crime.setLocation("Study");
		crime.setNeighborhood("CANTON");
		crime.setWeapon("Candlestick");
		
		// Setup Vacant object
		vacant.setReferenceID("0");
		vacant.setBlock("00");
		vacant.setBuildingAddress("123 Fake Street");
		vacant.setCouncilDistrict(0);
		vacant.setLocation("CANTON");
		vacant.setLot("00");
		vacant.setNeighborhood("CANTON");
		vacant.setNoticeDate(nowDate);
		vacant.setPoliceDistrict("SOUTHEASTERN");
	}
	
	@Test
	public void testCrimeJsonIsValid(){
		assertNotEquals(crime.getObjectAsJsonString(), "{}");
	}
	
	@Test
	public void testVacantJsonIsValid(){
		assertNotEquals(vacant.getObjectAsJsonString(), "{}");
	}
}
