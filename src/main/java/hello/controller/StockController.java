package hello.controller;
import hello.entities.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {


    private final AtomicLong counter = new AtomicLong();
    List<Stock> stock;
    List<String> stockReq;
    
    public StockController() {
    	stock = new ArrayList<>();
    	stockReq = new ArrayList<>();
    	stockReq.add("aapl");
    	stockReq.add("fb");
    	stockReq.add("googl");
    	stockReq.add("msft");
    	
    }
    
    @RequestMapping("/test")
    public void testConnection() {
    	System.out.println("===========================================================");
    	System.out.println("  Connection Successful !!                                 ");
    	System.out.println("===========================================================");
    }
    
  @RequestMapping("/stock")
  public List<Stock> getAllStock() {
	  
	  stock = new ArrayList<>();
	  String stockName = "";
	  for(int i = 0; i < stockReq.size(); i++) {
		  stockName = stockReq.get(i);
		  stock.add(new Stock(stockName, getPriceOfStock(stockName)));
	  }
      return stock;
  }
  
  @RequestMapping("/stock/apple")
  public List<Stock> getAppleStock(){
	  stock = new ArrayList<>();
	  stock.add(new Stock("aapl", getPriceOfStock("aapl")));
	  
	  return stock;
  }
  
  @RequestMapping("/stock/facebook")
  public List<Stock> getFacebookStock(){
	  stock = new ArrayList<>();
	  stock.add(new Stock("fb", getPriceOfStock("fb")));
	  
	  return stock;
  }
  
  @RequestMapping("/stock/google")
  public List<Stock> getGoogleStock(){
	  stock = new ArrayList<>();
	  stock.add(new Stock("googl", getPriceOfStock("googl")));
	  
	  return stock;
  }
	
	public Double getPriceOfStock(String stockName){
		
		//Initial variable
		//------------------------------------------------------------	
		StringBuilder urlStr;
		URL url;
		HttpURLConnection conn;
		JSONParser parse;
		JSONObject jsonObj;
		JSONObject quoteObj;
		
		String symbol;
		Double lastestPrice = 0.0;
		Scanner sc;
		String inline;
		
		//Make URL 
		//------------------------------------------------------------	
		urlStr = new StringBuilder();
		urlStr.append("https://api.iextrading.com/1.0/stock/");
		urlStr.append(stockName);
		urlStr.append("/batch?types=quote,news,chart&range=1m&last=10");
		
		System.out.println("URL : " + urlStr.toString());
		try {
			url = new URL(urlStr.toString());
			
			//Call Rest Ful Api
			//------------------------------------------------------------	
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			int responsecode = conn.getResponseCode();
			
			//Error
			if(responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			}
			
			//Sprit Line
			//------------------------------------------------------------	
			inline = "";
			sc = new Scanner(url.openStream());
			while(sc.hasNext())
			{
				inline+=sc.nextLine();
			}
			sc.close();
			
			//Convert String to JSON
			//---------------------------------------------------------------
			parse = new JSONParser(); 
			try {
				jsonObj = (JSONObject)parse.parse(inline); 
				quoteObj = (JSONObject)jsonObj.get("quote");

				symbol = (String)quoteObj.get("symbol");
			
				try {
					lastestPrice = (Double)quoteObj.get("latestPrice");
				}catch(Exception e) {
					Long temp = (Long)quoteObj.get("latestPrice");
					lastestPrice = Double.valueOf(temp);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return lastestPrice;
			} 
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return lastestPrice;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return lastestPrice;
		}
	
		return lastestPrice;
	}
	
	public void setStock(List<Stock> stock) {
		this.stock = stock;
	}
	
	public List<Stock> getStock() {
		return this.stock;
	}
}