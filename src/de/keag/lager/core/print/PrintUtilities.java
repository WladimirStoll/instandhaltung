package de.keag.lager.core.print;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.RepaintManager;

//http://download.oracle.com/javase/tutorial/2d/printing/index.html
// http://forum.lowyat.net/topic/795997

public class PrintUtilities implements  Printable {
	  private Component componentToBePrinted;

	  public static void printComponent(Component c) {
	    new PrintUtilities(c).print();
	  }
	 
	  public PrintUtilities(Component componentToBePrinted) {
	    this.componentToBePrinted = componentToBePrinted;
	  }
	 
	  public void print() {
	    PrinterJob printJob = PrinterJob.getPrinterJob();
	    printJob.setJobName("Lagerprogrammdruck:"+componentToBePrinted.getName());
	    
	    PageFormat pageFormat = printJob.defaultPage();
	    Paper paper = new Paper();
	    double margin = 36; // half inch
	    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
	        - margin * 2);

	    
	    printJob.setPrintable(this,pageFormat);
	    
//	    printJob.setPrintable(this);
	    if (printJob.printDialog())
	      try {
	        printJob.print();
	      } catch(PrinterException pe) {
	        System.out.println("Error printing: " + pe);
	      }
	  }

	  public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
	    if (pageIndex > 0) {
	      return(NO_SUCH_PAGE);
	    } else {
	      Graphics2D g2d = (Graphics2D)g;
	      g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
	      disableDoubleBuffering(componentToBePrinted);
	      componentToBePrinted.paint(g2d);
	      enableDoubleBuffering(componentToBePrinted);
	      return(PAGE_EXISTS);
	    }
	  }
	  
//		@Override
//		 public int print(Graphics g, PageFormat pf, int pageIndex) {
//		    if (pageIndex != 0)
//		      return NO_SUCH_PAGE;
//		    Graphics2D g2 = (Graphics2D) g;
//		    g2.setFont(new Font("Serif", Font.PLAIN, 36));
//		    g2.setPaint(Color.black);
//		    g2.drawString("www.java2s.com", 100, 100);
//		    Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
//		        .getImageableWidth(), pf.getImageableHeight());
//		    g2.draw(outline);
//		    return PAGE_EXISTS;
//		  }	  

	  public static void disableDoubleBuffering(Component c) {
	    RepaintManager currentManager = RepaintManager.currentManager(c);
	    currentManager.setDoubleBufferingEnabled(false);
	  }

	  public static void enableDoubleBuffering(Component c) {
	    RepaintManager currentManager = RepaintManager.currentManager(c);
	    currentManager.setDoubleBufferingEnabled(true);
	  }
}
