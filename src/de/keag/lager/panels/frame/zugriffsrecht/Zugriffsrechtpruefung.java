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
package de.keag.lager.panels.frame.zugriffsrecht;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;

public class Zugriffsrechtpruefung {
	
	static Zugriffsrechtpruefung zugriffsrechtpruefung = null;
	private HashMap<Object, ArrayList<ZugriffsrechtBean>> komponentenRechte;
	
	private Zugriffsrechtpruefung() {
		super();
	}
	static public Zugriffsrechtpruefung getOneInstance(){
		if (zugriffsrechtpruefung ==null){
			zugriffsrechtpruefung = new Zugriffsrechtpruefung();
		}
		return zugriffsrechtpruefung;
	}

	private HashMap<Object, ArrayList<ZugriffsrechtBean>> getKomponentenRechte() {
		if (komponentenRechte==null){
			komponentenRechte = new HashMap<Object, ArrayList<ZugriffsrechtBean>>();
		}
		return komponentenRechte;
	}
	
	public boolean istErlaubt(Object object){
		//Benutzer-Zugriffsrechte ermitteln
		ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans = 
			Run.getOneInstance().getBenutzerBean().getBenutzerZugriffsrechtBeans();		
		//hashCode(Kompomente)-Zugriffsrechte ermitteln
		ArrayList<ZugriffsrechtBean> kompontenteZugriffsrechtBeans = 
				getKomponentenRechte().get(object);
		if (kompontenteZugriffsrechtBeans==null){
			//Keine Rechte hängen an dieser Komponente (an diesem HashCode)
			//Falls die Komponente nicht "berechtigt" ist, ist der Zugriff jedem User erlaubt.
			return true;
		}
		//Prüfen, ob der Benutzer Zugriffsrechte für die Komponente besitzt 
		for(int i=0; i < kompontenteZugriffsrechtBeans.size();i++){
			ZugriffsrechtBean kzrb = kompontenteZugriffsrechtBeans.get(i);
			for(int k=0; k < benutzerZugriffsrechtBeans.size();k++){
				BenutzerZugriffsrechtBean bzrb = benutzerZugriffsrechtBeans.get(k);
				if (kzrb.equals(bzrb.getZugriffsrecht())){
					return true;
				}
			}
		}
		return false;
	}

	public void addKomponentenZugriffsRecht(Object object, ZugriffsrechtBean zugriffsrechtBean){
		//Bereits bestehende Rechte zu dem hashCode aus der Tabelle holen
		ArrayList<ZugriffsrechtBean> zugriffsrechtBeans = getKomponentenRechte().get(object);
		//Falls zum Objekt noch nichts gespeichert ist, wird eine neue Liste erzeugt.
		if (zugriffsrechtBeans == null){
			zugriffsrechtBeans = new ArrayList<ZugriffsrechtBean>();
		};
		//Liste mit Rechte wird vervollständigt
		zugriffsrechtBeans.add(zugriffsrechtBean);
		getKomponentenRechte().put(object,zugriffsrechtBeans);
	}
	
	public static void addRecht(Object object, ZugriffsrechtBean zugriffsrechtBean){
		Zugriffsrechtpruefung.getOneInstance().addKomponentenZugriffsRecht(object,zugriffsrechtBean);
		
	}
	
	public void jComponentCheck(Component component){
		
		if (component instanceof Container){
			Container container = (Container) component;
//			System.out.println("CONT " + container.getComponentCount() + " " +container.getName() + " >> " + container.getClass().getCanonicalName());
			for(int i = 0; i<container.getComponentCount();i++){
				Component contComp = container.getComponent(i);
//				System.out.println("comp " + contComp.getName() + " >> " + contComp.getClass().getCanonicalName());
				if(contComp instanceof JMenuItem){
					Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(contComp);
					((JMenuItem)contComp).setEnabled(erlaubt);
				}else if(contComp instanceof JMenu){
					jMenuCheck((JMenu)contComp);
				}else if(contComp instanceof JMenuBar){
					JMenuBar jMenuBar = (JMenuBar)contComp;
					for(int k = 0; k<jMenuBar.getMenuCount();k++ ){
						JMenu jMenu = jMenuBar.getMenu(k);
						jMenuCheck(jMenu);
					}
				}else if (contComp instanceof javax.swing.JSplitPane){
//					jComponentCheck(contComp);
					jComponentCheck(((javax.swing.JSplitPane)contComp).getLeftComponent()); 
					jComponentCheck(((javax.swing.JSplitPane)contComp).getRightComponent()); 
				}else if (contComp instanceof javax.swing.JToolBar){
					jComponentCheck(contComp);
				}else if (contComp instanceof JButton){
					JButton jButton = (JButton)contComp;
//					System.out.println(jButton.getText());
//					Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(contComp.hashCode());
					Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(contComp);
					jButton.setEnabled(erlaubt);
				}else if (contComp instanceof JRootPane){
					   jComponentCheck(contComp);
				}else if (contComp instanceof JPanel){
					   jComponentCheck(contComp);
				}else if (contComp instanceof Container){
				   jComponentCheck(contComp);
				}
			}
		}
		
	}
	
//	public void jMenuBarCheck(JMenuBar jMenuBar){
//		for(int i = 0; i<jMenuBar.getMenuCount();i++){
//			JMenu jMenu = jMenuBar.getMenu(i);
//			jMenuCheck(jMenu);
//		}
//	}
	
	private void jMenuCheck(JMenu jMenu) {
		for(int k = 0; k<jMenu.getMenuComponentCount(); k++){
			Component component = jMenu.getMenuComponent(k);
			if (component instanceof JMenuItem){
				Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(component);
				((JMenuItem)component).setEnabled(erlaubt);
			}
			if (component instanceof JMenu){
				jMenuCheck((JMenu)component);
			}
		}
	}
	

}
