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
package de.keag.lager.panels.frame.lieferanthersteller.pane.details.Lh;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.View;
import de.keag.lager.core.enums.KnotenZustand;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.formatter.LagerStringFormatter;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogModelKnotenBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhZugriffsrechtBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhZugriffsrechtModelKnotenBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

import de.keag.lager.panels.frame.email.EmailBean;



import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class LhDetailsView extends DetailsView implements ILhDetailsBeobachter, PropertyChangeListener {

	private static final long serialVersionUID = 1L;// @jve:
	private JPanel jPanelOben = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelLieferant_herstellerId = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelErstellungsDatum = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelName = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelAdresse_land = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabeAdresse_plz = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelAdresse_stadt = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelAdresse_strasse = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelTelefon = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelFax = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelIMail = null; // @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelAnsprechpartner = null; // @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jTextFieldId = null; // @jve:decl-index=0:visual-constraint=""
	private JDateChooser JDateChooserErstellungsDatum = null; // @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextFieldName = null; // @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextFieldAdresse_land = null; // @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextFieldAdresse_plz = null; // @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jTextFieldAdresse_stadt = null; // @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextFieldAdresse_strasse = null;// @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextFieldTelefon = null;// @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextFieldFax = null;// @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextFieldEmail = null; // @jve:decl-index=0:visual-constraint=""
	private JFormattedTextField jFormattedTextAnsprechpartner = null;// @jve:decl-index=0:visual-constraint=""
	private JLabel jLabelRechtsLeer = null; // @jve:decl-index=0:visual-constraint=""
	private JToolBar jToolBar = null; // @jve:decl-index=0:visual-constraint=""
	private JButton jButtonNeu = null; // @jve:decl-index=0:visual-constraint=""
	private JButton jButtonLoeschen = null; // @jve:decl-index=0:visual-constraint=""
	private JScrollPane jScrollPane = null; // @jve:decl-index=0:visual-constraint=""
	private JTable jTable = null; // @jve:decl-index=0:visual-constraint=""
	private AbstractTableModel abstractTableModel;  // @jve:
	private AbstractTableModel abstractTableModelZugriffsrecht; // @jve:  
	private JComboBox jComboBoxFehler = null; // @jve:
	private ModelKnotenBean modelKnotenBean = null;   // @jve:
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 * @param controller 
	 */
	public LhDetailsView(LhDetailsController controller) {
		super();
		setController(controller);//Controller merken
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() { 
		GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
		gridBagConstraints24.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints24.gridx = 1;
		gridBagConstraints24.gridy = 13;
		gridBagConstraints24.weightx = 1.0;
		GridBagConstraints gridBagConstraints44 = new GridBagConstraints();
		gridBagConstraints44.gridx = 3;
		gridBagConstraints44.gridy = 14;
		jLabelRechtsLeer = new JLabel();
		jLabelRechtsLeer.setText("                             ");
		GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		gridBagConstraints19.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints19.gridy = 10;
		gridBagConstraints19.weightx = 1.0;
		gridBagConstraints19.gridx = 1;
		GridBagConstraints gridBagConstraints151 = new GridBagConstraints();
		gridBagConstraints151.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints151.gridy = 11;
		gridBagConstraints151.weightx = 1.0;
		gridBagConstraints151.gridx = 1;
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints14.gridx = 1;
		gridBagConstraints14.gridy = 10;
		gridBagConstraints14.weightx = 1.0;
		GridBagConstraints gridBagConstraints131 = new GridBagConstraints();
		gridBagConstraints131.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints131.gridy = 10;
		gridBagConstraints131.weightx = 1.0;
		gridBagConstraints131.gridx = 1;
		GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
		gridBagConstraints121.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints121.gridy = 10;
		gridBagConstraints121.weightx = 1.0;
		gridBagConstraints121.gridx = 1;
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints111.gridy = 8;
		gridBagConstraints111.weightx = 1.0;
		gridBagConstraints111.gridx = 1;
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.gridy = 9;
		gridBagConstraints9.weightx = 1.0;
		gridBagConstraints9.gridx = 1;
		GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
		gridBagConstraints81.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints81.gridy = 13;
		gridBagConstraints81.weightx = 1.0;
		gridBagConstraints81.gridx = 1;
		GridBagConstraints gridBagConstraints62 = new GridBagConstraints();
		gridBagConstraints62.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints62.gridy = 10;
		gridBagConstraints62.weightx = 1.0;
		gridBagConstraints62.gridx = 1;
		GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
		gridBagConstraints43.gridx = 0;
		gridBagConstraints43.anchor = GridBagConstraints.EAST;
		gridBagConstraints43.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints43.gridy = 13;
		GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
		gridBagConstraints35.gridx = 0;
		gridBagConstraints35.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints35.anchor = GridBagConstraints.EAST;
		gridBagConstraints35.gridy = 11;
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.gridx = 0;
		gridBagConstraints23.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints23.anchor = GridBagConstraints.EAST;
		gridBagConstraints23.gridy = 10;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints12.anchor = GridBagConstraints.EAST;
		gridBagConstraints12.gridy = 9;
		GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
		gridBagConstraints34.fill = GridBagConstraints.BOTH;
		gridBagConstraints34.gridy = 18;
		gridBagConstraints34.weightx = 1.0;
		gridBagConstraints34.weighty = 1.0;
		gridBagConstraints34.gridwidth = 4;
		gridBagConstraints34.gridx = 0;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints21.gridy = 17;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.anchor = GridBagConstraints.WEST;
		gridBagConstraints21.gridx = 0;
		GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
		gridBagConstraints51.fill = GridBagConstraints.BOTH;
		gridBagConstraints51.gridy = 16;
		gridBagConstraints51.weightx = 1.0;
		gridBagConstraints51.weighty = 1.0;
		gridBagConstraints51.gridwidth = 4;
		gridBagConstraints51.gridx = 0;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.gridy = 5;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints61.gridy = 6;
		gridBagConstraints61.weightx = 1.0;
		gridBagConstraints61.gridx = 1;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints5.gridy = 7;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.gridx = 1;
		GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
		gridBagConstraints42.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints42.gridy = 10;
		gridBagConstraints42.weightx = 1.0;
		gridBagConstraints42.gridx = 1;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.gridx = 0;
		gridBagConstraints13.anchor = GridBagConstraints.EAST;
		gridBagConstraints13.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints13.gridy = 12;
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints22.gridy = 12;
		gridBagConstraints22.weightx = 1.0;
		gridBagConstraints22.anchor = GridBagConstraints.WEST;
		gridBagConstraints22.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 7;
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.gridx = 0;
		gridBagConstraints41.fill = GridBagConstraints.BOTH;
		gridBagConstraints41.weightx = 1.0D;
		gridBagConstraints41.weighty = 1.0D;
		gridBagConstraints41.insets = new Insets(1, 1, 1, 1);
		gridBagConstraints41.ipadx = 1;
		gridBagConstraints41.gridy = 0;
		GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
		gridBagConstraints32.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints32.gridy = 15;
		gridBagConstraints32.weightx = 1.0;
		gridBagConstraints32.anchor = GridBagConstraints.WEST;
		gridBagConstraints32.weighty = 0.0D;
		gridBagConstraints32.gridx = 0;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.gridy = 5;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.gridy = 19;
		gridBagConstraints31.gridheight = 1;
		gridBagConstraints31.gridwidth = 4;
		gridBagConstraints31.weightx = 1.0;
		GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
		gridBagConstraints16.fill = GridBagConstraints.BOTH;
		gridBagConstraints16.gridy = 6;
		gridBagConstraints16.weightx = 1.0;
		gridBagConstraints16.weighty = 1.0;
		gridBagConstraints16.gridwidth = 3;
		gridBagConstraints16.gridx = 0;
		GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		gridBagConstraints15.fill = GridBagConstraints.BOTH;
		gridBagConstraints15.gridy = 6;
		gridBagConstraints15.weightx = 1.0;
		gridBagConstraints15.weighty = 1.0;
		gridBagConstraints15.gridwidth = 3;
		gridBagConstraints15.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.fill = GridBagConstraints.NONE;
		gridBagConstraints7.gridy = 3;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.NONE;
		gridBagConstraints6.gridy = 1;
		gridBagConstraints6.weightx = 2.0D;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.anchor = GridBagConstraints.EAST;
		gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints4.gridy = 8;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.anchor = GridBagConstraints.EAST;
		gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints3.gridy = 6;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 5;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.anchor = GridBagConstraints.EAST;
		gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints1.gridy = 3;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelLieferant_herstellerId = new JLabel();
		jLabelLieferant_herstellerId.setText("Lieferant/Hersteller-ID");
		jLabelErstellungsDatum = new JLabel();
		jLabelErstellungsDatum.setText("Erstellungsdatum");
		jLabelName = new JLabel();
		jLabelName.setText("Name");
		jLabelAdresse_land = new JLabel();
		jLabelAdresse_land.setText("Adresse/" +
				"Land");
		jLabeAdresse_plz = new JLabel();
		jLabeAdresse_plz.setText("Adresse/PLZ");
		jLabelAdresse_stadt = new JLabel();
		jLabelAdresse_stadt.setText("Adresse/Stadt");
		jLabelAdresse_strasse = new JLabel();
		jLabelAdresse_strasse.setText("Adresse/Strasse");
		jLabelTelefon = new JLabel();
		jLabelTelefon.setText("Telefon");
		jLabelFax = new JLabel();
		jLabelFax.setText("Fax");
		jLabelIMail = new JLabel();
		jLabelIMail.setText("EMail");
		jLabelAnsprechpartner = new JLabel();
		jLabelAnsprechpartner.setText("Ansprechpartner");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(jLabelLieferant_herstellerId, gridBagConstraints);
		this.add(jLabelErstellungsDatum, gridBagConstraints1);
		this.add(jLabelName, gridBagConstraints2);
		this.add(jLabelAdresse_land, gridBagConstraints3);
		this.add(jLabeAdresse_plz, gridBagConstraints11);
		this.add(jLabelAdresse_stadt, gridBagConstraints4);
		this.add(jLabelAdresse_strasse, gridBagConstraints12);
		this.add(jLabelTelefon, gridBagConstraints23);
		this.add(jLabelFax, gridBagConstraints35);
		this.add(jLabelIMail, gridBagConstraints13);
		this.add(jLabelAnsprechpartner, gridBagConstraints43);
		this.add(getJFormattedTextFieldId(), gridBagConstraints6);
		this.add(getJDateChooserErstellungsDatum(), gridBagConstraints7);
		this.add(getJFormattedTextFieldName(), gridBagConstraints8);
		this.add(getJFormattedTextFieldAdresse_land(), gridBagConstraints61);
		this.add(getJFormattedTextFieldAdresse_plz(), gridBagConstraints5);
		this.add(getJFormattedTextFieldAdresse_stadt(), gridBagConstraints111);
		this.add(getJFormattedTextFieldAdresse_strasse(), gridBagConstraints9);
		this.add(getJFormattedTextFieldTelefon(), gridBagConstraints19);
		this.add(getJFormattedTextFieldFax(), gridBagConstraints151);
		this.add(getJFormattedTextFieldEmail(), gridBagConstraints22);
		this.add(getJFormattedTextAnsprechpartner(), gridBagConstraints24);
		this.add(jLabelRechtsLeer, gridBagConstraints44);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJScrollPane(), gridBagConstraints51);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				LhDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				System.out.println("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				LhDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
				LhDetailsView.this.uebernehmeDaten();
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Lh-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof LhBean){
					LhBean lhBean = (LhBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt LhBean sein. Es sei denn, dass Objet ist neu(INSERT).
				}else{
					Log.log().severe("Fehler3");
				}
			}else{
				Log.log().severe("Fehler2");
			}
		}else{
			Log.log().severe("Fehler");
		}
	}

	/**
	 * This method initializes jTextFieldId	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldId() {
		if (jTextFieldId == null) {
			jTextFieldId = new JFormattedTextField();
			jTextFieldId.setPreferredSize(new Dimension(50, 20));
		}
		return jTextFieldId;
	}

	/**
	 * This method initializes JDateChooserErstellungsDatum	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JDateChooser getJDateChooserErstellungsDatum() {
		if (JDateChooserErstellungsDatum == null) {
			JDateChooserErstellungsDatum = new JDateChooser(Konstanten.FORMAT_DATUM,false);
			JDateChooserErstellungsDatum.setLayout(new GridBagLayout());
			JDateChooserErstellungsDatum.setPreferredSize(new Dimension(100, 20));
		}
		return JDateChooserErstellungsDatum;
	}

	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		setzeHintergrund();
		if(modelKnotenBean!=null){
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_HERSTELLER){
				getJComboBoxFehler().removeAllItems();
				setModelBean(modelKnotenBean);//merken
				LhBean lhBean = (LhBean) modelKnotenBean.getIBean();
				getJFormattedTextFieldId().setText(lhBean.getId().toString());
				getJFormattedTextFieldId().setEnabled(false);
				getJFormattedTextFieldName().setText(lhBean.getName());
				getJFormattedTextFieldAdresse_land().setText(lhBean.getAdresse_land());
				getJFormattedTextFieldAdresse_plz().setText(lhBean.getAdresse_plz());
				getJFormattedTextFieldAdresse_stadt().setText(lhBean.getAdresse_stadt());
				getJFormattedTextFieldAdresse_strasse().setText(lhBean.getAdresse_strasse());
				getJFormattedTextFieldTelefon().setText(lhBean.getTelefon());
				getJFormattedTextFieldFax().setText(lhBean.getFax());
				getJFormattedTextFieldEmail().setText(lhBean.getEmail());
				getJFormattedTextAnsprechpartner().setText(lhBean.getAnsprechpartner());
				
		//		//Erstellungsdatum wird angezeigt.
		//		getJDateChooserErstellungsDatum().setDate(lhBean.getErstellungsDatum());
		//		getJDateChooserErstellungsDatum().setEnabled(false);
				
				//Fehler anzeigen.
//				lhBean.pruefeEigeneDaten();
				for(int i=0; i<lhBean.getFehlerList().size();i++){
					Fehler fehler = lhBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				
				((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
				this.repaint();    //alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
//				System.out.println("zeichneDich() LhDetailsView");
			}
		}
	}

	/**
	 * This method initializes jFormattedTextAnsprechpartner	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextAnsprechpartner() {
		if (jFormattedTextAnsprechpartner == null) {
			jFormattedTextAnsprechpartner = new JFormattedTextField(new LagerStringFormatter(0,100));
			jFormattedTextAnsprechpartner.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextAnsprechpartner.addPropertyChangeListener("value", this);
		}
		return jFormattedTextAnsprechpartner;
	}

	/**
	 * This method initializes jComboBoxFehler	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxFehler() {
		if (jComboBoxFehler == null) {
			jComboBoxFehler = new JComboBox();
		}
		return jComboBoxFehler;
	}


	private AbstractTableModel getAbstractTableModel() {
		if(abstractTableModel==null){
			abstractTableModel = new AbstractTableModel() {
				private String[] columnNames = {"Katalog"};

				@Override
				public String getColumnName(int column){
					return columnNames[column];
				}
				
				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean()==null){
						return "";
					}
					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(row);
					if (modelKnotenBean.getIBean() instanceof LhKatalogBean){
						LhKatalogBean lhKatalogBean = 
							(LhKatalogBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								if (lhKatalogBean!=null && lhKatalogBean.getKatalog()!=null){
									return lhKatalogBean.getKatalog().getName();
								}else return "";
							}
							case 999 : return lhKatalogBean; 
							default : return "Fehler, column:"+new Integer(column).toString(); 
							}
					}else{
						return "Falsche Klasse:"+modelKnotenBean.getIBean().toString();
					}
				}
				
				@Override
				public int getRowCount() {
					 if (getModelBean()==null){
						 return 0;
					 }else{
						 int anzahlKatalogen = 0;
						 for (int i=0;i<getModelBean().getKinderList().size();i++){
							 if (getModelBean().getKinderList().get(i) instanceof LhKatalogModelKnotenBean){
								 anzahlKatalogen++;
							 }
						 }
						 return anzahlKatalogen;
					 }
				}
				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModel;
	}
	
	private AbstractTableModel getAbstractTableModelZugriffrecht() {
		if(abstractTableModelZugriffsrecht==null){
			abstractTableModelZugriffsrecht = new AbstractTableModel() {
				private String[] columnNames = {"Zugriffsrecht"};

				@Override
				public String getColumnName(int column){
					return columnNames[column];
				}
				
				@Override
				public Object getValueAt(int row, int column) {
					if (getModelBean()==null){
						return "";
					}
					
					//Finde heraus, ab welcher Zeile die Zugriffsrechte beginnen.
					int zugriffsrechtAbZeile = 0;
					 for (int i=0;i<getModelBean().getKinderList().size()&&zugriffsrechtAbZeile==0;i++){
						 if (getModelBean().getKinderList().get(i) instanceof LhZugriffsrechtModelKnotenBean){
							 zugriffsrechtAbZeile = i;
						 }
					 }
					
					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(zugriffsrechtAbZeile+row);
					if (modelKnotenBean.getIBean() instanceof LhZugriffsrechtBean){
						LhZugriffsrechtBean lhZugriffsrechtBean = 
							(LhZugriffsrechtBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								if (lhZugriffsrechtBean!=null && lhZugriffsrechtBean.getZugriffsrecht()!=null){
									return lhZugriffsrechtBean.getZugriffsrecht().getZugriffsrechtName();
								}else return "";
							}
							case 999 : return lhZugriffsrechtBean; 
							default : return "Fehler, column:"+new Integer(column).toString(); 
							}
					}else{
						return "Falsche Klasse:"+modelKnotenBean.getIBean().toString();
					}
				}
				
				@Override
				public int getRowCount() {
					 if (getModelBean()==null){
						 return 0;
					 }else{
						 int anzahlZugriffsrecht = 0;
						 for (int i=0;i<getModelBean().getKinderList().size();i++){
							 if (getModelBean().getKinderList().get(i) instanceof LhZugriffsrechtModelKnotenBean){
								 anzahlZugriffsrecht++;
							 }
						 }
						 return anzahlZugriffsrecht;
					 }
				}
				@Override
				public int getColumnCount() {
					return columnNames.length;
				}
			};
		}
		return abstractTableModelZugriffsrecht;
	}
	


	@Override
	public void setModelBean(ModelKnotenBean lhModelBean) {
		this.modelKnotenBean = lhModelBean;
	}
			
	
	@Override
	public ModelKnotenBean getModelBean() {
		return modelKnotenBean;
	}
				

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
			jToolBar.add(getJButtonNeu());
			jToolBar.add(getJButtonLoeschen());
		}
		return jToolBar;
	}

	/**
	 * This method initializes jButtonLoeschen	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonLoeschen() {
		if (jButtonLoeschen == null) {
			jButtonLoeschen = new JButton();
			jButtonLoeschen.setText("Löschen");
			jButtonLoeschen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						if (getJTable().getSelectedRow()>=0){
							LhKatalogBean bean = 
								(LhKatalogBean) getJTable().
										getModel().
											getValueAt(getJTable().getSelectedRow(), 999);
							getController().loeschePosition(bean);
						}else{
							getJComboBoxFehler().addItem(new Fehler(27));
						}
					}

				}
			});
//			Zugriffsrechtpruefung.addRecht(jButtonLoeschen,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
		}
		return jButtonLoeschen;
	}

	/**
	 * This method initializes jPanelOben	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelOben() {
		if (jPanelOben == null) {
			jPanelOben = new JPanel();
			jPanelOben.setLayout(new GridBagLayout());
			jPanelOben.setPreferredSize(new Dimension(10, 10));
		}
		return jPanelOben;
	}

	@Override
	public void setStandardFocusPosition() {
		getJFormattedTextFieldName().requestFocus();
	}

	/**
	 * enable = benutzbar
	 * editable = beschreibbar
	 */
	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJButtonNeu().setEnabled(enabled);
		getJButtonLoeschen().setEnabled(enabled);
		getJFormattedTextFieldEmail().setEditable(enabled);
		getJFormattedTextFieldName().setEditable(enabled);
		getJFormattedTextFieldAdresse_land().setEditable(enabled);
		getJFormattedTextFieldAdresse_plz().setEditable(enabled);
		getJFormattedTextFieldAdresse_stadt().setEditable(enabled);
		getJFormattedTextFieldAdresse_strasse().setEditable(enabled);
		getJFormattedTextFieldTelefon().setEditable(enabled);
		getJFormattedTextFieldFax().setEditable(enabled);
		getJFormattedTextFieldEmail().setEditable(enabled);
		getJFormattedTextFieldEmail().setEditable(enabled);

		getJDateChooserErstellungsDatum().setEnabled(enabled);
		getJTable().setEnabled(enabled);
	}

	/**
	 * This method initializes jTextFieldEmail	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldEmail() {
		if (jFormattedTextFieldEmail == null) {
			jFormattedTextFieldEmail = new JFormattedTextField(new LagerStringFormatter(0,45));
			jFormattedTextFieldEmail.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldEmail.addPropertyChangeListener("value", this);
		}
		return jFormattedTextFieldEmail;
	}

	//setzen der Email aus dem Matchcode-Fenster
	public void setMatchCodeEmail(EmailBean emailBean,String email) {
		if (email!=null){
			LhBean lhBean = (LhBean)getModelBean().getIBean();
			lhBean.setEmail(email);
		}
		if(emailBean!=null){
			LhBean lhBean = (LhBean)getModelBean().getIBean();
			lhBean.setEmail(emailBean.getEmail());
			getJFormattedTextFieldEmail().setText(lhBean.getEmail());
		}
	}

	/**
	 * This method initializes jTextFieldAdresse_stadt	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldAdresse_stadt() {
		if (jTextFieldAdresse_stadt == null) {
			jTextFieldAdresse_stadt = new JFormattedTextField(new LagerStringFormatter(1,20));
			jTextFieldAdresse_stadt.setInputVerifier(LagerFormate.getInputVerifier());
			jTextFieldAdresse_stadt.addPropertyChangeListener("value", this);
			
		}
		return jTextFieldAdresse_stadt;
	}

	/**
	 * This method initializes jTextFieldLoginName	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldAdresse_plz() {
		if (jFormattedTextFieldAdresse_plz == null) {
			jFormattedTextFieldAdresse_plz = new JFormattedTextField(new LagerStringFormatter(1,20));
			jFormattedTextFieldAdresse_plz.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldAdresse_plz.addPropertyChangeListener("value", this);
		}
		return jFormattedTextFieldAdresse_plz;
	}

	/**
	 * This method initializes jTextFieldAdresse_land	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldAdresse_land() {
		if (jFormattedTextFieldAdresse_land == null) {
			jFormattedTextFieldAdresse_land = new JFormattedTextField(new LagerStringFormatter(1,15));
			jFormattedTextFieldAdresse_land.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldAdresse_land.addPropertyChangeListener("value", this);
			
			
		}
		return jFormattedTextFieldAdresse_land;
	}

	/**
	 * This method initializes jTextFieldName	
	 * 	 
	 *		
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldName() {
		if (jFormattedTextFieldName == null) {
			jFormattedTextFieldName = new JFormattedTextField(new LagerStringFormatter(1,50));
			jFormattedTextFieldName.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldName.addPropertyChangeListener("value", this);
		}
		return jFormattedTextFieldName;
	}

	/**
	 * This method initializes jButtonNeu	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNeu() {
		if (jButtonNeu == null) {
			jButtonNeu = new JButton();
			jButtonNeu.setText("Neu");
			jButtonNeu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						getController().erstelleNeuenSatz(ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG);
					}
				}
			});
//			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
//			Zugriffsrechtpruefung.addRecht(jButtonNeu,new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
		}
		return jButtonNeu;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJTable());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable();
			jTable.setModel(getAbstractTableModel());
			jTable.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
			jTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount()==2){
						//ausgewählte Bean wird ermittelt. 
						int aktuelleZeile = jTable.getSelectedRow();
						if (getModelBean()!=null 
								&& getModelBean().getKinderList().size()>0
								&& getModelBean().getKinderList().size()>=aktuelleZeile){
							ModelKnotenBean modelKnotenBean =  getModelBean().getKinderList().get(aktuelleZeile);
							getController().selectKnoten(modelKnotenBean);
						}
						
					};
				}
			});
		}
		return jTable;
	}
	private void leseAusgetJFormattedTextFieldName()  {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextFieldName().getValue() != null) {
			wert = getJFormattedTextFieldName().getValue().toString();
			lhBean.setName(wert);
		}
	}
	private void leseAusgetJFormattedTextFieldAdresse_land()  {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextFieldAdresse_land().getValue() != null) {
			wert = getJFormattedTextFieldAdresse_land().getValue().toString();
			lhBean.setAdresse_land(wert);
		}
	}
	private void leseAusgetJFormattedTextFieldAdresse_plz()  {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextFieldAdresse_plz().getValue() != null) {
			wert = getJFormattedTextFieldAdresse_plz().getValue().toString();
			lhBean.setAdresse_plz(wert);
		}
	}
	private void leseAusgetJFormattedTextFieldAdresse_stadt()  {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextFieldAdresse_stadt().getValue() != null) {
			wert = getJFormattedTextFieldAdresse_stadt().getValue().toString();
			lhBean.setAdresse_stadt(wert);
		}
	}
	private void leseAusgetJFormattedTextFieldAdresse_strasse()  {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextFieldAdresse_strasse().getValue() != null) {
			wert = getJFormattedTextFieldAdresse_strasse().getValue().toString();
			lhBean.setAdresse_strasse(wert);
		}
	}
	private void leseAusgetJFormattedTextFieldTelefon()  {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextFieldTelefon().getValue() != null) {
			wert = getJFormattedTextFieldTelefon().getValue().toString();
			lhBean.setTelefon(wert);
		}
	}
	

	private void leseAusgetJFormattedTextFieldFax()  {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextFieldFax().getValue() != null) {
			wert = getJFormattedTextFieldFax().getValue().toString();
			lhBean.setFax(wert);
		}
	}
	

	private void leseAusgetJFormattedTextFieldEmail() {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextFieldEmail().getValue() != null) {
			wert = getJFormattedTextFieldEmail().getValue().toString();
			lhBean.setEmail(wert);
		}
	}

	private void leseAusgetJFormattedTextFieldAnsprechpartner() {
		LhBean lhBean = (LhBean)getModelBean().getIBean();
		String wert ;
		if (getJFormattedTextAnsprechpartner().getValue() != null) {
			wert = getJFormattedTextAnsprechpartner().getValue().toString();
			lhBean.setAnsprechpartner(wert);
		}
	}





	
	/**
	 * Diese Funktion wird immer dann aufgerufen, falls in einem JFormattedField der Inhalt geändert wird.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJFormattedTextFieldName()){
			leseAusgetJFormattedTextFieldName();
		}
		if (source==getJFormattedTextFieldAdresse_land()){
			leseAusgetJFormattedTextFieldAdresse_land();
		}
		if (source==getJFormattedTextFieldAdresse_plz()){
			leseAusgetJFormattedTextFieldAdresse_plz();
		}
		if (source==getJFormattedTextFieldAdresse_stadt()){
			leseAusgetJFormattedTextFieldAdresse_stadt();
		}
		if (source==getJFormattedTextFieldAdresse_strasse()){
			leseAusgetJFormattedTextFieldAdresse_strasse();
		}
		if (source==getJFormattedTextFieldTelefon()){
			leseAusgetJFormattedTextFieldTelefon();
		}
		if (source==getJFormattedTextFieldFax()){
			leseAusgetJFormattedTextFieldFax();
		}
		if (source==getJFormattedTextFieldEmail()){
			leseAusgetJFormattedTextFieldEmail();
		}
		if (source==getJFormattedTextAnsprechpartner()){
			leseAusgetJFormattedTextFieldAnsprechpartner();
		}

		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}
	

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {
			if (!getJFormattedTextFieldName().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldName())) {
				errors.add(new Fehler(60));
			} else {
				leseAusgetJFormattedTextFieldName();
			}
			if (!getJFormattedTextFieldAdresse_land().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldAdresse_land())) {
				errors.add(new Fehler(61));
			} else {
				leseAusgetJFormattedTextFieldAdresse_land();
			}
			if (!getJFormattedTextFieldAdresse_plz().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldAdresse_plz())) {
				errors.add(new Fehler(62));
			} else {
				leseAusgetJFormattedTextFieldAdresse_plz();
			}
			if (!getJFormattedTextFieldAdresse_stadt().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldAdresse_stadt())) {
				errors.add(new Fehler(63));
			} else {
				leseAusgetJFormattedTextFieldAdresse_stadt();
			}
			if (!getJFormattedTextFieldAdresse_strasse().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldAdresse_strasse())) {
				errors.add(new Fehler(64));
			} else {
				leseAusgetJFormattedTextFieldAdresse_strasse();
			}
			if (!getJFormattedTextFieldTelefon().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldTelefon())) {
				errors.add(new Fehler(65));
			} else {
				leseAusgetJFormattedTextFieldTelefon();
			}
			if (!getJFormattedTextFieldFax().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldFax())) {
				errors.add(new Fehler(66));
			} else {
				leseAusgetJFormattedTextFieldFax();
			}
//			if (!getJFormattedTextFieldAdresse_stadt().getInputVerifier().shouldYieldFocus(
//					getJFormattedTextFieldAdresse_stadt())) {
//				errors.add(new Fehler(67));
//			} else {
//				leseAusgetJFormattedTextFieldAdresse_stadt();
//			}
			if (!getJFormattedTextFieldEmail().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldEmail())) {
				errors.add(new Fehler(67));
			} else {
				leseAusgetJFormattedTextFieldEmail();
			}
			if (!getJFormattedTextAnsprechpartner().getInputVerifier().shouldYieldFocus(
					getJFormattedTextAnsprechpartner())) {
				errors.add(new Fehler(68));
			} else {
				leseAusgetJFormattedTextFieldAnsprechpartner();
			}
		} catch (Exception e) {
			//Alle Fehler abfangen
			errors.add(new Fehler(36,FehlerTyp.FEHLER,e.getMessage()));
			e.printStackTrace();
		}
		return errors;
	}


	
	@Override
	protected DetailsController getController() {
		return detailsController;
	}
	
	@Override
	protected void setController(DetailsController detailsController) {
		this.detailsController = detailsController;
	}
	


	/**
	 * This method initializes jTextFieldAdresse_strasse	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
//	private JFormattedTextField getJFormattedTextFieldAdresse_strasse() {
//		if (jTextFieldAdresse_strasse == null) {
//			jTextFieldAdresse_strasse = new JFormattedTextField();
//		}
//		return jTextFieldAdresse_strasse;
//	}
	private JFormattedTextField getJFormattedTextFieldAdresse_strasse() {
		if (jFormattedTextFieldAdresse_strasse == null) {
			jFormattedTextFieldAdresse_strasse = new JFormattedTextField(new LagerStringFormatter(0,50));
			jFormattedTextFieldAdresse_strasse.setInputVerifier(LagerFormate.getInputVerifier());
			jFormattedTextFieldAdresse_strasse.addPropertyChangeListener("value", this);
		}
		return jFormattedTextFieldAdresse_strasse;
	}
	


//	private JFormattedTextField getJFormattedTextFieldTelefon() {
//		if (jTextFieldTelefon == null) {
//			jTextFieldTelefon = new JFormattedTextField(new LagerStringFormatter(4, 4));
//			jTextFieldTelefon.setInputVerifier(LagerFormate.getInputVerifier());
//			jTextFieldTelefon.addPropertyChangeListener("value", this);
//			
//		}
//		return jTextFieldTelefon;
//	}

	/**
	 * This method initializes jTextAnsprechpartner	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
//	private JFormattedTextField getJTextAnsprechpartner() {
//		if (jTextFieldAnsprechpartner == null) {
//			jTextFieldAnsprechpartner = new JFormattedTextField(new LagerStringFormatter(1,20));
//			jTextFieldAnsprechpartner.setInputVerifier(LagerFormate.getInputVerifier());
//			jTextFieldAnsprechpartner.addPropertyChangeListener("value", this);
//		}
//
//		return jTextFieldAnsprechpartner;
//	}

	/**
	 * This method initializes jTextFieldFax	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldFax() {
//		if (jTextFieldFax == null) {
//			jTextFieldFax = new JFormattedTextField();
//		}
		if (jFormattedTextFieldFax == null) {
		jFormattedTextFieldFax = new JFormattedTextField(new LagerStringFormatter(0,20));
		jFormattedTextFieldFax.setInputVerifier(LagerFormate.getInputVerifier());
		jFormattedTextFieldFax.addPropertyChangeListener("value", this);
	}
		return  jFormattedTextFieldFax;
	}


	/**
	 * This method initializes jTextFieldTelefon	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldTelefon() {
		if (jFormattedTextFieldTelefon == null) {
		jFormattedTextFieldTelefon = new JFormattedTextField(new LagerStringFormatter(0, 20));
		jFormattedTextFieldTelefon.setInputVerifier(LagerFormate.getInputVerifier());
		jFormattedTextFieldTelefon.addPropertyChangeListener("value", this);
		
	}
		return jFormattedTextFieldTelefon;
	}

	
	
}  //  @jve:decl-index=0:visual-constraint="18,-73"
