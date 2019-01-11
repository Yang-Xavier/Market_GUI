package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import stock.InformationDialog;
import stock.KLinePanel;
import stock.StockDayItem;
import stock.VolumePanel;
import stock.PriceScaleBar;
import tool.CheckingStock;
import tool.MyRequest;
import tool.MyURL;
import tool.Parse;

public class MarketView extends JFrame{
	CheckingStock stock;
	ArrayList<StockDayItem> dayItems;
	
	JPanel mainPanel;
	JScrollPane jScrollPane;
	
	InformationDialog informationDialog;
	PriceScaleBar priceScaleBar;
	
	final private int WIDTH = 800, HEIGHT = 600;
	final private int HEADERHEIGHT = 500; 
	final private int STEPWIDTH = 20; 
	// Header panel for showing k line graph and footer panel for showing the volume graph
	
	public MarketView(CheckingStock stock) {
		this.stock = stock;
		
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();  // Get the size of the screen
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(stock.getStockName() + " " + stock.getStart() + "---" + stock.getEnd());
		setSize(WIDTH, HEIGHT+150); // avoid the horizontal scroll bar
		setLocation(screenSize.width/2- WIDTH/2, screenSize.height/2-HEIGHT/2);  // Maker sure the window opening in the centre
		
		mainPanel = new JPanel();
		jScrollPane = new JScrollPane();
		informationDialog = new InformationDialog(this);
		priceScaleBar = new PriceScaleBar();
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		setBackground(new Color(25, 28, 32));
		mainPanel.setOpaque(true);
		mainPanel.setBackground(new Color(25, 28, 32));
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				informationDialog.setVisible(false);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	/**
	 * Send request, process feedback data and display the data by using the graph*/
	public void display() {
		MyURL myURL = new MyURL(stock.getTicker(),stock.getNumRows(),stock.getStart(),stock.getEnd());
		String feedback = MyRequest.get(myURL);
		Parse parse = new Parse();
		dayItems = parse.paeseStockDataToArraylist(feedback);
		dayItems = parse.processToDrawable(dayItems, HEIGHT, HEADERHEIGHT, STEPWIDTH);
		priceScaleBar.setPriceScalePanel(parse.pmax, parse.pmin, HEADERHEIGHT, 4);
		priceScaleBar.setVolumeScalePanel(parse.vmax, parse.vmin, HEIGHT-HEADERHEIGHT, 3);
		createGraphPanel(dayItems);
		addAllToPane(dayItems);
		informationDialog.setVisible(true);
	}
	
	void createGraphPanel(ArrayList<StockDayItem> items) {
		for (StockDayItem item: items) {
			JPanel itemPanel = new JPanel();
			itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
			KLinePanel kLinePanel = new KLinePanel(item.getDrawableStock().getRectangle(), item.getDrawableStock().getLine(), item.getDrawableStock().getColor());
			VolumePanel volumePanel = new VolumePanel(item.getDrawableStock().getVolumeRectangle(),item.getDrawableStock().getColor());
			kLinePanel.setPreferredSize(new Dimension(STEPWIDTH, HEADERHEIGHT + 40));
			volumePanel.setPreferredSize(new Dimension(STEPWIDTH, HEIGHT-HEADERHEIGHT + 40));
			kLinePanel.setBorder(new MatteBorder(0,0,1,0, new Color(50,50,50)));
			volumePanel.setBorder(new MatteBorder(1,0,0,0, new Color(50,50,50)));
			itemPanel.add(kLinePanel);
			itemPanel.add(volumePanel);
			item.getDrawableStock().setItemPanel(itemPanel);
		}
	}
	
	void addAllToPane(ArrayList<StockDayItem> items) {
		mainPanel.add(priceScaleBar);
		for (StockDayItem item: items) {
			JPanel itemPanel = item.getDrawableStock().getItemPanel();
			itemPanel.setBackground(null);
			bindEvent(itemPanel, item);
			mainPanel.add(itemPanel);
		}
		jScrollPane.setViewportView(mainPanel);
		
		add(jScrollPane);
	}
	
	void bindEvent(JPanel panel, StockDayItem item) {
		panel.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JPanel panel = (JPanel)e.getSource();
				panel.setBackground(new Color(100, 100, 100));
				informationDialog.updateInformation(item);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				JPanel panel = (JPanel)e.getSource();
				panel.setBackground(null);
			}
			
		});
	}
}
