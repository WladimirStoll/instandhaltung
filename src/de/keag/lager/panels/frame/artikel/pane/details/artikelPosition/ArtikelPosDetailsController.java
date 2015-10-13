package de.keag.lager.panels.frame.artikel.pane.details.artikelPosition;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IConroller;
import de.keag.lager.core.PaneController;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.panels.frame.abteilung.AbteilungBean;
import de.keag.lager.panels.frame.artikel.pane.ArtikelPaneController;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck.BestandspackstueckDetailsView;
import de.keag.lager.panels.frame.matchcode.abteilung.AbteilungMCController;
import de.keag.lager.panels.frame.matchcode.abteilung.IAbteilungMCAnforderer;
import de.keag.lager.panels.frame.matchcode.etage.EtageMCController;
import de.keag.lager.panels.frame.matchcode.etage.IEtageMCAnforderer;
import de.keag.lager.panels.frame.matchcode.halle.HalleMCController;
import de.keag.lager.panels.frame.matchcode.halle.IHalleMCAnforderer;
import de.keag.lager.panels.frame.position.model.PositionBean;

public class ArtikelPosDetailsController extends DetailsController implements
		IConroller, IHalleMCAnforderer, IEtageMCAnforderer,
		IAbteilungMCAnforderer {
	private static ArtikelPosDetailsController detailsController;
	private DetailsView detailsView;

	private ArtikelPosDetailsController() {
		super();
	}

	public static ArtikelPosDetailsController getOneInstance() {
		if (detailsController == null) {
			detailsController = new ArtikelPosDetailsController();
			detailsController.addDetailsBeobachter(detailsController
					.getDetailsView());
		}
		return detailsController;
	}

	@Override
	public void addDetailsBeobachter(DetailsView detailsView) {
		ArtikelPaneController.getOneInstance()
				.addDetailsBeobachter(detailsView);
		// BaPaneController.getOneInstance().addBaPosDetailsBeobachter(baPosDetailsView);
	}

	@Override
	public DetailsView getDetailsView() {
		if (detailsView == null) {
			detailsView = new ArtikelPosDetailsView(this);
		}
		return detailsView;
	}

	@Override
	public PaneController getPaneController() {
		return ArtikelPaneController.getOneInstance();
	}

	@Override
	public void erstelleNeuenSatz(ModelKnotenTyp modelKnotenTyp) {
		Log.log().severe("nicht nötig");
	}

	@Override
	public void loeschePosition(Bean bean) {
		Log.log().severe("nicht nötig");
	}

	@Override
	public void holeHalle() throws SQLException, LagerException {
		HalleMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedHalleBeans(ArrayList<HalleBean> halleBeans) {
		if (halleBeans != null && halleBeans.size() > 0) {
			((ArtikelPosDetailsView) getDetailsView()).setHalle(halleBeans
					.get(0));
		}
	}

	@Override
	public void holeEtage() throws SQLException, LagerException {
		EtageMCController.getOneInstance().holeAlleBeans(this);
	}

	@Override
	public void selectedEtageBeans(ArrayList<EtageBean> etageBeans) {
		if (etageBeans != null && etageBeans.size() > 0) {
			((ArtikelPosDetailsView) getDetailsView()).setEtage(etageBeans
					.get(0));
		}
	}

	/**
	 * VervollständigePositionsdaten
	 * 
	 * @param positionBean
	 * @throws SQLException
	 * @throws LagerException
	 */
	public void vervollstaendigePosition(PositionBean positionBean)
			throws LagerException, SQLException {
		((ArtikelPaneController) getPaneController())
				.vervollstaendigePosition(positionBean);

	}

	@Override
	public void holeAbteilung() throws SQLException, LagerException {
		AbteilungMCController.getOneInstance().holeAlleBeans(this);
	}

	// Matchode - Fenster "Abteilung" ruft zurück.
	@Override
	public void selectedAbteilungBeans(ArrayList<AbteilungBean> abteilungBeans) {
		if (abteilungBeans != null && abteilungBeans.size() > 0) {
			((ArtikelPosDetailsView) getDetailsView())
					.setAbteilung(abteilungBeans.get(0));
		}
	}

}
