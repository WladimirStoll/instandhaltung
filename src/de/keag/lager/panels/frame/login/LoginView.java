package de.keag.lager.panels.frame.login;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JPasswordField;

import de.keag.lager.core.IPanel;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.LagerProperties;
import de.keag.lager.core.main.Run;
import de.keag.lager.panels.frame.MainFrame;
import de.keag.lager.panels.frame.zugriffsrecht.Zugriffsrechtpruefung;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;


import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Font;

public class LoginView extends JPanel implements IPanel, ILoginBeobachter {

	private static final long serialVersionUID = 1L;
	private JLabel jLabelHost = null;
	private JLabel jLabelUserName = null;
	private JTextField jTextFieldUserName = null;
	private JTextField jTextFieldHost = null;
	private JLabel jLabelPassword = null;
	private JPasswordField jPasswordField = null;
	private JButton jButtonLogin = null;
	private LoginController loginController;
	private ILoginModel iLoginModel;
	private LoginBean loginBean; // @jve:decl-index=0:
	private JPanel jPanel = null;
	private JComboBox jFehlerComboBox = null;
	private JCheckBox jCheckBox = null;

	private LoginBean getBenutzerBean() {
		return loginBean;
	}

	public ILoginModel getiLoginModel() {
		return iLoginModel;
	}

	private void setBenutzerBean(LoginBean benutzerBean) {
		this.loginBean = benutzerBean;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	/**
	 * This is the default constructor
	 */
	protected LoginView(LoginController benutzerController,
			ILoginModel iBenutzerModel) {
		super();
		this.loginController = benutzerController;
		this.iLoginModel = iBenutzerModel;

		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
		gridBagConstraints7.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints7.gridx = 1;
		gridBagConstraints7.gridy = 1;
		gridBagConstraints7.anchor = GridBagConstraints.SOUTH;
		gridBagConstraints7.weighty = 1.0D;
		gridBagConstraints7.weightx = 1.0;
		GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
		gridBagConstraints31.ipadx = 1;
		gridBagConstraints31.gridx = 1;
		gridBagConstraints31.anchor = GridBagConstraints.CENTER;
		gridBagConstraints31.fill = GridBagConstraints.BOTH;
		gridBagConstraints31.weighty = 100.0D;
		gridBagConstraints31.weightx = 100.0D;
		GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
		gridBagConstraints13.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints13.gridy = 4;
		gridBagConstraints13.weightx = 1.0;
		gridBagConstraints13.anchor = GridBagConstraints.SOUTH;
		gridBagConstraints13.gridx = 1;
		jLabelPassword = new JLabel();
		jLabelPassword.setText("Passwort");
		jLabelUserName = new JLabel();
		jLabelUserName.setText("Benutzer");
		jLabelHost = new JLabel();
		jLabelHost.setText("Host");
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
		this.add(getJPanel(), gridBagConstraints31);
		this.add(getJFehlerComboBox(), gridBagConstraints7);
	}

	/**
	 * This method initializes jTextFieldUserName
	 * 
	 * @return javax.swing.JTextField
	 */
	protected JTextField getJTextFieldUserName() {
		if (jTextFieldUserName == null) {
			jTextFieldUserName = new JTextField();
			jTextFieldUserName.setPreferredSize(new Dimension(100, 20));
			jTextFieldUserName.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					int key = e.getKeyCode();
					if (key == KeyEvent.VK_ENTER) {
						Toolkit.getDefaultToolkit().beep();
						getJPasswordField().requestFocus();
					}
				}
			});
		}
		return jTextFieldUserName;
	}

	protected JTextField getJTextFieldHost() {
		if (jTextFieldHost == null) {
			jTextFieldHost = new JTextField();
			jTextFieldHost.setPreferredSize(new Dimension(100, 20));
			jTextFieldHost.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					int key = e.getKeyCode();
					if (key == KeyEvent.VK_ENTER) {
						Toolkit.getDefaultToolkit().beep();
						getJPasswordField().requestFocus();
					}
				}
			});
		}
		return jTextFieldHost;
	}
	
	protected JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			String lizenzSatz = Run.getOneInstance().getLagerProperties().getLizenzSatz().trim(); 
			jCheckBox = new JCheckBox(lizenzSatz);
			jCheckBox.setSelected(true);
			if (lizenzSatz.equals("")){
				jCheckBox.setVisible(false);
			}
		}
		return jCheckBox;
	}
	

	/**
	 * This method initializes jPasswordField
	 * 
	 * @return javax.swing.JPasswordField
	 */
	protected JPasswordField getJPasswordField() {
		if (jPasswordField == null) {
			jPasswordField = new JPasswordField();
			jPasswordField.setPreferredSize(new Dimension(100, 20));
			jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					int key = e.getKeyCode();
					if (key == KeyEvent.VK_ENTER) {
						Toolkit.getDefaultToolkit().beep();
						getJButtonLogin().doClick();
					}
				}
			});
		}
		return jPasswordField;
	}

	@Override
	public void setContentPane(MainFrame mainFrame) {
		mainFrame.getJMenuBar().removeAll();
		mainFrame.getJMenuBar().setVisible(false);
		mainFrame.setContentPane(this);
		mainFrame.setTitle(Konstanten.UEBERSCHRIFT_ANWENDUNG + Konstanten.SLASH
				+ Konstanten.LOGIN);
		Zugriffsrechtpruefung.getOneInstance().jComponentCheck(mainFrame);

	}

	/**
	 * This method initializes jButtonLogin
	 * 
	 * @return javax.swing.JButton
	 */
	protected JButton getJButtonLogin() {
		if (jButtonLogin == null) {
			jButtonLogin = new JButton();
			jButtonLogin.setPreferredSize(new Dimension(100, 20));
			jButtonLogin.setVerticalAlignment(SwingConstants.CENTER);
			jButtonLogin.setText("OK");
			jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//Lizenz-Bedingungen akzeptieren
					if (!LoginView.this.getJCheckBox().isSelected()&&getJCheckBox().isVisible()){
						JOptionPane.showMessageDialog(Run.getOneInstance().getMainFrame(), "Sie dürfen dieses Programm nicht benutzen!");
					}else{
						// Host übernehmen
						String host = getJTextFieldHost().getText();
						LagerProperties.userEingabeHost = host;

						iLoginModel.getLoginBean().setLoginName(
								getJTextFieldUserName().getText());
						iLoginModel.getLoginBean().setPassword(
								new String(getJPasswordField().getPassword()));
						loginController.tuEinloggen(iLoginModel.getLoginBean());
						// JFehlerDialogWechselController.getOneIntstance().showView(errors);
						
						Log.log().severe("Anmeldung:"+getJTextFieldUserName().getText());
//						try{
//							new Integer("--");
//						}catch(Exception e2){
//							e2.printStackTrace();
//						}
					}
				}
			});
		}
		return jButtonLogin;
	}

	@Override
	public void zeichneDich() {
		setBenutzerBean(iLoginModel.getLoginBean());
		getJTextFieldUserName().setText(getBenutzerBean().getLoginName());
		getJPasswordField().setText(getBenutzerBean().getPassword());
		// Fehler anzeigen, falls vorhanden
		jFehlerComboBox.setEnabled(false);
		jFehlerComboBox.removeAllItems();
		for (int i = 0; i < iLoginModel.getFehlerListe().size(); i++) {
			jFehlerComboBox.addItem(iLoginModel.getFehlerListe().get(i)
					.getMessage()
					+ " Fehlercode:"
					+ iLoginModel.getFehlerListe().get(i).getId() + "");
			jFehlerComboBox.setEnabled(true);
		}
	}

	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.anchor = GridBagConstraints.NORTH;
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridy = 3;
			gridBagConstraints6.weightx = 0.0D;
			gridBagConstraints6.anchor = GridBagConstraints.SOUTH;
			gridBagConstraints6.weighty = 1.0D;
			gridBagConstraints6.gridx = 1;

			GridBagConstraints gridBagConstraints = new GridBagConstraints(); // Benutzer
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0D;
			gridBagConstraints.weighty = 0.0D;
			gridBagConstraints.insets = new Insets(0, 0, 0, 40);

			GridBagConstraints gridBagConstraints1 = new GridBagConstraints(); // Benuterz
																				// Name
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridx = 2;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0D;
			gridBagConstraints1.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;

			GridBagConstraints gridBagConstraints2 = new GridBagConstraints(); // Passwort
																				// Label
			gridBagConstraints2.anchor = GridBagConstraints.EAST;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 2;
			gridBagConstraints2.weightx = 0.0D;
			gridBagConstraints2.weighty = 0.0D;
			gridBagConstraints2.insets = new Insets(0, 0, 0, 40);

			GridBagConstraints gridBagConstraints3 = new GridBagConstraints(); // Passwort
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridx = 2;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.weightx = 0.0D;
			gridBagConstraints3.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;

			GridBagConstraints gridBagConstraints4 = new GridBagConstraints(); // Button
																				// OK
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.weightx = 0.0D;
			gridBagConstraints4.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.gridy = 3;

			GridBagConstraints gridBagConstraints11 = new GridBagConstraints(); // Hostlabel
			gridBagConstraints11.anchor = gridBagConstraints11.EAST;
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 5;
			gridBagConstraints11.weightx = 1.0D;
			gridBagConstraints11.weighty = 0.0D;
			gridBagConstraints11.insets = new Insets(0, 0, 0, 40);

			GridBagConstraints gridBagConstraints12 = new GridBagConstraints(); // Hostname
																				// Name
			gridBagConstraints12.anchor = GridBagConstraints.WEST;
			gridBagConstraints12.gridx = 2;
			gridBagConstraints12.gridy = 5;
			gridBagConstraints12.weightx = 1.0D;
			gridBagConstraints12.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints12.fill = GridBagConstraints.VERTICAL;
			
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints(); // Hostname
			// Name
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.gridx = 2;
			gridBagConstraints13.gridy = 6;
			gridBagConstraints13.weightx = 1.0D;
			gridBagConstraints13.insets = new Insets(2, 2, 2, 2);
			gridBagConstraints13.fill = GridBagConstraints.VERTICAL;
			

			// jPanel = new JPanel();
			jPanel = new JPanel() {
				Image image = null;

				private Image getLocalImage() {
					if (image == null) {
						BufferedImage bimg = null;
						try {
							image = Run.createMenuImage2();
						} catch (Exception e) {
							System.out.println("The error in Frame..." + e);
						}
					}
					return image;
				}

				@Override
				public void paint(Graphics g) {
					super.paintComponent(g); // paint background
					if (this.getLocalImage() != null) { // there is a picture:
														// draw it
						g.drawImage(this.getLocalImage(), 0, 0,
								this.getSize().width, this.getSize().height,
								this);
						for (int i = 0; i < this.getComponentCount(); i++) {
							Component c = this.getComponent(i);
							if (c instanceof JTextField || c instanceof JButton) {
								c.repaint();// alte Komponenten werden gelöscht
							}
							if (c instanceof JLabel) {
								g.setFont(new Font("Dialog", Font.BOLD, 14));
								g.drawString(((JLabel) c).getText(), c.getX(),
										c.getY() + 12);

								// c.repaint();//alte Komponenten werden
								// gelöscht

								// c.paint(g);//alte Komponenten werden gelöscht

								// g.drawString(((JLabel)c).getText(), c.getX(),
								// c.getY()+12);
								// c.repaint();//alte Komponenten werden
								// gelöscht
								// c.invalidate(); //alle bis zu dem obersten
								// Kontainer auf ungültig
								// c.validate(); //werden gezeichnet.
								// c.revalidate(); //Layout-Manager tut seinen
								// JOB, und richtet this.invalidate();

							}
							if (c instanceof JCheckBox) {
								c.repaint();// alte Komponenten werden gelöscht
//								g.setFont(new Font("Dialog", Font.BOLD, 14));
//								g.drawString(((JCheckBox) c).getText(), c.getX(),
//										c.getY() + 12);
							}
							
						}
						// this.revalidate();
					} // end if
				} // end paint
			};

			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabelUserName, gridBagConstraints);
			jPanel.add(getJTextFieldUserName(), gridBagConstraints1);
			jPanel.add(jLabelPassword, gridBagConstraints2);
			jPanel.add(getJPasswordField(), gridBagConstraints3);
			jPanel.add(getJButtonLogin(), gridBagConstraints4);
			
//			jPanel.add(jLabelHost, gridBagConstraints11);
//			jPanel.add(getJTextFieldHost(), gridBagConstraints12);
			
			jPanel.add(getJCheckBox(),gridBagConstraints13);
		}
		return jPanel;
	}

	/**
	 * This method initializes jFehlerComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJFehlerComboBox() {
		if (jFehlerComboBox == null) {
			jFehlerComboBox = new JComboBox();
		}
		return jFehlerComboBox;
	}

	public void showView() {
		setContentPane(Run.getOneInstance().getMainFrame());
	}

	class JLoginLabel extends JLabel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			// // super.paint(g);
			// setFont(new Font("Dialog", Font.BOLD, 14));
			// g.drawString(getText(), getX(), getY()+12);
		}
	}

}
