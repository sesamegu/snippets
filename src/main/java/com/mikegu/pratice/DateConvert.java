package com.mikegu.pratice;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @version $Id: DateConvert.java, v 0.1 2014年10月11日 下午5:53:39 mike Exp $
 */
public class DateConvert {

	public static void main(String[] args) {
		// System.out.println(new Date(1412911063405l));
		// System.out.println(new Date(1416492397606l));

		long dd = 1416492397606l;
		System.out.println(new Date(dd));
		System.out.println(formatGMT8(dd));

	}

	public static String formatGMT8(long ts) {
		final DateFormat OL_DT_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
		OL_DT_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return OL_DT_FORMAT.format(new Date(ts));
	}
}
