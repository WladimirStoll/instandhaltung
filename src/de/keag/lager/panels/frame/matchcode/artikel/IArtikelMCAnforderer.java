package de.keag.lager.panels.frame.matchcode.artikel;

import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.artikel.ArtikelBean;


public interface IArtikelMCAnforderer {
	public void selectedArtikelBeans(ArrayList<ArtikelBean> artikelBeans);

	void holeArtikel() throws SQLException, LagerException;
}
