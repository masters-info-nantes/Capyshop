package com.alma.service.supplier;

import java.util.ArrayList;
import java.util.List;

public class Supplier {
	
	private DBManager db;
	private List<Product> products;
	
	public Supplier()
	{
		this.db = new DBManager();
		//Mock
		this.products = new ArrayList<Product>();
		products.add(new Product("capy_roux","Capybara roux", 4, 150.0));
		products.add(new Product("capy_raye","Capybara ray�", 4, 170.50));
		products.add(new Product("capy_blanc","Capybara blanc", 4, 185.0));
		products.add(new Product("capy_rose","Capybara rose", 4, 199.99));
		// End Mock
	}
	
	public List<Product> getProducts(){
		// Mock
		
		// End Mock
		return this.products;
	}
	
	public String saveOrder(String[] products, String name, String address, String postCode, String city){
		// Return order and put products in DB
		//Mock
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
		// End Mock
		return o.getId();
	}
	
	public boolean validate(String order){
		//Mock
		order = "Order N�131431 Total: 152� Status: Validated";
		// End Mock
		//Notify payment
		return true;
	}
	
}
