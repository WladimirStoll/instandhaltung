package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.IBean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.EmailBeanDB;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.readingDeep.DepthLevelArrayDB;
import de.keag.lager.panels.frame.email.EmailBean;

public class EmailBeanDB extends BeanDB{
	public EmailBeanDB() {
		super();
	}

	@Override
	public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
		ArrayList<Bean> resultList = new ArrayList<Bean>();
		ResultSet rs;
		//SQL absetzen
		PreparedStatement stmt =  getSelectStmt();
		rs = stmt.executeQuery();
		//ergebnisse auswerten.
		while(rs.next()){
			EmailBean bean = new EmailBean();
			erzeugeBeanAusResultSet(rs, bean, 0, null);
			resultList.add(bean);
			Log.log().finest(bean.toString());
		}
		return resultList;
	}
	
	@Override
	protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws  SQLException, LagerException  {
		EmailBean emailBean = (EmailBean) bean;
		emailBean.setEmail(rs.getString("email"));
		emailBean.setBeschreibung(rs.getString("beschreibung"));
		emailBean.setBeanDBStatus(BeanDBStatus.SELECT);
	}
	

	@Override
	public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean, int depthLevel, DepthLevelArrayDB depthLevelArrayDB)
			throws SQLException, LagerException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String getSelectSQL() {
		if(selectSQL==null){
			selectSQL = "" +
					" SELECT email, name as beschreibung FROM benutzer b where email <> \"\"" +
					" union" +
					" select email, name from herstellerlieferant where email <> \"\" " +
					" order by email" +
					"";
		}
		return selectSQL;
	}

	@Override
	public void deleteBean(Bean bean) throws SQLException, LagerException {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected String getDeleteSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String getInsertSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected String selectAnhandIdSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}


	@Override
	protected String getUpdateSQL() {
		Log.log().severe("nicht implementiert");
		return null;
	}

	@Override
	protected void insertBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		
	}

	@Override
	public void saveBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		
	}

	@Override
	protected void selectBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		
	}

	@Override
	public Bean selectAnhandID(Integer id, int depthLevel, DepthLevelArrayDB depthLevelArrayDB) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		return null;
	}


	@Override
	protected void updateBean(Bean bean) throws SQLException, LagerException {
		Log.log().severe("nicht implementiert");
		
	}



}
