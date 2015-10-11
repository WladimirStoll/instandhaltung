package de.keag.lager.panels.frame.katalog.pane.details.anforderung;

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
import de.keag.lager.core.formatter.LagerStringFormatter;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.artikel.pane.details.artikel.ArtikelDetailsController;
import de.keag.lager.panels.frame.artikel.pane.details.artikelPosition.ArtikelPosDetailsController;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.ebene.EbeneBean;
import de.keag.lager.panels.frame.halle.model.HalleBean;
import de.keag.lager.panels.frame.halle.pane.details.bestandspackstueck.BestandspackstueckDetailsController;
import de.keag.lager.panels.frame.katalog.KatalogBean;
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


public class KatalogDetailsView extends DetailsView implements PropertyChangeListener  {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelKatalogId = null;
	private JTextField jTextFieldId = null;
	private DetailsController detailsController = null;  //  @jve:decl-index=0:
	private JComboBox jComboBoxFehler = null;
	private AbstractTableModel abstractTableModelKatalogition = null;
	private JToolBar jToolBar = null;
	private ListSelectionListener listSelectionListener;
	private JPanel jPanelOben = null;
	private JLabel jLabelKatalog = null;
	private JFormattedTextField jFormattedTextFieldName = null;
	private JPanel jPanelOben1 = null;
	private JPanel jPanelOben11 = null;
	/**
	 * This is the default constructor
	 * @param einlagerungBlattController 
	 */
	public KatalogDetailsView(KatalogDetailsController einlagerungBlattController) {
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
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 19;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.anchor = GridBagConstraints.WEST;
		gridBagConstraints13.gridx = 1;
		GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
		gridBagConstraints12.gridx = 0;
		gridBagConstraints12.anchor = GridBagConstraints.EAST;
		gridBagConstraints12.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints12.gridy = 19;
		jLabelKatalog = new JLabel();
		jLabelKatalog.setText("Katalogname");
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
		GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
		gridBagConstraints6.fill = GridBagConstraints.NONE;
		gridBagConstraints6.gridy = 1;
		gridBagConstraints6.weightx = 2.0D;
		gridBagConstraints6.anchor = GridBagConstraints.WEST;
		gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints6.gridx = 1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.anchor = GridBagConstraints.EAST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.insets = new Insets(0, 0, 0, 5);
		gridBagConstraints.gridy = 1;
		jLabelKatalogId = new JLabel();
		jLabelKatalogId.setText("Katalog-ID");
		this.setSize(500, 400);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(SystemColor.controlShadow, 1));
		this.add(jLabelKatalogId, gridBagConstraints);
		this.add(getJTextFieldId(), gridBagConstraints6);
		this.add(getJComboBoxFehler(), gridBagConstraints31);
		this.add(getJToolBar(), gridBagConstraints32);
		this.add(getJPanelOben(), gridBagConstraints41);
		this.add(jLabelKatalog, gridBagConstraints12);
		this.add(getJFormattedTextFieldName(), gridBagConstraints13);
		this.add(getJPanelOben1(), gridBagConstraints20);
		this.add(getJPanelOben11(), gridBagConstraints211);
		this.addFocusListener(new java.awt.event.FocusAdapter() {   
			public void focusGained(java.awt.event.FocusEvent e) {
				KatalogDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 5));
				Log.log().finest("focusGained()"); // TODO Auto-generated Event stub focusGained()
			}
			public void focusLost(java.awt.event.FocusEvent e) {
				KatalogDetailsView.this.setBorder(BorderFactory.createLineBorder(SystemColor.inactiveCaption, 5));
					try {
						KatalogDetailsView.this.uebernehmeDaten();
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
				if (getModelBean().getIBean() instanceof KatalogBean){
					KatalogBean einlagerungBean = (KatalogBean)getModelBean().getIBean();
					//id aus dem Formular muss gleich der Id in dem Objekt KatalogBean sein. Es sei denn, dass Objet ist neu(INSERT).
					if(!(!(einlagerungBean.getBeanDBStatus()!=BeanDBStatus.INSERT) && (einlagerungBean.getId().compareTo(Integer.decode(getJTextFieldId().getText()))!=0))){
//						String lhName = getJTextFieldHerstellerLieferant().getText().trim();
//						if(!lhName.equals("")){
//							//Name wurde angegeben.
//							try {
//     							if (einlagerungBean.getLhBean()!=null){
//	    							//Es gibt den "alten" Lieferanten
//		    						if(lhName.compareToIgnoreCase(einlagerungBean.getLhBean().getName())!=0){
//			    						//neuen Lieferanten holen
//				    					LhBean lhBean = ((KatalogDetailsController)getController()).getLhAnhandName(lhName); //Hole Lieferanten von DB
//					    				einlagerungBean.setLhBean(lhBean); //Der aktuelle Lieferant wird durch einen neuen ausgetauscht.
//						    		}else{
//							    		//Der Lieferant-Name hat sich nicht geändert.
//								    }
//							    }else{
//							    	//Lieferant war nicht vorhanden und ist neu angegeben.
//								    LhBean lhBean = ((KatalogDetailsController)getController()).getLhAnhandName(lhName);
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
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.KATALOG) {
				setModelBean(modelKnotenBean);// merken
				KatalogBean bean = (KatalogBean) modelKnotenBean
						.getIBean();
				// id anzeigen
				getJTextFieldId().setText(bean.getId().toString());
				getJTextFieldId().setEnabled(false);
				
				getJFormattedTextFieldName().removePropertyChangeListener("value", this);
				getJFormattedTextFieldName().setValue(
						bean.getName());
				getJFormattedTextFieldName().addPropertyChangeListener("value", this);
				
				this.repaint();// alte Komponenten werden gelöscht
				this.invalidate(); // alle bis zu dem obersten Kontainer auf
				// ungültig
				this.validate(); // werden gezeichnet.
				this.revalidate(); // Layout-Manager tut seinen JOB, und richtet
				// this.invalidate();
			}
		}
	}

	
	private KatalogBean getKatalogBean(){
		return (KatalogBean) getModelBean()
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
					KatalogDetailsView.super.mouseClickedFehler(e, (JComboBox)e.getSource());
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

			if (!getJFormattedTextFieldName().getInputVerifier()
					.shouldYieldFocus(getJFormattedTextFieldName())) {
				errors.add(new Fehler(74));
			} else {
				leseAusgetJFormattedTextFieldName();
			}

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
		getJTextFieldId().setEnabled(false);
		getJFormattedTextFieldName().setEnabled(enabled);
	}



	@Override
	public void setStandardFocusPosition() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method initializes jFormattedTextFieldMengen	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getJFormattedTextFieldName() {
		if (jFormattedTextFieldName == null) {
			jFormattedTextFieldName = new JFormattedTextField(
					new LagerStringFormatter(3, 50));
			jFormattedTextFieldName.setInputVerifier(LagerFormate
					.getInputVerifier());
			jFormattedTextFieldName.addKeyListener(this);		}
		return jFormattedTextFieldName;
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
		if (source == getJFormattedTextFieldName()) {
			leseAusgetJFormattedTextFieldName();
		}
		//Status des Bestandspackstückes wird auf "UPDATE" gesetzt. 
		getKatalogBean().setId(getKatalogBean().getId());

		// Alle Beobachter des Models werden über eine Änderung benachrichtigt.
		Log.log().finest("ausgewaehlterKnotenIstGeandert vor");
		getController().ausgewaehlterKnotenIstGeandert();
		Log.log().finest("ausgewaehlterKnotenIstGeandert nach");
	}


	private void leseAusgetJFormattedTextFieldName() {
		KatalogBean bestandBean = (KatalogBean) getModelBean()
				.getIBean();
		String wert;
			if (getJFormattedTextFieldName().getValue() != null) {
				wert = getJFormattedTextFieldName().getValue().toString();
				bestandBean.setName(wert);
			}
//		getController().ausgewaehlterKnotenIstGeandert();
	}
	
}
