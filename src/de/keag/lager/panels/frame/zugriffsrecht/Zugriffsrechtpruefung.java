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
