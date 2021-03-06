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
package de.keag.lager.panels.frame.benutzer.pane.tree;

import javax.swing.Icon;
import javax.swing.JTree;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import de.keag.lager.core.GenericNode;
import de.keag.lager.core.IModel;
import de.keag.lager.core.JLagerTree;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
import de.keag.lager.panels.frame.benutzer.model.BenutzerAbteilungModelKnotenBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerModelKnotenBean;
import de.keag.lager.panels.frame.benutzer.model.BenutzerZugriffsrechtModelKnotenBean;

import java.awt.Color;

public class BenutzerTreePaneView extends TreeView{

	private JTree jTree = null;
	private DefaultTreeModel treeModel = null; // @jve:decl-index=0:
	private TreeController treeController = null;  //  @jve:decl-index=0:
	private TreeSelectionListener treeSelectionListner = null;  //  @jve:decl-index=0:

	/**
	 * This method initializes
	 * @param treeController 
	 * 
	 */
	protected BenutzerTreePaneView(TreeController treeController) {
		super();
		setTreeController(treeController);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
		gridBagConstraints.weightx = 1.0D;
		gridBagConstraints.weighty = 1.0D;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		this.add(getJTree(), gridBagConstraints);
	}

	/**
	 * This method initializes jTree
	 * 
	 * @return javax.swing.JTree
	 */
	@Override
	public JTree getJTree() {
		if (jTree == null) {
			jTree = new JLagerTree();
			jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION );
			DefaultMutableTreeNode root = new GenericNode(null);
			jTree.setModel(getTreeModel(root));
			jTree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTree.setCellRenderer(new AnfoderungTreeRenderer());
			jTree.setToolTipText("");
			
		}
		return jTree;
	}

	@Override
	public void treeValueChanged(TreeSelectionEvent e, JTree jTree) {
		//Returns the last path element of the selection.
		//This method is useful only when the selection model allows a single selection.
//		if (jTree.isFocusOwner()||jTree.requestFocusInWindow()){
//			System.out.println("Fokus 005 Tree vor");
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
		    if (node==null){
		    	//nix ist gewählt
		    }else{
		    	if (node.getUserObject()!=null){
			    	ModelKnotenBean selectedModelKnotenBean = (ModelKnotenBean) node.getUserObject();
			    	//ausgewählten Knoten an Kontroller weitergeben.
			    	getTreeController().selectKnoten(selectedModelKnotenBean);
		    	}
		    }
//			System.out.println("Fokus 005 Tree nach");
			
//		}else{
//			System.out.println("kein Fokus 004 Tree");
//		}
	}


	@Override
	/**
	 * Der gewählt Knoten (Anforderung, oder die Anforderungsposition wird gewählt)
	 */
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		//Während wir zeichnen, soll JTree nicht reagieren.(z.B. auf Auswahl eines Knotens)
		jTree.removeTreeSelectionListener(getTreeSelectionListner());
		//Wir holen den Zeiger auf den aktuellen root-Knoten
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getJTree().getModel().getRoot();
		//aktuellen Inhalt des Trees löschen.
		root.removeAllChildren();
		
		//neuen Root-Knoten setzen.
		if (modelKnotenBean != null) {
			root.setUserObject(null);
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER) {
				root.setUserObject(modelKnotenBean); // Anforderung wurde
				// angeklickt oder
				// gewählt.
			}else{
				if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER_ABTEILUNG
						|| modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.BENUTZER_ZUGRIFFSRECHT) {
					// Der Anforderungsknoten ist der root-Knoten
					// Aber der User hat eine Anforderungsposition gewählt.
					// root-Anforderung wird gesetzt.
					root.setUserObject(modelKnotenBean.getVater());
				} else {
					// kein Inhalt im Knoten
					root.setUserObject(null);
					// Log.log().severe("Fehler: Knoten ist nicht gefüllt");
				}
			}
		}
		
		//der neu gesetzte Root-Knoten wird geholt.
		root = (DefaultMutableTreeNode) getJTree().getModel().getRoot();
		if (root.getUserObject() instanceof BenutzerModelKnotenBean){
			//Anforderung wir im Tree gezeichnet.
			TreePath angeklickterKnoten = null;
			BenutzerModelKnotenBean benutzerModelKnotenBean = (BenutzerModelKnotenBean)root.getUserObject();
			for (ModelKnotenBean modelKnotenBeanKind : benutzerModelKnotenBean
					.getKinderList()) {
				DefaultMutableTreeNode neuerPosTreeKnoten = new GenericNode(modelKnotenBeanKind);
				root.add(neuerPosTreeKnoten);
				if (modelKnotenBean == modelKnotenBeanKind) {
					angeklickterKnoten = new TreePath(neuerPosTreeKnoten.getPath());
					// angeklickterKnoten = new
					// TreePath(((DefaultTreeModel)jTree.getModel()).getPathToRoot(
					// neuerPosTreeKnoten ) );
				}
			}
			
			//neu Zeichnen
			((DefaultTreeModel) getJTree().getModel()).reload();
			
			//angeklickten Knoten setzen
			if (modelKnotenBean==benutzerModelKnotenBean){
				angeklickterKnoten = new TreePath(root.getPath	());
			}
			if(angeklickterKnoten!=null){
				  getJTree().setSelectionPath(angeklickterKnoten);
			}
		}else{
			Log.log().severe("Fehler! Im Tree konnte das UserObjekt nicht korrekt gesetzt werden.");
		}
		
		
		
		//Nachdem der JTree neu gezeichnet ist, darf er wieder auf die Maus reagieren.
		jTree.addTreeSelectionListener(getTreeSelectionListner());
	}

	@Override
	public DefaultTreeModel getTreeModel(TreeNode root) {
		if (treeModel == null) {
			treeModel = new DefaultTreeModel(root);
		}
		return treeModel;
	}



	@Override
	public TreeSelectionListener getTreeSelectionListner() {
		if(treeSelectionListner==null){
			//Wenn der Zeiger auf TreeSelectionListner-Interface noch leer ist,
			//dann wird ein Objekt erzeugt, welches das Interface TreeSelectionListner
			//implemntiert.
			//Ein TreeSelectionListner-Objekt ist ein Objekt, 
			//welches auf den Maus-Klick auf ein JTree-Knoten reagiert .
			treeSelectionListner = new TreeSelectionListener() {
				
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					if (prueferFehler()){
						treeValueChanged(e,BenutzerTreePaneView.this.getJTree());
					}
				}
			};
		}
		return treeSelectionListner;
	}

	//Symbole im JTree werden hier gesetzt
	//http://java.sun.com/docs/books/tutorial/uiswing/components/tree.html
	private class AnfoderungTreeRenderer extends DefaultTreeCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Icon[] icons;

	    public AnfoderungTreeRenderer() {
	    }
	    
	    @Override
	    public Component getTreeCellRendererComponent(
	                        JTree tree,
	                        Object value,
	                        boolean sel,
	                        boolean expanded,
	                        boolean leaf,
	                        int row,
	                        boolean hasFocus) {

	        super.getTreeCellRendererComponent(
	                        tree, value, sel,
	                        expanded, leaf, row,
	                        hasFocus);
	        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
			if (node != null && node.getUserObject()!=null) {
				if (node.getUserObject() instanceof ModelKnotenBean) {

					ModelKnotenBean modelKnotenBean = (ModelKnotenBean) node.getUserObject();
					
					//Falls Fehler vorhanden sind, wir das Icon geladen.
					String iconError; 
					if (modelKnotenBean.getFehlerList().size()>0){
						iconError = Fehler.getFehlerIcon(); 
					}else{
						iconError = null;
					}

					if (modelKnotenBean instanceof BenutzerModelKnotenBean) {
						BenutzerModelKnotenBean benutzerModelKnotenBean = (BenutzerModelKnotenBean)modelKnotenBean;
						String IconNameStatus = BeanDBStatus.JavaToIconName(benutzerModelKnotenBean.getIBean().getBeanDBStatus());
						Icon icon = Run.createCompoundIcon(Konstanten.ICON_BENUTZER, IconNameStatus,iconError);
						setIcon(icon);
					}
					;
					if (modelKnotenBean instanceof BenutzerAbteilungModelKnotenBean) {
						BenutzerAbteilungModelKnotenBean benutzerAbteilungModelKnotenBean = (BenutzerAbteilungModelKnotenBean)modelKnotenBean;
						String IconNameStatus = BeanDBStatus.JavaToIconName(benutzerAbteilungModelKnotenBean.getIBean().getBeanDBStatus());
						Icon icon = Run.createCompoundIcon(Konstanten.ICON_ABTEILUNG, IconNameStatus,iconError);
						setIcon(icon);
					}
					;
					
					if (modelKnotenBean instanceof BenutzerZugriffsrechtModelKnotenBean) {
						BenutzerZugriffsrechtModelKnotenBean benutzerZugriffsrechtModelKnotenBean = (BenutzerZugriffsrechtModelKnotenBean)modelKnotenBean;
						String IconNameStatus = BeanDBStatus.JavaToIconName(benutzerZugriffsrechtModelKnotenBean.getIBean().getBeanDBStatus());
						Icon icon = Run.createCompoundIcon(Konstanten.ICON_RECHT, IconNameStatus,iconError);
						setIcon(icon);
					}
					;
					
					
				}
			}
	        return this;
	    }


	}


	@Override
	public TreeController getTreeController() {
		return treeController;
	}
	
	
	@Override
	public void setTreeController(TreeController treeController) {
		this.treeController = treeController;
	}
	
}


