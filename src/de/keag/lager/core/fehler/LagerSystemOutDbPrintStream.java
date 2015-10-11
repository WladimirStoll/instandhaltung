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
package de.keag.lager.core.fehler;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;

public class LagerSystemOutDbPrintStream extends PrintStream {
	
	public LagerSystemOutDbPrintStream(OutputStream out) {
        super(out, true);
    }

	
	@Override
	public void print(boolean b) {
		super.print(b);
        localLog(new Boolean(b).toString());
	}
	
	 @Override
	public void print(String s) {
        super.print(s);
        localLog(s);
	}
	 
	 private void localLog(String s) {
		 try {
			 Log.log().severe(s);
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}


	@Override
	public void print(char c) {
		// TODO Auto-generated method stub
		super.print(c);
        localLog(new Integer(c).toString());
	}
	 
	 @Override
	public void print(char[] s) {
		// TODO Auto-generated method stub
		super.print(s);
        localLog(new String(s).toString());
	}
	 @Override
	public void print(double d) {
		// TODO Auto-generated method stub
		super.print(d);
        localLog(new Double(d).toString());
	}
	 @Override
	public void print(float f) {
		// TODO Auto-generated method stub
		super.print(f);
        localLog(new Float(f).toString());
	}
	 @Override
	public void print(int i) {
		// TODO Auto-generated method stub
		super.print(i);
        localLog(new Integer(i).toString());
	}
	 @Override
	public void print(long l) {
		// TODO Auto-generated method stub
		super.print(l);
        localLog(new Long(l).toString());
		
	}
	 @Override
	public void print(Object obj) {
		// TODO Auto-generated method stub
		super.print(obj);
        localLog(obj.toString());
	}
	 @Override
	public PrintStream printf(Locale l, String format, Object... args) {
		// TODO Auto-generated method stub
		return super.printf(l, format, args);
	}
	 
	 @Override
	public PrintStream printf(String format, Object... args) {
		// TODO Auto-generated method stub
		return super.printf(format, args);
	}
	 @Override
	public void println() {
		// TODO Auto-generated method stub
		super.println();
        localLog("|");
	}
	 @Override
	public void println(boolean x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(new Boolean(x).toString());
	}
	 @Override
	public void println(char x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(new Integer(x).toString());
	}
	 @Override
	public void println(char[] x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(new String(x).toString());
	}
	 @Override
	public void println(double x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(new Double(x).toString());
	}
	 @Override
	public void println(float x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(new Float(x).toString());
	}
	 @Override
	public void println(int x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(new Integer(x).toString());
	}
	 @Override
	public void println(long x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(new Long(x).toString());
	}
	 @Override
	public void println(Object x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(x.toString());
		
	}
	 @Override
	public void println(String x) {
		// TODO Auto-generated method stub
		super.println(x);
        localLog(x.toString());
	}

}
