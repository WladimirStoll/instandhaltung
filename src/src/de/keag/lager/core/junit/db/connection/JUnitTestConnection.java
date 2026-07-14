package de.keag.lager.core.junit.db.connection;

import java.sql.PreparedStatement;

import junit.framework.TestCase;
import de.keag.lager.db.connection.LagerConnection;


public class JUnitTestConnection extends TestCase {
	public void testConnection() throws Exception{
		LagerConnection.getOneInstance().getConnection();
	}
	
	public void testGetID() throws Exception{
		LagerConnection.getOneInstance().getConnection();
		
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
