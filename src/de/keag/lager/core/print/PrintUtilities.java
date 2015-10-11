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
