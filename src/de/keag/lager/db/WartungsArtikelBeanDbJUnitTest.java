package de.keag.lager.db;

import java.sql.PreparedStatement;

import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import junit.framework.TestCase;

public class WartungsArtikelBeanDbJUnitTest extends TestCase {
	
	public void testDB() throws Exception{
		LagerConnection.getOneInstance().getConnection();
		WartungsArtikelBeanDB wartungsArtikelBeanDB = 
				new WartungsArtikelBeanDB();
		
		WartungsArtikelBean wartungsArtikel = new WartungsArtikelBean();
		wartungsArtikel.setId(999998888);
		
		
		//alte Werte werden gelöscht.
		PreparedStatement stmt = LagerConnection.getOneInstance()
				.getConnection().prepareStatement(
						"DELETE from sequence where tablefield = 'agent007'");
		stmt.executeUpdate();
		
		//eine ID wird geholt.
		Integer new_id = LagerConnection.getOneInstance().getID("agent007");
		assertEquals(new Integer(2), new_id);
		
		//eine weitere ID wird geholt.
		new_id = LagerConnection.getOneInstance().getID("agent007");
		assertEquals(new Integer(3), new_id);
		
	}
	

}
