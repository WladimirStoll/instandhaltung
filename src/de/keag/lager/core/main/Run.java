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
package de.keag.lager.core.main;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import compoundIcon.CompoundIcon;
import compoundIcon.OverlayedIcon;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import de.keag.lager.panels.frame.MainFrame;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.login.LoginController;

public class Run {
	private static Run run = null;

	private BenutzerBean benutzerBean; //eingeloggter Benutzer
	private MainFrame mainFrame;
	private LagerProperties lagerProperties;

	/**
	 * Konstruktor
	 */
	private Run() {
		super();
	}
	
	public MainFrame getMainFrame() {
		if (mainFrame==null){
			mainFrame = new MainFrame(this);
		}
		return mainFrame;
	}
	
	public LagerProperties getLagerProperties() {
		if(lagerProperties == null){
			lagerProperties = new LagerProperties();
		}
		return lagerProperties;
	}

	public static Run getOneInstance(){
		if(run==null){
			run = new Run();
		}
		return run;
	}
	
	/**
	 * Delegation --> Anzeigen der Login-Maske
	 */
	public void zeigeLoginView(){
		LoginController.getOneInstance().showView(); //AnmeldeMaske anzeigen
		getMainFrame().setVisible(true); //anzeige am Bildschirm
	}

	 // Returns an ImageIcon, or null if the path was invalid. 
    static private ImageIcon createImageIcon(String path) {
        URL imgURL = Run.class.getResource(Konstanten.VERZEICHNIS_IMAGES+path);	
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find image in system: " + Konstanten.VERZEICHNIS_IMAGES + path);
            return null;
        }
    }
    
    static public CompoundIcon createCompoundIcon(String pfadIcon1){
    	return createCompoundIcon(pfadIcon1, null, null);
    }
    static public CompoundIcon createCompoundIcon(String pfadIcon1, String pfadIcon2){
    	return createCompoundIcon(pfadIcon1, pfadIcon2, null);
    }
    static public CompoundIcon createCompoundIcon(String pfadIcon1, String pfadIcon2, String pfadIcon3){
    	//Anzahl der angeforderten Icons berechnen
    	int i = 0;
    	if(pfadIcon1!=null){
    		i=i+1;
    	}
    	if(pfadIcon2!=null){
    		i=i+1;
    	}
    	if(pfadIcon3!=null){
    		i=i+1;
    	}

    	//Array mit der Anzahl der Icons erstellen
    	Icon[] icons  = new Icon[i];
    	
    	i = 0;
    	if(pfadIcon1!=null){
    		icons[i++] = createImageIcon(pfadIcon1); //ein Icon erstellen
    	}
    	if(pfadIcon2!=null){
    		icons[i++] = createImageIcon(pfadIcon2);
    	}
    	if(pfadIcon3!=null){
    		icons[i++] = createImageIcon(pfadIcon3);
    	}
    	
    	return new CompoundIcon(icons); //Zusammengesetztes Icon erstellen.
    }

    static public OverlayedIcon createOverlayedIcon(String pfadIcon1){
    	return createOverlayedIcon(pfadIcon1, null, null);
    }
    static public OverlayedIcon createOverlayedIcon(String pfadIcon1, String pfadIcon2){
    	return createOverlayedIcon(pfadIcon1, pfadIcon2, null);
    }
    static public OverlayedIcon createOverlayedIcon(String pfadIcon1, String pfadIcon2, String pfadIcon3){
    	//Anzahl der angeforderten Icons berechnen
    	int i = 0;
    	if(pfadIcon1!=null){
    		i=i+1;
    	}
    	if(pfadIcon2!=null){
    		i=i+1;
    	}
    	if(pfadIcon3!=null){
    		i=i+1;
    	}

    	//Array mit der Anzahl der Icons erstellen
    	Icon[] icons  = new Icon[i];
    	
    	i = 0;
    	if(pfadIcon1!=null){
    		icons[i++] = createImageIcon(pfadIcon1); //ein Icon erstellen
    	}
    	if(pfadIcon2!=null){
    		icons[i++] = createImageIcon(pfadIcon2);
    	}
    	if(pfadIcon3!=null){
    		icons[i++] = createImageIcon(pfadIcon3);
    	}
    	
    	return new OverlayedIcon(icons); //Zusammengesetztes Icon erstellen.
    }
    
    /**
     * @param myParent ist das Objekt in welchem das Dialog sich befinden soll
     * @param me ist das Dialog, welche in der Mitte des Programms angezeigt werden soll.
     */
	static public void setDialogPosition(Component myParent, Component me) {
		int x;
		int y;

		Point topLeft = myParent.getLocationOnScreen();
		Dimension parentSize = myParent.getSize();

		Dimension mySize = me.getSize();

		if (parentSize.width > mySize.width)
			x = ((parentSize.width - mySize.width) / 2) + topLeft.x;
		else
			x = topLeft.x;

		if (parentSize.height > mySize.height)
			y = ((parentSize.height - mySize.height) / 2) + topLeft.y;
		else
			y = topLeft.y;

		me.setLocation(x, y);
	}

	public void setBenutzer(BenutzerBean benutzerBean) {
		this.benutzerBean = benutzerBean; 
		
	}

	public BenutzerBean getBenutzerBean() {
		if(benutzerBean == null){
			benutzerBean = new BenutzerBean();
		}
		return benutzerBean;
	}

	public void setBenutzerBean(BenutzerBean benutzerBean) {
		this.benutzerBean = benutzerBean;
	}

	public static Icon createMenuImage() {
//		return new ImageIcon(getClass().getResource("/de/keag/lager/core/main/images/zahnrad.jpg"));
        URL imgURL = Run.class.getResource(Konstanten.VERZEICHNIS_IMAGES+Konstanten.ICON_MENU_IMAGE);	
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find image in system: " + Konstanten.VERZEICHNIS_IMAGES + Konstanten.ICON_MENU_IMAGE);
            return null;
        }
	}
	public static Image createMenuImage2() {
		try {
	        URL imgURL = Run.class.getResource(Konstanten.VERZEICHNIS_IMAGES+Konstanten.ICON_MENU_IMAGE);	
	        if (imgURL != null) {
//	            return new ImageIcon(imgURL);
				BufferedImage bi = ImageIO.read(imgURL);
				return bi;
	        } else {
	            System.err.println("Couldn't find image in system: " + Konstanten.VERZEICHNIS_IMAGES + Konstanten.ICON_MENU_IMAGE);
	            return null;
	        }
//			
//			return ImageIO.read(new File("./"+Konstanten.VERZEICHNIS_IMAGES+Konstanten.ICON_MENU_IMAGE));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
