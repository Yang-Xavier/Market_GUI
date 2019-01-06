package test;

import java.net.MalformedURLException;
import java.net.URL;

import tool.MyRequest;
import tool.MyURL;

public class NetTest {
	public static void main(String[] args) {
		String feedback = MyRequest.get(new MyURL("AAPL", "300", "01/01/2018", "12/31/2018"));
		System.out.println(feedback);
	}
}

