package ui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tool.CheckingStock;
import tool.Constant;
import tool.DateSelection;

public class EntryGUI extends JFrame{
	final private int WIDTH = 600, HEIGHT = 150;
	private CheckingStock stock; // This object contains all of information about the stock such as ticker, start date and end date
	private JComboBox<String> tickerSelectBox;
	private JLabel tickerLabel,startLabel, endLabel;
	private JPanel left, center, ctop, cmiddle, cbottom;
	private Consumer<CheckingStock> callback; // This is a callback function
	private JButton checkBottom;
	public DateSelection startDateSelection, endDateSelection;
	
	
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
		
		// all drop boxes
		tickerSelectBox = new JComboBox<>(Constant.TICKERSNAME);
		
		// inquie bottom
		checkBottom = new JButton("Inquire");
		checkBottom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(checkDateValidation()) {
					stock.setTiker(Constant.TICKERMAP.get((String)tickerSelectBox.getSelectedItem()), (String)tickerSelectBox.getSelectedItem());
					stock.setStart(startDateSelection.getFormatDate());
					stock.setEnd(endDateSelection.getFormatDate());
					
					stock.setNumRows(daysBetween(startDateSelection.getFormatDate(),endDateSelection.getFormatDate()));
					// Open a market viewer
					openCheckPane(stock);
				} else {
					JOptionPane.showMessageDialog(null, "Please make sure the end date is over the start date.","Notice",JOptionPane.WARNING_MESSAGE);  
				}
			}
		});
		
		// date selection 
		startDateSelection = new DateSelection();
		endDateSelection = new DateSelection();
		
		left.add(tickerLabel);
		left.add(startLabel);
		left.add(endLabel);
		
		ctop.add(tickerSelectBox);
		ctop.add(checkBottom);
		
		// get DateSelection panel
		cmiddle.add(startDateSelection.getDateSelectionPanel());
		cbottom.add(endDateSelection.getDateSelectionPanel());
		
		center.add(ctop);
		center.add(cmiddle);
		center.add(cbottom);
		
		add(left, BorderLayout.WEST);
		add(center, BorderLayout.CENTER);
		
		startDateSelection.initDateSelection(false);
		endDateSelection.initDateSelection(true);
	}
	
	public boolean checkDateValidation() {
		// Translate date string into an integer and use start date minus end date if result is below 0 that would be valid otherwise would be invalid
		String start = String.format("%04d", startDateSelection.getSelectedYear())+String.format("%02d", startDateSelection.getSelectedMonth())+String.format("%02d", startDateSelection.getSelectedDay());
		String end = String.format("%04d", endDateSelection.getSelectedYear())+String.format("%02d", endDateSelection.getSelectedMonth())+String.format("%02d", endDateSelection.getSelectedDay());
		return Integer.parseInt(start)-Integer.parseInt(end) < 0;
	}
	
	public void setCallback(Consumer<CheckingStock> callback) {
		this.callback = callback;
	}
	
	// Callback to open the check window
	private void openCheckPane(CheckingStock stock) {
		this.callback.accept(stock);
	}
	
	int daysBetween(String startDay, String endDay) {
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    Date date1 = null;
	    Date date2 = null;
	    try {
	        date1 = sdf.parse(startDay);
	        date2 = sdf.parse(endDay);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date1);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    long time1 = cal.getTimeInMillis();
	    cal.setTime(date2);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    long time2 = cal.getTimeInMillis();
	    long between_days = (time2 - time1) / (1000 * 3600 * 24);
	    return Integer.parseInt(String.valueOf(between_days));
	}
}
