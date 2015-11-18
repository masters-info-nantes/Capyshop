package com.alma.service.shop;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;

import com.alma.service.bank.BankStub;
import com.alma.service.supplier.SupplierStub.SaveOrder;
import com.alma.service.supplier.SupplierStub;
import com.alma.service.supplier.SupplierStub.GetProducts;
import com.alma.service.supplier.SupplierStub.Product;
import com.alma.service.supplier.SupplierStub.Order;
import com.alma.service.supplier.SupplierStub.Validate;


public class Shop {
	
	private SupplierStub supplier;
	private BankStub bank;
	
	public Shop() throws AxisFault
	{
		this.supplier = new SupplierStub("http://localhost:9763/services/Supplier/");
		this.bank = new BankStub("http://localhost:9763/services/Bank/");
	}
	
	public Product[] getProducts() throws RemoteException{
		//Call Supplier and return product list
		GetProducts g = new GetProducts();
		return this.supplier.getProducts(g).get_return();
	}
	
	public Order placeOrder(String[] products, String name, String address, String postCode, String city) throws RemoteException{
		//Call Supplier to validate command
		SaveOrder p = new SaveOrder();
		p.setProducts(products);
		p.setName(name);
		p.setAddress(address);
		p.setPostCode(postCode);
		p.setCity(city);
		return this.supplier.saveOrder(p).get_return();
	}
	
	public boolean pay(String orderId, String number, String expire, int crypt){
		//Call Bank to pay
		//Call supplier to validate order
		return true;
	}
	

	
}
