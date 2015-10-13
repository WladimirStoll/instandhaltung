package de.keag.lager.panels.frame.einlagerung.pane.details.anforderung;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import de.keag.lager.core.DetailsController;
import de.keag.lager.core.DetailsView;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.LagerException;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.formatter.LagerEmptyNumberFormatter;
import de.keag.lager.core.formatter.LagerFormate;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.pane.details.artikel.ArtikelDetailsController;
import de.keag.lager.panels.frame.artikel.pane.details.artikelPosition.ArtikelPosDetailsController;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungStatus;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck.BestandspackstueckDetailsController;
import de.keag.lager.panels.frame.kostenstelle.KostenstelleBean;
import de.keag.lager.panels.frame.position.model.PositionBean;
import de.keag.lager.panels.frame.saeule.model.SaeuleBean;
import de.keag.lager.panels.frame.zeile.model.ZeileBean;

import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import java.awt.SystemColor;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyEvent;


public class EinlagerungPosDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelEinlagerungId = null;
	private JLabel jLabelErstellungsDatum = null;
	private JLabel jLabelStatus = null;
	private JLabel jLabelEntnehmer = null;
	private JTextField jTextFieldId = null;
	private JDateChooser JDateChooserErstellungsDatum = null;
	private JTextField jTextFieldEntnehmer = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private JTextField jTextFieldStatus = null;
	private AbstractTableModel abstractTableModelEinlagerungPosition = null;
	private JToolBar jToolBar = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JLabel jLabelHalle = null;
	private JButton jButtonMatchCodeHalle = null;
	private JTextField jTextFieldHalle = null;
	private JLabel jLabelZeile = null;
	private JFormattedTextField jFormattedTextFieldZeile = null;
	private JLabel jLabelSaeule = null;
	private JFormattedTextField jFormattedTextFieldSaeule = null;
	private JLabel jLabelEbene = null;
	private JFormattedTextField jFormattedTextFieldEbene = null;
	private JLabel jLabelPosition = null;
	private JFormattedTextField jFormattedTextFieldPosition = null;
	private JLabel jLabelMenge = null;
	private JFormattedTextField jFormattedTextFieldMenge = null;
	private JLabel jLabelMengeneinheit = null;
	private JFormattedTextField jFormattedTextFieldMengeneinheit = null;
	private JLabel jLabelBestellnummerKEG = null;
	private JButton jButtonMatchcodeMengeneinheit = null;
	private JFormattedTextField jFormattedTextFieldArtikel = null;
	private JPanel jPanelOben1 = null;
	private JPanel jPanelOben11 = null;
	private JLabel jLabelEtage = null;
	private JTextField jTextFieldEtage = null;
	private JLabel jLabelArtikelHersteller = null;
	private JTextField jTextArtikelHersteller = null;
	private JLabel jLabelKostenstelle = null;
	private JTextField jTextKostenstelle = null;
	private JButton jButtonMatchcodeKostenstelle = null;
	private JButton jButtonBestaetigen = null;
	/**
	 * This is the default constructor
	 * @param einlagerungBlattController 
	 */
	public EinlagerungPosDetailsView(EinlagerungPosDetailsController einlagerungBlattController) {
		super();
		setController(einlagerungBlattController);//Controller merken
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
		gridBagConstraints24.gridx = 3;
		gridBagConstraints24.gridy = 3;
		GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
		gridBagConstraints30.gridx = 3;
		gridBagConstraints30.anchor = GridBagConstraints.WEST;
		gridBagConstraints30.gridy = 7;
		GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
		gridBagConstraints29.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints29.gridy = 7;
		gridBagConstraints29.weightx = 1.0;
		gridBagConstraints29.anchor = GridBagConstraints.WEST;
		gridBagConstraints29.gridx = 1;
		GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
		gridBagConstraints28.gridx = 0;
		gridBagConstraints28.gridy = 7;
		jLabelKostenstelle = new JLabel();
		jLabelKostenstelle.setText("Kostenstelle");
		GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
		gridBagConstraints27.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints27.gridy = 17;
		gridBagConstraints27.weightx = 1.0;
		gridBagConstraints27.anchor = GridBagConstraints.WEST;
		gridBagConstraints27.gridx = 1;
		GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
		gridBagConstraints26.gridx = 0;
		gridBagConstraints26.gridy = 17;
		jLabelArtikelHersteller = new JLabel();
		jLabelArtikelHersteller.setText("ArtikelHersteller");
		GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
		gridBagConstraints23.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints23.gridy = 11;
		gridBagConstraints23.weightx = 1.0;
		gridBagConstraints23.anchor = GridBagConstraints.WEST;
		gridBagConstraints23.gridx = 1;
		GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
		gridBagConstraints22.gridx = 0;
		gridBagConstraints22.gridy = 11;
		jLabelEtage = new JLabel();
		jLabelEtage.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelEtage.setText("Etage");
		GridBagConstraints gridBagConstraints211 = new GridBagConstraints();
		gridBagConstraints211.gridx = 4;
		gridBagConstraints211.weightx = 1.0D;
		gridBagConstraints211.weighty = 1.0D;
		gridBagConstraints211.gridy = 23;
		GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
		gridBagConstraints20.gridx = 0;
		gridBagConstraints20.weightx = 1.0D;
		gridBagConstraints20.weighty = 1.0D;
		gridBagConstraints20.gridy = 23;
		GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
		gridBagConstraints19.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints19.gridy = 16;
		gridBagConstraints19.weightx = 1.0;
		gridBagConstraints19.anchor = GridBagConstraints.WEST;
		gridBagConstraints19.gridx = 1;
		GridBagConstraints gridBagConstraints181 = new GridBagConstraints();
		gridBagConstraints181.gridx = 3;
		gridBagConstraints181.gridy = 16;
		GridBagConstraints gridBagConstraints161 = new GridBagConstraints();
		gridBagConstraints161.gridx = 0;
		gridBagConstraints161.gridy = 16;
		jLabelBestellnummerKEG = new JLabel();
		jLabelBestellnummerKEG.setText("Artikel");
		GridBagConstraints gridBagConstraints151 = new GridBagConstraints();
		gridBagConstraints151.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints151.gridy = 21;
		gridBagConstraints151.weightx = 1.0;
		gridBagConstraints151.anchor = GridBagConstraints.WEST;
		gridBagConstraints151.gridx = 1;
		GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
		gridBagConstraints14.gridx = 0;
		gridBagConstraints14.gridy = 21;
		jLabelMengeneinheit = new JLabel();
		jLabelMengeneinheit.setText("Mengeneinheit");
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 19;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.anchor = GridBagConstraints.WEST;
		gridBagConstraints13.gridx = 1;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.gridy = 19;
		jLabelMenge = new JLabel();
		jLabelMenge.setText("Menge");
		GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
		gridBagConstraints111.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints111.gridy = 15;
		gridBagConstraints111.weightx = 1.0;
		gridBagConstraints111.anchor = GridBagConstraints.WEST;
		gridBagConstraints111.gridx = 1;
		GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
		gridBagConstraints101.gridx = 0;
		gridBagConstraints101.gridy = 15;
		jLabelPosition = new JLabel();
		jLabelPosition.setText("Position");
		GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
		gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints9.gridy = 14;
		gridBagConstraints9.weightx = 1.0;
		gridBagConstraints9.anchor = GridBagConstraints.WEST;
		gridBagConstraints9.gridx = 1;
		GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
		gridBagConstraints8.gridx = 0;
		gridBagConstraints8.gridy = 14;
		jLabelEbene = new JLabel();
		jLabelEbene.setText("Ebene");
		GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
		gridBagConstraints71.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints71.gridy = 13;
		gridBagConstraints71.weightx = 1.0;
		gridBagConstraints71.anchor = GridBagConstraints.WEST;
		gridBagConstraints71.gridx = 1;
		GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
		gridBagConstraints61.gridx = 0;
		gridBagConstraints61.gridy = 13;
		jLabelSaeule = new JLabel();
		jLabelSaeule.setText("Säule");
		GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
		gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints5.gridy = 12;
		gridBagConstraints5.weightx = 1.0;
		gridBagConstraints5.anchor = GridBagConstraints.WEST;
		gridBagConstraints5.gridx = 1;
		GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
		gridBagConstraints42.gridx = 0;
		gridBagConstraints42.gridy = 12;
		jLabelZeile = new JLabel();
		jLabelZeile.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelZeile.setText("Zeile");
		GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
		gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints3.gridy = 8;
		gridBagConstraints3.weightx = 1.0;
		gridBagConstraints3.anchor = GridBagConstraints.WEST;
		gridBagConstraints3.gridx = 1;
		GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
		gridBagConstraints21.gridx = 3;
		gridBagConstraints21.gridy = 8;
		GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
		gridBagConstraints11.gridx = 0;
		gridBagConstraints11.gridy = 8;
		jLabelHalle = new JLabel();
		jLabelHalle.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabelHalle.setText("Halle");
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
		gridBagConstraints32.gridy = 10;
		gridBagConstraints32.weightx = 1.0;
		gridBagConstraints32.anchor = GridBagConstraints.WEST;
		gridBagConstraints32.weighty = 0.0D;
		gridBagConstraints32.gridx = 0;
		GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
		gridBagConstraints18.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints18.gridy = 3;
		gridBagConstraints18.weightx = 1.0;
		gridBagConstraints18.gridx = 1;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints31.gridx = 0;
		gridBagConstraints31.gridy = 24;
		gridBagConstraints31.gridheight = 1;
		gridBagConstraints31.gridwidth = 5;
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
		GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
		gridBagConstraints10.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints10.gridy = 6;
		gridBagConstraints10.weightx = 1.0;
		gridBagConstraints10.anchor = GridBagConstraints.WEST;
		gridBagConstraints10.gridx = 1;
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.anchor = GridBagConstraints.WEST;
		gridBagConstraints7.fill = GridBagConstraints.NONE;
		gridBagConstraints7.gridy = 2;
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
		gridBagConstraints4.gridy = 6;
		jLabelEntnehmer = new JLabel();
		jLabelEntnehmer.setText("Entnehmer");
		GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
		gridBagConstraints2.gridx = 0;
		gridBagConstraints2.anchor = GridBagConstraints.EAST;
		gridBagConstraints2.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints2.gridy = 3;
		jLabelStatus = new JLabel();
		jLabelStatus.setText("Status");
		GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
		gridBagConstraints1.gridx = 0;
		gridBagConstraints1.anchor = GridBagConstraints.EAST;
		gridBagConstraints1.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints1.gridy = 2;
		jLabelErstellungsDatum = new JLabel();
		jLabelErstellungsDatum.setText("Erstellungsdatum");
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelEinlagerungId = new JLabel();
		jLabelEinlagerungId.setText("Einlagerung-ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(jLabelEinlagerungId, gridBagConstraints);
		this.add(jLabelErstellungsDatum, gridBagConstraints1);
		this.add(jLabelStatus, gridBagConstraints2);
		this.add(jLabelEntnehmer, gridBagConstraints4);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJDateChooserErstellungsDatum(), gridBagConstraints7);
		this.add(getJTextFieldStatus(), gridBagConstraints18);
		this.add(getJTextFieldEntnehmer(), gridBagConstraints10);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(jLabelHalle, gridBagConstraints11);
		this.add(getJButtonMatchCodeHalle(), gridBagConstraints21);
		this.add(getJTextFieldHalle(), gridBagConstraints3);
		this.add(jLabelZeile, gridBagConstraints42);
		this.add(getJFormattedTextFieldZeile(), gridBagConstraints5);
		this.add(jLabelSaeule, gridBagConstraints61);
		this.add(getJFormattedTextFieldSaeule(), gridBagConstraints71);
		this.add(jLabelEbene, gridBagConstraints8);
		this.add(getJFormattedTextFieldEbene(), gridBagConstraints9);
		this.add(jLabelPosition, gridBagConstraints101);
		this.add(getJFormattedTextFieldPosition(), gridBagConstraints111);
		this.add(jLabelMenge, gridBagConstraints12);
		this.add(getJFormattedTextFieldMenge(), gridBagConstraints13);
		this.add(jLabelMengeneinheit, gridBagConstraints14);
		this.add(getJFormattedTextFieldMengeneinheit(), gridBagConstraints151);
		this.add(jLabelBestellnummerKEG, gridBagConstraints161);
		this.add(getJButtonMatchcodeMengeneinheit(), gridBagConstraints181);
		this.add(getJFormattedTextFieldArtikel(), gridBagConstraints19);
		this.add(getJPanelOben1(), gridBagConstraints20);
		this.add(getJPanelOben11(), gridBagConstraints211);
		this.add(jLabelEtage, gridBagConstraints22);
		this.add(getJTextFieldEtage(), gridBagConstraints23);
		this.add(jLabelArtikelHersteller, gridBagConstraints26);
		this.add(getJTextArtikelHersteller(), gridBagConstraints27);
		this.add(jLabelKostenstelle, gridBagConstraints28);
		this.add(getJTextKostenstelle(), gridBagConstraints29);
		this.add(getJButtonMatchcodeKostenstelle(), gridBagConstraints30);
		this.add(getJButtonMatchcodeKostenstelle1(), gridBagConstraints24);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				EinlagerungPosDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				EinlagerungPosDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
					try {
						EinlagerungPosDetailsView.this.uebernehmeDaten();
					} catch (LagerException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				Log.log().finest("focusLost()");
			}
		});
	}

	//Diese Funktion übernimmt Benutzer-Eingabe-Daten aus allen Komponenten(Texte, Auswahl etc.)
	@Override
	protected void uebernehmeDaten() throws LagerException {
		if (getModelBean()!=null){
			if (getModelBean().getIBean()!=null){
				if (getModelBean().getIBean() instanceof EinlagerungPosBean){
					EinlagerungPosBean einlagerungBean = (EinlagerungPosBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt EinlagerungPosBean sein. Es sei denn, dass Objet ist neu(INSERT).
					if(!(!(einlagerungBean.getBeanDBStatus()!=BeanDBStatus.INSERT) && (einlagerungBean.getId().compareTo(Integer.decode(getJTextFieldId().getText()))!=0))){
//						String lhName = getJTextFieldHerstellerLieferant().getText().trim();
//						if(!lhName.equals("")){
//							//Name wurde angegeben.
//							try {
//     							if (einlagerungBean.getLhBean()!=null){
//	    							//Es gibt den "alten" Lieferanten
//		    						if(lhName.compareToIgnoreCase(einlagerungBean.getLhBean().getName())!=0){
//			    						//neuen Lieferanten holen
//				    					LhBean lhBean = ((EinlagerungDetailsController)getController()).getLhAnhandName(lhName); //Hole Lieferanten von DB
//					    				einlagerungBean.setLhBean(lhBean); //Der aktuelle Lieferant wird durch einen neuen ausgetauscht.
//						    		}else{
//							    		//Der Lieferant-Name hat sich nicht geändert.
//								    }
//							    }else{
//							    	//Lieferant war nicht vorhanden und ist neu angegeben.
//								    LhBean lhBean = ((EinlagerungDetailsController)getController()).getLhAnhandName(lhName);
//									einlagerungBean.setLhBean(lhBean); //Der aktuelle Lieferant wird durch einen neuen ausgetauscht.
//							    }
//							} catch (HerstellerLieferantLagerException e) {
//								getJTextFieldHerstellerLieferant().setText(lhName + " <Fehler>");
//								e.printStackTrace();
//							} catch (SQLException e) {
//								getJTextFieldHerstellerLieferant().setText(lhName + " <Fehler>");
//								e.printStackTrace();
//							} //Hole Lieferanten von DB
						}else{
						    //Der Name wurde gelöscht, oder nicht angegeben.
//							einlagerungBean.setLhBean(null); //Lieferant ist nicht angegeben.
						}
					}else{
						Log.log().severe("Fehler4");
					}
				}else{
					Log.log().severe("Fehler3");
				}
			}else{
				Log.log().severe("Fehler2");
			}
	
//			Log.log().severe("Fehler");
//			}else{}
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

	/**
	 * This method initializes jTextFieldEntnehmer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldEntnehmer() {
		if (jTextFieldEntnehmer == null) {
			jTextFieldEntnehmer = new JTextField();
			jTextFieldEntnehmer.setPreferredSize(new Dimension(100, 20));
			jTextFieldEntnehmer.setEditable(false);
		}
		return jTextFieldEntnehmer;
	}

	@Override
	public DetailsController getController() {
		return detailsController;
	}

	@Override
	protected void setController(DetailsController detailsController) {
		this.detailsController = detailsController;
	}

	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		getJComboBoxFehler().removeAllItems(); // alte Fehler werden gelöscht.
		setzeHintergrund();

		if (modelKnotenBean != null) {
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.EINLAGERUNGSPOSITION) {
				setModelBean(modelKnotenBean);// merken
				EinlagerungPosBean bean = (EinlagerungPosBean) modelKnotenBean
						.getIBean();
				// id anzeigen
				getJTextFieldId().setText(bean.getId().toString());
				getJTextFieldId().setEnabled(false);
				// Artikel-Daten anzeigen
				ArtikelBean artikelBean = bean.getArtikelBean();
				getJFormattedTextFieldArtikel().setText(artikelBean.toString());
				getJTextFieldHalle()
						.setText(getHalleBean().toString());
				getJTextFieldEtage().setText(
						bean.getPositionBean().getEbeneBean().getSaeuleBean()
								.getZeileBean().getEtageBean().getName());
				
				//getJFormattedTextFieldZeile().getText()
				getJFormattedTextFieldZeile().removePropertyChangeListener("value", this);				
				getJFormattedTextFieldZeile().removeKeyListener(this);				
				getJFormattedTextFieldZeile().setValue(
						bean.getPositionBean().getEbeneBean().getSaeuleBean()
								.getZeileBean().getNummer());
				getJFormattedTextFieldZeile().addKeyListener(this);
				getJFormattedTextFieldZeile().addPropertyChangeListener("value", this);				
				//getJFormattedTextFieldSaeule().getText()	
				
				getJFormattedTextFieldSaeule().removePropertyChangeListener("value", this);				
				getJFormattedTextFieldSaeule().setValue(
						bean.getPositionBean().getEbeneBean().getSaeuleBean().getNummer());
				getJFormattedTextFieldSaeule().addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldEbene().removePropertyChangeListener("value", this);
				getJFormattedTextFieldEbene()
						.setValue(bean.getPositionBean().getEbeneBean().getNummer());
				getJFormattedTextFieldEbene().addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldPosition().removePropertyChangeListener("value", this);
				getJFormattedTextFieldPosition().setValue(
						getPositionBean().getNummer());
				getJFormattedTextFieldPosition().addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldMenge().removePropertyChangeListener("value", this);
				getJFormattedTextFieldMenge().setValue(
						bean.getMenge());
				getJFormattedTextFieldMenge().addPropertyChangeListener("value", this);
				
				getJFormattedTextFieldMengeneinheit().setText(bean.getMengenEinheitBean().getName());
				getJTextKostenstelle().setText(bean.getKostenstelle().getName());
//				getJTextFieldHalle().setText(bean.getHalleBean().getName());

				getJTextArtikelHersteller().setText(bean.getArtikelBean().getHersteller().getName());
				
				getJTextFieldStatus().setText(bean.getStatus().toString());
				
				
				setEnabled(bean.getStatus().equals(EinlagerungStatus.OFFEN));
				
				this.repaint();// alte Komponenten werden gelöscht
				this.invalidate(); // alle bis zu dem obersten Kontainer auf
				// ungültig
				this.validate(); // werden gezeichnet.
				this.revalidate(); // Layout-Manager tut seinen JOB, und richtet
				// this.invalidate();
			}
		}
	}

	
	private HalleBean getHalleBean() {
		return getZeileBean().getHalleBean();
		// if (halleBean == null) {
		// halleBean = new HalleBean();
		// }
		// return halleBean;
	}
	
	private ZeileBean getZeileBean() {
		return getSaeuleBean().getZeileBean();
		// if (zeileBean == null) {
		// zeileBean = new ZeileBean();
		// }
		// return zeileBean;
	}
	
	private SaeuleBean getSaeuleBean() {
		return getEbeneBean().getSaeuleBean();
		// if (saeuleBean == null) {
		// saeuleBean = new SaeuleBean();
		// }
		// return saeuleBean;
	}	
	
	private EbeneBean getEbeneBean() {
		return getPositionBean().getEbeneBean();
		// if (ebeneBean == null) {
		// ebeneBean = new EbeneBean();
		// }
		// return ebeneBean;
	}
	
	private PositionBean getPositionBean() {
		 
		return getEinlagerungPosBean().getPositionBean();
		// if (positionBean == null) {
		// positionBean = new PositionBean();
		// }
		// return positionBean;
	}
	
	private EinlagerungPosBean getEinlagerungPosBean(){
		return (EinlagerungPosBean) getModelBean()
			.getIBean();
	}	
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
					EinlagerungPosDetailsView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
				}
			});
		}
		return jComboBoxFehler;
	}

//	protected void setMatchCodeLieferanten(LhBean lhBean) {
//		getJTextFieldHerstellerLieferant().setText(lhBean.getName());
//		getJTextFieldHerstellerLieferant().requestFocusInWindow();
//	}

	/**
	 * This method initializes jTextFieldStatus	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldStatus() {
		if (jTextFieldStatus == null) {
			jTextFieldStatus = new JTextField();
			jTextFieldStatus.setEditable(false);
		}
		return jTextFieldStatus;
	}

//	private AbstractTableModel getAbstractTableModelEinlagerungPosition() {
//		if(abstractTableModelEinlagerungPosition==null){
//			abstractTableModelEinlagerungPosition = new AbstractTableModel() {
//				private String[] columnNames = {"","id","KEG-Nummer","Bezeichnung","Typ","Menge","ME","Position","Ebene","Saeule","Zeile","Halle","Status"};
//
//				@Override
//				public String getColumnName(int column){
//					return columnNames[column];
//				}
//				
//				@Override
//				public Object getValueAt(int row, int column) {
//					if (getModelBean()==null){
//						return "";
//					}
//					ModelKnotenBean modelKnotenBean = getModelBean().getKinderList().get(row);
//					if (modelKnotenBean.getIBean() instanceof EinlagerungPosBean){
//						EinlagerungPosBean einlagerungPosBean = (EinlagerungPosBean)modelKnotenBean.getIBean();
//							switch(column){
//							case 0 : return new Integer(row+1).toString(); 
//							case 1 : return einlagerungPosBean.getId().toString(); 
//							case 2 : if (einlagerungPosBean.getArtikelBean()==null){
//								return "";  
//							}else{
//								return einlagerungPosBean.getArtikelBean().getKeg_nr().toString();
//							}
//							case 3 : if (einlagerungPosBean.getArtikelBean()==null){
//								return "";  
//							}else{
//								return einlagerungPosBean.getArtikelBean().getBezeichnung().toString();
//							}
//							case 4 : if (einlagerungPosBean.getArtikelBean()==null){
//								return "";  
//							}else{
//								return einlagerungPosBean.getArtikelBean().getTyp().toString();
//							}
//							case 5 : return einlagerungPosBean.getMenge().toString(); 
//							case 6 :  if (einlagerungPosBean.getArtikelBean()==null){
//								return "";  
//							}else{
//								if (einlagerungPosBean.getArtikelBean().getMengenEinheitBean()==null){
//									return "";
//								}else{
//									return einlagerungPosBean.getArtikelBean().getMengenEinheitBean().getName();
//								}
//							} 
//							case 7 :  if (einlagerungPosBean.getPositionBean()==null){
//								return "";  
//							}else{
//								if (einlagerungPosBean.getPositionBean().getNummer()==null){
//									return "";
//								}else{
//									return einlagerungPosBean.getPositionBean().getNummer().toString();
//								}
//							}
//							case 8 :  if (einlagerungPosBean.getEbeneBean()==null){
//								return "";  
//							}else{
//								if (einlagerungPosBean.getEbeneBean().getNummer()==null){
//									return "";
//								}else{
//									return einlagerungPosBean.getEbeneBean().getNummer().toString();
//								}
//							}
//							case 9 :  if (einlagerungPosBean.getSaeuleBean().getNummer()==null){
//								return "";  
//							}else{
//								if (einlagerungPosBean.getSaeuleBean().getNummer()==null){
//									return "";
//								}else{
//									return einlagerungPosBean.getSaeuleBean().getNummer().toString();
//								}
//							}
//							case 10 :  if (einlagerungPosBean.getZeileBean()==null){
//								return "";  
//							}else{
//								if (einlagerungPosBean.getZeileBean().getNummer()==null){
//									return "";
//								}else{
//									return einlagerungPosBean.getZeileBean().getNummer().toString();
//								}
//							}
//							case 11 :  if (einlagerungPosBean.getHalleBean()==null){
//								return "";  
//							}else{
//								if (einlagerungPosBean.getHalleBean().getName()==null){
//									return "";
//								}else{
//									return einlagerungPosBean.getHalleBean().getName().toString();
//								}
//							}
//
////							case 7 : return einlagerungPosBean.getLiefertermin().toString(); 
//							case 12 : return einlagerungPosBean.getStatus().toString(); 
//							case 999 : return einlagerungPosBean; 
//							default : return "Fehler, column:"+new Integer(column).toString(); 
//							}
//					}else{
//						return "Falsche Klasse:"+modelKnotenBean.getIBean().toString();
//					}
//				}
//				
//				@Override
//				public int getRowCount() {
//					 if (getModelBean()==null){
//						 return 0;
//					 }else{
//					     return getModelBean().getKinderList().size();
//					 }
//				}
//				
//				@Override
//				public int getColumnCount() {
//					return columnNames.length;
//				}
//			};
//		}
//		return abstractTableModelEinlagerungPosition;
//	}


	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jToolBar = new JToolBar();
			jToolBar.setFloatable(false);
		}
		return jToolBar;
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


//	@Override
//	public void setStandardFocusPosition() {
//		getJButtonMatchCodeLieferant().requestFocus();
//	}

	@Override
	public ArrayList<Fehler> pruefeDich() {
		ArrayList<Fehler> errors = new ArrayList<Fehler>();
		try {

			if (!getJFormattedTextFieldZeile().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldZeile())) {
				errors.add(new Fehler(72));
			} else {
				leseAusgetJFormattedTextFieldZeile();
			}

			if (!getJFormattedTextFieldSaeule().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldSaeule())) {
				errors.add(new Fehler(77));
			} else {
				leseAusgetJFormattedTextFieldSaeule();
			}

			if (!getJFormattedTextFieldEbene().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldEbene())) {
				errors.add(new Fehler(76));
			} else {
				leseAusgetJFormattedTextFieldEbene();
			}

			if (!getJFormattedTextFieldPosition().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldPosition())) {
				errors.add(new Fehler(75));
			} else {
				leseAusgetJFormattedTextFieldPosition();
			}

			if (!getJFormattedTextFieldMenge().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldMenge())) {
				errors.add(new Fehler(74));
			} else {
				leseAusgetJFormattedTextFieldMenge();
			}

			// die gesamte Auswahl des Benutzers wird zusammen gefasst.
//			getPositionBean().setEbeneBean(getEbeneBean());
//			getEbeneBean().setSaeuleBean(getSaeuleBean());
//			getSaeuleBean().setZeileBean(getZeileBean());
//			getZeileBean().setEtageBean(getEtageBean());
//			getZeileBean().setHalleBean(getHalleBean());
//			getEtageBean().setHalleBean(getHalleBean());

			((EinlagerungPosDetailsController) getController())
					.vervollstaendigePosition(getPositionBean());

			// Wir gehen davon aus, dass der Benutzer die Daten korrekt
			// eingegeben hat.
			// Die Prüfung erfolgt beim Speichern der Daten
			EinlagerungPosBean bestandBean = (EinlagerungPosBean) getModelBean()
					.getIBean();
			bestandBean.setPositionBean(getPositionBean());

		} catch (Exception e) {
			// Alle Fehler abfangen
			errors.add(new Fehler(36, FehlerTyp.FEHLER, e.getMessage()));
			e.printStackTrace();
		}
		return errors;
	}
	
	
	@Override
	public void setEnabled (boolean enabled){
		super.setEnabled(enabled);
		getJDateChooserErstellungsDatum().setEnabled(false);
		getJButtonMatchCodeHalle().setEnabled(enabled);
		getJButtonMatchcodeKostenstelle().setEnabled(enabled);
		getJButtonMatchcodeKostenstelle1().setEnabled(enabled);
		getJButtonMatchcodeMengeneinheit().setEnabled(enabled);
		getJFormattedTextFieldZeile().setEnabled(enabled);
		getJFormattedTextFieldSaeule().setEnabled(enabled);
		getJFormattedTextFieldEbene().setEnabled(enabled);
		getJFormattedTextFieldPosition().setEnabled(enabled);
		getJFormattedTextFieldMenge().setEnabled(enabled);
//		getJButtonMatchCodeLieferant().setEnabled(enabled);
//		getJButtonNeu().setEnabled(enabled);
//		getJButtonLoeschen().setEnabled(enabled);
//		getJTextFieldEmail().setEditable(enabled);
//		getJButtonMatchCodeEmail().setEnabled(enabled);
	}



	@Override
	public void setStandardFocusPosition() {
		// TODO Auto-generated method stub
		
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
//						if (pruefeFehler()) {
							((EinlagerungPosDetailsController) getController())
									.holeHalle();
//						}
					} catch (SQLException e1) {
						getJComboBoxFehler().addItem(
								new Fehler(24, FehlerTyp.FEHLER, e1
										.getMessage()));
						e1.printStackTrace();
					} catch (LagerException e1) {
						getJComboBoxFehler().addItem(
								new Fehler(24, FehlerTyp.FEHLER, e1
										.getMessage()));
						e1.printStackTrace();
					}
				}
			});
		}
		return jButtonMatchCodeHalle;
	}

	/**
	 * This method initializes jTextFieldHalle	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldHalle() {
		if (jTextFieldHalle == null) {
			jTextFieldHalle = new JTextField();
			jTextFieldHalle.setEditable(false);
		}
		return jTextFieldHalle;
	}

	/**
	 * This method initializes jTextFieldZeile	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldZeile() {
		if (jFormattedTextFieldZeile == null) {
			jFormattedTextFieldZeile = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldZeile.setInputVerifier(LagerFormate
					.getInputVerifier());
			jFormattedTextFieldZeile.setPreferredSize(new Dimension(80, 20));
		}
		return jFormattedTextFieldZeile;
	}

	/**
	 * This method initializes jTextFieldSaeule	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldSaeule() {
		if (jFormattedTextFieldSaeule == null) {
			jFormattedTextFieldSaeule = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldSaeule.setInputVerifier(LagerFormate
					.getInputVerifier());
//			jFormattedTextFieldSaeule.addPropertyChangeListener("value", this);
			jFormattedTextFieldSaeule.addKeyListener(this);
		}
		return jFormattedTextFieldSaeule;
	}

	/**
	 * This method initializes jTextFieldEbene	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldEbene() {
		if (jFormattedTextFieldEbene == null) {
			jFormattedTextFieldEbene = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldEbene.setInputVerifier(LagerFormate
					.getInputVerifier());
			jFormattedTextFieldEbene.addKeyListener(this);
		}
		return jFormattedTextFieldEbene;
	}

	/**
	 * This method initializes jTextFieldPosition	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldPosition() {
		if (jFormattedTextFieldPosition == null) {
			jFormattedTextFieldPosition = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 99));
			jFormattedTextFieldPosition.setInputVerifier(LagerFormate
					.getInputVerifier());
			jFormattedTextFieldPosition.addKeyListener(this);
		}
		return jFormattedTextFieldPosition;
	}

	/**
	 * This method initializes jFormattedTextFieldMengen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldMenge() {
		if (jFormattedTextFieldMenge == null) {
			jFormattedTextFieldMenge = new JFormattedTextField(
					new LagerEmptyNumberFormatter(1, 999));
			jFormattedTextFieldMenge.setInputVerifier(LagerFormate
					.getInputVerifier());
			jFormattedTextFieldMenge.addKeyListener(this);		}
		return jFormattedTextFieldMenge;
	}

	/**
	 * This method initializes jFormattedTextFieldMengeneinheit	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldMengeneinheit() {
		if (jFormattedTextFieldMengeneinheit == null) {
			jFormattedTextFieldMengeneinheit = new JFormattedTextField();
			jFormattedTextFieldMengeneinheit.setPreferredSize(new Dimension(80, 20));
			jFormattedTextFieldMengeneinheit.setEditable(false);
		}
		return jFormattedTextFieldMengeneinheit;
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
								((EinlagerungPosDetailsController)getController()).holeBestandspackstueck(getPositionBean());
							} catch (SQLException ex) {
								getJComboBoxFehler().addItem(new Fehler(24,FehlerTyp.FEHLER,ex.getMessage()));
								ex.printStackTrace();
							} catch (LagerException ex) {
								getJComboBoxFehler().addItem(ex.getFehler());
								ex.printStackTrace();
							}
						}
					});
		}
		return jButtonMatchcodeMengeneinheit;
	}

	/**
	 * This method initializes jTextFieldArtikel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldArtikel() {
		if (jFormattedTextFieldArtikel == null) {
			jFormattedTextFieldArtikel = new JFormattedTextField();
			jFormattedTextFieldArtikel.setEditable(false);
		}
		return jFormattedTextFieldArtikel;
	}

	/**
	 * This method initializes jPanelOben1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelOben1() {
		if (jPanelOben1 == null) {
			jPanelOben1 = new JPanel();
			jPanelOben1.setLayout(new GridBagLayout());
			jPanelOben1.setPreferredSize(new Dimension(10, 10));
		}
		return jPanelOben1;
	}

	/**
	 * This method initializes jPanelOben11	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelOben11() {
		if (jPanelOben11 == null) {
			jPanelOben11 = new JPanel();
			jPanelOben11.setLayout(new GridBagLayout());
			jPanelOben11.setPreferredSize(new Dimension(10, 10));
		}
		return jPanelOben11;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object source = evt.getSource();
		Log.log().severe("propertyChange");
		// Das Model wird geändert.
		if (source == getJFormattedTextFieldZeile()) {
			leseAusgetJFormattedTextFieldZeile();
		}
		if (source == getJFormattedTextFieldSaeule()) {
			leseAusgetJFormattedTextFieldSaeule();
		}
		if (source == getJFormattedTextFieldEbene()) {
			leseAusgetJFormattedTextFieldEbene();
		}
		if (source == getJFormattedTextFieldPosition()) {
			leseAusgetJFormattedTextFieldPosition();
			Log.log().severe("leseAusgetJFormattedTextFieldPosition");
		}
		if (source == getJFormattedTextFieldMenge()) {
			leseAusgetJFormattedTextFieldMenge();
		}

		//Status des Bestandspackstückes wird auf "UPDATE" gesetzt. 
		getEinlagerungPosBean().setId(getEinlagerungPosBean().getId());

		try {
			((EinlagerungPosDetailsController) getController())
				.vervollstaendigePosition(getPositionBean());
		} catch (LagerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		};
		
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}

	private void leseAusgetJFormattedTextFieldSaeule() {
		// BestandspackstueckBean bestandBean = (BestandspackstueckBean)
		// getModelBean()
		// .getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldSaeule().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldSaeule().getValue())
						.intValue();
				getSaeuleBean().setNummer(wert);
				getSaeuleBean().setId(0);
			}
		} catch (NumberFormatException ex) {
		}
	}
	
	
	private void leseAusgetJFormattedTextFieldEbene() {
		// BestandspackstueckBean bestandBean = (BestandspackstueckBean)
		// getModelBean()
		// .getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldEbene().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldEbene().getValue())
						.intValue();
				getEbeneBean().setNummer(wert);
				getEbeneBean().setId(0);
			}
		} catch (NumberFormatException ex) {
		}
	}

	private void leseAusgetJFormattedTextFieldPosition() {
		// BestandspackstueckBean bestandBean = (BestandspackstueckBean)
		// getModelBean()
		// .getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldPosition().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldPosition().getValue())
						.intValue();
				getPositionBean().setNummer(wert);
				getPositionBean().setId(0);
			}
		} catch (NumberFormatException ex) {
		}

	}

	private void leseAusgetJFormattedTextFieldMenge() {
		EinlagerungPosBean bestandBean = (EinlagerungPosBean) getModelBean()
				.getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldMenge().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldMenge().getValue())
						.intValue();
				bestandBean.setMenge(wert);
			}
		} catch (NumberFormatException ex) {
		}
	}
	
	private void leseAusgetJFormattedTextFieldZeile() {
		// BestandspackstueckBean bestandBean = (BestandspackstueckBean)
		// getModelBean()
		// .getIBean();
		Integer wert;
		try {
			if (getJFormattedTextFieldZeile().getValue() != null) {
				wert = ((Number) getJFormattedTextFieldZeile().getValue())
						.intValue();
				getZeileBean().setNummer(wert);
				getZeileBean().setId(0);
			}
		} catch (NumberFormatException ex) {
		}
	}
	
	/**
	 * This method initializes jTextFieldEtage	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldEtage() {
		if (jTextFieldEtage == null) {
			jTextFieldEtage = new JTextField();
			jTextFieldEtage.setEditable(false);
		}
		return jTextFieldEtage;
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
	 * This method initializes jButtonMatchcodeKostenstelle	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchcodeKostenstelle() {
		if (jButtonMatchcodeKostenstelle == null) {
			jButtonMatchcodeKostenstelle = new JButton();
			jButtonMatchcodeKostenstelle.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButtonMatchcodeKostenstelle.setText("...");
			jButtonMatchcodeKostenstelle
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
//								if (pruefeFehler()) {
									((EinlagerungPosDetailsController) getController())
											.holeKostenstelle();
//								}
							} catch (SQLException e1) {
								getJComboBoxFehler().addItem(
										new Fehler(24, FehlerTyp.FEHLER, e1
												.getMessage()));
								e1.printStackTrace();
							} catch (LagerException e1) {
								getJComboBoxFehler().addItem(
										new Fehler(24, FehlerTyp.FEHLER, e1
												.getMessage()));
								e1.printStackTrace();
							}
						}
					});
		}
		return jButtonMatchcodeKostenstelle;
	}

	public void setHalle(HalleBean halleBean) {
		getZeileBean().setHalleBean(halleBean);
		// setHalleBean(halleBean);
		getJTextFieldHalle().setText(getHalleBean().getName());
	}

	public void setKostenstelle(KostenstelleBean kostenstelleBean) {
		// Das Model wird geändert.
		((EinlagerungPosBean) getModelBean().getIBean())
				.setKostenstelle(kostenstelleBean);
		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

//	public void setArtikel(ArtikelBean artikelBean) {
//		//Das Model wird geändert.
//		EinlagerungPosBean bean = ((EinlagerungPosBean)getModelBean().getIBean());
//		bean.setArtikelBean(artikelBean);
////		bean.setMengenEinheitBean(artikelBean.getMengenEinheitBean());
//		
//		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
//		getController().ausgewaehlterKnotenIstGeandert();
//	}

	public void setBestandspackstueck(
			BestandspackstueckBean bestandspackstueckBean) {
		//Das Model wird geändert.
		EinlagerungPosBean bean = ((EinlagerungPosBean)getModelBean().getIBean());
		bean.setArtikelBean(bestandspackstueckBean.getArtikelBean());
		bean.setMenge(0);
//		bean.setMenge(bestandspackstueckBean.getMenge());
		bean.setMengenEinheitBean(bestandspackstueckBean.getArtikelBean().getMengenEinheitBean());
//		bean.setMengenEinheitBean(artikelBean.getMengenEinheitBean());
		//Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		getController().ausgewaehlterKnotenIstGeandert();
	}

	/**
	 * This method initializes jButtonMatchcodeKostenstelle1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonMatchcodeKostenstelle1() {
		if (jButtonBestaetigen == null) {
			jButtonBestaetigen = new JButton();
			jButtonBestaetigen.setMnemonic(KeyEvent.VK_UNDEFINED);
			jButtonBestaetigen.setText("Bestätigen");
			jButtonBestaetigen
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					try {
						if (pruefeFehler()) {
							((EinlagerungPosDetailsController) getController())
									.bestaetigeEinlagerungPosition();
						}
//					} catch (SQLException e1) {
//						getJComboBoxFehler().addItem(
//								new Fehler(24, FehlerTyp.FEHLER, e1
//										.getMessage()));
//						e1.printStackTrace();
//					} catch (LagerException e1) {
//						getJComboBoxFehler().addItem(
//								new Fehler(24, FehlerTyp.FEHLER, e1
//										.getMessage()));
//						e1.printStackTrace();
//					}
				}
			});
			
		}
		return jButtonBestaetigen;
	}



	
}
