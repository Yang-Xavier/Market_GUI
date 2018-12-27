package ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tool.CheckingStock;
import tool.Constant;
import tool.DateSelection;

public class EntryGUI extends JFrame{
	final private int WIDTH = 600, HEIGHT = 150;
	private CheckingStock stock; // This object contains all of information about the stock such as ticker, start date and end date
	private JComboBox<String> tickerSelectBox, startDay, startMonth, startYear, endDay, endMonth, endYear;
	private JLabel tickerLabel,startLabel, endLabel, sdayLabel, smonthLabel, syearLabel, edayLabel, emonthLabel, eyearLabel;
	private JPanel left, center, ctop, cmiddle, cbottom;
	private Consumer<CheckingStock> callback; // This is a callback function
	private JButton checkBottom;
	private DateSelection startDateSelection, endDateSelection;
	
	public EntryGUI() {
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();  // Get the size of the screen
		stock= new CheckingStock();
		setTitle("Ticker Selection");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setLocation(screenSize.width/2- WIDTH/2, screenSize.height/2-HEIGHT/2-200);  // Maker sure the window opening in the centre
		
		// all panels
		left = new JPanel();
		center = new JPanel();
		ctop = new JPanel();
		cmiddle = new JPanel();
		cbottom = new JPanel();
		left.setLayout(new GridLayout(3,1));
		center.setLayout(new GridLayout(3,1));
		
		// all labels
		tickerLabel = new JLabel("  Ticker :");
		startLabel = new JLabel("  Start Date :");
		endLabel = new JLabel("  End Date :");
		smonthLabel = new JLabel("Month");
		syearLabel = new JLabel("Year");
		sdayLabel = new JLabel("Day");
		emonthLabel = new JLabel("Month");
		eyearLabel = new JLabel("Year");
		edayLabel = new JLabel("Day");
		
		// all drop boxes
		tickerSelectBox = new JComboBox<>(Constant.TICKERSNAME);
		startDay = new  JComboBox<>();
		startMonth = new  JComboBox<>();
		startYear = new  JComboBox<>();
		endDay = new  JComboBox<>();
		endMonth = new  JComboBox<>();
		endYear = new  JComboBox<>();
		
		// inquie bottom
		checkBottom = new JButton("Inquire");
		
		// date selection 
		startDateSelection = new DateSelection();
		endDateSelection = new DateSelection();
		
		left.add(tickerLabel);
		left.add(startLabel);
		left.add(endLabel);
		
		ctop.add(tickerSelectBox);
		ctop.add(checkBottom);
		
		cmiddle.add(startYear);
		cmiddle.add(syearLabel);
		cmiddle.add(startMonth);
		cmiddle.add(smonthLabel);
		cmiddle.add(startDay);
		cmiddle.add(sdayLabel);
		
		cbottom.add(endYear);
		cbottom.add(eyearLabel);
		cbottom.add(endMonth);
		cbottom.add(emonthLabel);
		cbottom.add(endDay);
		cbottom.add(edayLabel);
		
		center.add(ctop);
		center.add(cmiddle);
		center.add(cbottom);
		
		add(left, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
	}
	
	public void updateDateSelection() {
		
	}
	
	public void setCallback(Consumer<CheckingStock> callback) {
		this.callback = callback;
	}
	
	// Callback to open the check window
	private void openCheckPane(CheckingStock stock) {
		this.callback.accept(stock);
	}
}
