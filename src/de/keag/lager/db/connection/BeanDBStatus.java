/** 
    Copyright (C) 2015 Wladimir Stoll, Kempten, Bayern, Deutschland
    wladimir.stoll@gmx.de, wladimir.stoll.bayern@googlemail.com

   This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

    Dieses Programm ist Freie Software: Sie können es unter den Bedingungen
    der GNU General Public License, wie von der Free Software Foundation,
    Version 3 der Lizenz oder (nach Ihrer Wahl) jeder neueren
    veröffentlichten Version, weiterverbreiten und/oder modifizieren.

    Dieses Programm wird in der Hoffnung, dass es nützlich sein wird, aber
    OHNE JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
    Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN ZWECK.
    Siehe die GNU General Public License für weitere Details.

    Sie sollten eine Kopie der GNU General Public License zusammen mit diesem
    Programm erhalten haben. Wenn nicht, siehe <http://www.gnu.org/licenses/>.
*/    	
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
