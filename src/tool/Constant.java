package tool;

import java.util.HashMap;

public class Constant {
	public final static String[] 
			TICKERSNAME= {
			"Apple Inc.", 
//			"B Communications Ltd. (BCOM)", 
//			"C M Holdings PLC (COLON)", 
//			"D&F Roalba S.A. (S0731)",
//			"A&T Corp. (6722)", 
//			"F&C Capital & Income Investment Trust PLC (FCI)", 
//			"G Capital PCL (GCAP)"
			}, 
			
			TICKERS = {
					"AAPL", 
//					"BCOM",
//					"COLON",
//					"S0731",
//					"XTKS",
//					"FCI", 
//					"GCAP"
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
