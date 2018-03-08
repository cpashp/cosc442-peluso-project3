/**
 * 
 */
package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Collin
 *unit test class for VendingMachineItem
 */
public class VendingMachineItemTest {

	/** Declaring test objects for VendingMachineItem */
	VendingMachineItem item1, item2, item3;
	
	/**
	 * Initializes test objects for test cases to use.
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		item1 = new VendingMachineItem("chips", 1.0);
		item2 = new VendingMachineItem(" ", 1);
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem#VendingMachineItem(java.lang.String, double)}.
	 * ensures an item cannot be entered with a value below zero
	 */
	@Test(expected = VendingMachineException.class)
	public void testVendingMachineItem() {
		item3= new VendingMachineItem("", -1.0);
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem#getName()}.
	 * ensures item names are returned properly
	 */
	@Test
	public void testGetName() {
		assertEquals("chips", item1.getName());
		assertEquals(" ", item2.getName());
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem#getPrice()}.
	 * ensures prices are returned properly
	 */
	@Test
	public void testGetPrice() {
		assertEquals(1.0, item1.getPrice(), 0.0001);
		assertEquals(1, item2.getPrice(), 0.0001);
	}
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		item1 = null;
		item2 = null;
	}

}
