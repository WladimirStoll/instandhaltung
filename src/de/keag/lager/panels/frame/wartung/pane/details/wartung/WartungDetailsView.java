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
package de.keag.lager.panels.frame.wartung.pane.details.wartung;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.Bean;
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
import de.keag.lager.panels.frame.artikel.model.ArtikelModelKnotenBean;
import de.keag.lager.panels.frame.wartung.model.WartungsMitarbeiterModelKnotenBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean;
import de.keag.lager.panels.frame.wartung.model.WartungBean.StatusEnum;
import de.keag.lager.panels.frame.wartung.pane.suche.WartungSuchController;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean;
import de.keag.lager.panels.frame.wartungsart.model.WartungsArtBean.WartungsArtIntervallFaehigEnum;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelBean;
import de.keag.lager.panels.frame.wartungsartikel.model.WartungsArtikelModelKnotenBean;
import de.keag.lager.panels.frame.wartungsmitarbeiter.model.WartungsMitarbeiterBean;
import de.keag.lager.panels.frame.wartungstyp.model.WartungsTypBean;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerModelKnotenBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckModelKnotenBean;
import de.keag.lager.panels.frame.bestellanforderung.BaPosBean;

import de.keag.lager.panels.frame.email.EmailBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;



import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;


public class WartungDetailsView extends DetailsView implements IWartungDetailsBeobachter, PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelAnforderungsId = null;
	private JLabel jLabelTerminSoll = null;
	private JLabel jLabelBemerkung = null;
	private JLabel jLabelVorname = null;
	private JTextField jTextFieldId = null;
	private JDateChooser JDateChooserTerminSoll = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;

	private ModelKnotenBean modelKnotenBean = null;  //  @jve:decl-index=0:
	private JToolBar jToolBar = null;
	private JButton jButtonLoeschen = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JLabel jLabeLoginName = null;
	private JFormattedTextField jTextFieldInterval = null;
	private JFormattedTextField jTextFieldWartungsKatenID = null;
	private JFormattedTextField jTextFieldBemerkung = null;
	private JButton jButtonNeu = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private AbstractTableModel abstractTableModel;  //  @jve:decl-index=0:
	private AbstractTableModel abstractTableModelZugriffsrecht;  //  @jve:decl-index=0:
	private JToolBar jToolBar2 = null;
	private JButton jButtonNeuZugriffsrecht = null;
	private JButton jButtonLoeschenZugriffsrecht = null;
	private JScrollPane jScrollPaneZugriffsrechte = null;
	private JTable jTableZugriffsrechte = null;
	private JLabel jLabelBaugruppe = null;
	private JTextField jTextFieldBaugrupe = null;
	private JButton jButtonMatchCodeBaugruppe = null;
	private JLabel jLabelArt = null;
	private JLabel jLabelTyp = null;
	private JTextField jTextFieldWartungsArt = null;
	private JTextField jTextFieldWartungsTyp = null;
	private JButton jButtonMatchCodeWartungsArt = null;
	private JButton jButtonMatchCodeWartungsTyp = null;
	private JLabel jLabelTerminIst = null;
	private JDateChooser JDateChooserTerminIst = null;
	private JLabel jLabelStatus = null;
	private JComboBox jComboBoxStatus = null;
	private JLabel jLabelBeschreibung = null;
	private JTextArea jTextAreaBeschreibung = null;
	private JButton jButtonKartenId = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel jPanelMitarbeiter = null;
	private JPanel jPanelArtikel = null;
	private JLabel jLabel = null;
	private DocumentListener documentListenerBeschreibung;  //  @jve:decl-index=0:
	private ActionListener jComboBoxStatusActionListener;  //  @jve:decl-index=0:
	private JLabel jLabelUebergeordneteBaugruppe = null;
	private JTextField jTextUebergeordneteBaugruppe = null;
	private JScrollPane jScrollPaneBeschreibung = null;

	
	/**
	 * This is the default constructor
	 * @param controller 
	 */
	public WartungDetailsView(WartungDetailsController controller) {
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
		GridBagConstraints gridBagConstraints112 = new GridBagConstraints();
		gridBagConstraints112.fill = GridBagConstraints.BOTH;
		gridBagConstraints112.gridy = 8;
		gridBagConstraints112.weightx = 1.0;
		gridBagConstraints112.weighty = 1.0;
		gridBagConstraints112.gridx = 1;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.fill = GridBagConstraints.BOTH;
		gridBagConstraints21.gridy = 3;
		gridBagConstraints21.weightx = 1.0;
		gridBagConstraints21.gridx = 1;
		GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
		gridBagConstraints110.gridx = 0;
		gridBagConstraints110.anchor = GridBagConstraints.EAST;
		gridBagConstraints110.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints110.gridy = 3;
		jLabelUebergeordneteBaugruppe = new JLabel();
		jLabelUebergeordneteBaugruppe.setText("Übergeordnete Baugruppe");
		GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
		gridBagConstraints42.gridx = 0;
		gridBagConstraints42.weightx = 1.0D;
		gridBagConstraints42.weighty = 0.0D;
		gridBagConstraints42.gridy = 1;
		jLabel = new JLabel();
		jLabel.setText(" ");
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.BOTH;
		gridBagConstraints13.gridy = 24;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.weighty = 1.0;
		gridBagConstraints13.gridwidth = 3;
		gridBagConstraints13.gridx = 0;
		GridBagConstraints gridBagConstraints161 = new GridBagConstraints();
		gridBagConstraints161.gridx = 2;
		gridBagConstraints161.anchor = GridBagConstraints.WEST;
		gridBagConstraints161.gridy = 12;
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.gridx = 0;
		gridBagConstraints14.anchor = GridBagConstraints.EAST;
		gridBagConstraints14.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints14.weighty = 0.0D;
		gridBagConstraints14.weightx = 1.0D;
		gridBagConstraints14.gridy = 8;
		jLabelBeschreibung = new JLabel();
		jLabelBeschreibung.setText("Beschreibung");
		GridBagConstraints gridBagConstraints131 = new GridBagConstraints();
		gridBagConstraints131.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints131.gridy = 18;
		gridBagConstraints131.weightx = 1.0;
		gridBagConstraints131.anchor = GridBagConstraints.WEST;
		gridBagConstraints131.weighty = 0.0D;
		gridBagConstraints131.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints131.gridx = 1;
		GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
		gridBagConstraints121.gridx = 0;
		gridBagConstraints121.anchor = GridBagConstraints.EAST;
		gridBagConstraints121.insets = new Insets(10, 0, 0, 5);
		gridBagConstraints121.gridy = 18;
		jLabelStatus = new JLabel();
		jLabelStatus.setText("Status");
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.gridx = 1;
		gridBagConstraints111.anchor = GridBagConstraints.WEST;
		gridBagConstraints111.weighty = 0.0D;
		gridBagConstraints111.insets = new Insets(2, 2, 10, 2);
		gridBagConstraints111.gridy = 19;
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.gridx = 0;
		gridBagConstraints10.anchor = GridBagConstraints.EAST;
		gridBagConstraints10.insets = new Insets(0, 0, 10, 5);
		gridBagConstraints10.gridy = 19;
		jLabelTerminIst = new JLabel();
		jLabelTerminIst.setText("IST-Termin (durchgeführt am)");
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.gridx = 2;
		gridBagConstraints9.anchor = GridBagConstraints.WEST;
		gridBagConstraints9.gridy = 6;
		GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
		gridBagConstraints81.gridx = 2;
		gridBagConstraints81.anchor = GridBagConstraints.WEST;
		gridBagConstraints81.gridy = 5;
		GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
		gridBagConstraints71.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints71.gridy = 6;
		gridBagConstraints71.weightx = 1.0;
		gridBagConstraints71.weighty = 0.0D;
		gridBagConstraints71.gridx = 1;
		GridBagConstraints gridBagConstraints62 = new GridBagConstraints();
		gridBagConstraints62.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints62.gridy = 5;
		gridBagConstraints62.weightx = 1.0;
		gridBagConstraints62.weighty = 0.0D;
		gridBagConstraints62.gridx = 1;
		GridBagConstraints gridBagConstraints52 = new GridBagConstraints();
		gridBagConstraints52.gridx = 0;
		gridBagConstraints52.anchor = GridBagConstraints.EAST;
		gridBagConstraints52.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints52.gridy = 6;
		jLabelTyp = new JLabel();
		jLabelTyp.setText("Typ");
		GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
		gridBagConstraints43.gridx = 0;
		gridBagConstraints43.anchor = GridBagConstraints.EAST;
		gridBagConstraints43.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints43.gridy = 5;
		jLabelArt = new JLabel();
		jLabelArt.setText("Art");
		GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
		gridBagConstraints35.gridx = 2;
		gridBagConstraints35.anchor = GridBagConstraints.WEST;
		gridBagConstraints35.weightx = 1.0D;
		gridBagConstraints35.gridy = 4;
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints23.gridy = 4;
		gridBagConstraints23.weightx = 1.0;
		gridBagConstraints23.weighty = 0.0D;
		gridBagConstraints23.gridx = 1;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.anchor = GridBagConstraints.EAST;
		gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints12.gridy = 4;
		jLabelBaugruppe = new JLabel();
		jLabelBaugruppe.setText("Anlage/Baugruppe");
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints8.gridy = 7;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.weighty = 0.0D;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints61.gridy = 12;
		gridBagConstraints61.weightx = 1.0;
		gridBagConstraints61.weighty = 0.0D;
		gridBagConstraints61.gridx = 1;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints5.gridy = 17;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.weighty = 0.0D;
		gridBagConstraints5.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.anchor = GridBagConstraints.EAST;
		gridBagConstraints11.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints11.gridy = 17;
		jLabeLoginName = new JLabel();
		jLabeLoginName.setText("Intervall (in Tagen)");
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.gridx = 0;
		gridBagConstraints41.fill = GridBagConstraints.BOTH;
		gridBagConstraints41.weightx = 1.0D;
		gridBagConstraints41.weighty = 0.0D;
		gridBagConstraints41.insets = new Insets(1, 1, 1, 1);
		gridBagConstraints41.ipadx = 1;
		gridBagConstraints41.gridy = 0;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.gridy = 5;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.gridy = 25;
		gridBagConstraints31.gridheight = 1;
		gridBagConstraints31.gridwidth = 3;
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
		gridBagConstraints7.weighty = 0.0D;
		gridBagConstraints7.insets = new Insets(2, 2, 2, 2);
		gridBagConstraints7.gridy = 15;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints6.gridy = 2;
		gridBagConstraints6.weightx = 10.0D;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.weighty = 0.0D;
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.anchor = GridBagConstraints.EAST;
		gridBagConstraints3.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints3.gridy = 12;
		jLabelVorname = new JLabel();
		jLabelVorname.setText("Wartungs-Karten-ID");
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 7;
		jLabelBemerkung = new JLabel();
		jLabelBemerkung.setText("Bemerkung");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.anchor = GridBagConstraints.EAST;
		gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints1.gridy = 15;
		jLabelTerminSoll = new JLabel();
		jLabelTerminSoll.setText("SOLL-Termin (geplant)");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.weighty = 0.0D;
		gridBagConstraints.gridy = 2;
		jLabelAnforderungsId = new JLabel();
		jLabelAnforderungsId.setText("Wartungs-ID");
		this.setSize(700, 600);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(jLabelAnforderungsId, gridBagConstraints);
		this.add(jLabelTerminSoll, gridBagConstraints1);
		this.add(jLabelBemerkung, gridBagConstraints2);
		this.add(jLabelVorname, gridBagConstraints3);
		this.add(jLabeLoginName, gridBagConstraints11);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJTextFieldBemerkung(), gridBagConstraints8);
		this.add(getJTextFieldWartungsKatenID(), gridBagConstraints61);
		this.add(getJTextFieldInterval(), gridBagConstraints5);
		this.add(getJDateChooserTerminSoll(), gridBagConstraints7);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(jLabelBaugruppe, gridBagConstraints12);
		this.add(getJTextFieldBaugrupe(), gridBagConstraints23);
		this.add(getJButtonMatchCodeBaugruppe(), gridBagConstraints35);
		this.add(jLabelArt, gridBagConstraints43);
		this.add(jLabelTyp, gridBagConstraints52);
		this.add(getJTextFieldWartungsArt(), gridBagConstraints62);
		this.add(getJTextFieldWartungsTyp(), gridBagConstraints71);
		this.add(getJButtonMatchCodeWartungsArt(), gridBagConstraints81);
		this.add(getJButtonMatchCodeWartungsTyp(), gridBagConstraints9);
		this.add(jLabelTerminIst, gridBagConstraints10);
		this.add(getJDateChooserTerminIst(), gridBagConstraints111);
		this.add(jLabelStatus, gridBagConstraints121);
		this.add(getJComboBoxStatus(), gridBagConstraints131);
		this.add(jLabelBeschreibung, gridBagConstraints14);
		this.add(getJButtonKartenId(), gridBagConstraints161);
		this.add(getJTabbedPane(), gridBagConstraints13);
		this.add(jLabel, gridBagConstraints42);
		this.add(jLabelUebergeordneteBaugruppe, gridBagConstraints110);
		this.add(getJTextUebergeordneteBaugruppe(), gridBagConstraints21);
		this.add(getJScrollPaneBeschreibung(), gridBagConstraints112);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				WartungDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				WartungDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
				WartungDetailsView.this.uebernehmeDaten();
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof WartungBean){
					WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt WartungBean sein. Es sei denn, dass Objet ist neu(INSERT).
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
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldId() {
		if (jTextFieldId == null) {
			jTextFieldId = new JTextField();
			jTextFieldId.setPreferredSize(new Dimension(50, 20));
			jTextFieldId.setEditable(false);
		}
		return jTextFieldId;
	}

	/**
	 * This method initializes JDateChooserTerminSoll	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JDateChooser getJDateChooserTerminSoll() {
		if (JDateChooserTerminSoll == null) {
			JDateChooserTerminSoll = new JDateChooser(Konstanten.FORMAT_DATUM,false);
			JDateChooserTerminSoll.setLayout(new GridBagLayout());
			JDateChooserTerminSoll.setPreferredSize(new Dimension(100, 20));
		}
		return JDateChooserTerminSoll;
	}

	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
//		super.zeichneDich(modelKnotenBean, iModel);
		setzeHintergrund();
		if(modelKnotenBean!=null){
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.WARTUNG){
				getJComboBoxFehler().removeAllItems();
				setModelBean(modelKnotenBean);//merken
				WartungBean wartungBean = (WartungBean) modelKnotenBean.getIBean();
				getJTextFieldId().setText(wartungBean.getId().toString());
				getJTextFieldId().setEnabled(false);
				
				String s = wartungBean.getFk_baugruppe().getVaterBaugruppeNamenListe();
				if (s.compareTo(getJTextUebergeordneteBaugruppe().getText().toString())!=0){
					getJTextUebergeordneteBaugruppe().setText(wartungBean.getFk_baugruppe().getVaterBaugruppeNamenListe());
					getJTextUebergeordneteBaugruppe().setToolTipText(getJTextUebergeordneteBaugruppe().getText());
				}

				getJTextFieldBaugrupe().setText(wartungBean.getFk_baugruppe().toString());
				getJTextFieldWartungsArt().setText(wartungBean.getFk_wartungsart().toString());
				getJTextFieldWartungsTyp().setText(wartungBean.getFk_wartungstyp().toString());
				
				getJTextFieldBemerkung().removePropertyChangeListener("value", this);				
				getJTextFieldBemerkung().removeKeyListener(this);				
				getJTextFieldBemerkung().setValue(wartungBean.getBemerkung());
				getJTextFieldBemerkung().addKeyListener(this);
				getJTextFieldBemerkung().addPropertyChangeListener("value", this);				
				
				if (getJTextAreaBeschreibung().getText().compareTo(
						wartungBean.getBeschreibung().toString()
				)!=0){
//					getJTextAreaBeschreibung().removePropertyChangeListener("text", this);				
//					getJTextAreaBeschreibung().removeKeyListener(this);
					getJTextAreaBeschreibung().getDocument().removeDocumentListener(getDocumentListenerBeschreibung());
					getJTextAreaBeschreibung().setText(wartungBean.getBeschreibung().toString());
					getJTextAreaBeschreibung().getDocument().addDocumentListener(getDocumentListenerBeschreibung());
//					getJTextAreaBeschreibung().addKeyListener(this);
//					getJTextAreaBeschreibung().addPropertyChangeListener("text", this);				
				}
				
				
				getJTextFieldWartungsKatenID().removePropertyChangeListener("value", this);				
				getJTextFieldWartungsKatenID().removeKeyListener(this);				
				getJTextFieldWartungsKatenID().setValue(wartungBean.getKarteiid());
				getJTextFieldWartungsKatenID().addKeyListener(this);
				getJTextFieldWartungsKatenID().addPropertyChangeListener("value", this);
				
				getJDateChooserTerminSoll().removePropertyChangeListener("date", this);				
				getJDateChooserTerminSoll().removeKeyListener(this);				
				getJDateChooserTerminSoll().setDate(wartungBean.getTermin_soll());
				getJDateChooserTerminSoll().addKeyListener(this);
				getJDateChooserTerminSoll().addPropertyChangeListener("date", this);
				
				getJDateChooserTerminIst().removePropertyChangeListener("date", this);				
				getJDateChooserTerminIst().removeKeyListener(this);				
//				if (wartungBean.getTermin_ist().getTime()==Bean.getLeeresDatum().getTime()){
//					//leer
//					getJDateChooserTerminIst().setDate(new Date());
//				}else{
					//gefüllt
					getJDateChooserTerminIst().setDate(wartungBean.getTermin_ist());
//				}
				getJDateChooserTerminIst().addKeyListener(this);
				getJDateChooserTerminIst().addPropertyChangeListener("date", this);
				if (wartungBean.getFk_wartungsart().getIntervallFaehig() == WartungsArtIntervallFaehigEnum.JA){
					getJTextFieldInterval().setEnabled(true);
				}else{
					getJTextFieldInterval().setEnabled(false);
				}
				
				getJTextFieldInterval().removePropertyChangeListener("value", this);				
				getJTextFieldInterval().removeKeyListener(this);				
				getJTextFieldInterval().setValue(wartungBean.getIntervall());
				getJTextFieldInterval().addKeyListener(this);
				getJTextFieldInterval().addPropertyChangeListener("value", this);	
				
				
				getJComboBoxStatus().removeActionListener(getJComboBoxStatusActionListener());
				switch (wartungBean.getStatus()) {
				case OFFEN:
					getJComboBoxStatus().setSelectedIndex(0);	
					setEnabled(true);
					getJDateChooserTerminIst().setEnabled(false);
					getJComboBoxStatus().setEnabled(true);
					getJButtonKartenId().setEnabled(true);
					break;
				case ABGESCHLOSSEN:
					setEnabled(false);
					getJComboBoxStatus().setSelectedIndex(1);	
					getJComboBoxStatus().setEnabled(false);
					if (wartungBean.getBeanDBStatus()== BeanDBStatus.SELECT){
						//Nach dem Abspeichern kann das Datum nicht mehr geändert werden.
						getJDateChooserTerminIst().setEnabled(false);
					}else{
						//nur im Änderungsmödus kann das IST-Datum noch gesetzt werden.
						getJDateChooserTerminIst().setEnabled(true);
					}
					break;
				default:
					setEnabled(false);
					getJComboBoxStatus().setSelectedIndex(0);
					Log.log().severe("WRONG STATUS");
					getJComboBoxFehler().addItem(new Fehler("Falscher Status"));
					getJComboBoxStatus().setEnabled(true);
					break;
				}
				getJComboBoxStatus().addActionListener(getJComboBoxStatusActionListener());

				
//				getJTextFieldName().setText(wartungBean.getName());
//				getJTextFieldVorname().setText(wartungBean.getVorname());
//				getJTextFieldLoginName().setText(wartungBean.getLoginName());
//				getJTextFieldPasswort().setText(wartungBean.getPassword());
//				getJTextFieldEmail().setText(wartungBean.getEmail());
				
		//		//Erstellungsdatum wird angezeigt.
		//		getJDateChooserErstellungsDatum().setDate(wartungBean.getErstellungsDatum());
		//		getJDateChooserErstellungsDatum().setEnabled(false);
				
				//Fehler anzeigen.
//				wartungBean.pruefeEigeneDaten();
				for(int i=0; i<wartungBean.getFehlerList().size();i++){
					Fehler fehler = wartungBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				
				((AbstractTableModel)jTable.getModel()).fireTableDataChanged();
				((AbstractTableModel)jTableZugriffsrechte.getModel()).fireTableDataChanged();
				this.repaint();    //alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
//				System.out.println("zeichneDich() WartungDetailsView");
			}
		}
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
				private String[] columnNames = {"Mitarbeiter","Firma (Hersteller/Lieferant) ","Stunden"};

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
					if (modelKnotenBean.getIBean() instanceof WartungsMitarbeiterBean){
						WartungsMitarbeiterBean wartungsMitarbeiterBean = 
							(WartungsMitarbeiterBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								if (wartungsMitarbeiterBean!=null && wartungsMitarbeiterBean.getFk_benutzer()!=null){
									return wartungsMitarbeiterBean.getFk_benutzer();
								}else return "";
							}
							case 1 : {
								if (wartungsMitarbeiterBean!=null && wartungsMitarbeiterBean.getFk_herstellerlieferant()!=null){
									return wartungsMitarbeiterBean.getFk_herstellerlieferant().toString();
								}else return "";
							}
							case 2 : {
								if (wartungsMitarbeiterBean!=null){
									return wartungsMitarbeiterBean.getStunden().toString();
								}else return "";
							}
							case 999 : return wartungsMitarbeiterBean; 
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
						 int anzahlAbteilungen = 0;
						 for (int i=0;i<getModelBean().getKinderList().size();i++){
							 if (getModelBean().getKinderList().get(i) instanceof WartungsMitarbeiterModelKnotenBean){
								 anzahlAbteilungen++;
							 }
						 }
						 return anzahlAbteilungen;
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
				private String[] columnNames = {"KEG-Nr","Bezeichnung","Typ","Hersteller","Menge","Mengeneinheit"};

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
						 if (getModelBean().getKinderList().get(i) instanceof WartungsArtikelModelKnotenBean){
							 zugriffsrechtAbZeile = i;
						 }
					 }
					
					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(zugriffsrechtAbZeile+row);
					if (modelKnotenBean.getIBean() instanceof WartungsArtikelBean){
						WartungsArtikelBean wartungsArtikelBean = 
							(WartungsArtikelBean)modelKnotenBean.getIBean();
							switch(column){
							case 0 : {
								if (wartungsArtikelBean!=null && wartungsArtikelBean.getFk_artikel()!=null){
									return wartungsArtikelBean.getFk_artikel().getKeg_nr().toString();
								}else return "";
							}
							case 1 : {
								if (wartungsArtikelBean!=null && wartungsArtikelBean.getFk_artikel()!=null){
									return wartungsArtikelBean.getFk_artikel().getBezeichnung().toString();
								}else return "";
							}
							case 2 : {
								if (wartungsArtikelBean!=null && wartungsArtikelBean.getFk_artikel()!=null){
									return wartungsArtikelBean.getFk_artikel().getTyp().toString();
								}else return "";
							}
							case 3 : { //Hersteller
								if (wartungsArtikelBean!=null && wartungsArtikelBean.getFk_artikel()!=null){
									return wartungsArtikelBean.getFk_artikel().getHersteller().getName();
								}else return "";
							}
							case 4 : {
								if (wartungsArtikelBean!=null && wartungsArtikelBean.getFk_artikel()!=null){
									return wartungsArtikelBean.getMenge().toString();
								}else return "";
							}
							case 5 : {
								if (wartungsArtikelBean!=null && wartungsArtikelBean.getFk_artikel()!=null){
									return wartungsArtikelBean.getFk_artikel().getMengenEinheitBean().toString();
								}else return "";
							}
							case 999 : return wartungsArtikelBean; 
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
							 if (getModelBean().getKinderList().get(i) instanceof WartungsArtikelModelKnotenBean){
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
	public void setModelBean(ModelKnotenBean wartungModelBean) {
		this.modelKnotenBean = wartungModelBean;
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
							WartungsMitarbeiterBean bean = 
								(WartungsMitarbeiterBean) getJTable().
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
			jPanelOben.setPreferredSize(new Dimension(1, 1));
		}
		return jPanelOben;
	}

	@Override
	public void setStandardFocusPosition() {
		getJTextFieldBemerkung().requestFocus();
	}

	/**
	 * enable = benutzbar
	 * editable = beschreibbar
	 */
	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		
		getJButtonNeu().setEnabled(enabled);
		getJButtonNeuZugriffsrecht().setEnabled(enabled);
		
		getJButtonLoeschen().setEnabled(enabled);
		getJButtonLoeschenZugriffsrecht().setEnabled(enabled);
		
		getJButtonMatchCodeBaugruppe().setEnabled(enabled);
		getJButtonMatchCodeWartungsArt().setEnabled(enabled);
		getJButtonMatchCodeWartungsTyp().setEnabled(enabled);
		getJButtonKartenId().setEnabled(enabled);
		
		getJTextAreaBeschreibung().setEditable(enabled);
		getJTextFieldBemerkung().setEditable(enabled);
		getJTextFieldInterval().setEditable(enabled);
		getJTextFieldWartungsKatenID().setEditable(enabled);
		
		getJDateChooserTerminIst().setEnabled(enabled);
		getJDateChooserTerminSoll().setEnabled(enabled);
		
////		getJButtonMatchCodeEmail().setEnabled(enabled);
//		getJButtonNeu().setEnabled(enabled);
//		getJButtonLoeschen().setEnabled(enabled);
////		getJTextFieldEmail().setEditable(enabled);
////		getJButtonMatchCodeEmail().setEnabled(enabled);
//		getJTextFieldBemerkung().setEditable(enabled);
//		getJTextFieldInterval().setEditable(enabled);
////		getJTextFieldPasswort().setEditable(enabled);
//		getJTextFieldWartungsKatenID().setEditable(enabled);
//		getJDateChooserTerminSoll().setEnabled(enabled);
//		getJTable().setEnabled(enabled);
	}

	//setzen der Email aus dem Matchcode-Fenster
	public void setMatchCodeEmail(EmailBean emailBean,String email) {
		if (email!=null){
//			WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
//			wartungBean.setEmail(email);
		}
		if(emailBean!=null){
//			WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
//			wartungBean.setEmail(emailBean.getEmail());
//			getJTextFieldEmail().setText(wartungBean.getEmail());
		}
	}

	/**
	 * This method initializes jTextFieldInterval	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldInterval() {
		if (jTextFieldInterval == null) {
			jTextFieldInterval = new JFormattedTextField(new LagerEmptyNumberFormatter(0,999));
			jTextFieldInterval.setInputVerifier(LagerFormate.getInputVerifier());
//			jTextFieldInterval.addPropertyChangeListener("value", this);
		}
		return jTextFieldInterval;
	}

	/**
	 * This method initializes jTextFieldWartungsKatenID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldWartungsKatenID() {
		if (jTextFieldWartungsKatenID == null) {
			jTextFieldWartungsKatenID = new JFormattedTextField(new LagerEmptyNumberFormatter(0,9999));
			jTextFieldWartungsKatenID.setInputVerifier(LagerFormate.getInputVerifier());
//			jTextFieldWartungsKatenID.addPropertyChangeListener("value", this);
		}
		return jTextFieldWartungsKatenID;
	}

	/**
	 * This method initializes jTextFieldBemerkung	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextFieldBemerkung() {
		if (jTextFieldBemerkung == null) {	
			jTextFieldBemerkung = new JFormattedTextField(new LagerStringFormatter(0,100));
			jTextFieldBemerkung.setInputVerifier(LagerFormate.getInputVerifier());
		}
		return jTextFieldBemerkung;
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
						getController().erstelleNeuenSatz(ModelKnotenTyp.WARTUNG_MITARBEITER);
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


	private void leseAusgetJTextFieldEmail() {
//		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldEmail().getValue() != null) {
//			wert = getJTextFieldEmail().getValue().toString();
////			wartungBean.setEmail(wert);
//		}
	}

	private void leseAusgetJTextFieldPasswort() {
//		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldPasswort().getValue() != null) {
//			wert = getJTextFieldPasswort().getValue().toString();
//			wartungBean.setPassword(wert);
//		}
	}

	private void leseAusgetJTextFieldLoginName() {
		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
		Integer wert;
		if (((JFormattedTextField) getJTextFieldInterval())
				.getValue() != null) {
			JFormattedTextField jFormattedTextField = (JFormattedTextField) getJTextFieldInterval();
			Number number = (Number) jFormattedTextField.getValue();
			wert = number.intValue();
			wartungBean.setIntervall(wert);
		}
//		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldInterval().getValue() != null) {
//			wert = getJTextFieldInterval().getValue().toString();
//			wartungBean.setLoginName(wert);
//		}
	}

	private void leseAusgetJTextWartungsKatenID() {
		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
		Integer wert;
		if (((JFormattedTextField) getJTextFieldWartungsKatenID())
				.getValue() != null) {
			JFormattedTextField jFormattedTextField = (JFormattedTextField) getJTextFieldWartungsKatenID();
			Number number = (Number) jFormattedTextField.getValue();
			wert = number.intValue();
			wartungBean.setKarteiid(wert);
		}
//		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
//		String wert ;
//		if (getJTextFieldWartungsKatenID().getValue() != null) {
//			wert = getJTextFieldWartungsKatenID().getValue().toString();
//			wartungBean.setKarteiid(wert);
//		}	
	}

	private void leseAusgetJTextFieldName()  {
		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
		String wert ;
		if (getJTextFieldBemerkung().getValue() != null) {
			wert = getJTextFieldBemerkung().getValue().toString();
			wartungBean.setBemerkung(wert);
		}
	}
	

	private void leseAusgetJTextAreaBeschreibung() {
		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
		String wert ;
		if (getJTextAreaBeschreibung().getText() != null) {
			wert = getJTextAreaBeschreibung().getText();
			wartungBean.setBeschreibung(wert);
		}
	}	
	
	private void leseAusgetJDateChooserTerminSoll() {
		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
		Date wert ;
		if (getJDateChooserTerminSoll().getDate() != null) {
			wert = getJDateChooserTerminSoll().getDate();
			wartungBean.setTermin_soll(new java.sql.Date(wert.getTime()));
		}
	}

	private void leseAusgetJDateChooserTerminIst() {
		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
		Date wert ;
		if (getJDateChooserTerminIst().getDate() != null) {
			wert = getJDateChooserTerminIst().getDate();
			wartungBean.setTermin_ist(new java.sql.Date(wert.getTime()));
		}
	}
	
	private void leseAusgetJComboBoxStatus() {
		WartungBean wartungBean = (WartungBean)getModelBean().getIBean();
		StatusEnum status ;
		if (getJComboBoxStatus().getSelectedItem() != null) {
			status = (StatusEnum) getJComboBoxStatus().getSelectedItem();
			wartungBean.setStatus(status);
		}
	}

	
	
	
	/**
	 * Diese Funktion wird immer dann aufgerufen, falls in einem JFormattedField der Inhalt geändert wird.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJTextFieldBemerkung()){
			leseAusgetJTextFieldName();
		}
		if (source==getJTextAreaBeschreibung()){
			leseAusgetJTextAreaBeschreibung();
		}
		if (source==getJTextFieldWartungsKatenID()){
			leseAusgetJTextWartungsKatenID();
		}
		if (source==getJTextFieldInterval()){
			leseAusgetJTextFieldLoginName();
		}
		if (source==getJDateChooserTerminSoll()){
			leseAusgetJDateChooserTerminSoll();
		}
		if (source==getJDateChooserTerminIst()){
			leseAusgetJDateChooserTerminIst();
		}
		if (source==getJComboBoxStatus()){
			leseAusgetJComboBoxStatus();
		}
		
		getController().ausgewaehlterKnotenIstGeandert();
	}
	


	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {
			if (!getJTextFieldBemerkung().getInputVerifier().shouldYieldFocus(
					getJTextFieldBemerkung())) {
				errors.add(new Fehler(140));
			} else {
				leseAusgetJTextFieldName();
			}
			
			
//			if (!getJTextAreaBeschreibung().getInputVerifier().shouldYieldFocus(
//					getJTextAreaBeschreibung())) {
//				errors.add(new Fehler(44));
//			} else {
//				leseAusgetJTextAreaBeschreibung();
//			}
			
			
			if (!getJTextFieldWartungsKatenID().getInputVerifier().shouldYieldFocus(
					getJTextFieldWartungsKatenID())) {
				errors.add(new Fehler(139));
			} else {
				leseAusgetJTextWartungsKatenID();
			}
			
//			if (!getJDateChooserTerminSoll().getInputVerifier().shouldYieldFocus(
//					getJDateChooserTerminSoll())) {
//				errors.add(new Fehler(45));
//			} else {
//				leseAusgetJDateChooserTerminSoll();
//			}
//
//			if (!getJDateChooserTerminIst().getInputVerifier().shouldYieldFocus(
//					getJDateChooserTerminIst())) {
//				errors.add(new Fehler(45));
//			} else {
//				leseAusgetJDateChooserTerminIst();
//			}
			
			if (!getJTextFieldInterval().getInputVerifier().shouldYieldFocus(
					getJTextFieldInterval())) {
				errors.add(new Fehler(129));
			} else {
				leseAusgetJTextFieldLoginName();
			}
			
//			if (!getJComboBoxStatus().getInputVerifier().shouldYieldFocus(
//					getJComboBoxStatus())) {
//				errors.add(new Fehler(46));
//			} else {
//				leseAusgetJComboBoxStatus();
//			}
			
			
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
	 * This method initializes jToolBar2	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar2() {
		if (jToolBar2 == null) {
			jToolBar2 = new JToolBar();
			jToolBar2.setFloatable(false);
			jToolBar2.add(getJButtonNeuZugriffsrecht());
			jToolBar2.add(getJButtonLoeschenZugriffsrecht());
		}
		return jToolBar2;
	}

	/**
	 * This method initializes jButtonNeuZugriffsrecht	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNeuZugriffsrecht() {
		if (jButtonNeuZugriffsrecht == null) {
			jButtonNeuZugriffsrecht = new JButton();
			jButtonNeuZugriffsrecht.setText("Neu");
			jButtonNeuZugriffsrecht.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						getController().erstelleNeuenSatz(ModelKnotenTyp.WARTUNG_ARTIKEL);
					}
				}
			});
			
		}
		return jButtonNeuZugriffsrecht;
	}

	/**
	 * This method initializes jButtonLoeschenZugriffsrecht	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonLoeschenZugriffsrecht() {
		if (jButtonLoeschenZugriffsrecht == null) {
			jButtonLoeschenZugriffsrecht = new JButton();
			jButtonLoeschenZugriffsrecht.setText("Löschen");
			jButtonLoeschenZugriffsrecht.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (pruefeFehler()){
						if (getJTableZugriffsrechte().getSelectedRow()>=0){
							WartungsArtikelBean bean = 
								(WartungsArtikelBean) getJTableZugriffsrechte().
										getModel().
											getValueAt(getJTableZugriffsrechte().getSelectedRow(), 999);
							getController().loeschePosition(bean);
						}else{
							getJComboBoxFehler().addItem(new Fehler(27));
						}
					}

				}
			});
			
			
		}
		return jButtonLoeschenZugriffsrecht;
	}

	/**
	 * This method initializes jScrollPaneZugriffsrechte	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneZugriffsrechte() {
		if (jScrollPaneZugriffsrechte == null) {
			jScrollPaneZugriffsrechte = new JScrollPane();
			jScrollPaneZugriffsrechte.setViewportView(getJTableZugriffsrechte());
		}
		return jScrollPaneZugriffsrechte;
	}

	/**
	 * This method initializes jTableZugriffsrechte	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTableZugriffsrechte() {
		if (jTableZugriffsrechte == null) {
			jTableZugriffsrechte = new JTable();
			jTableZugriffsrechte.setModel(getAbstractTableModelZugriffrecht());
			jTableZugriffsrechte.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
			jTableZugriffsrechte.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if(e.getClickCount()==2){
						//ausgewählte Bean wird ermittelt. 
						int aktuelleZeile = jTableZugriffsrechte.getSelectedRow(); //aktuelleTabelle
						int anzahlMitarbeiter = jTable.getModel().getRowCount(); // vorige Tabelle
						if (getModelBean() != null) {
							ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(
													anzahlMitarbeiter + aktuelleZeile);
							getController().selectKnoten(modelKnotenBean);
						}
					};
				}
			});
			
		}
		return jTableZugriffsrechte;
	}

	/**
	 * This method initializes jTextFieldBaugrupe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldBaugrupe() {
		if (jTextFieldBaugrupe == null) {
			jTextFieldBaugrupe = new JTextField();
			jTextFieldBaugrupe.setPreferredSize(new Dimension(100, 20));
			jTextFieldBaugrupe.setEditable(false);
		}
		return jTextFieldBaugrupe;
	}

	/**
	 * This method initializes jButtonMatchCodeBaugruppe	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeBaugruppe() {
		if (jButtonMatchCodeBaugruppe == null) {
			jButtonMatchCodeBaugruppe = new JButton();
			jButtonMatchCodeBaugruppe.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeBaugruppe.setText("...");
			jButtonMatchCodeBaugruppe
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
//								if (pruefeFehler()){
									((WartungDetailsController)getController()).holeBaugruppe();
//								}
							} catch (SQLException e1) {
								getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
								e1.printStackTrace();
							} catch (LagerException e1) {
								getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
								e1.printStackTrace();
							}
						}
					});
		}
		return jButtonMatchCodeBaugruppe;
	}

	/**
	 * This method initializes jTextFieldWartungsArt	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldWartungsArt() {
		if (jTextFieldWartungsArt == null) {
			jTextFieldWartungsArt = new JTextField();
			jTextFieldWartungsArt.setPreferredSize(new Dimension(100, 20));
			jTextFieldWartungsArt.setEditable(false);
		}
		return jTextFieldWartungsArt;
	}

	/**
	 * This method initializes jTextFieldWartungsTyp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldWartungsTyp() {
		if (jTextFieldWartungsTyp == null) {
			jTextFieldWartungsTyp = new JTextField();
			jTextFieldWartungsTyp.setPreferredSize(new Dimension(100, 20));
			jTextFieldWartungsTyp.setEditable(false);
		}
		return jTextFieldWartungsTyp;
	}

	/**
	 * This method initializes jButtonMatchCodeWartungsArt	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeWartungsArt() {
		if (jButtonMatchCodeWartungsArt == null) {
			jButtonMatchCodeWartungsArt = new JButton();
			jButtonMatchCodeWartungsArt.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeWartungsArt.setText("...");
			jButtonMatchCodeWartungsArt
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
//						if (pruefeFehler()){
							((WartungDetailsController)getController()).holeWartungsArt();
//						}
					} catch (SQLException e1) {
						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
						e1.printStackTrace();
					} catch (LagerException e1) {
						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
						e1.printStackTrace();
					}
				}
			});
			
		}
		return jButtonMatchCodeWartungsArt;
	}

	/**
	 * This method initializes jButtonMatchCodeWartungsTyp	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeWartungsTyp() {
		if (jButtonMatchCodeWartungsTyp == null) {
			jButtonMatchCodeWartungsTyp = new JButton();
			jButtonMatchCodeWartungsTyp.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeWartungsTyp.setText("...");
			jButtonMatchCodeWartungsTyp
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
//								if (pruefeFehler()){
									((WartungDetailsController)getController()).holeWartungsTyp();
//								}
							} catch (SQLException e1) {
								getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
								e1.printStackTrace();
							} catch (LagerException e1) {
								getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
								e1.printStackTrace();
							}
						}
					});
		}
		return jButtonMatchCodeWartungsTyp;
	}

	/**
	 * This method initializes JDateChooserTerminIst	
	 * 	
	 * @return com.toedter.calendar.JDateChooser	
	 */
	private JDateChooser getJDateChooserTerminIst() {
		if (JDateChooserTerminIst == null) {
			JDateChooserTerminIst = new JDateChooser(Konstanten.FORMAT_DATUM, false);
			JDateChooserTerminIst.setLayout(new GridBagLayout());
			JDateChooserTerminIst.setPreferredSize(new Dimension(100, 20));
		}
		return JDateChooserTerminIst;
	}

	/**
	 * This method initializes jComboBoxStatus	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxStatus() {
		if (jComboBoxStatus == null) {
			WartungBean.StatusEnum[] stautsList = 
				new WartungBean.StatusEnum[]{
					WartungBean.StatusEnum.OFFEN,
					WartungBean.StatusEnum.ABGESCHLOSSEN
					};			
			jComboBoxStatus = new JComboBox(stautsList);
			jComboBoxStatus.setSelectedIndex(0);
//			jComboBoxStatus.addActionListener(getComboBoxStatusActionListener());
		}
		return jComboBoxStatus;
	}

//	private ActionListener getComboBoxStatusActionListener() {
//		return new java.awt.event.ActionListener() {
//			public void actionPerformed(java.awt.event.ActionEvent e) {
//				((WartungDetailsController)getController()).changeStatus((WartungBean.StatusEnum)jComboBoxStatus.getSelectedItem());
//			}
//		};
//	}

	private ActionListener getJComboBoxStatusActionListener() {
		if (jComboBoxStatusActionListener==null){
			jComboBoxStatusActionListener = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//					//Oberflächen - Änderung (muss teoretisch nicht sein. Auch praktisch nicht)
//					WartungDetailsView.this.propertyChange(
//							new PropertyChangeEvent(getJComboBoxStatus(), null, null, null)
//					);
					//Änderung im Objekt (setzten des Status)
					((WartungDetailsController)getController()).changeStatus((WartungBean.StatusEnum)jComboBoxStatus.getSelectedItem());					
				}
			};
		}
		return jComboBoxStatusActionListener;
	}

	/**
	 * This method initializes jTextAreaBeschreibung	
	 * http://download.oracle.com/javase/tutorial/uiswing/components/text.html
	 * http://download.oracle.com/javase/7/docs/api/javax/swing/JTextArea.html	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaBeschreibung() {
		if (jTextAreaBeschreibung == null) {
			jTextAreaBeschreibung = new JTextArea();
			jTextAreaBeschreibung.setPreferredSize(new Dimension(0, 120));
			jTextAreaBeschreibung.setLineWrap(true);
			jTextAreaBeschreibung.setWrapStyleWord(true);
			//2012.08.02 Behebung des Fehlers von Eugen
			jTextAreaBeschreibung.getDocument().addDocumentListener(getDocumentListenerBeschreibung());
		}
		return jTextAreaBeschreibung;
	}
	
	private DocumentListener getDocumentListenerBeschreibung(){
		if (documentListenerBeschreibung==null){
			documentListenerBeschreibung = new DocumentListener() {
				@Override
				public void removeUpdate(DocumentEvent e) {
					WartungDetailsView.this.propertyChange(new PropertyChangeEvent(WartungDetailsView.this.getJTextAreaBeschreibung(),null, null, null));
				}
				
				@Override
				public void insertUpdate(DocumentEvent e) {
					WartungDetailsView.this.propertyChange(new PropertyChangeEvent(WartungDetailsView.this.getJTextAreaBeschreibung(),null, null, null));
				}
				
				@Override
				public void changedUpdate(DocumentEvent e) {
					WartungDetailsView.this.propertyChange(new PropertyChangeEvent(WartungDetailsView.this.getJTextAreaBeschreibung(),null, null, null));
				}
			}; 
		}
		return documentListenerBeschreibung;
	}

	/**
	 * This method initializes jButtonKartenId	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonKartenId() {
		if (jButtonKartenId == null) {
			jButtonKartenId = new JButton();
			jButtonKartenId.setText("<<");
			jButtonKartenId.setToolTipText("Die nächste freie Karten-ID wird ermittelt");
			jButtonKartenId.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						((WartungDetailsController)getController()).generiereWartungsKartenId();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (LagerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return jButtonKartenId;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Mitarbeiter", BenutzerModelKnotenBean.getEasyIcon(), getJPanelMitarbeiter(), null);
			jTabbedPane.addTab("Artikel", ArtikelModelKnotenBean.getEasyIcon(), getJPanelArtikel(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes jPanelMitarbeiter	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelMitarbeiter() {
		if (jPanelMitarbeiter == null) {
			GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
			gridBagConstraints20.anchor = GridBagConstraints.WEST;
			gridBagConstraints20.gridx = 0;
			gridBagConstraints20.gridy = 1;
			gridBagConstraints20.weightx = 1.0;
			gridBagConstraints20.weighty = 0.0D;
			gridBagConstraints20.gridheight = 1;
			gridBagConstraints20.fill = GridBagConstraints.VERTICAL;
			GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
			gridBagConstraints17.fill = GridBagConstraints.BOTH;
			gridBagConstraints17.gridx = 0;
			gridBagConstraints17.gridy = 2;
			gridBagConstraints17.weightx = 1.0;
			gridBagConstraints17.weighty = 1.0;
			gridBagConstraints17.gridwidth = 1;
			jPanelMitarbeiter = new JPanel();
			jPanelMitarbeiter.setLayout(new GridBagLayout());
			jPanelMitarbeiter.add(getJScrollPane(), gridBagConstraints17);
			jPanelMitarbeiter.add(getJToolBar(), gridBagConstraints20);
		}
		return jPanelMitarbeiter;
	}

	/**
	 * This method initializes jPanelArtikel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelArtikel() {
		if (jPanelArtikel == null) {
			GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
			gridBagConstraints19.anchor = GridBagConstraints.WEST;
			gridBagConstraints19.gridx = 0;
			gridBagConstraints19.gridy = 1;
			gridBagConstraints19.weightx = 1.0;
			gridBagConstraints19.fill = GridBagConstraints.VERTICAL;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.gridwidth = 3;
			jPanelArtikel = new JPanel();
			jPanelArtikel.setLayout(new GridBagLayout());
			jPanelArtikel.add(getJScrollPaneZugriffsrechte(), gridBagConstraints4);
			jPanelArtikel.add(getJToolBar2(), gridBagConstraints19);
		}
		return jPanelArtikel;
	}

	public void setBaugruppe(BaugruppeBean baugruppeBean) {
		((WartungBean) getModelBean().getIBean())
			.setFk_baugruppe(baugruppeBean);
		getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setWartungsArt(WartungsArtBean wartungsArtBean) {
		((WartungBean) getModelBean().getIBean())
		.setFk_wartungsart(wartungsArtBean);
	getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setWartungsTyp(WartungsTypBean wartungsTypBean) {
		((WartungBean) getModelBean().getIBean())
		.setFk_wartungstyp(wartungsTypBean);
		getController().ausgewaehlterKnotenIstGeandert();
	}

	/**
	 * This method initializes jTextUebergeordneteBaugruppe	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextUebergeordneteBaugruppe() {
		if (jTextUebergeordneteBaugruppe == null) {
			jTextUebergeordneteBaugruppe = new JTextField();
			jTextUebergeordneteBaugruppe.setPreferredSize(new Dimension(50, 20));
			jTextUebergeordneteBaugruppe.setEditable(false);
		}
		return jTextUebergeordneteBaugruppe;
	}

	/**
	 * This method initializes jScrollPaneBeschreibung	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPaneBeschreibung() {
		if (jScrollPaneBeschreibung == null) {
			jScrollPaneBeschreibung = new JScrollPane();
			jScrollPaneBeschreibung.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			jScrollPaneBeschreibung.setViewportView(getJTextAreaBeschreibung());
		}
		return jScrollPaneBeschreibung;
	}


	
	
}
