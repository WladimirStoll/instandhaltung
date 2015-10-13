package de.keag.lager.panels.frame.artikel.pane.details.unterArtikel;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;
import de.keag.lager.panels.frame.matchcode.artikel.ArtikelMCController;
import de.keag.lager.panels.frame.matchcode.artikel.IArtikelMCAnforderer;

public class UnterArtikelDetailsController extends DetailsController
											   implements IArtikelMCAnforderer 
{
	private static UnterArtikelDetailsController detailsController;
	private UnterArtikelDetailsView detailsView;
	
	private UnterArtikelDetailsController() {
		super();
	}

	public static UnterArtikelDetailsController getOneInstance() {
		if(detailsController==null){
			detailsController = new UnterArtikelDetailsController();
			detailsController.addDetailsBeobachter (detailsController.getDetailsView());
		}
		return detailsController;
	}

	@Override
	public DetailsView getDetailsView() {
		if(detailsView==null){
			detailsView = new UnterArtikelDetailsView(this);
		}
		return detailsView;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		getPaneController().addDetailsBeobachter(detailsView);
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		if (modelKnotenTyp==ModelKnotenTyp.UNTER_ARTIKEL){
			ArtikelPaneController.getOneInstance().erzeugeNeuenUnterArtikel();
		}else{
			Log.log().severe("Fehlerhafter Typ: " + modelKnotenTyp.toString());
		}
	}

	@Override
	public void loeschePosition(Bean bean) {
		Log.log().severe("nicht implementiert");
	}

	@Override
	public PaneController getPaneController() {
		return ArtikelPaneController.getOneInstance();
	}

	@Override
	public void holeArtikel() throws SQLException, LagerException {
		ArtikelMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans) {
		if (artikelBeans!=null && artikelBeans.size()>0){
			((UnterArtikelDetailsView)getDetailsView()).setArtikel(artikelBeans.get(0));
		}
	}

	
}
