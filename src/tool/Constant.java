package tool;

import java.util.HashMap;

public class Constant {
	public final static String[] 
			TICKERSNAME= {
			"Apple Inc.", 
			"Balchem Corp. (BCPC)", 
			"C.H. Robinson Worldwide Inc. (CHRW)", 
			}, 
			
			TICKERS = {
					"AAPL", 
					"BCPC",
					"CHRW",
					};
	
	public final static HashMap<String, String> TICKERMAP = createTickers();
			
	private static HashMap<String, String> createTickers() {
		HashMap<String, String> t = new HashMap<String, String>();
		for (int i = 0; i< TICKERSNAME.length && i< TICKERSNAME.length; i++) {
			t.put(TICKERSNAME[i], TICKERS[i]);
		}
		return t;
	}
}
