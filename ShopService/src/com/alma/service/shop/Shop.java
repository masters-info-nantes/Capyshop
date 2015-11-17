package com.alma.service.shop;

import java.rmi.RemoteException;
import java.util.ArrayList;

import org.apache.axis2.AxisFault;

import com.alma.service.bank.BankStub;
import com.alma.service.supplier.SupplierStub;
import com.alma.service.supplier.SupplierStub.GetProducts;
import com.alma.service.supplier.SupplierStub.Validate;


public class Shop {
	
	private SupplierStub supplier;
	private BankStub bank;
	
	public Shop() throws AxisFault
	{
		this.supplier = new SupplierStub("http://localhost:9763/services/Supplier/");
		this.bank = new BankStub("http://localhost:9763/services/Bank/");
	}
	
	public String[] getProducts() throws RemoteException{
		//Call Supplier and return product list
		GetProducts g = new GetProducts();
		return this.supplier.getProducts(g).get_return();
	}
	
	public String placeOrder(ArrayList<String> product){
		//Call Supplier to validate command
		return null;
	}
	
	public boolean pay(String order, String creditCard){
		//Call Bank to pay
		return true;
	}
	

	
}
