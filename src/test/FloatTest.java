package test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FloatTest {
	public static void main(String[] args) {
		System.out.println(daysBetween("2018/10/12", "2019/02/02"));
	}
	public static int daysBetween(String startDay, String endDay) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
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
