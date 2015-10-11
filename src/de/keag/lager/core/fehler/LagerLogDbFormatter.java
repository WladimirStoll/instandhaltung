package de.keag.lager.core.fehler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LagerLogDbFormatter extends Formatter {

	@Override
	public String format(LogRecord rec) {
		StringBuffer buf = new StringBuffer(1000);
		buf.append(calcDate(rec.getMillis()));
		buf.append(" ");
		buf.append(formatMessage(rec));
		return buf.toString();
		
//		StringBuffer buf = new StringBuffer(1000);
//		// Bold any levels >= WARNING
//		buf.append("<tr>");
//		buf.append("<td>");
//
//		if (rec.getLevel().intValue() >= Level.WARNING.intValue())
//		{
//			buf.append("<b>");
//			buf.append(rec.getLevel());
//			buf.append("</b>");
//		} else
//		{
//			buf.append(rec.getLevel());
//		}
//		buf.append("</td>");
//		buf.append("<td>");
//		buf.append(calcDate(rec.getMillis()));
//		buf.append(' ');
//		buf.append(formatMessage(rec));
//		buf.append('\n');
//		buf.append("<td>");
//		buf.append("</tr>\n");
//		return buf.toString();	
	}

	private String calcDate(long millisecs)
	{
		SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}	
}
