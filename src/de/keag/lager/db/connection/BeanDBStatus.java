package de.keag.lager.db.connection;

import java.lang.String;

import javax.swing.Icon;

import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;

public enum BeanDBStatus{
	FEHLERHAFT,
	INSERT,
	DELETE,
	UPDATE,
	SELECT,
	INSERT_DELETE,
	OHNE_STATUS_ANGABE //zuerst wurde der Satz erzeugt, und jetzt muss er gelöscht werden ohne zu speichern
	;
	
	private static Icon ICON_FEHLERHAFT = null;
	private static Icon ICON_INSERT = null;
	private static Icon ICON_INSERT_DELETE = null;
	private static Icon ICON_DELETE = null;
	private static Icon ICON_UPDATE = null;
	private static Icon ICON_SELECT = null;
	
	public static String JavaToIconName(BeanDBStatus beanStatus){
		if (beanStatus == BeanDBStatus.FEHLERHAFT||beanStatus == BeanDBStatus.OHNE_STATUS_ANGABE)
			return Konstanten.ICON_BEAN_STATUS_FEHLERHAFT;
		else if (beanStatus == BeanDBStatus.INSERT)
			return Konstanten.ICON_BEAN_STATUS_INSERT;
		else if (beanStatus == BeanDBStatus.INSERT_DELETE)
			return Konstanten.ICON_BEAN_STATUS_INSERT_DELETE;
		else if (beanStatus == BeanDBStatus.DELETE)
			return Konstanten.ICON_BEAN_STATUS_DELETE;
		else if (beanStatus == BeanDBStatus.UPDATE)
			return Konstanten.ICON_BEAN_STATUS_UPDATE;
		else if (beanStatus == BeanDBStatus.SELECT)
//			return Konstanten.ICON_BEAN_STATUS_SELECT;
			return null;
		else
			return "Fehlerhaft"; //Fehlerhaft
	}
	
	public static String JavaToString(BeanDBStatus beanStatus){
		if (beanStatus == BeanDBStatus.FEHLERHAFT||beanStatus == BeanDBStatus.OHNE_STATUS_ANGABE)
			return "fehlerhaft";
		else if (beanStatus == BeanDBStatus.INSERT)
			return "neu";
		else if (beanStatus == BeanDBStatus.INSERT_DELETE)
			return "neu, nicht übernommen";
		else if (beanStatus == BeanDBStatus.DELETE)
			return "gelöscht";
		else if (beanStatus == BeanDBStatus.UPDATE)
			return "geändert";
		else if (beanStatus == BeanDBStatus.SELECT)
			return "gelesen";
		else
			return "Fehlerhaft"; //Fehlerhaft
	}	
	
	public static Icon JavaToIcon(BeanDBStatus beanStatus){
		if (beanStatus == BeanDBStatus.FEHLERHAFT||beanStatus == BeanDBStatus.OHNE_STATUS_ANGABE)
			return getICON_FEHLERHAFT();
		else if (beanStatus == BeanDBStatus.INSERT)
			return getICON_INSERT();
		else if (beanStatus == BeanDBStatus.INSERT_DELETE)
			return getICON_INSERT_DELETE();
		else if (beanStatus == BeanDBStatus.DELETE)
			return getICON_DELETE();
		else if (beanStatus == BeanDBStatus.UPDATE)
			return getICON_UPDATE();
		else if (beanStatus == BeanDBStatus.SELECT)
			return getICON_SELECT();
		else
			return null;
	}
	

	private static Icon getICON_FEHLERHAFT() {
		if (ICON_FEHLERHAFT==null){
			ICON_FEHLERHAFT = Run.createCompoundIcon(Konstanten.ICON_BESTELLANFORDERUNG, JavaToIconName(BeanDBStatus.FEHLERHAFT));
		}
		return ICON_FEHLERHAFT;
	}

	private static Icon getICON_INSERT() {
		if (ICON_INSERT==null){
			ICON_INSERT = Run.createCompoundIcon(Konstanten.ICON_BESTELLANFORDERUNG, JavaToIconName(BeanDBStatus.INSERT));
		}
		return ICON_INSERT;
	}

	private static Icon getICON_INSERT_DELETE() {
		if (ICON_INSERT_DELETE==null){
			ICON_INSERT_DELETE = Run.createCompoundIcon(Konstanten.ICON_BESTELLANFORDERUNG, JavaToIconName(BeanDBStatus.INSERT_DELETE));
		}
		return ICON_INSERT_DELETE;
	}

	private static Icon getICON_DELETE() {
		if (ICON_DELETE==null){
			ICON_DELETE = Run.createCompoundIcon(Konstanten.ICON_BESTELLANFORDERUNG, JavaToIconName(BeanDBStatus.DELETE));
		}
		return ICON_DELETE;
	}

	private static Icon getICON_UPDATE() {
		if (ICON_UPDATE==null){
			ICON_UPDATE = Run.createCompoundIcon(Konstanten.ICON_BESTELLANFORDERUNG, JavaToIconName(BeanDBStatus.UPDATE));
		}
		return ICON_UPDATE;
	}

	private static Icon getICON_SELECT() {
		if (ICON_SELECT==null){
			ICON_SELECT = Run.createCompoundIcon(Konstanten.ICON_BESTELLANFORDERUNG, JavaToIconName(BeanDBStatus.SELECT));
		}
		return ICON_SELECT;
	}	
	
}
