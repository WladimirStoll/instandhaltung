package de.keag.lager.panels.frame.bestellanforderung.pane.details.position;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IBean;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.ArtikelBeanDbLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KatalogBeanDbLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;
import de.keag.lager.panels.frame.bestellanforderung.pane.BaPaneController;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCController;
import de.keag.lager.panels.frame.matchcode.artikel.IArtikelMCAnforderer;
import de.keag.lager.panels.frame.matchcode.kostenstelle.IKostenstelleMCAnforderer;
import de.keag.lager.panels.frame.matchcode.kostenstelle.KostenstelleMCController;
import de.keag.lager.panels.frame.matchcode.lieferantenBestellnummer.ILieferantenBestellnummerMCAnforderer;
import de.keag.lager.panels.frame.matchcode.lieferantenBestellnummer.LieferantenBestellnummerMCController;
import de.keag.lager.panels.frame.matchcode.mengeneinheit.IMengeneinheitMCAnforderer;
import de.keag.lager.panels.frame.matchcode.mengeneinheit.MengeneinheitMCController;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

public class BaPosDetailsController extends 
										DetailsController 
									implements 
										IConroller,
										IKostenstelleMCAnforderer, 
										IArtikelMCAnforderer, 
										ILieferantenBestellnummerMCAnforderer, 
										IMengeneinheitMCAnforderer {
	private static BaPosDetailsController baPosController;
	private DetailsView baPosBlattView;
	
	private BaPosDetailsController() {
		super();
	}

	public static BaPosDetailsController getOneInstance() {
		if(baPosController==null){
			baPosController = new BaPosDetailsController();
			baPosController.addDetailsBeobachter (baPosController.getDetailsView());
		}
		return baPosController;
	}
	
	@Override
	public void addDetailsBeobachter(DetailsView baPosDetailsView) {
		BaPaneController.getOneInstance().addDetailsBeobachter(baPosDetailsView);
//		BaPaneController.getOneInstance().addBaPosDetailsBeobachter(baPosDetailsView);
	}

	@Override
	public DetailsView getDetailsView() {
		if(baPosBlattView==null){
			baPosBlattView = new BaPosDetailsView(this);
		}
		return baPosBlattView;
	}

	@Override
	public void holeKostenstelle() throws SQLException, LagerException {
		KostenstelleMCController.getOneInstance().holeAlleBeans(this);
	}

	//Matchcode - Fenster "artikel" wird aufgerufen.
	@Override
	public void holeArtikel() throws SQLException, LagerException {
		ArtikelMCController.getOneInstance().holeAlleBeans(this);
	}

	//Matchode - Fenster "artikel" ruft zurück.
	@Override
	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
		if (artikelBeans!=null && artikelBeans.size()>0){
			((BaPosDetailsView)getDetailsView()).setArtikel(artikelBeans.get(0));
		}
	}
	
	@Override
	public void selectedKostenstelleBeans(ArrayList<KostenstelleBean> kostenstelleBeans) {
		if (kostenstelleBeans!=null && kostenstelleBeans.size()>0){
			((BaPosDetailsView)getDetailsView()).setKostenstelle(kostenstelleBeans.get(0));
		}
	}

	public void sucheLieferantenBestellnummerByArtikelId(Integer artikelId) throws LagerException {
		LieferantenBestellnummerMCController.getOneInstance().sucheLieferantenBestellnummerByArtikelId(artikelId,this);
	}

	@Override
	public void selectedLieferantenBestellnummerBeans(
			ArrayList<LieferantenBestellnummerBean> lieferantenBestellnummerBeans) {
		if (lieferantenBestellnummerBeans!=null && lieferantenBestellnummerBeans.size()>0){
			((BaPosDetailsView)getDetailsView()).setLieferantenBestellnummer(lieferantenBestellnummerBeans.get(0));
		}
	}

	
	@Override
	public void holeMengeneinheit() throws SQLException, LagerException {
		MengeneinheitMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedMengeneinheitBeans(
			ArrayList<MengenEinheitBean> mengeneinheitBeans) {
		if (mengeneinheitBeans!=null && mengeneinheitBeans.size()>0){
			((BaPosDetailsView)getDetailsView()).setMengeneinheit(mengeneinheitBeans.get(0));
		}
	}


	@Override
	public PaneController getPaneController(){
		return BaPaneController.getOneInstance(); 
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		Log.log().severe("nicht nötig");
	}

	@Override
	public void loeschePosition(Bean bean) {
		Log.log().severe("nicht nötig");
	}

	
}
