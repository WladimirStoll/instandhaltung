/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
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
