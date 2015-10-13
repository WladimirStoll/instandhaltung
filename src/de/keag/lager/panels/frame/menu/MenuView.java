package de.keag.lager.panels.frame.menu;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import de.keag.lager.core.IPanel;
//import de.keag.lager.core.eigeneKomponenten.JLagerMenuItem;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.MainFrame;
import de.keag.lager.panels.frame.zugriffsrecht.ZugriffsrechtBean;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.SwingConstants;

public class MenuView extends JPanel implements IPanel, IMenuBeobachter{
//	private static MenuView menuView = null;
//	private JMenuBar jMenuBar;  
	private JMenu jMenuFormulareListen = null;  //  @jve:decl-index=0:visual-constraint="147,120"
	private JMenuItem jMenuItemEntnahme = null;
	private JMenuItem jMenuItemBestellAnforderungsListe = null;
	private JMenuItem jMenuItemMindestBestandsUnterstreitung = null;
	private JMenuItem jMenuItemMindestBestandsListe = null;
	private JMenuItem jMenuItemBerichte = null;
	private JMenu jMenuStammdaten = null;  //  @jve:decl-index=0:visual-constraint="364,122"
	private JMenuItem jMenuItemAbteilung = null;
	private JMenuItem jMenuItemBenutzer = null;
	private JMenuItem jMenuItemArtikel = null;
	private JMenuItem jMenuItemHallen = null;
	private JMenuItem jMenuItemAnlagen = null;
	private JMenuItem jMenuItemGrosshandelHersteller = null;
	private JMenu jMenuSystemmenue = null;  //  @jve:decl-index=0:visual-constraint="10,135"
	private JMenuItem jMenuItemLogout = null;
	private MenuController menuController = null;  //  @jve:decl-index=0:
	private JMenuItem jMenuItemBestellung = null;
	private JLabel jLabel = null;
	private JPanel jPanel = null;
	private JMenuItem jMenuItemEinlagerung = null;
	private JMenuItem jMenuItemWartung = null;
	private JMenuItem jMenuItemGrosshandelKatalog;
	private JMenu jMenuFormulareInfo;
	private JMenuItem jMenuItemImpressum;
	private JMenuItem MenuItemDebugging;
	
	protected MenuController getMenuController() {
		return menuController;
	}

	protected void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}

	/**
	 * This method initializes 
	 * 
	 */
	protected MenuView(MenuController menuController) {
		super();
		setMenuController(menuController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
                this.setBackground(new Color(255, 74, 86));
                this.add(getJPanel(), null);
                getJLabel();
	}

	private JLabel getJLabel() {
		if (jLabel==null){
			jLabel = new JLabel();
	        jLabel.setIcon(Run.createMenuImage());
	        jLabel.setHorizontalAlignment(SwingConstants.LEFT);
	        jLabel.setHorizontalTextPosition(SwingConstants.CENTER);
	        jLabel.setBackground(new Color(14, 238, 238));
	        this.add(jLabel, null);
		}
		return jLabel;
	}

	/**
	 * This method initializes jMenuFormulareListen	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuFormulareListen() {
		if (jMenuFormulareListen == null) {
			jMenuFormulareListen = new JMenu();
			jMenuFormulareListen.setText("Formulare / Listen");
			jMenuFormulareListen.add(getJMenuItemEntnahme());
			jMenuFormulareListen.add(getJMenuItemEinlagerung());
			jMenuFormulareListen.add(getJMenuItemWartung());
			jMenuFormulareListen.add(getJMenuItemBestellAnforderungsListe());
			jMenuFormulareListen.add(getJMenuItemBestellung());
			jMenuFormulareListen.add(getJMenuItemMindestBestandsListe());
			jMenuFormulareListen.add(getJMenuItemBerichte());
			Zugriffsrechtpruefung.addRecht(jMenuFormulareListen,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jMenuFormulareListen, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			Zugriffsrechtpruefung.addRecht(jMenuFormulareListen, new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
		}
		return jMenuFormulareListen;
	}
	
	/**
	 * This method initializes jMenuFormulareListen	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuInfo() {
		if (jMenuFormulareInfo == null) {
			jMenuFormulareInfo = new JMenu();
			jMenuFormulareInfo.setText("Info");
			jMenuFormulareInfo.add(getJMenuItemImpressum());
			jMenuFormulareInfo.add(getJMenuItemFehlerProtokollAn());
		}
		return jMenuFormulareInfo;
	}
	
	
	/**
	 * This method initializes jMenuItemEntnahmeEinlagerung	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemImpressum() {
		if (jMenuItemImpressum == null) {
			jMenuItemImpressum = new JMenuItem();
			jMenuItemImpressum.setText("Lizenz");
			jMenuItemImpressum
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showImpressumPane();
					Log.log().finest("Lizenz wird angezeigt.");
				}
			});
		}
		return jMenuItemImpressum;
	}
	
	/**
	 * This method initializes jMenuItemEntnahmeEinlagerung	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemFehlerProtokollAn() {
		if (MenuItemDebugging == null) {
			MenuItemDebugging = new JCheckBoxMenuItem();
			MenuItemDebugging.setText("Debugging");
			MenuItemDebugging
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
//					getMenuController().showImpressumPane();
					if (Log.log().getLevel().intValue() >= Level.WARNING.intValue()){
						Log.log().setLevel(Level.ALL);
						Log.log().finest("Debugging an.");
					}else{
						Log.log().setLevel(Level.WARNING);
						Log.log().severe("Debugging aus.");
					}
				}
			});
		}
		return MenuItemDebugging;
	}	
	

	/**
	 * This method initializes jMenuItemInventur	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemMindestBestandsListe() {
		if (jMenuItemMindestBestandsListe == null) {
			jMenuItemMindestBestandsListe = new JMenuItem();
			jMenuItemMindestBestandsListe.setText("Mindestbestandsliste");
			jMenuItemMindestBestandsListe
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								getMenuController().druckeMindestBestandsListe();
							} catch (Exception e1) {
								ArrayList<Fehler> errors = new ArrayList<Fehler>();
								errors.add(new Fehler(e1.getMessage()));
								JFehlerDialogWechselController.
									getOneIntstance().showView(errors);
								e1.printStackTrace();
							}
							Log.log().finest("MindestBestandsListe wird angezeigt.");
						}
					});
		}
		Zugriffsrechtpruefung.addRecht(jMenuItemMindestBestandsListe,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
		Zugriffsrechtpruefung.addRecht(jMenuItemMindestBestandsListe, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
		Zugriffsrechtpruefung.addRecht(jMenuItemMindestBestandsListe, new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
		return jMenuItemMindestBestandsListe;
	}
	
	private JMenuItem getJMenuItemBerichte() {
		if (jMenuItemBerichte == null) {
			jMenuItemBerichte = new JMenuItem();
			jMenuItemBerichte.setText("Dokumente");
			jMenuItemBerichte.setVisible(true);
			jMenuItemBerichte.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showBerichtePane();
					Log.log().finest("Dokumente wird angezeigt.");
				}
			});
		}
		Zugriffsrechtpruefung.addRecht(jMenuItemBerichte,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
		Zugriffsrechtpruefung.addRecht(jMenuItemBerichte, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
		Zugriffsrechtpruefung.addRecht(jMenuItemBerichte, new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
		return jMenuItemBerichte;
	}
	
	
	/**
	 * This method initializes jMenuItemEntnahmeEinlagerung	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemEntnahme() {
		if (jMenuItemEntnahme == null) {
			jMenuItemEntnahme = new JMenuItem();
			jMenuItemEntnahme.setText("Entnahme");
			jMenuItemEntnahme
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showEntnahmePane();
					Log.log().finest("Entnahmeliste wird angezeigt.");
				}
			});
		}
		return jMenuItemEntnahme;
	}
	
	private JMenuItem getJMenuItemEinlagerung() {
		if (jMenuItemEinlagerung == null) {
			jMenuItemEinlagerung = new JMenuItem();
			jMenuItemEinlagerung.setText("Einlagerung");
			jMenuItemEinlagerung
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showEinlagerungPane();
					Log.log().finest("Einlagerungliste wird angezeigt.");
				}
			});
		}
		return jMenuItemEinlagerung ;
	}
	
	private JMenuItem getJMenuItemWartung() {
		if (jMenuItemWartung == null) {
			jMenuItemWartung = new JMenuItem();
			jMenuItemWartung.setText("Wartung");
			jMenuItemWartung
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showWartungPane();
					Log.log().finest("Wartung");
				}
			});
		}
		return jMenuItemWartung;
	}
	

	/**
	 * This method initializes jMenuItemBestellAnforderungsListe	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemBestellAnforderungsListe() {
		if (jMenuItemBestellAnforderungsListe == null) {
			jMenuItemBestellAnforderungsListe = new JMenuItem();
			jMenuItemBestellAnforderungsListe.setText("Anforderungsliste");
			jMenuItemBestellAnforderungsListe
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							getMenuController().showAnforderungPane();
							Log.log().finest("Anforderungsliste wird angezeigt.");
						}
					});
		}
		return jMenuItemBestellAnforderungsListe;
	}




	/**
	 * This method initializes jMenuStammdaten	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuStammdaten() {
		if (jMenuStammdaten == null) {
			jMenuStammdaten = new JMenu();
			jMenuStammdaten.setText("Stammdaten");
			jMenuStammdaten.add(getJMenuItemAbteilung());
			jMenuStammdaten.add(getJMenuItemArtikel());
			jMenuStammdaten.add(getJMenuItemBenutzer());
			jMenuStammdaten.add(getJMenuItemHallen());
			jMenuStammdaten.add(getJMenuItemAnlagen());
//			jMenuStammdaten.add(getJMenuItemTeile());
			jMenuStammdaten.add(getJMenuItemGrosshandelHersteller());
			jMenuStammdaten.add(getJMenuItemKatalog());
		};
		
		return jMenuStammdaten;
	}

	/**
	 * This method initializes jMenuItemAbteilung	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemAbteilung() {
		if (jMenuItemAbteilung == null) {
			jMenuItemAbteilung = new JMenuItem();
			jMenuItemAbteilung.setName("jMenuItemAbteilung");
			jMenuItemAbteilung.setText("Abteilung");
			jMenuItemAbteilung.setVisible(false);
			Zugriffsrechtpruefung.addRecht(jMenuItemAbteilung,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jMenuItemAbteilung, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
		}
//		Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(jMenuItemAbteilung.hashCode());
//		jMenuItemAbteilung.setEnabled(erlaubt);
//		
		return jMenuItemAbteilung;
	}
	
	/**
	 * This method initializes jMenuItemAnlagen	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemAnlagen() {
		if (jMenuItemAnlagen == null) {
			jMenuItemAnlagen = new JMenuItem();
			jMenuItemAnlagen.setText("Anlagen");
			Zugriffsrechtpruefung.addRecht(jMenuItemAnlagen,new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
			Zugriffsrechtpruefung.addRecht(jMenuItemAnlagen,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jMenuItemAnlagen, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			jMenuItemAnlagen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showAnlagenPane();
					Log.log().finest("Menüpunkt Anlagen.");
				}
			});
			
		}
		return jMenuItemAnlagen;
	}
	

	/**
	 * This method initializes jMenuItemBenutzer	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemBenutzer() {
		if (jMenuItemBenutzer == null) {
			jMenuItemBenutzer = new JMenuItem();
			jMenuItemBenutzer.setText("Benutzer");
			Zugriffsrechtpruefung.addRecht(jMenuItemBenutzer,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			jMenuItemBenutzer.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showBenutzerPane();
					Log.log().finest("Benutzer wird angezeigt.");
				}
			});
			Zugriffsrechtpruefung.addRecht(jMenuItemBenutzer,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			
		}
//		Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(jMenuItemBenutzer);
//		jMenuItemBenutzer.setEnabled(erlaubt);
		return jMenuItemBenutzer;
	}
	
	/**
	 * This method initializes jMenuItemArtikel	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemArtikel() {
		if (jMenuItemArtikel == null) {
			jMenuItemArtikel = new JMenuItem();
			jMenuItemArtikel.setText("Artikel");
			Zugriffsrechtpruefung.addRecht(jMenuItemArtikel,new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
			Zugriffsrechtpruefung.addRecht(jMenuItemArtikel,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jMenuItemArtikel, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			jMenuItemArtikel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showArtikelPane();
					Log.log().finest("Artikel wird angezeigt.");
				}
			});
		}
//		Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(jMenuItemBenutzer);
//		jMenuItemBenutzer.setEnabled(erlaubt);
		return jMenuItemArtikel;
	}

	

	/**
	 * This method initializes jMenuItemHallen	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemHallen() {
		if (jMenuItemHallen == null) {
			jMenuItemHallen = new JMenuItem();
			jMenuItemHallen.setText("Hallen");
			Zugriffsrechtpruefung.addRecht(jMenuItemHallen,new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
			Zugriffsrechtpruefung.addRecht(jMenuItemHallen,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jMenuItemHallen, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			jMenuItemHallen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showHallePane();
					Log.log().finest("Menüpunkt Hallen");
				}
			});
		}
		return jMenuItemHallen;
	}


//	/**
//	 * This method initializes jMenuItemTeile	
//	 * 	
//	 * @return javax.swing.JMenuItem	
//	 */
//	private JMenuItem getJMenuItemTeile() {
//		if (jMenuItemTeile == null) {
//			jMenuItemTeile = new JMenuItem();
//			jMenuItemTeile.setText("Teile");
//		}
//		return jMenuItemTeile;
//	}

	/**
	 * This method initializes jMenuItemGrosshandelHersteller	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemGrosshandelHersteller() {
		if (jMenuItemGrosshandelHersteller == null) {
			jMenuItemGrosshandelHersteller = new JMenuItem();
			jMenuItemGrosshandelHersteller.setText("Grosshandel / Hersteller");
			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelHersteller,new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelHersteller,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelHersteller, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			jMenuItemGrosshandelHersteller.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showGrosshandelHerstellerPane();
					Log.log().finest("Menüpunkt GrosshandelHersteller.");
				}
			});
		}
		return jMenuItemGrosshandelHersteller;
	}
	
	private JMenuItem getJMenuItemKatalog() {
		if (jMenuItemGrosshandelKatalog == null) {
			jMenuItemGrosshandelKatalog = new JMenuItem();
			jMenuItemGrosshandelKatalog.setText("Katalog");
			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelKatalog,new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelKatalog,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelKatalog, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
			jMenuItemGrosshandelKatalog.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showKatalogPane();
					Log.log().finest("Menüpunkt Katalog");
				}
			});
		}
		return jMenuItemGrosshandelKatalog;
	}
	
//	private JMenuItem getJMenuItemAnlagen() {
//		if (jMenuItemAnlagen == null) {
//			jMenuItemAnlagen = new JMenuItem();
//			jMenuItemAnlagen.setText("Anlagen");
//			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelHersteller,new ZugriffsrechtBean(Konstanten.RECHT_MITARBEITER));
//			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelHersteller,new ZugriffsrechtBean(Konstanten.RECHT_ADMINISTRATOR));
//			Zugriffsrechtpruefung.addRecht(jMenuItemGrosshandelHersteller, new ZugriffsrechtBean(Konstanten.RECHT_ABTEILUNGSLEITER));
//			jMenuItemAnlagen.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					getMenuController().showGrosshandelHerstellerPane();
//					Log.log().finest("Menüpunkt GrosshandelHersteller.");
//				}
////		Boolean erlaubt = Zugriffsrechtpruefung.getOneInstance().istErlaubt(jMenuItemBenutzer);
////		jMenuItemBenutzer.setEnabled(erlaubt);
//		return jMenuItemBenutzer;
//	}
	
//	public static MenuView getOneInstance(){
//		if (menuView == null){
//			menuView = new MenuView(null);
//		}
//		return menuView;
//	}

	@Override
	public void setContentPane(MainFrame mainFrame) {
		mainFrame.setTitle(Konstanten.UEBERSCHRIFT_ANWENDUNG + Konstanten.SLASH + Konstanten.MENUE);
		mainFrame.getJMenuBar().removeAll();
		mainFrame.getJMenuBar().add(getJMenuSystemmenue());
		mainFrame.getJMenuBar().add(getJMenuFormulareListen());
		mainFrame.getJMenuBar().add(getJMenuStammdaten());
		mainFrame.getJMenuBar().add(getJMenuInfo());
//		mainFrame.setContentPane(this);
		mainFrame.setContentPane(getJPanel());
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.getJMenuBar().setVisible(true);
		
		getJPanel().setSize(mainFrame.getSize());
		getJLabel().setSize(getJPanel().getSize());
		
		Zugriffsrechtpruefung.getOneInstance().jComponentCheck(mainFrame);
//		Zugriffsrechtpruefung.getOneInstance().jComponentCheck(this);
		
		mainFrame.validate();
	}
	
	/**
	 * Anzeige dieser View im HauptFrame
	 */
	protected void showView(){
		setContentPane(Run.getOneInstance().getMainFrame());
	}

	/**
	 * This method initializes jMenuSystemmenue	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuSystemmenue() {
		if (jMenuSystemmenue == null) {
			jMenuSystemmenue = new JMenu();
			jMenuSystemmenue.setText("Systemmenü");
			jMenuSystemmenue.add(getJMenuItemLogout());
		}
		return jMenuSystemmenue;
	}

	/**
	 * This method initializes jMenuItemLogout	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemLogout() {
		if (jMenuItemLogout == null) {
			jMenuItemLogout = new JMenuItem();
			jMenuItemLogout.setText("Logout");
			jMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Log.log().finest("actionPerformed(): JMenuItem getJMenuItemLogout()");
					MenuController.getOneInstance().zeigeLoginMaske();
				}
			});
		}
		return jMenuItemLogout;
	}

	/**
	 * Methode wird duch IMenuBeobachter erzwungen
	 */
	@Override
	public void zeichneDich(IMenuModel iMenuModel) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method initializes jMenuItemBestellung	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemBestellung() {
		if (jMenuItemBestellung == null) {
			jMenuItemBestellung = new JMenuItem();
			jMenuItemBestellung.setText("Bestellung");
			jMenuItemBestellung.setVisible(false);
			jMenuItemBestellung.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getMenuController().showBestellungsPane();
					Log.log().finest("Bestellung wird angezeigt.");
				}
			});
		}
		return jMenuItemBestellung;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			jPanel = new JPanel(){
				Image image = null;
				private Image getLocalImage(){
					if (image == null){
						BufferedImage bimg = null;
						try {
							image =  Run.createMenuImage2();
						} catch (Exception e) {
							System.out.println("The error in Frame..." + e);
						}
					}
					return image;
				}
				@Override
			    public void paint(Graphics g) {
			        super.paintComponent(g); //paint background
			        if (this.getLocalImage() != null) { //there is a picture: draw it
			            g.drawImage(this.getLocalImage(),0,0, this.getSize().width, this.getSize().height, this);
			         }  //end if
			    } //end paint				
			};
			jPanel.setLayout(new GridBagLayout());
			jPanel.setBackground(new Color(238, 0, 238));
//			jPanel.add(getJLabel(), gridBagConstraints);
		}
		return jPanel;
	}
	
//	// http://www.daniweb.com/forums/thread173147.html
//	
//	class StrechImageJPanel extends JPanel {
//
//	    /**
//		 * 
//		 */
//		private static final long serialVersionUID = 5847108122791309529L;
//		Image image = null;
//
//	    public StrechImageJPanel() {      }
//
//	    public StrechImageJPanel(Image image) {
//	        this.image = image;
//	    }
//	   
//
//	    public void setImage(Image image){
//	        this.image = image;
//	      
//	        System.out.println("Image set...");
//	    }
//	    
//	    public Image getImage(Image image){
//	        return image;
//	    }
//	    
//	    @Override
//	    public void paint(Graphics g) {
//	        super.paintComponent(g); //paint background
//	        if (image != null) { //there is a picture: draw it
//	            int height = this.getSize().height;
//	            int width = this.getSize().width;         
//	            g.drawImage(image,0,0, width, height, this);
//	            System.out.println("paint Component method ...");
//	               //g.drawImage(image, 0, 0, this); //original image size 
//	         }  //end if
//	    } //end paint
//	    
//	    
//		public static Image getMyImage(String image) {
//			BufferedImage bimg = null;
//			try {
//				// Track the image to completion.
//				bimg = ImageIO.read(new File(image));
//				StrechImageJPanel imgobj = new StrechImageJPanel(bimg);
//				imgobj.setImage(bimg);
//				Image img = imgobj.getImage(bimg);
//				imgobj.prepareImage(img, MenuView.this.getHeight(), MenuView.this.getWidth(),
//						null);
//				
////				getContentPane().add(imgobj, BorderLayout.CENTER);
//
//				System.out.println("Image Readed...");
//				
//				return imgobj.g;
//
//			} catch (Exception e) {
//				System.out.println("The error in Frame..." + e);
//			}
//
//		}
//	    
//	} //end class

	

}

//					Run.getOneInstance().getjPanelBenutzerView().setContentPane(Run.getOneInstance().getMainFrame());
