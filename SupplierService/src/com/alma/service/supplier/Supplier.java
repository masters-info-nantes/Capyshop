package com.alma.service.supplier;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
	
	private DBManager db;
	
	public Supplier()
	{
		this.db = new DBManager();
	}
	
	public List<Product> getProducts(){
		// Mock
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("Capybara roux","Capybara roux", 4, 150.0));
		products.add(new Product("Capybara roux","Capybara rayé", 4, 170.50));
		products.add(new Product("Capybara roux","Capybara blanc", 4, 185.0));
		products.add(new Product("Capybara roux","Capybara rose", 4, 199.99));
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
