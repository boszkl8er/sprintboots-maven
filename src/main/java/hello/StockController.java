package hello;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    //private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/stock")
    public Stock stock(@RequestParam(value="price", defaultValue="0") String name) {
        return new Stock(counter.incrementAndGet(),
                            String.format(name));
    }
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/debug")
	public void testDebug(){
		System.out.println("==============================================");
		System.out.println("Test Console printing");
		
		try {
			URL url = new URL("https://api.iextrading.com/1.0/stock/aapl/batch?types=quote,news,chart&range=1m&last=10");
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			int responsecode = conn.getResponseCode();
			System.out.println("responsecode : " + responsecode);
			
			//Error
			if(responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " +responsecode);
			}
			
			String inline = "";
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext())
			{
			inline+=sc.nextLine();
			}
//			System.out.println("\nJSON data in string format");
//			System.out.println(inline);
			sc.close();
			
			//Convert String to JSON
			//---------------------------------------------------------------
			
			
			JSONParser parse = new JSONParser(); 
			try {
				JSONObject jsonObj = (JSONObject)parse.parse(inline); 
//				System.out.println("==========================================");
//				System.out.println("String Quote : " + jsonObj);
				

				//JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
				JSONObject quoteObj = (JSONObject)jsonObj.get("quote");
				System.out.println("==========================================");
				System.out.println("String Quote : " + quoteObj);
				
				String symbol = (String)quoteObj.get("symbol");
				Double lastestPrice = (Double)quoteObj.get("latestPrice");
				
				System.out.println("-------------------------------------------");
				System.out.println("Symbol : " + symbol);
				System.out.println("lastest price : " + lastestPrice);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}