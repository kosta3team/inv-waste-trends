package kr.swdl.util;

public class DateFormatter {
	public static String toYearMonthDay(String date){
		// 
		if (null == date)
			return null;
		
		return date.trim().replaceAll("^(\\d{4})-(\\d{2})-(\\d{2}).*", "$1년 $2월 $3일");
	}
}
