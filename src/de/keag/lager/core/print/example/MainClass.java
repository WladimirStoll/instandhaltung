package de.keag.lager.core.print.example;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class MainClass {
	  public static void main(String[] args) throws Exception {
	    PrinterJob pj = PrinterJob.getPrinterJob();

	    PageFormat pf = pj.defaultPage();
	    Paper paper = new Paper();
	    double margin = 36; // half inch
	    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
	        - margin * 2);
	    pf.setPaper(paper);

	    pj.setPrintable(new MyPrintable(), pf);
	    if (pj.printDialog()) {
	      try {
	        pj.print();
	      } catch (PrinterException e) {
	        System.out.println(e);
	      }
	    }
	  }
	}