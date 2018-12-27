package tool;

import java.util.Calendar;

public class DateSelection {
	private Calendar now = Calendar.getInstance();
	private int minYear = 1900, maxYear = now.get(Calendar.YEAR);
	private int selectedYear = minYear, selectedMonth = 1, selectedDay = 1;
	private String month30d = "04060912"; // these month contain 30 days;
	
	public String[] getDays() {
		int maxDay = 31;
		String cmonth = String.format("%2d", selectedMonth);
		if (month30d.indexOf(cmonth)>0) {maxDay = 30;}
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
		int maxMonth = selectedYear==maxYear?now.get(Calendar.MONTH)+1:12;
		String[] months = new String[maxMonth];
		for(int i = 1; i<=maxMonth-0; i++) {
			months[i-1] =i + "";
		}
		return months;
	}

	public String[] getYears() {
		String[] years = new String[maxYear-minYear+1];
		for(int i = 0; i<=maxYear-minYear+1; i++) {
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
	
}
