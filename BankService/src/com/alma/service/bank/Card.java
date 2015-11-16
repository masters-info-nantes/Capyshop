package com.alma.service.bank;

public class Card {
	
	private int number;
	private int year;
	private int month;
	private int money;	

	public Card(int number, int year, int month, int money)
	{
		this.number = number;
		this.year = year;
		this.month = month;
		this.money = money;
	}

	public int getNumber() {
		return number;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getMoney() {
		return money;
	}
}
