package de.keag.lager.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import de.keag.lager.core.fehler.BenutzerBeanDbLagerException;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;

abstract public class Model implements IModel{

	// DB-Zugriff
	protected BeanDB beanDB;
	protected ISuchBean iSuchBean;
//	protected ModelKnotenBean selectedListModelKnotenBean;
	protected ModelKnotenBean aktiveTreeModelBean;
	private ArrayList<Fehler> fehlerList;
	private ArrayList<ModelKnotenBean> modelKnotenBeanList;
	
	private ArrayList<IListBeobachter> iListBeobachters;
	private ArrayList<ISuchBeobachter> iSuchBeobachters;
	private ArrayList<ITreeBeobachter> iTreeBeobachters;
	private ArrayList<IDetailsBeobachter> iDetailsBeobachters;

	protected ArrayList<IDetailsBeobachter> getiDetailsBeobachters() {
		if (iDetailsBeobachters == null) {
			iDetailsBeobachters = new ArrayList<IDetailsBeobachter>();
		}
		return iDetailsBeobachters;
	}
	
	public void setiSuchBean(ISuchBean iSuchBean) {
		this.iSuchBean = iSuchBean;
	}
	
	//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
	public void ausgewaehlterKnotenIstGeandert() {
		ArrayList<Fehler> errors = getAktiveTreeModelBean().getIBean().pruefeEigeneDaten();
		getAktiveTreeModelBean().setFehlerList(errors);
		benachrichtigeDetailsBeobachter();
//		if (getAktiveTreeModelBean() instanceof BaPosModelKnotenBean){
//			benachrichtigeBaPosDetailsBeobachter();
//		}else if(getAktiveTreeModelBean() instanceof BaModelKnotenBean){
//			benachrichtigeBaDetailsBeobachter();
//		}else {
//			Log.log().severe("Fehler im Ablauf. Objekt-Klasse ist nicht vorgesehen.");
//		}
		benachrichtigeTreeBeobachter();
	}
	

	protected void benachrichtigeDetailsBeobachter() {
		for (Iterator<IDetailsBeobachter> i = getiDetailsBeobachters().iterator(); i.hasNext();) {
			IDetailsBeobachter iDetailsBeobachter = (IDetailsBeobachter) i.next();
			if (iDetailsBeobachter != null) {
				iDetailsBeobachter.zeichneDich(getAktiveTreeModelBean(), this);
			}
		}
	}
	
	public void addIDetailsBeobachter(IDetailsBeobachter iDetailsBeobachter) {
		getiDetailsBeobachters().add(iDetailsBeobachter);
	}

	// ein Knoten wurde ausgewählt und soll dargestellt werden.
	public void selectKnoten(ModelKnotenBean selectedModelKnotenBean) {
		setAktiveTreeModelBean(selectedModelKnotenBean);
	}

	
	
//	private void benachrichtigeBaPosDetailsBeobachter() {
//		for (Iterator<IDetailsBeobachter> i = getiBaPosDetailsBeobachters()
//				.iterator(); i.hasNext();) {
//			IDetailsBeobachter iBaPosDetailsBeobachter = (IDetailsBeobachter) i
//					.next();
//			if (iBaPosDetailsBeobachter != null) {
//				iBaPosDetailsBeobachter.zeichneDich(getAktiveTreeModelBean());
//			}
//		}
//	}
	
	
	
	
	protected ArrayList<IListBeobachter> getIListBeobachters() {
		if (iListBeobachters == null) {
			iListBeobachters = new ArrayList<IListBeobachter>();
		}
		return iListBeobachters;
	}
	
	public void benachrichtigeTreeBeobachter() {
		for (Iterator<ITreeBeobachter> i = getiTreeBeobachters().iterator(); i.hasNext();) {
			ITreeBeobachter treeBeobachter = (ITreeBeobachter) i
					.next();
			if (treeBeobachter != null) {
				treeBeobachter.zeichneDich(getAktiveTreeModelBean(), this);
			}
		}
	}
	
	public void addIListBeobachter(
			IListBeobachter iListBeobachter) {
		getIListBeobachters().add(iListBeobachter);
		benachrichtigeListBeobachter();
	}


	public void addISuchBeobachters(ISuchBeobachter iSuchBeobachter) {
		getiSuchBeobachters().add(iSuchBeobachter);
		benachrichtigeSuchBeobachter();
	}

	public void addITreeBeobachter(
			ITreeBeobachter iTreeBeobachter) {
		getiTreeBeobachters().add(iTreeBeobachter);
		benachrichtigeTreeBeobachter();
	}
	
	
	public void benachrichtigeListBeobachter() {
		for (Iterator<IListBeobachter> i = getIListBeobachters()
				.iterator(); i.hasNext();) {
			IListBeobachter iBaListPaneBeobachter = (IListBeobachter) i
					.next();
			if (iBaListPaneBeobachter != null) {
				iBaListPaneBeobachter.zeichneDich(this);
			}
		}
	}

	
	public void suchen(ISuchBean iSuchBean) {
		getFehlerList().clear();
		try {
			// neue AnforderungsListe wird erstellt und aus der Datenbank
			// gefüllt.
			ArrayList<Bean> beanList = getBeanDB().selectAnhandSuchBean(iSuchBean, 0+1, null);

			// nicht mehr benötigte Elemente der alten Liste werden gelöscht.
			for (int i = getModelBeanList().size(); i > beanList.size(); i--) {
				getModelBeanList().remove(i - 1);
			}

			// neue Elemente werden erstellt(oder erweitert)
			for (int i = 0; i < beanList.size(); i++) {
				if (getModelBeanList().size() > i) {
					getModelBeanList().get(i).initialize();
					getModelBeanList().get(i).setIBean(beanList.get(i));
				} else {
					ModelKnotenBean modelKnotenBean = getNeueListBean(beanList.get(i)); 
//					baModelBean.setIBean(baBeanList.get(i));
					modelKnotenBean.setVater(null); // Das ist das höchte Nevau im
												// Baum
					modelKnotenBean.setKinderList(null); 
					getModelBeanList().add(modelKnotenBean);
				}
			}
			if (getModelBeanList().size() > 0) {
				// nach einer neuen Auswahl ist die erste Position ausgewählt
				setAktiveTreeModelBean(getModelBeanList().get(0));
			} else {
				// es gibt keine Daten in der Liste
				// Der Zeiger auf die aktuelle Bean ist also leer
				setAktiveTreeModelBean(null);
			}
			benachrichtigeBeobachter();
		} catch (SQLException e) {
			e.printStackTrace();
			getFehlerList().add(
					new Fehler(13, FehlerTyp.FEHLER, iSuchBean.toString()));
		} catch (BenutzerOberflacheLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (HerstellerLieferantLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (BenutzerBeanDbLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (LagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		}
	}


	
	
	abstract public  ModelKnotenBean getNeueListBean(IBean iBean);

	public void benachrichtigeBeobachter() {
		benachrichtigeListBeobachter();
		benachrichtigeSuchBeobachter();
		benachrichtigeTreeBeobachter();
		
//		Log.log().severe("unbedingt implementieren!");
//		benachrichtigeBaDetailsBeobachter();
//		benachrichtigeBaPosDetailsBeobachter();
		benachrichtigeDetailsBeobachter();
	}
	
	public void benachrichtigeSuchBeobachter() {
		for (Iterator<ISuchBeobachter> i = getiSuchBeobachters()
				.iterator(); i.hasNext();) {
			ISuchBeobachter baSuchPaneBeobachter = (ISuchBeobachter) i
					.next();
			if (baSuchPaneBeobachter != null) {
				baSuchPaneBeobachter.zeichneDich(getiSuchBean());
			}
		}
	}
	
	
	protected ArrayList<ISuchBeobachter> getiSuchBeobachters() {
		if (iSuchBeobachters == null) {
			iSuchBeobachters = new ArrayList<ISuchBeobachter>();
		}
		return iSuchBeobachters;
	}
	
	protected ArrayList<ITreeBeobachter> getiTreeBeobachters() {
		if (iTreeBeobachters == null) {
			iTreeBeobachters = new ArrayList<ITreeBeobachter>();
		}
		return iTreeBeobachters;
	}

	
	
	
	public Model() {
		super();
	}
	
	abstract protected BeanDB getBeanDB();
	abstract public ISuchBean getiSuchBean();
//	abstract public void setiSuchBean(ISuchBean iSuchBean);

//	protected void setSelectedModelKnotenBean(
//			ModelKnotenBean selectedModelKnotenBean) {
//		this.selectedListModelKnotenBean = selectedModelKnotenBean;
//	}
	
	protected void setFehlerList(ArrayList<Fehler> fehlerList) {
		this.fehlerList = fehlerList;
	}
	
	public ArrayList<Fehler> pruefeAktuelleBean(ModelKnotenBean modelKnotenBean) {
		//Die aktuelle Bestellanforderung wird geprüft
		ArrayList<Fehler> errors  = null;
		if (modelKnotenBean.getIBean() !=null){
			 errors = modelKnotenBean.getIBean().pruefeEigeneDaten();
				if (errors.size()>0){
					errors.add(0,new Fehler(25,FehlerTyp.FEHLER,"Fehler im Objekt:" + modelKnotenBean.getIBean().toString()));
				}
				Iterator<ModelKnotenBean> iterator = modelKnotenBean.getKinderList().iterator(); 
				while(iterator.hasNext()){
					//Jede Position der aktuellen Bestellanforderung wird geprüft
					ModelKnotenBean modelKnotenBeanKind = iterator.next();
					pruefeAktuelleBean(modelKnotenBeanKind);
				}
		}
		for(int i=0;i<errors.size();i++){
			getFehlerList().add(errors.get(i));
		}
		return getFehlerList();
	}


	
	
	@Override
	public ArrayList<Fehler> getFehlerList() {
		if (fehlerList == null) {
			fehlerList = new ArrayList<Fehler>();
		}
		return fehlerList;
	}
	
	@Override
	public ArrayList<ModelKnotenBean> getModelBeanList() {
		if (modelKnotenBeanList == null) {
			modelKnotenBeanList = new ArrayList<ModelKnotenBean>(){
				@Override
				public ModelKnotenBean get(int index) {
					if (this.size()>index){
						return super.get(index);
					}else{
						Log.log().fine("Kein Element an der Position "+ index);
						return null;
					}
				}
			};
		}
		return modelKnotenBeanList;
	}
	
	public ModelKnotenBean getAktiveTreeModelBean() {
		if (aktiveTreeModelBean == null) {
			if (getModelBeanList().size() > 0) {
				// wenn der aktive Knoten unbekannt ist
				// und die Liste nicht leer ist,
				// dann ist das erste Objekt der Liste der aktive Knoten.
				setAktiveTreeModelBean(getModelBeanList().get(0));
			}
		}
		return aktiveTreeModelBean;
	}
	
	
	/**
	 * Der gesamte Baum wird durchsucht.
	 * Der "alte" aktuell gewählte(im JTree) Knoten wird gesucht.
	 * Falls er gefunden wird, so wird er ersetzt. 
	 */
	private void reSetAktiveTreeModelBean() {
		for(Iterator<ModelKnotenBean> iterator = this.iteratorUeberSelectedModelKnotenBean();iterator.hasNext();){
			ModelKnotenBean modelKnotenBean = iterator.next(); 
			if (modelKnotenBean.equals(getAktiveTreeModelBean())){
				setAktiveTreeModelBean(modelKnotenBean);
				return;
			}
		}
	}

	protected void setAktiveTreeModelBean(ModelKnotenBean aktiveModelBean) {
		if (aktiveModelBean!=null){
//			System.out.println("Model:setAktiveTreeModelBean:"+aktiveModelBean.toString());
			Log.log().finest("Model:setAktiveTreeModelBean:"+aktiveModelBean.toString());
		}else{
//			System.out.println("Model:setAktiveTreeModelBean:null");
			Log.log().finest("Model:setAktiveTreeModelBean:null");
		}
		this.aktiveTreeModelBean = aktiveModelBean;
//		Log.log().severe("unbedingt implementieren!");
//		benachrichtigeBaDetailsBeobachter();
//		benachrichtigeBaPosDetailsBeobachter();
		benachrichtigeDetailsBeobachter();
		benachrichtigeTreeBeobachter();
	}

//	abstract public void benachrichtigeDetailsBeobachter() ;
//	


	/**
	 * Die Funktion liefert die gerade ausgewählte Bestellanforderungsklasse
	 */
	@Override
	public ModelKnotenBean getSelectedListModelKnotenBean() {
		ModelKnotenBean  modelKnotenBean = getAktiveTreeModelBean();
		if (modelKnotenBean == null){
			return null;
		}else{
			for(;;){
				if (modelKnotenBean.getVater() == null){
					return modelKnotenBean; 
				}else{
					modelKnotenBean = modelKnotenBean.getVater();
				}
			}
//			if (modelKnotenBean.getIBean() instanceof BaBean){
//				return modelKnotenBean;
//			}else{
//				if (modelKnotenBean.getIBean() instanceof BaPosBean){
//					return modelKnotenBean.getVater();
//				}else{
//					Log.log().severe("falsche Klasse:"+modelKnotenBean.getIBean());
//					return null;
//				}
//			}
		}
	}
	
	/**
	 * Ermittelt, an welcher Stelle in der Ba-Liste die aktuell selektierte Bestellanforderung steht.
	 */
	@Override
	public int getSelectedListModelKnotenBeanLaufendeNummer() {
		ModelKnotenBean modelKnotenBean =  getSelectedListModelKnotenBean();
		for(int i = 0; i<getModelBeanList().size();i++){
			if (getModelBeanList().get(i).equals(modelKnotenBean)){
				return i;
			}
		}
		return -1;
	}

//	abstract protected ModelKnotenBean selectKnoten(int gewaehlteZeilenNummer);

	abstract public void erstelleNeuenSatz();

	abstract public void speichereSatz(Bean bean);

	protected void leseSatzAnhandIDneuEin(Bean bean) throws SQLException,
			LagerException, BenutzerOberflacheLagerException {
		reSetAktiveTreeModelBean();
	}

	abstract public void loescheSatz(Bean bean);

	protected void loescheBeanAusModel(Bean bean, Boolean benachrichtigeBeobachter) {
		for(int i=getModelBeanList().size()-1; i>=0 ;i--){
			ModelKnotenBean modelKnotenBean = getModelBeanList().get(i);
			Bean tempBean = (Bean)modelKnotenBean.getIBean();
			if (bean.equals(tempBean)){
				getModelBeanList().remove(i);
				if (getModelBeanList().size()>0){
					selectKnoten(0); //auf den ersten Knoten positionieren, falls vorhanden
				}else{
					//es ist nix zu tun.
					//Danach sollte ein Repaint(ZeichneDich) laufen.
				}
				if (benachrichtigeBeobachter){
					benachrichtigeListBeobachter();
				}
				return;
			}
		}
	}

	public void abbrechenSatz(Bean bean) {
		getFehlerList().clear();
		bean.getFehlerList().clear();
		
		try {
			if(bean.getBeanDBStatus()==BeanDBStatus.INSERT 
					|| bean.getBeanDBStatus()==BeanDBStatus.FEHLERHAFT){
//				Fehler werden nicht beachtet.
				getFehlerList().clear();
				loescheBeanAusModel(bean, false);//this
				setAktiveTreeModelBean(null);
			}else{
				leseSatzAnhandIDneuEin(bean);
				
			}
//			BenutzerBean neueBaBean = getBaBeanDB().sucheAnhandID( //TODO
//					baBean.getId());
//			baBean.getModelKnotenBean().setIBean(neueBaBean); //Bestellanforderung austauschen
//			setAktiveModelBean(neueBaBean.getModelKnotenBean()); //Anzeige auffrischen
		} catch (SQLException e) {
			getFehlerList().add(new Fehler(25,FehlerTyp.FEHLER, e.getMessage()));
			e.printStackTrace();
		} catch (BenutzerBeanDbLagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		} catch (LagerException e) {
			getFehlerList().add(e.getFehler());
			e.printStackTrace();
		}
		benachrichtigeBeobachter();
	}
	
	
	protected Iterator<ModelKnotenBean> iteratorUeberSelectedModelKnotenBean(){
		ArrayList<ModelKnotenBean> list = new ArrayList<ModelKnotenBean>();
		getAlleKinderModelKnotenBeans(getSelectedListModelKnotenBean(),list);
		return list.iterator();
	}

	/**
	 * Alle Knoten in einer Liste speichern
	 * @param quelleModelKnotenBean
	 * @param destModelKnotenList
	 */
    private void getAlleKinderModelKnotenBeans(ModelKnotenBean quelleModelKnotenBean, ArrayList<ModelKnotenBean> destModelKnotenList) {
    	if (quelleModelKnotenBean!=null && destModelKnotenList!=null){
    		destModelKnotenList.add(quelleModelKnotenBean);
    		for(Iterator<ModelKnotenBean> it = quelleModelKnotenBean.getKinderList().iterator();it.hasNext();){
    			getAlleKinderModelKnotenBeans(it.next(), destModelKnotenList);
    		}
    	}
	}

}
