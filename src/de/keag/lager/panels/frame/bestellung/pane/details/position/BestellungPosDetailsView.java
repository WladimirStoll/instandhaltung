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
package de.keag.lager.panels.frame.bestellung.pane.details.position;

import java.awt.GridBagLayout;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IDetailsBeobachter;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.View;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.HerstellerLieferantLagerException;
import de.keag.lager.core.fehler.KostenstelleBeanDbLagerException;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.MengenEinheitBeanDbLagerException;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.bestellung.BestellungStatus;
import de.keag.lager.panels.frame.bestellung.BestellungBean;
import de.keag.lager.panels.frame.bestellung.BestellungPosBean;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.lieferantenBestellnummer.LieferantenBestellnummerBean;
import de.keag.lager.panels.frame.mengenEinheit.MengenEinheitBean;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import com.jgoodies.validation.formatter.EmptyNumberFormatter;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;


public class BestellungPosDetailsView extends DetailsView implements IDetailsBeobachter, PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private JLabel jLabelLeer = null;
	private JTextField jTextFieldId = null;
	private JTextField jTextKostenstelle = null;
	private JTextField jTextBestellnummerKEG = null;
	private JTextField jTextBestellnummerLieferant = null;
	private JTextField jTextArtikelBezeichnung = null;
	private JTextField jTextArtikelTyp = null;
	private JTextField jTextArtikelHersteller = null;
	private JTextField jTextKatalogseite = null;
	private JTextField jTextKatalog = null;
	private JFormattedTextField jTextBestellmenge = null;
	private JTextField jTextMengeneinheit = null;
	private JFormattedTextField jTextLiefertermin = null;
	private JLabel jLabelKostenstelle = null;
	private JLabel jLabelBestellnummerKEG = null;
	private JLabel jLabelBestellnummerLieferant = null;
	private JLabel jLabelArtikelBezeichnung = null;
	private JLabel jLabelArtikelTyp = null;
	private JLabel jLabelArtikelHersteller = null;
	private JLabel jLabelKatalogseite = null;
	private JLabel jLabelKatalog = null;
	private JLabel jLabelBestellmenge = null;
	private JLabel jLabelMengeneinheit = null;
	private JLabel jLabelLiefertermin = null;
	private JLabel jLabelBestellanforderungspositionID = null;
	private JLabel jLabelLeer2 = null;
	private JButton jButtonMatchcodeKostenstelle = null;
	private JButton jButtonMatchcodeArtikel = null;
//	private ModelKnotenBean baPosModelBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JPanel jPanelOben = null;
	private JPanel jPanelUnten = null;
	private JButton jButtonMatchcodeLieferantenBestellnummer = null;
	private JButton jButtonMatchcodeMengeneinheit = null;
	/**
	 * This is the default constructor
	 * @param baPosDetailsController 
	 */
	public BestellungPosDetailsView(BestellungPosDetailsController baPosDetailsController) {
		super();
		setController(baPosDetailsController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		gridBagConstraints19.gridx = 2;
		gridBagConstraints19.anchor = GridBagConstraints.WEST;
		gridBagConstraints19.gridy = 16;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 2;
		gridBagConstraints12.anchor = GridBagConstraints.WEST;
		gridBagConstraints12.gridy = 6;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.weighty = 150.0D;
		gridBagConstraints3.weightx = 1.0D;
		gridBagConstraints3.gridy = 19;
		GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
		gridBagConstraints28.gridx = 0;
		gridBagConstraints28.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints28.weightx = 1.0D;
		gridBagConstraints28.weighty = 150.0D;
		gridBagConstraints28.gridy = 0;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 25;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.gridwidth = 3;
		gridBagConstraints13.weighty = 1.0D;
		gridBagConstraints13.gridx = 0;
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.gridx = 2;
		gridBagConstraints14.anchor = GridBagConstraints.WEST;
		gridBagConstraints14.gridy = 5;
		GridBagConstraints gridBagConstraints39 = new GridBagConstraints();
		gridBagConstraints39.gridx = 2;
		gridBagConstraints39.anchor = GridBagConstraints.WEST;
		gridBagConstraints39.weightx = 1.0D;
		gridBagConstraints39.gridy = 4;
		GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
		gridBagConstraints37.gridx = 0;
		gridBagConstraints37.anchor = GridBagConstraints.EAST;
		gridBagConstraints37.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints37.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints37.weighty = 0.0D;
		gridBagConstraints37.gridy = 18;
		jLabelLeer2 = new JLabel();
		jLabelLeer2.setText("              ");
		GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
		gridBagConstraints35.gridx = 0;
		gridBagConstraints35.anchor = GridBagConstraints.EAST;
		gridBagConstraints35.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints35.weightx = 1.0D;
		gridBagConstraints35.weighty = 0.0D;
		gridBagConstraints35.gridy = 1;
		jLabelBestellanforderungspositionID = new JLabel();
		jLabelBestellanforderungspositionID.setText("Bestellanforderungsposition ID");
		jLabelBestellanforderungspositionID.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
		gridBagConstraints27.gridx = 0;
		gridBagConstraints27.anchor = GridBagConstraints.EAST;
		gridBagConstraints27.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints27.gridy = 17;
		jLabelLiefertermin = new JLabel();
		jLabelLiefertermin.setText("Liefertermin (KW)");
		jLabelLiefertermin.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
		GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
		gridBagConstraints26.gridx = 0;
		gridBagConstraints26.anchor = GridBagConstraints.EAST;
		gridBagConstraints26.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints26.gridy = 16;
		jLabelMengeneinheit = new JLabel();
		jLabelMengeneinheit.setText("Mengeneinheit");
		GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
		gridBagConstraints25.gridx = 0;
		gridBagConstraints25.anchor = GridBagConstraints.EAST;
		gridBagConstraints25.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints25.gridy = 15;
		jLabelBestellmenge = new JLabel();
		jLabelBestellmenge.setText("Bestellmenge");
		GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
		gridBagConstraints24.gridx = 0;
		gridBagConstraints24.anchor = GridBagConstraints.EAST;
		gridBagConstraints24.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints24.gridy = 7;
		jLabelKatalog = new JLabel();
		jLabelKatalog.setText("Katalog");
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.gridx = 0;
		gridBagConstraints23.anchor = GridBagConstraints.EAST;
		gridBagConstraints23.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints23.gridy = 8;
		jLabelKatalogseite = new JLabel();
		jLabelKatalogseite.setText("Katalogseite");
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.gridx = 0;
		gridBagConstraints22.anchor = GridBagConstraints.EAST;
		gridBagConstraints22.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints22.gridy = 11;
		jLabelArtikelHersteller = new JLabel();
		jLabelArtikelHersteller.setText("ArtikelHersteller");
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 0;
		gridBagConstraints21.anchor = GridBagConstraints.EAST;
		gridBagConstraints21.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints21.gridy = 10;
		jLabelArtikelTyp = new JLabel();
		jLabelArtikelTyp.setText("ArtikelTyp");
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.gridx = 0;
		gridBagConstraints18.anchor = GridBagConstraints.EAST;
		gridBagConstraints18.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints18.gridy = 9;
		jLabelArtikelBezeichnung = new JLabel();
		jLabelArtikelBezeichnung.setText("ArtikelBezeichnung");
		GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		gridBagConstraints17.gridx = 0;
		gridBagConstraints17.anchor = GridBagConstraints.EAST;
		gridBagConstraints17.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints17.gridy = 6;
		jLabelBestellnummerLieferant = new JLabel();
		jLabelBestellnummerLieferant.setText("Bestellnummer Lieferant");
		GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
		gridBagConstraints16.gridx = 0;
		gridBagConstraints16.anchor = GridBagConstraints.EAST;
		gridBagConstraints16.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints16.gridy = 5;
		jLabelBestellnummerKEG = new JLabel();
		jLabelBestellnummerKEG.setText("Bestellnummer KEG");
		GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
		gridBagConstraints15.gridx = 0;
		gridBagConstraints15.anchor = GridBagConstraints.EAST;
		gridBagConstraints15.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints15.gridy = 4;
		jLabelKostenstelle = new JLabel();
		jLabelKostenstelle.setText("Kostenstelle");
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints111.gridy = 17;
		gridBagConstraints111.weightx = 1.0;
		gridBagConstraints111.anchor = GridBagConstraints.WEST;
		gridBagConstraints111.gridx = 1;
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints10.gridy = 16;
		gridBagConstraints10.weightx = 1.0;
		gridBagConstraints10.anchor = GridBagConstraints.WEST;
		gridBagConstraints10.gridx = 1;
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints9.gridy = 15;
		gridBagConstraints9.weightx = 1.0;
		gridBagConstraints9.anchor = GridBagConstraints.WEST;
		gridBagConstraints9.gridx = 1;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints8.gridy = 7;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.anchor = GridBagConstraints.WEST;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints7.gridy = 8;
		gridBagConstraints7.weightx = 1.0;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.gridx = 1;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints6.gridy = 11;
		gridBagConstraints6.weightx = 1.0;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints5.gridy = 10;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.anchor = GridBagConstraints.WEST;
		gridBagConstraints5.gridx = 1;
		GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
		gridBagConstraints41.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints41.gridy = 9;
		gridBagConstraints41.weightx = 1.0;
		gridBagConstraints41.anchor = GridBagConstraints.WEST;
		gridBagConstraints41.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridy = 6;
		gridBagConstraints31.weightx = 1.0;
		gridBagConstraints31.anchor = GridBagConstraints.WEST;
		gridBagConstraints31.gridx = 1;
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints2.gridy = 5;
		gridBagConstraints2.weightx = 1.0;
		gridBagConstraints2.anchor = GridBagConstraints.WEST;
		gridBagConstraints2.gridx = 1;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints11.gridy = 4;
		gridBagConstraints11.weightx = 1.0;
		gridBagConstraints11.anchor = GridBagConstraints.WEST;
		gridBagConstraints11.gridx = 1;
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints1.gridy = 1;
		gridBagConstraints1.weightx = 2.0D;
		gridBagConstraints1.anchor = GridBagConstraints.WEST;
		gridBagConstraints1.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 2;
		jLabelLeer = new JLabel();
		jLabelLeer.setText("                ");
		jLabelLeer.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
		this.setSize(483, 352);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJTextFieldId(), gridBagConstraints1);
		this.add(getJTextKostenstelle(), gridBagConstraints11);
		this.add(getJTextBestellnummerKEG(), gridBagConstraints2);
		this.add(getJTextBestellnummerLieferant(), gridBagConstraints31);
		this.add(getJTextArtikelBezeichnung(), gridBagConstraints41);
		this.add(getJTextArtikelTyp(), gridBagConstraints5);
		this.add(getJTextArtikelHersteller(), gridBagConstraints6);
		this.add(getJTextKatalogseite(), gridBagConstraints7);
		this.add(getJTextKatalog(), gridBagConstraints8);
		this.add(getJTextBestellmenge(), gridBagConstraints9);
		this.add(getJTextMengeneinheit(), gridBagConstraints10);
		this.add(getJTextLiefertermin(), gridBagConstraints111);
		this.add(jLabelBestellanforderungspositionID, gridBagConstraints35);
		this.add(jLabelLeer, gridBagConstraints);
		this.add(jLabelKostenstelle, gridBagConstraints15);
		this.add(jLabelBestellnummerKEG, gridBagConstraints16);
		this.add(jLabelBestellnummerLieferant, gridBagConstraints17);
		this.add(jLabelArtikelBezeichnung, gridBagConstraints18);
		this.add(jLabelArtikelTyp, gridBagConstraints21);
		this.add(jLabelArtikelHersteller, gridBagConstraints22);
		this.add(jLabelKatalogseite, gridBagConstraints23);
		this.add(jLabelKatalog, gridBagConstraints24);
		this.add(jLabelBestellmenge, gridBagConstraints25);
		this.add(jLabelMengeneinheit, gridBagConstraints26);
		this.add(jLabelLiefertermin, gridBagConstraints27);
		this.add(jLabelLeer2, gridBagConstraints37);
		this.add(getJButtonMatchcodeKostenstelle(), gridBagConstraints39);
		this.add(getJButtonMatchcodeArtikel(), gridBagConstraints14);
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(getJPanelOben(), gridBagConstraints28);
		this.add(getJPanelUnten(), gridBagConstraints3);
		this.add(getJButtonMatchcodeLieferantenBestellnummer(), gridBagConstraints12);
		this.add(getJButtonMatchcodeMengeneinheit(), gridBagConstraints19);
	}

	@Override
	public void zeichneDich(ModelKnotenBean baPosModelBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(baPosModelBean!=null){
			if (baPosModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BESTELLUNGSPOSITION){
				setModelBean(baPosModelBean);//merken
				BestellungPosBean baPosBean = (BestellungPosBean) baPosModelBean.getIBean();
				//id anzeigen
				getJTextFieldId().setText(baPosBean.getId().toString());
				getJTextFieldId().setEnabled(false);
				//kostenstelle anzeigen
				if (baPosBean.getKostenstelle()!=null){
					getJTextKostenstelle().setText(baPosBean.getKostenstelle().getName());
				}else{
					getJTextKostenstelle().setText(null);
				}
				//Artikel-Daten anzeigen
				ArtikelBean artikelBean;
				if(baPosBean.getArtikelBean()!=null) 
					artikelBean = baPosBean.getArtikelBean(); 
				else artikelBean = new ArtikelBean(); //Den Artikel gibt es nicht. Also leeren Artikel anzeigen.
				getJTextBestellnummerKEG().setText(artikelBean.getKeg_nr().toString());
				getJTextArtikelBezeichnung().setText(artikelBean.getBezeichnung());
				getJTextArtikelTyp().setText(artikelBean.getTyp());
				getJTextArtikelHersteller().setText(artikelBean.getHersteller().getName());
				//Bestellmenge 
				getJTextBestellmenge().setText(baPosBean.getBestellmenge().toString());

				//Mengeneinheit
				getJTextMengeneinheit().setText(baPosBean.getMengenEinheitBean().getName());
				//Katalog - Info
				getJTextBestellnummerLieferant().setText(baPosBean.getLieferantenbestellnummer());
				getJTextKatalogseite().setText(baPosBean.getKatalogseite());
				getJTextKatalog().setText(baPosBean.getKatalogBean().getName());
				//Liefertermin
				getJTextLiefertermin().setText(baPosBean.getLiefertermin().toString());
				
				//Fehler anzeigen.
				for(int i=0; i<baPosModelBean.getFehlerList().size();i++){
					Fehler fehler = baPosModelBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				BestellungBean baBean = (BestellungBean)baPosModelBean.getVater().getIBean();
				if (baBean.getStatus()==BestellungStatus.OFFEN){
					this.setEnabled(true);
				}else{
					this.setEnabled(false);
				}
				
				this.repaint();//alte Komponenten werden gelöscht
				this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
				this.validate();   //werden gezeichnet.
				this.revalidate(); //Layout-Manager tut seinen JOB, und richtet				this.invalidate();
			}
		}
	}

	@Override
	protected DetailsController getController() {
		return detailsController;
	}

	@Override
	protected void setController(
			DetailsController detailsController) {
		this.detailsController = detailsController;
	}

	/**
	 * This method initializes jTextFieldId	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldId() {
		if (jTextFieldId == null) {
			jTextFieldId = new JTextField();
			jTextFieldId.setPreferredSize(new Dimension(40, 20));
			jTextFieldId.setEditable(false);
		}
		return jTextFieldId;
	}

	/**
	 * This method initializes jTextKostenstelle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextKostenstelle() {
		if (jTextKostenstelle == null) {
			jTextKostenstelle = new JTextField();
			jTextKostenstelle.setPreferredSize(new Dimension(40, 20));
			jTextKostenstelle.setEditable(false);
		}
		return jTextKostenstelle;
	}

	/**
	 * This method initializes jTextBestellnummerKEG	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextBestellnummerKEG() {
		if (jTextBestellnummerKEG == null) {
			jTextBestellnummerKEG = new JTextField();
			jTextBestellnummerKEG.setPreferredSize(new Dimension(80, 20));
			jTextBestellnummerKEG.setEditable(false);
		}
		return jTextBestellnummerKEG;
	}

	/**
	 * This method initializes jTextBestellnummerLieferant	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextBestellnummerLieferant() {
		if (jTextBestellnummerLieferant == null) {
			jTextBestellnummerLieferant = new JTextField();
			jTextBestellnummerLieferant.setPreferredSize(new Dimension(80, 20));
			jTextBestellnummerLieferant.setEditable(false);
		}
		return jTextBestellnummerLieferant;
	}

	/**
	 * This method initializes jTextArtikelBezeichnung	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextArtikelBezeichnung() {
		if (jTextArtikelBezeichnung == null) {
			jTextArtikelBezeichnung = new JTextField();
			jTextArtikelBezeichnung.setPreferredSize(new Dimension(80, 20));
			jTextArtikelBezeichnung.setEditable(false);
		}
		return jTextArtikelBezeichnung;
	}

	/**
	 * This method initializes jTextArtikelTyp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextArtikelTyp() {
		if (jTextArtikelTyp == null) {
			jTextArtikelTyp = new JTextField();
			jTextArtikelTyp.setPreferredSize(new Dimension(40, 20));
			jTextArtikelTyp.setEditable(false);
		}
		return jTextArtikelTyp;
	}

	/**
	 * This method initializes jTextArtikelHersteller	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextArtikelHersteller() {
		if (jTextArtikelHersteller == null) {
			jTextArtikelHersteller = new JTextField();
			jTextArtikelHersteller.setPreferredSize(new Dimension(80, 20));
			jTextArtikelHersteller.setEditable(false);
		}
		return jTextArtikelHersteller;
	}

	/**
	 * This method initializes jTextKatalogseite	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextKatalogseite() {
		if (jTextKatalogseite == null) {
			jTextKatalogseite = new JTextField();
			jTextKatalogseite.setPreferredSize(new Dimension(80, 20));
			jTextKatalogseite.setEditable(false);
		}
		return jTextKatalogseite;
	}

	/**
	 * This method initializes jTextKatalog	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextKatalog() {
		if (jTextKatalog == null) {
			jTextKatalog = new JTextField();
			jTextKatalog.setPreferredSize(new Dimension(80, 20));
			jTextKatalog.setEditable(false);
		}
		return jTextKatalog;
	}

	/**
	 * This method initializes jTextBestellmenge	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJTextBestellmenge() {
		if (jTextBestellmenge == null) {
//			jTextBestellmenge = new JFormattedTextField(BaPosBean.getLieferterminFormat());
//			jTextBestellmenge = new JFormattedTextField(new EmptyNumberFormatter());
			jTextBestellmenge = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 1000,""));
			jTextBestellmenge.setInputVerifier(LagerFormate.getInputVerifier());
			jTextBestellmenge.addPropertyChangeListener("value", this);
			jTextBestellmenge.setPreferredSize(new Dimension(80, 20));
			jTextBestellmenge.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					pruefeFehler();
				}
			});
		}
		return jTextBestellmenge;
	}



	
	/**
	 * This method initializes jTextMengeneinheit	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextMengeneinheit() {
		if (jTextMengeneinheit == null) {
			jTextMengeneinheit = new JTextField();
			jTextMengeneinheit.setPreferredSize(new Dimension(80, 20));
			jTextMengeneinheit.setEditable(false);
		}
		return jTextMengeneinheit;
	}

	/**
	 * This method initializes jTextLiefertermin	
	 * 	
	 * @return javax.swing.JTextField	
	 * @throws ParseException 
	 */
	private JFormattedTextField getJTextLiefertermin()  {
//		jTextLiefertermin = new JFormattedTextField(BaPosBean.getLieferterminFormat());
//		jTextLiefertermin = new JFormattedTextField(new EmptyNumberFormatter());
		if (jTextLiefertermin == null) {
			jTextLiefertermin = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 53, ""));
			jTextLiefertermin.setInputVerifier(LagerFormate.getInputVerifier());
			jTextLiefertermin.addPropertyChangeListener("value", this);
			jTextLiefertermin.setPreferredSize(new Dimension(80, 20));
			jTextLiefertermin.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					Log.log().finest("focusGained() getJTextLiefertermin() vor prüfeFehler"); // TODO Auto-generated Event stub focusGained()
					pruefeFehler();
					Log.log().finest("focusGained() nach prüfeFehler"); // TODO Auto-generated Event stub focusGained()
				}
			});
			jTextLiefertermin.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusLost(java.awt.event.FocusEvent e) {
					Log.log().finest("focusLost getJTextLiefertermin ()"); // TODO Auto-generated Event stub focusLost()
				}
			});
		}
		return jTextLiefertermin;
	}

	/**
	 * This method initializes jButtonMatchcodeKostenstelle	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchcodeKostenstelle() {
		if (jButtonMatchcodeKostenstelle == null) {
			jButtonMatchcodeKostenstelle = new JButton();
			jButtonMatchcodeKostenstelle.setText("...");
			jButtonMatchcodeKostenstelle.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButtonMatchcodeKostenstelle
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()){
									((BestellungPosDetailsController)getController()).holeKostenstelle();
								}
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
		return jButtonMatchcodeKostenstelle;
	}

	/**
	 * This method initializes jButtonMatchcodeArtikel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchcodeArtikel() {
		if (jButtonMatchcodeArtikel == null) {
			jButtonMatchcodeArtikel = new JButton();
			jButtonMatchcodeArtikel.setText("...");
			jButtonMatchcodeArtikel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((BestellungPosDetailsController)getController()).holeArtikel();
						}
					} catch (SQLException ex) {
						getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,ex.getMessage()));
						ex.printStackTrace();
					} catch (KostenstelleBeanDbLagerException ex) {
						getJComboBoxFehler().addItem(ex.getFehler());
						ex.printStackTrace();
					} catch (MengenEinheitBeanDbLagerException ex) {
						getJComboBoxFehler().addItem(ex.getFehler());
						ex.printStackTrace();
					} catch (HerstellerLieferantLagerException ex) {
						getJComboBoxFehler().addItem(ex.getFehler());
						ex.printStackTrace();
					} catch (LagerException ex) {
						getJComboBoxFehler().addItem(ex.getFehler());
						ex.printStackTrace();
					}
				}
			});
		}
		return jButtonMatchcodeArtikel;
	}

//	private ModelKnotenBean getBaPosModelBean() {
//		return baPosModelBean;
//	}
//
//	private void setBaPosModelBean(ModelKnotenBean baPosModelBean) {
//		this.baPosModelBean = baPosModelBean;
//	}

	/**
	 * This method initializes jComboBoxFehler	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxFehler() {
		if (jComboBoxFehler == null) {
			jComboBoxFehler = new JComboBox();
			jComboBoxFehler.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					BestellungPosDetailsView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
				}
			});
		}
		return jComboBoxFehler;
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

	/**
	 * This method initializes jPanelUnten	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelUnten() {
		if (jPanelUnten == null) {
			jPanelUnten = new JPanel();
			jPanelUnten.setLayout(new GridBagLayout());
			jPanelUnten.setPreferredSize(new Dimension(10, 10));
		}
		return jPanelUnten;
	}

	public void setKostenstelle(KostenstelleBean kostenstelleBean) {
		//Das Model wird geändert.
		((BestellungPosBean)getModelBean().getIBean()).setKostenstelle(kostenstelleBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setArtikel(ArtikelBean artikelBean) {
		//Das Model wird geändert.
		BestellungPosBean baPosBean = ((BestellungPosBean)getModelBean().getIBean());
		baPosBean.setArtikelBean(artikelBean);
		baPosBean.setMengenEinheitBean(artikelBean.getMengenEinheitBean());
		baPosBean.setBestellmenge(artikelBean.getEmpfohlene_bestellmenge());
		
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	/**
	 * This method initializes jButtonMatchcodeLieferantenBestellnummer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchcodeLieferantenBestellnummer() {
		if (jButtonMatchcodeLieferantenBestellnummer == null) {
			jButtonMatchcodeLieferantenBestellnummer = new JButton();
			jButtonMatchcodeLieferantenBestellnummer.setText("...");
			jButtonMatchcodeLieferantenBestellnummer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					BestellungPosBean baPosBean = ((BestellungPosBean)getModelBean().getIBean());
					Integer artikel_id = baPosBean.getArtikelBean().getId();
					if (pruefeFehler()){
						try {
							((BestellungPosDetailsController)getController()).sucheLieferantenBestellnummerByArtikelId(artikel_id);
						} catch (LagerException e1) {
							getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,e1.getMessage()));
							e1.printStackTrace();
						}
					}
				}
			});
		}
		return jButtonMatchcodeLieferantenBestellnummer;
	}

	public void setLieferantenBestellnummer(
			LieferantenBestellnummerBean lieferantenBestellnummerBean) {
		//Das Model wird geändert.
		BestellungPosBean baPosBean = ((BestellungPosBean)getModelBean().getIBean());
		baPosBean.setKatalogBean(lieferantenBestellnummerBean.getKatalogBean());
		baPosBean.setKatalogseite(lieferantenBestellnummerBean.getKatalogseite());
		baPosBean.setLieferantenbestellnummer(lieferantenBestellnummerBean.getBestellnummer());
		
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	/**
	 * This method initializes jButtonMatchcodeMengeneinheit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchcodeMengeneinheit() {
		if (jButtonMatchcodeMengeneinheit == null) {
			jButtonMatchcodeMengeneinheit = new JButton();
			jButtonMatchcodeMengeneinheit.setText("...");
			jButtonMatchcodeMengeneinheit
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								if (pruefeFehler()){
									((BestellungPosDetailsController)getController()).holeMengeneinheit();
								}
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
		return jButtonMatchcodeMengeneinheit;
	}

	public void setMengeneinheit(MengenEinheitBean mengenEinheitBean) {
		//Das Model wird geändert.
		((BestellungPosBean)getModelBean().getIBean()).setMengenEinheitBean(mengenEinheitBean);
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJTextBestellmenge()){
			leseAusgetJTextBestellmenge();
		}
		if (source==getJTextLiefertermin()){
				leseAusgetJTextLiefertermin();
		}
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJTextBestellmenge() {
		BestellungPosBean baPosBean = (BestellungPosBean)getModelBean().getIBean();
		Integer wert ;
		try{
			if(getJTextBestellmenge().getValue()!=null){
				wert = ((Number)getJTextBestellmenge().getValue()).intValue();
				baPosBean.setBestellmenge(wert);
			}
		}catch(NumberFormatException ex){
		}
	}

	private void leseAusgetJTextLiefertermin() {
		BestellungPosBean baPosBean = (BestellungPosBean)getModelBean().getIBean();
		int kw;
		try {
			if(getJTextLiefertermin().getValue()!=null){
				kw = ((Long) getJTextLiefertermin().getValue()).intValue();
				baPosBean.setLiefertermin(kw);
			}
		} catch (NumberFormatException ex) {
		}
	}

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {

			if (!getJTextLiefertermin().getInputVerifier().shouldYieldFocus(
					getJTextLiefertermin())) {
				errors.add(new Fehler(29));
			} else {
				leseAusgetJTextLiefertermin();
			}
			if (!getJTextBestellmenge().getInputVerifier().shouldYieldFocus(
					getJTextBestellmenge())) {
				errors.add(new Fehler(34));
			} else {
				leseAusgetJTextBestellmenge();
			}
		} catch (Exception e) {
			//Alle Fehler abfangen
			errors.add(new Fehler(36,FehlerTyp.FEHLER,e.getMessage()));
			e.printStackTrace();
		}
		return errors;
	}
	
	@Override
	protected boolean pruefeFehler() {
		ArrayList<Fehler> errors = getController().setzeNeuenAktivenController();
		for(int i=0; i<errors.size();i++){
			Log.log().finest("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
		}
		Boolean result = errors.size()==0;
		if(!result){
			JFehlerDialogWechselController.getOneIntstance().showView(errors);
		}
		return errors.size()==0;
	}


	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJButtonMatchcodeArtikel().setEnabled(enabled);
		getJButtonMatchcodeKostenstelle().setEnabled(enabled);
		getJButtonMatchcodeLieferantenBestellnummer().setEnabled(enabled);
		getJButtonMatchcodeMengeneinheit().setEnabled(enabled);
//		getJTextBestellmenge().setEnabled(enabled);
		getJTextBestellmenge().setEditable(enabled);
//		getJTextLiefertermin().setEnabled(enabled);
		getJTextLiefertermin().setEditable(enabled);
		}


	@Override
	protected void uebernehmeDaten() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStandardFocusPosition() {
		getJButtonMatchcodeKostenstelle().requestFocus();
	}


	
}  //  @jve:decl-index=0:visual-constraint="49,1"
