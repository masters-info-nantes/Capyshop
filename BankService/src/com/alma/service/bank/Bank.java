package com.alma.service.bank;

import java.io.BufferedReader;
import java.io.IOException;
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
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Bank {
	
	private Card getCard(String number, String expire,int crypt)
	{
		//Mock
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card("0000000000000000", "12/17",124, 5500, "EUR"));
		cards.add(new Card("1111111111111111", "12/17",124, 4200, "USD"));
		cards.add(new Card("2222222222222222", "12/17",124, 150000, "JPY"));
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
	
	private double convert(String currency, double amount) throws Exception
	{
		String url = "http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency="+currency+"&ToCurrency=EUR";
		String rawResponse = sendGet(url);
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    DocumentBuilder db = dbf.newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(rawResponse));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("double");
        Element element = (Element) nodes.item(0);
        String rawConversionRate = element.getFirstChild().getTextContent();
        double conversionRate = Double.parseDouble(rawConversionRate);
        
        return amount*conversionRate;
		
	}

	public boolean pay(String number, String expire, int crypt, int money) throws Exception{
		// Mock 
		if(!isCardValid(number,expire,crypt))
			return false;
		Card c = this.getCard(number, expire, crypt);
		// Be careful with currency conversion
		if(convert(c.getCurrency(),c.getMoney())<money)
			return false;
		// End Mock
		return true;
	}
	
}
