package cn.powerrun.locate.utils;

import java.util.Calendar;

public class DateUtils {
	public static String getDateFileName() {
		Calendar now = Calendar.getInstance();
		return String.valueOf(now.get(Calendar.YEAR)) + String.valueOf((now.get(Calendar.MONTH) + 1))
				+ String.valueOf(now.get(Calendar.DAY_OF_MONTH));
	}
}
