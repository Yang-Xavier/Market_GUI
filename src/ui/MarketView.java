package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import stock.KLinePanel;
import stock.StockDayItem;
import stock.VolumePanel;
import tool.CheckingStock;
import tool.MyRequest;
import tool.MyURL;
import tool.Parse;

public class MarketView extends JFrame{
	CheckingStock stock;
	MyURL myURL;
	ArrayList<StockDayItem> dayItems;
	
	final private int WIDTH = 800, HEIGHT = 600;
	final private int HEADERHEIGHT = 400; 
	final private int STEPWIDTH = 20; 
	// Header panel for showing k line graph and footer panel for showing the volume graph
	
	public MarketView(CheckingStock stock) {
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();  // Get the size of the screen
		this.stock = stock;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(stock.getStockName());
		setSize(WIDTH, HEIGHT);
		setLocation(screenSize.width/2- WIDTH/2, screenSize.height/2-HEIGHT/2);  // Maker sure the window opening in the centre
		myURL = new MyURL(stock.getTicker(),stock.getNumRows(),stock.getStart(),stock.getEnd());
	}
	
	public void display() {
		String feedback = MyRequest.get(myURL);
		Parse parse = new Parse();
		dayItems = parse.paeseStockDataToArraylist(feedback);
		dayItems = parse.processToDrawable(dayItems, HEIGHT, HEADERHEIGHT, STEPWIDTH);
		
		createGraphPanel(dayItems);
	}
	
	void createGraphPanel(ArrayList<StockDayItem> items) {
		for (StockDayItem item: items) {
			item.getDrawableStock().setKLine(new KLinePanel(item.getDrawableStock().getRectangle(), item.getDrawableStock().getLine()));
			item.getDrawableStock().setVolume(new VolumePanel(item.getDrawableStock().getVolumeRectangle()));
		}
	}
	
	
	
	
	
	
}
