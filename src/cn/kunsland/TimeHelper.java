package cn.kunsland;

import java.util.Calendar;

public class TimeHelper {
	
	public static String getHourMinuteNumOnly() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		return (hour < 10 ? "0" + hour : hour) + ""
				+ (minute < 10 ? "0" + minute : minute);
	}

	public static String getHourMinute() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		return (hour < 10 ? "0" + hour : hour) + ":"
				+ (minute < 10 ? "0" + minute : minute);
	}
	

	public static void main(String[] args) {
		System.out.println(TimeHelper.getHourMinute());
	}
}
