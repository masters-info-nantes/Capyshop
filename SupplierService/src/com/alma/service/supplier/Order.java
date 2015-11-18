package com.alma.service.supplier;

import java.util.List;

public class Order {

	String id;
	List<Product> products;
	
	double total;
	
	String address;
	String name;
	String postCode;
	String city;
	
	public Order(String id, String address, String name, String postCode, String city, List<Product> products) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.postCode = postCode;
		this.city = city;
		this.products = products;
		this.total = 0;
		for(Product p: products)
		{
			this.total+=p.getPrice();
		}
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
