package com.alma.service.supplier;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
	
	private DBManager db;
	
	public Supplier()
	{
		this.db = new DBManager();
	}
	
	public List<String> getProducts(){
		// Mock
		List<String> products = new ArrayList<String>();
		products.add("Capybara roux");
		products.add("Capybara rayé");
		products.add("Capybara gris (vieux)");
		products.add("Capybara blanc (albinos)");
		// End Mock
		return products;
	}
	
	public String saveOrder(ArrayList<String> product){
		// Return order and put products in DB
		String order = null;
		//Mock
		order = "Order N°131431 Total: 152€ Status: Not validated";
		// End Mock
		return order;
	}
	
	public boolean validate(String order){
		//Mock
		order = "Order N°131431 Total: 152€ Status: Validated";
		// End Mock
		//Notify payment
		return true;
	}
	
}
