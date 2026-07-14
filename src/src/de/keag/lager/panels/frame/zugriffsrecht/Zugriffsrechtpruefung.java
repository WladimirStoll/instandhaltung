package de.keag.lager.panels.frame.zugriffsrecht;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtBean;

public class Zugriffsrechtpruefung {
	
	static Zugriffsrechtpruefung zugriffsrechtpruefung = null;
	private HashMap<Integer, ArrayList<ZugriffsrechtBean>> komponentenRechte;
	
	private Zugriffsrechtpruefung() {
		super();
	}
	static public Zugriffsrechtpruefung getOneInstance(){
		if (zugriffsrechtpruefung ==null){
			zugriffsrechtpruefung = new Zugriffsrechtpruefung();
		}
		return zugriffsrechtpruefung;
	}

	private HashMap<Integer, ArrayList<ZugriffsrechtBean>> getKomponentenRechte() {
		if (komponentenRechte==null){
			komponentenRechte = new HashMap<Integer, ArrayList<ZugriffsrechtBean>>();
		}
		return komponentenRechte;
	}
	
	public boolean istErlaubt(int hashCode){
		//Benutzer-Zugriffsrechte ermitteln
		ArrayList<BenutzerZugriffsrechtBean> benutzerZugriffsrechtBeans = 
			Run.getOneInstance().getBenutzerBean().getBenutzerZugriffsrechtBeans();		
		//hashCode(Kompomente)-Zugriffsrechte ermitteln
		ArrayList<ZugriffsrechtBean> kompontenteZugriffsrechtBeans = 
				getKomponentenRechte().get(hashCode);
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

	public void addKomponentenZugriffsRecht(int hashCode, ZugriffsrechtBean zugriffsrechtBean){
		//Bereits bestehende Rechte zu dem hashCode aus der Tabelle holen
		ArrayList<ZugriffsrechtBean> zugriffsrechtBeans = getKomponentenRechte().get(hashCode);
		//Falls zum Objekt noch nichts gespeichert ist, wird eine neue Liste erzeugt.
		if (zugriffsrechtBeans == null){
			zugriffsrechtBeans = new ArrayList<ZugriffsrechtBean>();
		};
		//Liste mit Rechte wird vervollständigt
		zugriffsrechtBeans.add(zugriffsrechtBean);
		getKomponentenRechte().put(hashCode,zugriffsrechtBeans);
	}
	
	public static void addRecht(int hashCode, ZugriffsrechtBean zugriffsrechtBean){
		Zugriffsrechtpruefung.getOneInstance().addKomponentenZugriffsRecht(hashCode,zugriffsrechtBean);
		
	}
	
	public void jComponentCheck(Component component){
		
		if (component instanceof Container){
			Container container = (Container) component;
			for(int i = 0; i<container.getComponentCount();i++){
				Component contComp = container.getComponent(i);
				if(contComp instanceof JMenuItem){
					Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(contComp.hashCode());
					((JMenuItem)contComp).setEnabled(erlaubt);
				}
				if(contComp instanceof JMenu){
					jMenuCheck((JMenu)contComp);
				}
				if (contComp instanceof Container){
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
				Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(component.hashCode());
				((JMenuItem)component).setEnabled(erlaubt);
			}
			if (component instanceof JMenu){
				jMenuCheck((JMenu)component);
			}
		}
	}
	

}
