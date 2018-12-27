package tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** This is processing sending request and getting feedback
 * 
 *  The reason of setting the functions send and getFeedback as the static function is 
 *  that these two functions will be called frequently.
 *  
*/

public class MyRequest {
	static String feedback;
	
	// Return the feedback text if connect successfully otherwise return null
	public static String get(MyURL myUrl) {
		try {
			HttpURLConnection con = parameters(myUrl.getURL());
			con.connect();
			InputStream in = con.getInputStream();
			// decode feedback
			BufferedReader buffer = new BufferedReader(new InputStreamReader(in, "utf-8"));
			StringBuffer bs = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				bs.append(line+'\n');
			}
			feedback = bs.toString();
			return feedback;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static public String getFeedback() {
		return feedback;
	}
	
	static HttpURLConnection parameters(URL url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		// set default request
		con.setRequestMethod("GET");
		// set default user-agent
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		// charset utf-8
		con.setRequestProperty("Accept-Charset", "utf-8");
		// content type
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		return con;
	}
	
}
