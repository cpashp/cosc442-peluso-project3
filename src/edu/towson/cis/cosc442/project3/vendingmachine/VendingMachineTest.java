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
 *unit test class for VendingMachine
 */
public class VendingMachineTest {

	VendingMachine machine1, machine2;
	VendingMachineItem item;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		machine1 = new VendingMachine();
		machine2 = new VendingMachine();
		item = new VendingMachineItem("chips", 1.0);
	}

	
	@Test
	public void testGetSlotIndex() 
	{
		assertEquals(0, machine1.getSlotIndex("A"));
		assertEquals(1, machine1.getSlotIndex("B"));
		assertEquals(2, machine1.getSlotIndex("C"));
		assertEquals(3, machine1.getSlotIndex("D"));
	}
	
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#addItem(edu.towson.cis.cosc442.project3.vendingmachine.VendingMachineItem, java.lang.String)}.
	 * ensures when item is added, it is stored properly and can be retrieved
	 */
	@Test
	public void testAddItem() {
		//first progressive step
		machine1.addItem(item, "A");
		assertEquals(item, machine1.getItem("A"));
	}
	
	/*
	 *ensures an exception is thrown when an item is added to an occupied space
	 */
	@Test(expected = VendingMachineException.class)
	public void testAddItemOccupied() {
		machine1.addItem(item,  "A");
		machine1.addItem(item, "A");
	}
	/*
	 * ensures an exception is thrown when an invalid code is used
	 */
	@Test(expected = VendingMachineException.class)
	public void testAddItemInvalidCode() {
		machine1.addItem(item, "Z");
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#removeItem(java.lang.String)}.
	 *ensures after an item is added, it can be removed
	 */
	@Test
	public void testRemoveItem() {
		machine1.addItem(item, "A");
		machine1.removeItem("A");
		assertEquals(null, machine1.getItem("A"));
	}
	/*
	 * ensures item cannot be removed if there isn't anything to be removed
	 */
	@Test(expected = VendingMachineException.class)
	public void testRemoveItemEmptySlot() {
		machine1.removeItem("A");
	}
	/*
	 * ensures exception is thrown when invalid code is used to be removed
	 */
	@Test(expected = VendingMachineException.class)
	public void testRemoveItemInvalidCode() {
		machine1.removeItem("Z");
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#insertMoney(double)}.
	 *ensures money is inserted properly
	 */
	@Test
	public void testInsertMoney() {
		machine1.insertMoney(1.0);
		assertEquals(1.0, machine1.getBalance(), 0.0001);
	}
	
	/*
	 * ensures exception is thrown if value is below zero
	 */
	@Test(expected = VendingMachineException.class)
	public void testInsertMoneyBelowZero() {
		machine1.insertMoney(-1.0);
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#getBalance()}.
	 * ensures balance is returned properly
	 */
	@Test
	public void testGetBalance() {
		machine1.insertMoney(1.0);
		assertEquals(1.0, machine1.getBalance(), 0.0001);
		assertEquals(0.0, machine2.getBalance(), 0.0001);
	}
	/*
	 * ensures exception is thrown when balance is retrieved when it's below zero
	 */
	@Test(expected = VendingMachineException.class)
	public void testGetBalanceBelowZero() {
		machine1.insertMoney(-1.0);
		machine1.getBalance();
	}
	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#makePurchase(java.lang.String)}.
	 * ensures a true is returned when purchase is made properly
	 */
	@Test
	public void testMakePurchase() {
		machine1.addItem(item,  "A");
		machine1.insertMoney(1.0);
		assertEquals(true, machine1.makePurchase("A"));
		assertEquals(false, machine2.makePurchase("A"));
	}
	/*
	 * ensures purchase cannot be made if balance is less than zero
	 */
	@Test(expected = VendingMachineException.class)
	public void testMakePurchaseBalanceBelowZero() {
		machine1.addItem(item,  "A");
		machine1.insertMoney(-1.0);
		machine1.makePurchase("A");
	}
	/*
	 * ensures purchase cannot be made for an item that doesn't exist
	 */
	@Test
	public void testMakePurchaseEmptySlot() {
		assertEquals(false, machine1.makePurchase("A"));
	}

	/**
	 * Test method for {@link edu.towson.cis.cosc442.project3.vendingmachine.VendingMachine#returnChange()}.
	 * ensures change is returned and balance is set to zero afterwards
	 */
	@Test
	public void testReturnChange() {
		machine1.insertMoney(1.0);
		assertEquals(1.0, machine1.returnChange(), 0.0001);
		assertEquals(0.0, machine1.getBalance(), 0.0001);
		assertEquals(0.0, machine2.returnChange(), 0.0001);
	}
	/*
	 * ensures no change is returned if money inserted is less than zero
	 */
	@Test(expected = VendingMachineException.class)
	public void testReturnChangeBelowZero() {
		machine1.insertMoney(-1.0);
		machine1.returnChange();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		machine1 = null;
		machine2 = null;
	}

}
