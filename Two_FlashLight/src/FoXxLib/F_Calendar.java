package FoXxLib;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class F_Calendar {

	public static void main(String[] arg){
		
		int year = 2006;
		int week = 1;

		// 以2006-01-02位例
		Calendar c = new GregorianCalendar();
		c.set(2006, Calendar.JANUARY, 2);
		Date d = c.getTime();

		System.out.println("current date = " + d);
		System.out.println("getWeekOfYear = " + getWeekOfYear(d));
		System.out.println("getMaxWeekNumOfYear = " + getMaxWeekNumOfYear(year));
		System.out.println("getFirstDayOfWeek = " + getFirstDayOfWeek(year, week));
		System.out.println("getLastDayOfWeek = " + getLastDayOfWeek(year, week));
		System.out.println ("getFirstDayOfWeek = " + getFirstDayOfWeek(d));
		System.out.println("getLastDayOfWeek = " + getLastDayOfWeek(d));
		
	}
	
	
	
	/**
	 * 
	 * 無參數取得目前
	 */
	public F_Calendar(){
		
		// get today date
		Calendar cal = Calendar.getInstance();
		String dateStr = "" + cal.get(Calendar.YEAR) // 2012
				+ "/" + (cal.get(Calendar.MONTH) + 1) // 11 (add 1 because it
														// start from 0)
				+ "/" + cal.get(Calendar.DATE);// 13

		Calendar c = new GregorianCalendar();
		c.set(cal.get(Calendar.YEAR), Calendar.MONTH, cal.get(Calendar.DATE));
		Date d = c.getTime();
		
		
	}
	
	
	
	
	
	/**
	* 取得當前日期是多少週
	*
	* @param date
	* @return
	*/
	public static int getWeekOfYear(Date date) {
	Calendar c = new GregorianCalendar();
	c.setFirstDayOfWeek(Calendar.MONDAY);
	c.setMinimalDaysInFirstWeek(7);
	c.setTime (date);

	return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	* 得到某一年週的總數
	*
	* @param year
	* @return
	*/
	public static int getMaxWeekNumOfYear(int year) {
	Calendar c = new GregorianCalendar();
	c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);

	return getWeekOfYear(c.getTime());
	}

	/**
	* 得到某年某週的第一天
	*
	* @param year
	* @param week
	* @return
	*/
	public static Date getFirstDayOfWeek(int year, int week) {
	Calendar c = new GregorianCalendar();
	c.set(Calendar.YEAR, year);
	c.set (Calendar.MONTH, Calendar.JANUARY);
	c.set(Calendar.DATE, 1);

	Calendar cal = (GregorianCalendar) c.clone();
	cal.add(Calendar.DATE, week * 7);

	return getFirstDayOfWeek(cal.getTime ());
	}

	/**
	* 得到某年某週的最後一天
	*
	* @param year
	* @param week
	* @return
	*/
	public static Date getLastDayOfWeek(int year, int week) {
	Calendar c = new GregorianCalendar();
	c.set(Calendar.YEAR, year);
	c.set(Calendar.MONTH, Calendar.JANUARY);
	c.set(Calendar.DATE, 1);

	Calendar cal = (GregorianCalendar) c.clone();
	cal.add(Calendar.DATE , week * 7);

	return getLastDayOfWeek(cal.getTime());
	}

	/**
	* 取得當前日期所在週的第一天
	*
	* @param date
	* @return
	*/
	public static Date getFirstDayOfWeek(Date date) {
	Calendar c = new GregorianCalendar();
	c.setFirstDayOfWeek(Calendar.MONDAY);
	c.setTime(date);
	c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
	return c.getTime ();
	}

	/**
	* 取得當前日期所在週的最後一天
	*
	* @param date
	* @return
	*/
	public static Date getLastDayOfWeek(Date date) {
	Calendar c = new GregorianCalendar();
	c.setFirstDayOfWeek(Calendar.MONDAY);
	c.setTime(date);
	c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
	return c.getTime();
	}
}
