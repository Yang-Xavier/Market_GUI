package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import stock.EmptyLine;
import stock.InformationDialog;
import stock.KLinePanel;
import stock.ScalePanel;
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
	ScalePanel scalePanel;
	
	InformationDialog informationDialog;
	
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
		scalePanel = new ScalePanel();
		informationDialog = new InformationDialog(this);
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
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
	
	public void display() {
		MyURL myURL = new MyURL(stock.getTicker(),stock.getNumRows(),stock.getStart(),stock.getEnd());
		String feedback = MyRequest.get(myURL);
		Parse parse = new Parse();
		dayItems = parse.paeseStockDataToArraylist(feedback);
		dayItems = parse.processToDrawable(dayItems, HEIGHT, HEADERHEIGHT, STEPWIDTH);
		scalePanel.setPriceScalePanel(parse.pmax, parse.pmin, HEADERHEIGHT, 4);
		scalePanel.setVolumeScalePanel(parse.vmax, parse.vmin, HEIGHT-HEADERHEIGHT, 3);
		createGraphPanel(dayItems);
		addAllToPane(dayItems);
		informationDialog.setVisible(true);
	}
	
	void createGraphPanel(ArrayList<StockDayItem> items) {
		for (StockDayItem item: items) {
			JPanel itemPanel = new JPanel();
			EmptyLine e1 = new EmptyLine(STEPWIDTH, 20);
			EmptyLine e2 = new EmptyLine(STEPWIDTH, 20);
			EmptyLine e3 = new EmptyLine(STEPWIDTH, 20);
			EmptyLine e4 = new EmptyLine(STEPWIDTH, 20);
			EmptyLine eline = new EmptyLine(STEPWIDTH, 1);
			eline.setOpaque(true);
			eline.setBackground(Color.gray);
			itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
			KLinePanel kLinePanel = new KLinePanel(item.getDrawableStock().getRectangle(), item.getDrawableStock().getLine(), item.getDrawableStock().getColor());
			VolumePanel volumePanel = new VolumePanel(item.getDrawableStock().getVolumeRectangle(),item.getDrawableStock().getColor());
			kLinePanel.setPreferredSize(new Dimension(STEPWIDTH, HEADERHEIGHT));
			volumePanel.setPreferredSize(new Dimension(STEPWIDTH, HEIGHT-HEADERHEIGHT));
			itemPanel.add(e1);
			itemPanel.add(kLinePanel);
			itemPanel.add(e2);
			itemPanel.add(eline);
			itemPanel.add(e3);
			itemPanel.add(volumePanel);
			itemPanel.add(e4);
			item.getDrawableStock().setItemPanel(itemPanel);
		}
	}
	
	void addAllToPane(ArrayList<StockDayItem> items) {
		mainPanel.add(scalePanel);
		for (StockDayItem item: items) {
			JPanel itemPanel = item.getDrawableStock().getItemPanel();
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
				panel.setBackground(Color.gray);
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
