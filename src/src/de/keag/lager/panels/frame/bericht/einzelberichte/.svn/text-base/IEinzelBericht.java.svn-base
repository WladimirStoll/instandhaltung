package de.keag.lager.panels.frame.bericht.einzelberichte;

import java.sql.SQLException;

import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.panels.frame.bericht.BerichtBean;
import de.keag.lager.panels.frame.bericht.Berichtart;
import de.keag.lager.panels.frame.bericht.Berichttyp;

public interface IEinzelBericht {
	public void erzeugeBerichtDbSatz(Berichttyp berichttyp, Berichtart berichtart, Integer berichttypId) throws SQLException, LagerException;
	public void uebernehmeDruckDaten() throws SQLException, LagerException, Exception;
	public void verarbeiteBericht() throws SQLException, LagerException, Exception;
}
