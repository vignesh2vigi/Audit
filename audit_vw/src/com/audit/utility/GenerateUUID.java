package com.audit.utility;

import java.text.DecimalFormat;
import java.util.Calendar;

public class GenerateUUID {

	public GenerateUUID() {
	}

	public static int retval(int y) {
		String yrs = "" + y;
		yrs = yrs.substring(1);
		y = Integer.parseInt(yrs);
		return y;
	}

	public static String generateDelearListingId() {

		String id = null;

		int adder = 52;// Number Start from year 2011, change 64 to start from
		// 2001
		//int addertwo = 54;
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		
		int doy = cal.get(Calendar.DAY_OF_YEAR);
		int hr = cal.get(Calendar.HOUR_OF_DAY);
		int mi = cal.get(Calendar.MINUTE);
		int ss = cal.get(Calendar.SECOND);
		int mm = cal.get(Calendar.MILLISECOND);
		int yr = adder + retval(year);
		//int yrr = addertwo + retval(year);
		//char cc = (char) yrr;
		DecimalFormat df = new DecimalFormat("000");
		// System.out.println("Unique Num: " + c + yrr + df.format(doy) + hr +
		// mi + ss + mm);
		id = new StringBuffer().append(yr).append(df.format(doy))
				.append(hr).append(mi).append(ss).append(mm).toString();
		System.out.println( "id: " + id);
		
		return id;

	}

}
