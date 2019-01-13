package tool;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Calendar;

import javax.swing.Action;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DateSelection  {
	private Calendar now = Calendar.getInstance();
	private int minYear = 1900, maxYear = now.get(Calendar.YEAR);
	private int selectedYear = minYear, selectedMonth = 1, selectedDay = 1;
	private String month30d = "04060911"; // these month contain 30 days;
	
	public String[] getDays() {
		int maxDay = 31;
		String cmonth = String.format("%02d", selectedMonth);
		if (month30d.indexOf(cmonth)>=0) {maxDay = 30;}
		if (selectedMonth == 2) {
			maxDay = 28;  // not leap year
			if (selectedYear % 4 == 0 && selectedYear % 100 != 0) {  
	            maxDay = 29;  // leap year
	        } else if (selectedYear % 400 == 0) {  
	        	maxDay = 29;  // leap year
	        }  
		}
		if (selectedYear==maxYear && selectedMonth == now.get(Calendar.MONTH)) {
			maxDay = now.get(Calendar.DAY_OF_MONTH);  // today is the max day
		}
		
		String[] days = new String[maxDay];
		for(int i = 1; i<=maxDay; i++) {
			days[i-1] = i + "";
		}
		return days;
	}
	
	public String[] getMonths() {
		String[] months = new String[12];
		for(int i = 1; i<=12; i++) {
			months[i-1] =i + "";
		}
		return months;
	}

	public String[] getYears() {
		String[] years = new String[maxYear-minYear+1];
		for(int i = 0; i<=maxYear-minYear; i++) {
			years[i] =(i+minYear) + "";
		}
		return years;
	}
	
	public void selectYear(String year) {
		this.selectedYear = Integer.valueOf(year);
	}
	
	public void selectMonth(String month) {
		this.selectedMonth = Integer.valueOf(month);
	}
	
	public void selectDay(String day) {
		this.selectedDay = Integer.valueOf(day);
	}
	
	public int getSelectedYear() {
		return this.selectedYear;
	}
	
	public int getSelectedMonth() {
		return this.selectedMonth;
	}
	
	public int getSelectedDay() {
		return this.selectedDay;
	}
	
	public String getFormatDate() {
		String dateFormat = "%02d/%02d/%02d";
		return String.format(dateFormat, selectedMonth,selectedDay,selectedYear);
	}
	
	/** 
	 * 
	 * The part in the below is the DateSelection UI component*/
	JComboBox<String> yearComboBox;
	JComboBox<String>  monthComboBox;
	JComboBox<String>  dayComboBox;
	public JPanel getDateSelectionPanel() {
		JPanel p = new JPanel();
		yearComboBox = new JComboBox<String> ();
		monthComboBox = new JComboBox<String> ();
		dayComboBox = new JComboBox<String> ();
		
		yearComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectYear((String)yearComboBox.getSelectedItem());
				updateDateSelection();
			}
		});
		
		monthComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMonth((String)monthComboBox.getSelectedItem());
				updateDateSelection();
			}
		});
		
		dayComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectDay((String)dayComboBox.getSelectedItem());
				updateDateSelection();
			}
		});
		
		JLabel yearLabel = new JLabel("Year");
		JLabel monthLabel = new JLabel("Month");
		JLabel dayLabel = new JLabel("Day");
		
		p.add(yearComboBox);
		p.add(yearLabel);
		p.add(monthComboBox);
		p.add(monthLabel);
		p.add(dayComboBox);
		p.add(dayLabel);
		return p;
	}
	
	public void initDateSelection(Boolean isToday) {
		if (isToday) {
			selectYear(now.get(Calendar.YEAR)+"");
		} else {
			selectYear(now.get(Calendar.YEAR)-1+"");
		}
		selectMonth((now.get(Calendar.MONTH)+1)+"");
		selectDay(now.get(Calendar.DAY_OF_MONTH)+"");
		updateDateSelection();
}
	
	public void updateDateSelection() {
		yearComboBox.setModel(new DefaultComboBoxModel<String>(getYears()));
		monthComboBox.setModel(new DefaultComboBoxModel<String>(getMonths()));
		dayComboBox.setModel(new DefaultComboBoxModel<String>(getDays()));
		
		yearComboBox.setSelectedIndex(getSelectedYear()-1900);
		monthComboBox.setSelectedIndex(getSelectedMonth()-1);
		// Sometime the day selection would be overflow when the month has change
		try {
			dayComboBox.setSelectedIndex(getSelectedDay()-1);
		} catch (Exception e) {
			selectDay(1+"");
			dayComboBox.setSelectedIndex(getSelectedDay()-1);
		}
		
	}
	
	
}
