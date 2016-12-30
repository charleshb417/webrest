package com.charlesbishop.webrest.dao;

import java.io.Serializable;
import java.sql.Date;

import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.charlesbishop.webrest.model.Crime;

/*
 * Class for testing CRUD operations with Hibernate
 * Tests are executed as one test via testInOrder() method
 */
public class BaseDAOTest {

	private int testingKey = 0;
	private final static Crime testObj = new Crime();
	private static Date nowDate = new Date(System.currentTimeMillis());

	@BeforeClass
	public static void setup(){
		// Create the testing object
		testObj.setCrimeCode("000");
		testObj.setCrimeDate(nowDate);
		testObj.setDescription("Who killed Mr. Body?");
		testObj.setDistrict("SOUTHEASTERN");
		testObj.setLocation("Study");
		testObj.setNeighborhood("CANTON");
		testObj.setWeapon("Candlestick");
	}
	
	public void createAndGetObject(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		// Store the Crime object in the database
		Serializable id = crimeDAO.save(testObj);
		testingKey = (Integer) id; // set the global testingKey to that of the newly created object

		// Set the crime ID for later tests
		testObj.setCrimeID(testingKey);
		
		// Get the object from the database
		Crime retrievedCrime = crimeDAO.get(testingKey);
		context.close();
		
		if (retrievedCrime == null)
			fail();
	}
	
	public void updateCreatedObject(){
		testObj.setWeapon("Revolver");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		// Update Crime object in the database
		crimeDAO.update(testObj);

		// Get the object from the database
		Crime retrievedCrime = crimeDAO.get(testingKey);
		context.close();
		
		if (!retrievedCrime.getWeapon().equalsIgnoreCase(testObj.getWeapon()))
			fail();
	}
	
	public void deleteTestObjectAndVerify(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");		
		CrimeDAO crimeDAO = context.getBean(CrimeDAO.class);
		
		// Update Crime object in the database
		crimeDAO.delete(testObj);

		// Get the object from the database
		Crime retrievedCrime = crimeDAO.get(testingKey);
		context.close();
		
		if (retrievedCrime != null)
			fail();
	}
	
	@Test
	public void testInOrder(){
		System.out.println("Create and get object...");
		createAndGetObject();
		
		System.out.println("Update previously created object...");
		updateCreatedObject();
		
		System.out.println("Deleting and verifying deletion of test object...");
		deleteTestObjectAndVerify();
		
		System.out.println("Object was successfully created, retrieved, updated, and deleted!");
	}
	
}
