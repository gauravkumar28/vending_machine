package com.vending;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;

public class VendingMachineBuilderTest {
	private static VendingMachineBuilder vendingMachineBuilder;

	@BeforeClass
	public static void setUp() {
		vendingMachineBuilder = new VendingMachineBuilder();

		Product coke = new Product(1, "coke", 20);
		Product pepsi = new Product(2, "pepsi", 20);
		Product coffee = new Product(3, "coffee", 70);

		vendingMachineBuilder.addProduct(coke, 2);
		vendingMachineBuilder.addProduct(pepsi, 3);
		vendingMachineBuilder.addProduct(coffee, 4);

		vendingMachineBuilder.setCash(Cash.FIFTY_RUPEES, 2);
		vendingMachineBuilder.setCash(Cash.TWENTY_RUPEES, 2);
		vendingMachineBuilder.setCash(Cash.TWO_RUPEES, 2);

		// System.out.println(vendingMachineBuilder.toString());

	}

	@Test
	public void testVendingMachine() {

		VendingMachine vm = vendingMachineBuilder.build();
		Product coke = new Product(1, "coke", 20);
		Integer price = vm.selectItemAndGetPrice(coke);
		assertEquals(coke.getPrice(), price);

		Product pepsi = new Product(2, "pepsi", 20);
		price = vm.selectItemAndGetPrice(coke);
		assertEquals(pepsi.getPrice(), price);

	}

	@Test(expected = SoldOutException.class)
	public void testVendingMachineNotAvilable() {
		VendingMachine vm = vendingMachineBuilder.build();
		Product thupsup = new Product(4, "thumpsup", 20);
		Integer price = vm.selectItemAndGetPrice(thupsup);
		assertEquals(thupsup.getPrice(), price);
	}

	@Test(expected = InsufficientChangeException.class)
	public void testBuyItemWithMorePrice() {
		setUp();
		VendingMachine vm = vendingMachineBuilder.build();
		Product coke = new Product(1, "coke", 20);
		Integer price = vm.selectItemAndGetPrice(coke);
		assertEquals(coke.getPrice(), price);
		vm.insertCash(Cash.FIFTY_RUPEES);
		System.out.println(vm.toString());

		PurchageSummary<Product, Inventory<Cash>> purchageSummary = vm.collectItemAndChange();
		System.out.println(purchageSummary);
		Product item = purchageSummary.getFirst();
		Inventory<Cash> change = purchageSummary.getSecond();
		assertEquals(coke, item);
		assertEquals(change.toString(), "[20, 2, 2, 2, 2, 2]");
		System.out.println(vm.toString());

	}

	@Test(expected = SoldOutException.class)
	public void testResetVedingMachine() {
		VendingMachine vm = vendingMachineBuilder.build();
		vm.reset();
		Product coke = new Product(1, "coke", 20);
		Integer price = vm.selectItemAndGetPrice(coke);
		assertEquals(coke.getPrice(), price);
	}

	@AfterClass
	public static void tearDown() {
		vendingMachineBuilder = null;
	}
}
