package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import stock.KLinePanel;
import stock.StockDayItem;
import stock.VolumePanel;
import tool.CheckingStock;
import tool.MyRequest;
import tool.MyURL;
import tool.Parse;

public class MarketView extends JFrame{
	CheckingStock stock;
	ArrayList<StockDayItem> dayItems;
	
	JPanel mainPanel;
	JScrollPane jScrollPane;
	
	final private int WIDTH = 800, HEIGHT = 600;
	final private int HEADERHEIGHT = 500; 
	final private int STEPWIDTH = 20; 
	// Header panel for showing k line graph and footer panel for showing the volume graph
	
	public MarketView(CheckingStock stock) {
		this.stock = stock;
		
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();  // Get the size of the screen
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(stock.getStockName());
		setSize(WIDTH, HEIGHT+50); // avoid the horizontal scroll bar
		setLocation(screenSize.width/2- WIDTH/2, screenSize.height/2-HEIGHT/2);  // Maker sure the window opening in the centre
		
		mainPanel = new JPanel();
		jScrollPane = new JScrollPane();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	}
	
	public void display() {
		MyURL myURL = new MyURL(stock.getTicker(),stock.getNumRows(),stock.getStart(),stock.getEnd());
		String feedback = MyRequest.get(myURL);
		Parse parse = new Parse();
		dayItems = parse.paeseStockDataToArraylist(feedback);
		dayItems = parse.processToDrawable(dayItems, HEIGHT, HEADERHEIGHT, STEPWIDTH);
		
		createGraphPanel(dayItems);
		addAllToPane(dayItems);
	}
	
	void createGraphPanel(ArrayList<StockDayItem> items) {
		for (StockDayItem item: items) {
			JPanel itemPanel = new JPanel();
			itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
			KLinePanel kLinePanel = new KLinePanel(item.getDrawableStock().getRectangle(), item.getDrawableStock().getLine(), item.getDrawableStock().getColor());
			VolumePanel volumePanel = new VolumePanel(item.getDrawableStock().getVolumeRectangle(),item.getDrawableStock().getColor());
			kLinePanel.setPreferredSize(new Dimension(STEPWIDTH, HEADERHEIGHT));
			volumePanel.setPreferredSize(new Dimension(STEPWIDTH, HEIGHT-HEADERHEIGHT));
			itemPanel.add(kLinePanel);
			itemPanel.add(volumePanel);
			
			item.getDrawableStock().setItemPanel(itemPanel);
		}
	}
	
	void addAllToPane(ArrayList<StockDayItem> items) {
		for (StockDayItem item: items) {
			mainPanel.add(item.getDrawableStock().getItemPanel());
		}
		jScrollPane.setViewportView(mainPanel);
		add(jScrollPane);
	}
	
	
	
	
	
	
}
