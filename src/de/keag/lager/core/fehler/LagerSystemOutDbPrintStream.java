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
