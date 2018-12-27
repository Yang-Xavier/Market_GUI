package tool;

import java.util.HashMap;

public class Constant {
	public final static String[] 
			TICKERSNAME= {
			"A Clean Slate Inc. (DRWN)", "B Communications Ltd. (BCOM)", "C M Holdings PLC (COLON)", "D&F Roalba S.A. (S0731)",
			"E for L Aim PCL (EFORL)", "F&C Capital & Income Investment Trust PLC (FCI)", "G Capital PCL (GCAP)"
			}, 
			
			TICKERS = {"DRWN", "BCOM","COLON","S0731","EFORL", "FCI", "GCAP"};
	
	public final static HashMap<String, String> TICKERMAP = createTickers();
			
	private static HashMap<String, String> createTickers() {
		HashMap<String, String> t = new HashMap<String, String>();
		for (int i = 0; i< TICKERSNAME.length && i< TICKERSNAME.length; i++) {
			t.put(TICKERSNAME[i], TICKERS[i]);
		}
		return t;
	}
}
