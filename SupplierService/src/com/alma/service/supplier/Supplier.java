package com.alma.service.supplier;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
	
	private List<Product> products;
	
	public Supplier(){
		
		// Mock //
		
		this.products = new ArrayList<Product>();
		products.add(new Product("capy_roux","Capybara roux", 4, 150.0));
		products.add(new Product("capy_raye","Capybara rayé", 4, 170.50));
		products.add(new Product("capy_blanc","Capybara blanc", 4, 185.0));
		products.add(new Product("capy_rose","Capybara rose", 4, 199.99));
		
		// End Mock //
	}
	
	public List<Product> getProducts(){
		// This is the method where we should access the database
		// and request the products...

		return this.products;
	}
	
	public String saveOrder(String[] products, String name, String address, String postCode, String city){
		// This method should create an order,store it into the database and return the id of the order
		
		// Mock //
		
		// Find the product according to it's id
		
		List<Product> ps = new ArrayList<Product>();
		for(String product: products)
		{
			for(Product product2:this.products)
			{
				if(product.equals(product2.getId()))
				{
					ps.add(product2);
				}
			}
		}
		
		Order o = new Order(address,name,postCode,city,ps);
		
		// End Mock //
		
		return o.getId();
	}
	
	public boolean validate(String order){
		
		// Mock //
		
		// This method should update the status of the order to validated:true in the database
		
		// End Mock //
		
		return true;
	}
	
}
