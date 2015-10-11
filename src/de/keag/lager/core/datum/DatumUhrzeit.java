package de.keag.lager.core.datum;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import de.keag.lager.core.Bean;

public class DatumUhrzeit {
	public enum DatumFormat{
		DATUM,
		DATUM_ZEIT,
		MILLISEKUNDE
	}

	private DatumUhrzeit() {
		super();
	}
	
	/**
	 * http://download.oracle.com/javase/1.4.2/docs/api/java/util/GregorianCalendar.html
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDay(Date date, int days){
		if (days == 0){
			return new Date(date.getTime());
		}else{
			String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
			// create a Pacific Standard Time time zone
			 SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);
			 Calendar calendar = new GregorianCalendar(pdt);
			 calendar.setTime(date);
			 calendar.add(GregorianCalendar.DAY_OF_MONTH, days);
			 return new Date(calendar.getTimeInMillis());
		}
	}

	/*
	 * formatString "yyyy-MM-dd HH:mm:ss.SSS"
	 */
	public static String JavaToView(Date date, DatumFormat datumFormat){
		SimpleDateFormat df;
		switch (datumFormat) {
		case DATUM:
			df = new SimpleDateFormat( "dd.MM.yyyy" );
			break;
		case DATUM_ZEIT:
			df = new SimpleDateFormat( "dd.MM.yyyy HH:mm" );
			break;
		case MILLISEKUNDE:
			df = new SimpleDateFormat( "dd.MM.yyyy HH:mm:ss.SSS" );
			break;
		default:
			df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
			break;
		}
		return df.format(date);
	}

	public static String JavaToView(java.util.Date datum, DatumFormat format) {
		return JavaToView(new Date(datum.getTime()),format);
	}
}
