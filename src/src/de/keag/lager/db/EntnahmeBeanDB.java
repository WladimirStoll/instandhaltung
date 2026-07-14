package de.keag.lager.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.keag.lager.core.Bean;
import de.keag.lager.core.ISuchBean;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.db.connection.BeanDB;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.db.connection.LagerConnection;
import de.keag.lager.panels.frame.benutzer.model.BenutzerBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeStatus;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeSuchBean;
public class EntnahmeBeanDB extends BeanDB {
		
		private EntnahmePosBeanDB entnahmePosBeanDB;
		
		
		private LhBeanDB herstellerLieferantBeanDB;
		private BenutzerBeanDB benutzerBeanDB;
		
		
		@Override
		protected String getSelectSQL() {
			if(selectSQL==null){
				selectSQL = "select id, erstellungsdatum,fk_benutzerEntnehmer,status  from entnahme where id=?";
			}
			return selectSQL;
		}
		
		
		@Override
		protected String getDeleteSQL() {
			return deleteSQL;
		}

		@Override
		protected String getUpdateSQL() {
			if(updateSQL==null){
				updateSQL = "update entnahme " +
						"set erstellungsdatum=?, status=?,  " +
						"fk_benutzerEntnehmer=?, " +
						" where id = ? ";
			}
			return updateSQL;
		}

		@Override
		protected String getInsertSQL() {
			if(insertSQL==null){
				insertSQL = "insert into entnahme " +
						"(id, erstellungsdatum, status, " +
						"fk_benutzerEntnehmer)" +
						" values (?,?,?,?)";
			}
			return insertSQL;
		}

		

		public EntnahmeBeanDB(){
			super();
		}

		@Override
		public void saveBean(Bean bean) throws SQLException, LagerException{
			if (bean!=null){
				if (bean.getBeanDBStatus() == BeanDBStatus.INSERT){
					insertBean((EntnahmeBean)bean);
				}else if (bean.getBeanDBStatus() == BeanDBStatus.UPDATE){
					updateBean((EntnahmeBean)bean);
				}else if (bean.getBeanDBStatus() == BeanDBStatus.SELECT){
					//es passiert nix. Wir müssen die Bean nicht speichern
//				}else if (bean.getBeanStatus() == BeanStatus.DELETE){
//					deleteBean(bean);
//					throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Eine Bestellanforderung darf aus der DB nicht entfernt werden."));
				}else{
					throw new LagerException(new Fehler(4,FehlerTyp.FEHLER,bean.getBeanDBStatus().toString()));
				}
			}else{
				throw new LagerException(new Fehler(2));
			}
		}

		@Override
		public void deleteBean(Bean bean) throws LagerException {
			throw new LagerException(new Fehler(4));
		}


		@Override
		protected void updateBean(Bean bean) throws SQLException {
			getUpdateStmt().setDate(1, ((EntnahmeBean)bean).getErstellungsDatum());
			
			getUpdateStmt().setString(2, EntnahmeStatus.JavaToDB(((EntnahmeBean)bean).getStatus()));
			
			if (((EntnahmeBean)bean).getEntnehmerBenutzerBean()!=null && ((EntnahmeBean)bean).getEntnehmerBenutzerBean().getId()!=0)
				getUpdateStmt().setInt(3, ((EntnahmeBean)bean).getEntnehmerBenutzerBean().getId());
			else getUpdateStmt().setNull(3,java.sql.Types.INTEGER);
		
			getUpdateStmt().setInt(4, ((EntnahmeBean)bean).getId());
			
			getUpdateStmt().execute();
			
//			bean.setBeanDBStatus(BeanDBStatus.SELECT);
		}

		
		@Override
		protected void selectBean(Bean bean) {
			// TODO Auto-generated method stub
			
		}
		

		@Override
		protected void insertBean(Bean bean) throws SQLException {
			getInsertStmt().setInt(1, ((EntnahmeBean)bean).getId());
			getInsertStmt().setDate(2, ((EntnahmeBean)bean).getErstellungsDatum());
			getInsertStmt().setString(3, EntnahmeStatus.JavaToDB(((EntnahmeBean)bean).getStatus()));

			if (((EntnahmeBean)bean).getEntnehmerBenutzerBean()!=null && ((EntnahmeBean)bean).getEntnehmerBenutzerBean().getId()!=0)
				 getInsertStmt().setInt(4, ((EntnahmeBean)bean).getEntnehmerBenutzerBean().getId());
			else getInsertStmt().setNull(4,java.sql.Types.INTEGER);

			getInsertStmt().execute();
//			bean.setBeanDBStatus(BeanDBStatus.SELECT);
		}
		
		
		@Override
		protected String selectAnhandIdSQL() {
			// TODO Auto-generated method stub
			return "select id, erstellungsdatum, status, " +
			" fk_benutzerEntnehmer" +
			"from entnahme where id=?";
		}
		
		@Override
		public Bean selectAnhandID(Integer id) throws SQLException, LagerException{
			EntnahmeBean resultBean = new EntnahmeBean();
			ResultSet rs;
			//sql bilden
//			String sql = getSelectAnhandIdSQL();
			//SQL absetzen
//			PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
			PreparedStatement stmt = getSelectAnhandIdStmt();
			stmt.setInt(1, id);
			rs = stmt.executeQuery(); //Ausführen
			//ergebnisse auswerten.
			if(rs.next()){
				erzeugeBeanAusResultSet(rs, resultBean);
				resultBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
				Log.log().finest(resultBean.toString());
			}else{
				throw new LagerException(new Fehler(25,FehlerTyp.FEHLER,"Bestellanforderung nicht gefunden"));
			}
			return resultBean;	
		}
		
		private LhBeanDB getHerstellerLieferantBeanDB() {
			if (herstellerLieferantBeanDB == null) {
				herstellerLieferantBeanDB = new LhBeanDB();
			}
			return herstellerLieferantBeanDB;
		}

		private BenutzerBeanDB getBenutzerBeanDB() {
			if (benutzerBeanDB == null) {
				benutzerBeanDB = new BenutzerBeanDB();
			}
			return benutzerBeanDB;
		}

		
		public EntnahmePosBeanDB getEntnahmePosBeanDB() {
			if (entnahmePosBeanDB == null) {
				entnahmePosBeanDB = new EntnahmePosBeanDB();
			}
			return entnahmePosBeanDB;
		}

		@Override
		public ArrayList<Bean> selectAnhandSuchBean(ISuchBean iSuchBean) throws SQLException, LagerException{
			ArrayList<Bean> resultList = new ArrayList<Bean>();
			ResultSet rs;
			//sql bilden
			String sql = "select id, erstellungsdatum, status, fk_benutzerEntnehmer " +
					"from entnahme where erstellungsdatum>=? and erstellungsdatum<=?";
			switch (((EntnahmeSuchBean)iSuchBean).getEntnahmeStatus())	{
			case ALLE:break;
			default:{
				sql = sql + " and status = '"+EntnahmeStatus.JavaToDB(((EntnahmeSuchBean)iSuchBean).getEntnahmeStatus())+"'";	
			}
			};
			sql = sql + " order by erstellungsdatum desc";
			//SQL absetzen
			PreparedStatement stmt = LagerConnection.getOneInstance().getConnection().prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(((EntnahmeSuchBean)iSuchBean).getErstellungsDatumVon().getTime()));
			stmt.setDate(2, new java.sql.Date(((EntnahmeSuchBean)iSuchBean).getErstellungsDatumBis().getTime()));
			rs = stmt.executeQuery();
			//ergebnisse auswerten.
			while(rs.next()){
				EntnahmeBean entnahmeBean = new EntnahmeBean();
				erzeugeBeanAusResultSet(rs, entnahmeBean);
				entnahmeBean.setId(rs.getInt("id"));
				entnahmeBean.setErstellungsDatum(rs.getDate("erstellungsdatum"));
				entnahmeBean.setStatus(EntnahmeStatus.DbToJava(rs.getString("status")));
//				entnahmeBean.setLhBean(getHerstellerLieferantBeanDB().selectById(rs.getInt("fk_herstellerLieferant")));
				entnahmeBean.setFk_benutzerEntnehmer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzerEntnehmer")));
//				entnahmeBean.setFk_benutzerAnnehmer((BenutzerBean)getBenutzerBeanDB().sucheAnhandID(rs.getInt("fk_benutzerAnnehmer")));
//				entnahmeBean.setEmail(rs.getString("email_entnahme_empfaenger"));
				
				entnahmeBean.setBeanDBStatus(BeanDBStatus.SELECT); //Am ende setzen. Wichtig!
				Log.log().finest(entnahmeBean.toString());
				resultList.add(entnahmeBean);
			}
			return resultList;
		}

		

		@Override
		protected void erzeugeBeanAusResultSet(ResultSet rs, Bean bean)
				throws SQLException, LagerException {
			EntnahmeBean resultBean = (EntnahmeBean) bean;
			resultBean.setId(rs.getInt("id"));
			resultBean.setErstellungsDatum(rs.getDate("erstellungsdatum"));
			resultBean.setStatus(EntnahmeStatus.DbToJava(rs.getString("status")));
			resultBean.setFk_benutzerEntnehmer((BenutzerBean)getBenutzerBeanDB().selectAnhandID(rs.getInt("fk_benutzerEntnehmer")));

		}


		@Override
		public ArrayList<Bean> sucheAlle() throws SQLException, LagerException {
			// TODO Auto-generated method stub
			return new ArrayList<Bean>();
		}




	}


