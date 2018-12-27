package tool;

import java.net.MalformedURLException;
import java.net.URL;

public class MyURL{
	final private String HOST = "https://quotes.wsj.com/";
	final private String DOWNLOADTEMPLATE = "%s/historical-prices/download?MOD_VIEW=page&num_rows=%s&startDate=%s&endDate=%s";
	private URL url;

	
	public MyURL() {
		
	}
	
	public MyURL(String tiker, String numRows, String start, String end) {
		String path = String.format(DOWNLOADTEMPLATE, tiker, numRows, start, end);
		try {
			url = new URL(HOST + path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public URL getURL() {
		return url;
	}
}
