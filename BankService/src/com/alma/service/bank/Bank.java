package com.alma.service.bank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Bank {
	
	private Card getCard(String number, String expire,int crypt)
	{
		//Mock
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card("0000-0000-0000-0000", "12/17",124, 5500, "eur"));
		cards.add(new Card("1111-1111-1111-1111", "12/17",124, 4200, "usd"));
		cards.add(new Card("2222-2222-2222-2222", "12/17",124, 150000, "yen"));
		for(Card card : cards)
		{
			if(card.getNumber().equals(number)&&card.getExpire().equals(expire)&&card.getCrypt()==crypt)
			{
				return card;
			}
		}
		return null;
		//Mock End
	}
	
	private boolean isCardValid(String number, String expire, int crypt )
	{
		//Mock
		// To-Do
		if(!number.matches("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"))
			return false;
		
		Date today=new Date();
		Date expireDate = new Date();
		SimpleDateFormat standardDate = new SimpleDateFormat("MM/yy");
		try {
			expireDate= standardDate.parse(expire);
			today= standardDate.parse(standardDate.format(today));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(today.after(expireDate))
			return false;

		return true;
		//End Mock
	}

	public boolean pay(String number, String expire, int crypt, int money){
		// Mock 
		if(!isCardValid(number,expire,crypt))
			return false;
		Card c = this.getCard(number, expire, crypt);
		// Be careful with currency conversion
		if(c.getMoney()<money)
			return false;
		// End Mock
		return true;
	}
}
