package com.alma.service.bank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Bank {
	
	private Card getCard(String number, String expire,int crypt)
	{
		// This is the method where we should access the database
		// and request the cards...
		
		// Mock //
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card("0000000000000000", "12/17", 124, 5500, "EUR"));
		cards.add(new Card("1111111111111111", "12/17", 124, 50, "USD"));
		cards.add(new Card("2222222222222222", "12/17", 124, 150000, "JPY"));
		for(Card card : cards)
		{
			if(card.getNumber().equals(number)&&card.getExpire().equals(expire)&&card.getCrypt()==crypt)
			{
				return card; // As soon as we find the corresponding card, we return it.
			}
		}
		
		return null;
		
		// Mock End //
		
	}
	
	private void updateCardMoney(Card c, double amount)
	{
		// Mock //
		
		// This method should update the amount of money available after the user payment
		
		// Mock End //
	}
	
	private boolean isCardValid(String number, String expire, int crypt )
	{
		//Check if the card number is composed of 16 numbers
		if(!number.matches("[0-9]{16}"))
			return false;
		
		//Check if the cryptogram is only 3 digits long
		if(!(crypt<=999))
			return false;
		
		//Check if the expire date of the card is away from today
		Date today=new Date();
		Date expireDate = new Date();
		SimpleDateFormat standardDate = new SimpleDateFormat("MM/yy");
		try {
			expireDate= standardDate.parse(expire);
			today= standardDate.parse(standardDate.format(today));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(today.after(expireDate))
			return false;

		return true;
	}
	
	// Send a HTTP GET request, used to access web services
	private String sendGet(String url) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();

	}
	
	//Convert an amount of money from one currency to euros
	private double convert(String currency, double amount) throws Exception
	{
		//Calls the currency conversion web service
		
		String url = "http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency="+currency+"&ToCurrency=EUR";
		String rawResponse = sendGet(url);
		
		//Parse the xml response from the web service
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(rawResponse));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("double");
        Element element = (Element) nodes.item(0);
        String rawConversionRate = element.getFirstChild().getTextContent();
        double conversionRate = Double.parseDouble(rawConversionRate);
        
        //Calculate the converted amount
        
        return amount*conversionRate;
		
	}

	public boolean pay(String number, String expire, int crypt, int price) throws Exception{
		
		// Check if the card is valid
		
		if(!isCardValid(number,expire,crypt)){
			return false;
		}
		
		// Get the card from the database (mocked)
		
		Card card = this.getCard(number, expire, crypt);
		
		// Convert the amount of available money on the account
		// and check if there is enough to pay
		
		if(convert(card.getCurrency(),card.getMoney())>=price){
			updateCardMoney(card, price);
		}else{
			return false;
		}
			
		return true;
	}
	
}
