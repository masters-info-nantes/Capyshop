package com.alma.service.bank;

public class Bank {
	
	private Card getCard(int number, int year, int month)
	{
		//Mock
		return new Card(number,year,month,200);
		//Mock End
	}
	
	private boolean isCardValid(int number, int year, int month)
	{
		//Mock
		// valid if date > today && number is long enough
		if(String.valueOf(number).length()!=16)
			return false;
		return true;
		//End Mock
	}

	public boolean pay(int number, int year, int month, int money){
		// Mock 
		if(!isCardValid(number,year,month))
			return false;
		Card c = this.getCard(number, year, month);
		if(c.getMoney()<money)
			return false;
		// End Mock
		return true;
	}
}
