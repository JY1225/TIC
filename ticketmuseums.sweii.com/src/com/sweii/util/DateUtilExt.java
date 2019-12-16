package com.sweii.util;
import java.util.Calendar;
import java.util.Date;

import com.sweii.framework.utility.DateUtil;
/**
 *��չframework��dateutil����
 * 
 * @author Administrator
 *@date 2010-3-29
 * 
 **/
public class DateUtilExt extends DateUtil {
    /**
     * ������������һ��
     * 
     * @param date
     * @return
     */
    public static Date getEndOfMonth(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	cal.set(Calendar.HOUR_OF_DAY, 23);
	cal.set(Calendar.MINUTE, 59);
	cal.set(Calendar.SECOND, 59);
	cal.set(Calendar.MILLISECOND, 0);
	return cal.getTime();
    }
    /**
     * ��õ��µ�һ��
     * 
     * @param date
     * @return
     */
    public static Date getFirstOfMonth(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.DATE, 1);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	return cal.getTime();
    }
    public static Date getFirstOfWeek(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	// System.out.println("���������: " + cal.getTime());
	int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
	System.out.println("day_of_week: " + day_of_week);
	cal.add(Calendar.DATE, -day_of_week);
	System.out.println("���ܵ�һ��: " + cal.getTime());
	return cal.getTime();
    }
    public static Date getEndOfWeek(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(getFirstOfWeek(date));
	cal.add(Calendar.DATE, 6);
	cal.set(Calendar.HOUR, 23);
	cal.set(Calendar.MINUTE, 59);
	cal.set(Calendar.SECOND, 59);
	cal.set(Calendar.MILLISECOND, 99);
	System.out.println("����ĩ: " + cal.getTime());
	return cal.getTime();
    }
    /**
     * ���µڼ���
     * 
     * @param date
     * @return
     */
    public static int getMonthWeek(Date date) {
	int w = date.getDay(),d = date.getDate();
	return (d + 6 - w) / 7;
    }
    /**
     * ��ô���ʱ������
     * 
     * @param date
     * @return
     */
    public static Date getStartOfDay(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	return cal.getTime();
    }
    /**
     * ��ô���ʱ���23:59
     * 
     * @param date
     * @return
     */
    public static Date getEndOfDay(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	cal.set(Calendar.HOUR_OF_DAY, 23);
	cal.set(Calendar.MINUTE, 59);
	cal.set(Calendar.SECOND, 59);
	cal.set(Calendar.MILLISECOND, 999);
	return cal.getTime();
    }
    /**
     * ������������·ݵ�һ�������ڼ� <br>
     * 1 ��������
     * 
     * @param date
     * @return
     */
    public static int getWeekNumOfFirstDayInMonth(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	int week_num;
	cal.set(Calendar.DATE, 1);// // ����ʱ��Ϊ��Ҫ��ѯ�����µĵ�һ��
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	week_num = (int) (cal.get(Calendar.DAY_OF_WEEK));// �õ���һ�������
	return week_num;
    }
    /**
     * �����������ܼ���1Ϊ����
     * @param date
     * @return
     */
    public static int getWeekNumOfDay(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	int week_num;
	//cal.set(Calendar.DATE, 1);// // ����ʱ��Ϊ��Ҫ��ѯ�����µĵ�һ��
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	week_num = (int) (cal.get(Calendar.DAY_OF_WEEK));// �õ�����
	return week_num;
    }
    /**
     * ��õ��µ�����
     * @param date
     * @return
     */
    public static int getDayNumOfMonth(Date date) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	int month_day_score;
	cal.set(Calendar.DATE, 1);// // ����ʱ��Ϊ��Ҫ��ѯ�����µĵ�һ��
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	month_day_score = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 
	return month_day_score;
    }
    public static int checkWeek(Date date, String days) {
	int weekNum = DateUtilExt.getWeekNumOfDay(date);
	return 0;
    }
    /**
     * 
     * 
     * @param month
     * @return
     */
    public static String genHoliday1(Date month) {
	String defRest = "";
	int dayCount = DateUtilExt.getDayNumOfMonth(month);
	Calendar cal = Calendar.getInstance();
	cal.setTime(month);
	cal.set(Calendar.DATE, 1);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	defRest="1";
	for (int i = 1; i < dayCount; i++) {
	    defRest += ",";
	    cal.set(Calendar.DATE, i + 1);
	    int weekNum = DateUtilExt.getWeekNumOfDay(cal.getTime());
	    if (weekNum == 1 || weekNum == 7) {// ���գ�����
		defRest += "2";
	    } else {
		defRest += "1";
	    }
	}
	return defRest;
    }
    /**
     * 
     * 
     * @param month
     * @return
     */
    /**
     * ����Ĭ����Ϣ����
     * 
     * @param month
     * @return
     */
    public static String genWeeks(Date month) {
	String defRest = "";
	int dayCount = DateUtilExt.getDayNumOfMonth(month);
	Calendar cal = Calendar.getInstance();
	cal.setTime(month);
	cal.set(Calendar.DATE, 1);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	for (int i = 0; i < dayCount; i++) {
	    if (i != 0) {	
		defRest += ",";
	    }
	    cal.set(Calendar.DATE, i + 1);
	    int weekNum = DateUtilExt.getWeekNumOfDay(cal.getTime());
	    if (weekNum == 6) {// ���գ�����
		defRest += "0";
	    } else {
		defRest += "1";
	    }
	}
	return defRest;
    }
    /**
     * ����Ĭ����Ϣ����
     * 
     * @param month
     * @return
     */
    public static String genMonths(Date month) {
	String defRest = "";
	int dayCount = DateUtilExt.getDayNumOfMonth(month);
	Calendar cal = Calendar.getInstance();
	cal.setTime(month);
	cal.set(Calendar.DATE, 1);
	cal.set(Calendar.HOUR_OF_DAY, 0);
	cal.set(Calendar.MINUTE, 0);
	cal.set(Calendar.SECOND, 0);
	cal.set(Calendar.MILLISECOND, 0);
	boolean flag = false;
	for (int i = dayCount - 1; i >= 0; i--) {
	    if (i != dayCount - 1) {
		defRest += ",";
	    }
	    cal.set(Calendar.DATE, i + 1);
	    int weekNum = DateUtilExt.getWeekNumOfDay(cal.getTime());
	    if (flag == true || weekNum == 1 || weekNum == 7) {// ���գ�����
		defRest += "1";
	    } else {
		defRest += "0";
		flag = true;
	    }
	}
	String[] keys = defRest.split(",");
	String set = "";
	for (int i = keys.length - 1; i >= 0; i--) {
	    if (i != keys.length - 1) {
		set += ",";
	    }
	    set += keys[i];
	}
	return set;
    }
}
