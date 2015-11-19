package com.alma.service.bank;

public class Card {
	
	private String number;
	private String expire;
	private int crypt;
	private int money;
	private String currency;
	
	public Card(String number, String expire, int crypt, int money, String currency)
	{
		this.number = number;
		this.expire = expire;
		this.money = money;
		this.crypt = crypt;
		this.currency = currency;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public int getCrypt() {
		return crypt;
	}

	public void setCrypt(int crypt) {
		this.crypt = crypt;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
