package de.keag.lager.panels.frame.entnahme.pane.details.position;

import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.Spring;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IDetailsBeobachter;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmePosBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeBean;
import de.keag.lager.panels.frame.entnahme.model.EntnahmeStatus;
import de.keag.lager.panels.frame.etage.model.EtageBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.suche.HalleSuchController;
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
import javax.swing.text.JTextComponent;

import java.awt.Color;


public class EntnahmePosDetailsView extends DetailsView implements IDetailsBeobachter, PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private DetailsController detailsController;  //  @jve:decl-index=0:
	private JLabel jLabelLeer = null;
	private JTextField jTextFieldId = null;
	private JTextField jTextKostenstelle = null;
	private JTextField jTextBestellnummerKEG = null;
	private JTextField jTextArtikelBezeichnung = null;
	private JTextField jTextArtikelTyp = null;
	private JTextField jTextArtikelHersteller = null;
//	private JTextField jTextFieldMenge = null;
	private JLabel jLabelKostenstelle = null;
	private JLabel jLabelBestellnummerKEG = null;
	private JLabel jLabelArtikelBezeichnung = null;
	private JLabel jLabelArtikelTyp = null;
	private JLabel jLabelArtikelHersteller = null;
	private JLabel jLabelMenge = null;
	private JLabel jLabelMengeneinheit = null;
	private JLabel jLabelEntnahmeID = null;
	private JLabel jLabelLeer2 = null;
	private JButton jButtonMatchcodeKostenstelle = null;
	private JButton jButtonMatchcodeArtikel = null;
//	private ModelKnotenBean entnahmePosModelBean = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JPanel jPanelOben = null;
	private JPanel jPanelUnten = null;
	private JButton jButtonMatchcodeMengeneinheit = null;
	private JLabel jLabelPosition = null;
	private JLabel jLabelEbene = null;
	private JLabel jLabelSaeule = null;
	private JLabel jLabelZeile = null;
	private JLabel jLabelHalle = null;
	private JTextField jTextFieldEbene = null;
	private JTextField jTextFieldSaeule = null;
	private JTextField jTextFieldZeile = null;
	private JTextField jTextFieldPosition = null;
//	private JTextField jTextMengeneinheit = null;
	private JTextField jTextMenge = null;
	private JButton jButtonMatchCodeHalle = null;
	private JButton jButtonMatchCodeEtage = null;
	private JLabel jLabelEtage = null;
	private JFormattedTextField jFormattedTextFieldEtage = null;
	private JFormattedTextField jFormattedTextFieldHalle = null;
	private JFormattedTextField jFormattedTextFieldMengeneinheit = null;
	private JTextField jFormattedTextFieldMengen = null;
	/**
	 * This is the default constructor
	 * @param entnahmePosDetailsController 
	 */
	public EntnahmePosDetailsView(EntnahmePosDetailsController entnahmePosDetailsController) {
		super();
		setController(entnahmePosDetailsController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints114 = new GridBagConstraints();
		gridBagConstraints114.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints114.gridy = 15;
		gridBagConstraints114.weightx = 1.0;
		gridBagConstraints114.gridx = 1;
		GridBagConstraints gridBagConstraints113 = new GridBagConstraints();
		gridBagConstraints113.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints113.gridy = 16;
		gridBagConstraints113.weightx = 1.0;
		gridBagConstraints113.anchor = GridBagConstraints.WEST;
		gridBagConstraints113.gridx = 1;
		GridBagConstraints gridBagConstraints112 = new GridBagConstraints();
		gridBagConstraints112.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints112.gridy = 16;
		gridBagConstraints112.weightx = 1.0;
		gridBagConstraints112.gridx = 1;
		GridBagConstraints gridBagConstraints52 = new GridBagConstraints();
		gridBagConstraints52.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints52.gridy = 22;
		gridBagConstraints52.weightx = 1.0;
		gridBagConstraints52.gridx = 1;
		GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
		gridBagConstraints42.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints42.gridy = 21;
		gridBagConstraints42.weightx = 1.0;
		gridBagConstraints42.anchor = GridBagConstraints.WEST;
		gridBagConstraints42.gridx = 1;
		GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
		gridBagConstraints32.gridx = 0;
		gridBagConstraints32.anchor = GridBagConstraints.EAST;
		gridBagConstraints32.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints32.gridy = 21;
		jLabelEtage = new JLabel();
		jLabelEtage.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelEtage.setText("Etage");
		GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
		gridBagConstraints24.gridx = 2;
		gridBagConstraints24.anchor = GridBagConstraints.WEST;
		gridBagConstraints24.gridy = 21;
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.gridx = 2;
		gridBagConstraints111.anchor = GridBagConstraints.WEST;
		gridBagConstraints111.gridy = 22;
		GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
		gridBagConstraints110.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints110.gridy = 15;
		gridBagConstraints110.weightx = 1.0;
		gridBagConstraints110.gridx = 1;
		GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
		gridBagConstraints17.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints17.gridy = 16;
		gridBagConstraints17.weightx = 1.0;
		gridBagConstraints17.anchor = GridBagConstraints.WEST;
		gridBagConstraints17.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints17.anchor = GridBagConstraints.CENTER;
		gridBagConstraints17.gridx = 1;
		GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
		gridBagConstraints101.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints101.gridy = 17;
		gridBagConstraints101.weightx = 1.0;
		gridBagConstraints101.anchor = GridBagConstraints.WEST;
		gridBagConstraints101.gridx = 1;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints8.gridy = 20;
		gridBagConstraints8.weightx = 1.0;
		gridBagConstraints8.anchor = GridBagConstraints.WEST;
		gridBagConstraints8.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints7.gridy = 19;
		gridBagConstraints7.weightx = 1.0;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.gridx = 1;
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.fill = GridBagConstraints.VERTICAL;
		gridBagConstraints61.gridy = 18;
		gridBagConstraints61.weightx = 1.0;
		gridBagConstraints61.anchor = GridBagConstraints.WEST;
		gridBagConstraints61.gridx = 1;
		GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
		gridBagConstraints51.gridx = 0;
		gridBagConstraints51.anchor = GridBagConstraints.EAST;
		gridBagConstraints51.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints51.gridy = 22;
		jLabelHalle = new JLabel();
		jLabelHalle.setText("Halle");
		GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
		gridBagConstraints4.gridx = 0;
		gridBagConstraints4.anchor = GridBagConstraints.EAST;
		gridBagConstraints4.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints4.gridy = 20;
		jLabelZeile = new JLabel();
		jLabelZeile.setText("Zeile");
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.anchor = GridBagConstraints.NORTHEAST;
		gridBagConstraints31.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints31.gridy = 19;
		jLabelSaeule = new JLabel();
		jLabelSaeule.setText("Säule");
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.gridx = 0;
		gridBagConstraints23.anchor = GridBagConstraints.EAST;
		gridBagConstraints23.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints23.gridy = 18;
		jLabelEbene = new JLabel();
		jLabelEbene.setText("Ebene");
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.anchor = GridBagConstraints.EAST;
		gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints12.gridy = 17;
		jLabelPosition = new JLabel();
		jLabelPosition.setText("Position");
		GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		gridBagConstraints19.gridx = 2;
		gridBagConstraints19.anchor = GridBagConstraints.WEST;
		gridBagConstraints19.gridy = 16;
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.gridx = 0;
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.weighty = 150.0D;
		gridBagConstraints3.weightx = 1.0D;
		gridBagConstraints3.gridy = 24;
		GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
		gridBagConstraints28.gridx = 0;
		gridBagConstraints28.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints28.weightx = 1.0D;
		gridBagConstraints28.weighty = 150.0D;
		gridBagConstraints28.gridy = 0;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 30;
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
		gridBagConstraints37.gridy = 23;
		jLabelLeer2 = new JLabel();
		jLabelLeer2.setText("              ");
		GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
		gridBagConstraints35.gridx = 0;
		gridBagConstraints35.anchor = GridBagConstraints.EAST;
		gridBagConstraints35.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints35.weightx = 1.0D;
		gridBagConstraints35.weighty = 0.0D;
		gridBagConstraints35.gridy = 1;
		jLabelEntnahmeID = new JLabel();
		jLabelEntnahmeID.setText("Entnahme ID");
		jLabelEntnahmeID.setHorizontalAlignment(SwingConstants.LEADING);
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
		jLabelMenge = new JLabel();
		jLabelMenge.setText("Menge");
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
//		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
//		gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
//		gridBagConstraints10.gridy = 15;
//		gridBagConstraints10.weightx = 1.0;
//		gridBagConstraints10.anchor = GridBagConstraints.WEST;
//		gridBagConstraints10.gridx = 1;
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
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
		gridBagConstraints41.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints41.gridy = 9;
		gridBagConstraints41.weightx = 1.0;
		gridBagConstraints41.anchor = GridBagConstraints.WEST;
		gridBagConstraints41.gridx = 1;
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
		this.add(getJTextArtikelBezeichnung(), gridBagConstraints41);
		this.add(getJTextArtikelTyp(), gridBagConstraints5);
		this.add(getJTextArtikelHersteller(), gridBagConstraints6);
		this.add(getJTextFieldPosition(), gridBagConstraints101);
		this.add(getJTextFieldEbene(), gridBagConstraints61);
		this.add(getJTextFieldSaeule(), gridBagConstraints7);
		this.add(getJTextFieldZeile(), gridBagConstraints8);
		this.add(jLabelEntnahmeID, gridBagConstraints35);
		this.add(jLabelLeer, gridBagConstraints);
		this.add(jLabelKostenstelle, gridBagConstraints15);
		this.add(jLabelBestellnummerKEG, gridBagConstraints16);
		this.add(jLabelArtikelBezeichnung, gridBagConstraints18);
		this.add(jLabelArtikelTyp, gridBagConstraints21);
		this.add(jLabelArtikelHersteller, gridBagConstraints22);
		this.add(jLabelMenge, gridBagConstraints25);
		this.add(jLabelMengeneinheit, gridBagConstraints26);
		this.add(jLabelPosition, gridBagConstraints12);
		this.add(jLabelEbene, gridBagConstraints23);
		this.add(jLabelSaeule, gridBagConstraints31);
		this.add(jLabelZeile, gridBagConstraints4);
		this.add(jLabelHalle, gridBagConstraints51);
		this.add(jLabelLeer2, gridBagConstraints37);
		this.add(getJButtonMatchcodeKostenstelle(), gridBagConstraints39);
		this.add(getJButtonMatchcodeArtikel(), gridBagConstraints14);
		this.add(getJComboBoxFehler(), gridBagConstraints13);
		this.add(getJPanelOben(), gridBagConstraints28);
		this.add(getJPanelUnten(), gridBagConstraints3);
		this.add(getJButtonMatchcodeMengeneinheit(), gridBagConstraints19);
		this.add(getJButtonMatchCodeHalle(), gridBagConstraints111);
		this.add(getJButtonMatchCodeEtage(), gridBagConstraints24);
		this.add(jLabelEtage, gridBagConstraints32);
		this.add(getJFormattedTextFieldEtage(), gridBagConstraints42);
		this.add(getJFormattedTextFieldHalle(), gridBagConstraints52);
		this.add(getJFormattedTextFieldMengeneinheit(), gridBagConstraints113);
		this.add(getJFormattedTextFieldMengen(), gridBagConstraints114);
//		this.add(getJFormattedTextFieldMenge(), gridBagConstraints110);
	}

	@Override
	public void zeichneDich(ModelKnotenBean entnahmePosModelBean) {
		getJComboBoxFehler().removeAllItems(); //alte Fehler werden gelöscht.
		setzeHintergrund();
		
		if(entnahmePosModelBean!=null){
			if (entnahmePosModelBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ENTNAHMENSPOSITION){
				setModelBean(entnahmePosModelBean);//merken
				EntnahmePosBean entnahmePosBean = (EntnahmePosBean) entnahmePosModelBean.getIBean();
				//id anzeigen
				getJTextFieldId().setText(entnahmePosBean.getId().toString());
				getJTextFieldId().setEnabled(false);
				//kostenstelle anzeigen
				if (entnahmePosBean.getKostenstelle()!=null){
					getJTextKostenstelle().setText(entnahmePosBean.getKostenstelle().getName());
				}else{
					getJTextKostenstelle().setText(null);
				}
				//Artikel-Daten anzeigen
				ArtikelBean artikelBean;
				if(entnahmePosBean.getArtikelBean()!=null) 
					artikelBean = entnahmePosBean.getArtikelBean(); 
				else artikelBean = new ArtikelBean(); //Den Artikel gibt es nicht. Also leeren Artikel anzeigen.
				getJTextBestellnummerKEG().setText(artikelBean.getKeg_nr().toString());
				getJTextArtikelBezeichnung().setText(artikelBean.getBezeichnung());
				getJTextArtikelTyp().setText(artikelBean.getTyp());
				getJTextArtikelHersteller().setText(artikelBean.getHersteller().getName());
				//Bestellmenge 
//				getjTextMenge().setText(entnahmePosBean.getMenge().toString());
//				getJFormattedTextFieldMenge().setText(entnahmePosBean.getMenge().toString());

				//Mengeneinheit
				getJFormattedTextFieldMengeneinheit().setText(entnahmePosBean.getMengenEinheitBean().getName());
				getJFormattedTextFieldHalle().setText(entnahmePosBean.getHalleBean().getName());
				getJFormattedTextFieldEtage().setText(entnahmePosBean.getEtageBean().getName());
				//Katalog - Info
//				getJTextBestellnummerLieferant().setText(entnahmePosBean.getLieferantenbestellnummer());
//				getJTextKatalogseite().setText(entnahmePosBean.getKatalogseite());
//				getJTextKatalog().setText(entnahmePosBean.getKatalogBean().getName());
//				//Liefertermin
//				getJTextLiefertermin().setText(entnahmePosBean.getLiefertermin().toString());
				
				//Fehler anzeigen.
				for(int i=0; i<entnahmePosModelBean.getFehlerList().size();i++){
					Fehler fehler = entnahmePosModelBean.getFehlerList().get(i);
					getJComboBoxFehler().addItem(fehler);
				}
				
				EntnahmeBean entnahmeBean = (EntnahmeBean)entnahmePosModelBean.getVater().getIBean();
				if (entnahmeBean.getStatus()==EntnahmeStatus.OFFEN){
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

//	private JTextComponent getJFormattedTextFieldMenge() {
//		// TODO Auto-generated method stub
//		return null;
//	}

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

	//      
	
	/**
	 * This method initializes jTextKostenstelle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextKostenstelle(){    
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
	 * This method initializes jTextFieldMenge	
	 * 	
	 * @return javax.swing.JTextField	
	 */
//	private jTextMenge getJTextFieldMenge() {
//		if (jTextFieldMenge == null) {
//			jTextFieldMenge = new jTextFieldMenge();
//			jTextFieldMenge.setPreferredSize(new Dimension(80, 20));
//			jTextFieldMenge.setEditable(false);
//	}
//	return jTextFieldMenge;
//}
//	}
//		if (jTextMenge == null) {
//			jTextMenge = new jTextMenge(new LagerEmptyNumberFormatter(1, 1000, ""));
//			jTextMenge.setInputVerifier(LagerFormate.getInputVerifier());
//			jTextMenge.addPropertyChangeListener("value", this);
//			jTextMenge.setPreferredSize(new Dimension(80, 20));
//		}
//		return jTextFieldMenge;
//	}
//	private JFormattedTextField getJFormattedTextFieldMenge() {
//
//		if (jFormattedTextFieldMenge == null) {
//			jFormattedTextFieldMenge = new JFormattedTextField(new LagerEmptyNumberFormatter(1, 1000, ""));
//			jFormattedTextFieldMenge.setInputVerifier(LagerFormate.getInputVerifier());
//			jFormattedTextFieldMenge.addPropertyChangeListener("value", this);
//			jFormattedTextFieldMenge.setPreferredSize(new Dimension(80, 20));
//	}
//		return  jFormattedTextFieldMenge;
//	}
	/**
	 * This method initializes jFormattedTextFieldMengen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJFormattedTextFieldMengen() {
		if (jFormattedTextFieldMengen == null) {
			jFormattedTextFieldMengen = new JTextField();
		}
		return jFormattedTextFieldMengen;
	}



//		if (jTextFieldMenge == null) {
//			jTextFieldMenge = new JTextField();
//			jTextFieldMenge.setPreferredSize(new Dimension(80, 20));
//			jTextFieldMenge.setEditable(false);
//		}
//		return jTextFieldMenge;
//	}

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
									((EntnahmePosDetailsController)getController()).holeKostenstelle();
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
							((EntnahmePosDetailsController)getController()).holeArtikel();
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

//	private ModelKnotenBean getEntnahmePosModelBean() {
//		return entnahmePosModelBean;
//	}
//
//	private void setEntnahmePosModelBean(ModelKnotenBean entnahmePosModelBean) {
//		this.entnahmePosModelBean = entnahmePosModelBean;
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
					EntnahmePosDetailsView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
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
		((EntnahmePosBean)getModelBean().getIBean()).setKostenstelle(kostenstelleBean);
		//Alle Beoentnahmechter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setArtikel(ArtikelBean artikelBean) {
		//Das Model wird geändert.
		EntnahmePosBean entnahmePosBean = ((EntnahmePosBean)getModelBean().getIBean());
		entnahmePosBean.setArtikelBean(artikelBean);
		entnahmePosBean.setMengenEinheitBean(artikelBean.getMengenEinheitBean());
//		entnahmePosBean.setBestellmenge(artikelBean.getEmpfohlene_bestellmenge());
		
		//Alle Beoentnahmechter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

//	public void setLieferantenBestellnummer(
//			LieferantenBestellnummerBean lieferantenBestellnummerBean) {
//		//Das Model wird geändert.
//		EntnahmePosBean entnahmePosBean = ((EntnahmePosBean)getModelBean().getIBean());
//		entnahmePosBean.setKatalogBean(lieferantenBestellnummerBean.getKatalogBean());
//		entnahmePosBean.setKatalogseite(lieferantenBestellnummerBean.getKatalogseite());
//		entnahmePosBean.setLieferantenbestellnummer(lieferantenBestellnummerBean.getBestellnummer());
//		
//		//Alle Beoentnahmechter des Models werden über eine Änderung benachrichtigt.
//		getController().ausgewaehlterKnotenIstGeandert();
//	}

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
									((EntnahmePosDetailsController)getController()).holeMengeneinheit();
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
		((EntnahmePosBean)getModelBean().getIBean()).setMengenEinheitBean(mengenEinheitBean);
		//Alle Beoentnahmechter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		//Das Model wird geändert.
		if (source==getJFormattedTextFieldMenge()){
			leseAusgetJFormattedTextFieldMenge();
		}
//		if (source==getJTextLiefertermin()){
//				leseAusgetJTextLiefertermin();
//		}
		//Alle Beoentnahmechter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJFormattedTextFieldMenge() {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean)getModelBean().getIBean();
		Integer wert ;
		try{
			if(((JFormattedTextField) getJFormattedTextFieldMenge()).getValue()!=null){
				wert = ((Number)((JFormattedTextField) getJFormattedTextFieldMenge()).getValue()).intValue();
				entnahmePosBean.setMenge(wert);
			}
		}catch(NumberFormatException ex){
		}
	}
	
	

//private JFormattedTextField getJFormattedTextFieldMenge() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	//	private void leseAusgetJTextFieldPosition() {
//		EntnahmePosBean entnahmePosBean = (EntnahmePosBean)getModelBean().getIBean();
//		int pos;
//		try {
//			if(getJTextFieldPosition().getValue()!=null){
//				pos = ((Long) getJTextFieldPosition().getValue()).intValue();
//				entnahmePosBean.setPosition(pos);
//			}
//		} catch (NumberFormatException ex) {
//		}
//	}
	private void leseAusgetJTextFieldPosition() {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean)getModelBean().getIBean();
		int pos;
		try {
			if(((JFormattedTextField) getJTextFieldPosition()).getValue()!=null){
				pos = ((Long) ((JFormattedTextField) getJTextFieldPosition()).getValue()).intValue();
				entnahmePosBean.setPosition(pos);
			}
		} catch (NumberFormatException ex) {
		}
	}
	private void leseAusgetJTextFieldEbene() {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean)getModelBean().getIBean();
		int eb;
		try {
			if(((JFormattedTextField) getJTextFieldEbene()).getValue()!=null){
				eb = ((Long) ((JFormattedTextField) getJTextFieldEbene()).getValue()).intValue();
				entnahmePosBean.setEbene(eb);
			}
		} catch (NumberFormatException ex) {
		}
	}
	private void leseAusgetJTextFieldZeile() {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean)getModelBean().getIBean();
		int zeile;
		try {
			if(((JFormattedTextField) getJTextFieldZeile()).getValue()!=null){
				zeile = ((Long) ((JFormattedTextField) getJTextFieldZeile()).getValue()).intValue();
				entnahmePosBean.setZeile(zeile);
			}
		} catch (NumberFormatException ex) {
		}
	}
	private void leseAusgetJTextFieldSaeule() {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean)getModelBean().getIBean();
		int pos;
		try {
			if(((JFormattedTextField) getJTextFieldSaeule()).getValue()!=null){
				pos = ((Long) ((JFormattedTextField) getJTextFieldSaeule()).getValue()).intValue();
				entnahmePosBean.setSaeule(pos);
			}
		} catch (NumberFormatException ex) {
		}
	}
	private void leseAusgetJTextFieldHalle() {
		EntnahmePosBean entnahmePosBean = (EntnahmePosBean)getModelBean().getIBean();
	String halle;
		try {
			if(((JFormattedTextField) getJFormattedTextFieldHalle()).getValue()!=null){
				halle=  getJFormattedTextFieldHalle().getName();
				entnahmePosBean.setHalleBean();
			}
		} catch (NumberFormatException ex) {
		}
	}

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {

//			if (!getJTextLiefertermin().getInputVerifier().shouldYieldFocus(
//					getJTextLiefertermin())) {
//				errors.add(new Fehler(29));
//			} else {
//				leseAusgetJTextLiefertermin();
//			}
			if (!getJFormattedTextFieldMenge().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldMenge())) {
				errors.add(new Fehler(34));
			} else {
				leseAusgetJFormattedTextFieldMenge();
			}
			if (!getJTextFieldPosition().getInputVerifier().shouldYieldFocus(
					getJTextFieldPosition())) {
				errors.add(new Fehler(34));
			} else {
				leseAusgetJTextFieldPosition();
			}

			if (!getJTextFieldEbene().getInputVerifier().shouldYieldFocus(
					getJTextFieldEbene())) {
				errors.add(new Fehler(34));
			} else {
				leseAusgetJTextFieldEbene();
			}
			if (!getJTextFieldSaeule().getInputVerifier().shouldYieldFocus(
					getJTextFieldSaeule())) {
				errors.add(new Fehler(34));
			} else {
				leseAusgetJTextFieldSaeule();
			}
			if (!getJTextFieldZeile().getInputVerifier().shouldYieldFocus(
					getJTextFieldZeile())) {
				errors.add(new Fehler(34));
			} else {
				leseAusgetJTextFieldZeile();
			}
			if (!getJFormattedTextFieldHalle().getInputVerifier().shouldYieldFocus(
					getJFormattedTextFieldHalle())) {
				errors.add(new Fehler(34));
			} else {
				leseAusgetJTextFieldHalle();
			}

		} catch (Exception e) {
			//Alle Fehler abfangen
			errors.add(new Fehler(36,FehlerTyp.FEHLER,e.getMessage()));
			e.printStackTrace();
		}
		return errors;
	}
	
//	private void leseAusget() {
		// TODO Auto-generated method stub
		
	

	private JComponent getJFormattedTextFieldMenge() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean pruefeFehler() {
		ArrayList<Fehler> errors = getController().setzeNeuenAktivenController();
		for(int i=0; i<errors.size();i++){
			System.out.println("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
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
		getJButtonMatchcodeKostenstelle().setEnabled(enabled);
		getJButtonMatchcodeArtikel().setEnabled(enabled);
		getJButtonMatchcodeMengeneinheit().setEnabled(enabled);
//		getJFormattedTextFieldMenge().setEditable(enabled);
		getJFormattedTextFieldHalle().setEnabled(enabled);
    	getJTextFieldZeile().setEnabled(enabled);
	    getJTextFieldSaeule().setEnabled(enabled);
		getJTextFieldEbene().setEnabled(enabled);
		getJTextFieldPosition().setEnabled(enabled);
		}


	@Override
	protected void uebernehmeDaten() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStandardFocusPosition() {
		getJButtonMatchcodeKostenstelle().requestFocus();
	}
	/**
	 * This method initializes jTextFieldPosition	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldPosition() {
		if (jTextFieldPosition == null) {
			jTextFieldPosition = new JTextField();
			jTextFieldPosition.setPreferredSize(new Dimension(80, 20));
		}
		return jTextFieldPosition;
	}


	/**
	 * This method initializes jTextFieldEbene	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldEbene() {
		if (jTextFieldEbene == null) {
			jTextFieldEbene = new JTextField();
			jTextFieldEbene.setPreferredSize(new Dimension(80, 20));
		}
		return jTextFieldEbene;
	}

	/**
	 * This method initializes jTextFieldSaeule	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldSaeule() {
		if (jTextFieldSaeule == null) {
			jTextFieldSaeule = new JTextField();
			jTextFieldSaeule.setPreferredSize(new Dimension(80, 20));
		}
		return jTextFieldSaeule;
	}

	/**
	 * This method initializes jTextFieldZeile	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldZeile() {
		if (jTextFieldZeile == null) {
			jTextFieldZeile = new JTextField();
			jTextFieldZeile.setPreferredSize(new Dimension(80, 20));
		}
		return jTextFieldZeile;
	}

	public void setLieferantenBestellnummer(
			LieferantenBestellnummerBean lieferantenBestellnummerBean) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method initializes jTextMengeneinheit	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldMengeneinheit() {
		if (jFormattedTextFieldMengeneinheit == null) {
			jFormattedTextFieldMengeneinheit = new JFormattedTextField();
			jFormattedTextFieldMengeneinheit.setPreferredSize(new Dimension(80, 20));
			jFormattedTextFieldMengeneinheit.setPreferredSize(new Dimension(80, 20));
			jFormattedTextFieldMengeneinheit.setEditable(false);
		}
		return jFormattedTextFieldMengeneinheit;
	}

//	/**
//	 * This method initializes jButtonMatchCodeHalle	
//	 * 	
//	 * @return javax.swing.JButton	
//	 */
//	private JButton getJButtonMatchCodeHalle() {
//		if (jButtonMatchCodeHalle == null) {
//			jButtonMatchCodeHalle = new JButton();
//			jButtonMatchCodeHalle.setPreferredSize(new Dimension(30, 20));
//			jButtonMatchCodeHalle.setText("...");
//		}
//		return jButtonMatchCodeHalle;
//	}
	
	public IModel getiModel() {
		return getiModel();
	}

	/**
	 * This method initializes jButtonMatchCodeHalle	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeHalle() {
		if (jButtonMatchCodeHalle == null) {
			jButtonMatchCodeHalle = new JButton();
			jButtonMatchCodeHalle.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeHalle.setText("...");
			jButtonMatchCodeHalle.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((EntnahmePosDetailsController)getController()).holeHalle();
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
		return jButtonMatchCodeHalle;
	}



	/**
	 * This method initializes jButtonMatchCodeEtage	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchCodeEtage() {
		if (jButtonMatchCodeEtage == null) {
			jButtonMatchCodeEtage = new JButton();
			jButtonMatchCodeEtage.setPreferredSize(new Dimension(30, 20));
			jButtonMatchCodeEtage.setText("...");
			jButtonMatchCodeEtage.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						if (pruefeFehler()){
							((EntnahmePosDetailsController)getController()).holeEtage();
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
		return jButtonMatchCodeEtage;
	}
//
//	/**
//	 * This method initializes jFormattedTextFieldEtage	
//	 * 	
//	 * @return javax.swing.JFormattedTextField	
//	 */
//	private JFormattedTextField getJFormattedTextFieldEtage() {
//		if (jFormattedTextFieldEtage == null) {
//			jFormattedTextFieldEtage = new JFormattedTextField();
//			jFormattedTextFieldEtage.setEditable(false);
//		}
//		return jFormattedTextFieldEtage;
//	}
	/**
	 * This method initializes jFormattedTextFieldEtage	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldEtage() {
		if (jFormattedTextFieldEtage == null) {
			jFormattedTextFieldEtage = new JFormattedTextField();
			jFormattedTextFieldEtage.setEditable(false);
		}
		return jFormattedTextFieldEtage;
	}

	/**
	 * This method initializes jFormattedTextFieldHalle	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldHalle() {
		if (jFormattedTextFieldHalle == null) {
			jFormattedTextFieldHalle = new JFormattedTextField();
			jFormattedTextFieldHalle.setEditable(false);
		}
		return jFormattedTextFieldHalle;
	}

	public void setHalle(HalleBean halleBean) {
		//Das Model wird geändert.
		((EntnahmePosBean)getModelBean().getIBean()).setHalleBean(halleBean);
		//Alle Beoentnahmechter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	public void setEtage(EtageBean etageBean) {
		((EntnahmePosBean)getModelBean().getIBean()).setEtageBean(etageBean);
		//Alle Beoentnahmechter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
		
	}


	
}  //  @jve:decl-index=0:visual-constraint="-4,226"
