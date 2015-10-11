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
package de.keag.lager.panels.frame.baugruppe.pane.tree;

import javax.swing.Icon;
import javax.swing.JTree;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import de.keag.lager.core.GenericNode;
import de.keag.lager.core.IBean;
import de.keag.lager.core.IModel;
import de.keag.lager.core.ISuchBean;
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
import de.keag.lager.panels.frame.artikel.ArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeArtikelModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeModelKnotenBean;
import de.keag.lager.panels.frame.baugruppe.model.BaugruppeSuchBean;
import de.keag.lager.panels.frame.bestandspackstueck.model.BestandspackstueckBean;
import de.keag.lager.panels.frame.halle.model.HalleSuchBean;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class BaugruppeTreePaneView extends TreeView{

	private JTree jTree = null;
	private DefaultTreeModel treeModel = null; // @jve:decl-index=0:
	private TreeController treeController = null;  //  @jve:decl-index=0:
//	private TreeSelectionListener treeSelectionListner = null;  //  @jve:decl-index=0:
//	private TreePath angeklickterKnotenTreePath = null;

	/**
	 * This method initializes
	 * @param treeController 
	 * 
	 */
	protected BaugruppeTreePaneView(TreeController treeController) {
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
			GenericNode root = new GenericNode(null);
			jTree.setModel(getTreeModel(root));
			jTree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//			jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
//				public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
//					treeValueChanged(e,BaugruppeTreePaneView.this.getJTree());
////					System.out.println("valueChanged()"); // TODO Auto-generated Event stub valueChanged()
//				}
//			});
//			jTree.addTreeSelectionListener(getTreeSelectionListner());
			//Aussehen von JTree
			jTree.setCellRenderer(new AnfoderungTreeRenderer());
			jTree.setToolTipText("");
			
		}
		return jTree;
	}

	/**
	 * Achtung! Der Aufruf erfolgt von zwei Stellen(!)
	 * 1: JTree direkt(!) aus fireValueChanged
	 * 2: getTreeSelectionListner() aus diesem Programm.
	 * Aber! Ganz korrekt ist es nicht1
	 * Wir habe nämlich einen JTreeListner in TreeView und einen in dieser Klasse!
	 * Und die beiden zusammen sind falsch!
	 * Das dumme ist(oder das Coole), das Java die Reihenfolge des Aufrufs wechseln kann.
	 * Dann sieht es an der Oberfläche böse aus!
	 */
//	@Override
//	public void treeValueChanged(TreeSelectionEvent e, JTree jTree) {
//		//Returns the last path element of the selection.
//		//This method is useful only when the selection model allows a single selection.
////		if (jTree.isFocusOwner()||jTree.requestFocusInWindow()){
////			System.out.println("Fokus 005 Tree vor");
//		GenericNode node = (GenericNode)jTree.getLastSelectedPathComponent();
//		    if (node==null){
//		    	//nix ist gewählt
//		    }else{
//		    	if (node.getUserObject()!=null){
//		    		System.out.println("BaugruppeTreePaneView: getUserObjekt() vor.");
//			    	ModelKnotenBean selectedModelKnotenBean = (ModelKnotenBean) node.getUserObject();
//			    	//ausgewählten Knoten an Kontroller weitergeben.
//			    	getTreeController().selectKnoten(selectedModelKnotenBean);
//		    		System.out.println("BaugruppeTreePaneView: getUserObjekt() nach.");
//		    	}
//		    }
////			System.out.println("Fokus 005 Tree nach");
//			
////		}else{
////			System.out.println("kein Fokus 004 Tree");
////		}
//	}


//	@Override
//	/**
//	 * Der gewählt Knoten (Anforderung, oder die Anforderungsposition wird gewählt)
//	 */
//	public void zeichneDich(ModelKnotenBean modelKnotenBean) {
//		//Während wir zeichnen, soll JTree nicht reagieren.(z.B. auf Auswahl eines Knotens)
//		getJTree().removeTreeSelectionListener(getTreeSelectionListner());
//		
//		angeklickterKnotenTreePath = null;
//		
//		//Wir holen den Zeiger auf den aktuellen root-Knoten
//		GenericNode root = (GenericNode) getJTree().getModel().getRoot();
//		
//		//Alle aktuell gewählte Pfade werden gemerkt.
//		ArrayList<TreePath> offenePfade = new ArrayList<TreePath>();
//		if (root!=null){
//			merkeAlleOffenenPfade(getJTree(), offenePfade);
//		}
//		
//		
//		
//		//aktuellen Inhalt des Trees löschen.
//		root.removeAllChildren();
//		if (modelKnotenBean != null) {
//			ModelKnotenBean urvater = modelKnotenBean; //Annahme!
//			while (urvater.getVater()!=null){
//				urvater = urvater.getVater(); 
//			}
//			//Alle Kinder
//			zeichneKinderImTree(urvater,root,modelKnotenBean);
//		}else{
//			root.setUserObject(null);
//		}
//		
////		
////
//////		for (int i = 0; i < getJTree().getRowCount(); i++) {
//////			getJTree().collapseRow(i);
//////		}
////		
////		
//		//neu Zeichnen
//		((DefaultTreeModel) getJTree().getModel()).reload();
//		
////		if (angeklickterKnoten!=null){
////			getJTree().expandPath(angeklickterKnoten);
////		}
//		
//		//alte Pfade nach möglichkeit öffenen (expandieren)
//		for (TreePath tp: offenePfade ){
//			getJTree().expandPath(tp);
//		}
//
//		expandAll(getJTree(),true, angeklickterKnotenTreePath); //Alle Zweige aufklappen		
////		expandAll(getJTree(),true, null); //Alle Zweige aufklappen		
//		
//		
//		//angeklickten Knoten neu markieren
//		if(angeklickterKnotenTreePath!=null){
//		  getJTree().setSelectionPath(angeklickterKnotenTreePath);
//		}
//		
////		//System.out.println(angeklickterKnoten.toString());
////		
////		//Nachdem der JTree neu gezeichnet ist, darf er wieder auf die Maus reagieren.
//		jTree.addTreeSelectionListener(getTreeSelectionListner());
//		
//	}

//	@Override
//	public void zeichneKinderImTree(ModelKnotenBean vater,	
//			GenericNode treeNodeDesVaters, 
//			ModelKnotenBean angeklickterModelKnotenBean) {
//		treeNodeDesVaters.setUserObject(vater);
//		if (vater == angeklickterModelKnotenBean){
////			GenericNode neuerPosTreeKnoten = new GenericNode(vater);
////			angeklickterKnoten = new TreePath(neuerPosTreeKnoten.getPath());
//			
//			angeklickterKnotenTreePath = new TreePath(treeNodeDesVaters.getPath());
//			
////			getJTree().expandPath(angeklickterKnoten);
//			
////			getJTree().fireTreeExpanded(angeklickterKnoten);
////			getJTree().scrollPathToVisible(angeklickterKnoten);
////			getJTree().updateUI();
////			
////			getJTree().setExpandsSelectedPaths(true);
////			getJTree().expandPath(angeklickterKnoten);			
//		}
//		for(int i = 0; i< vater.getKinderList().size();i++){
//			GenericNode neuerPosTreeKnoten = new GenericNode(vater.getKinderList().get(i));
//			treeNodeDesVaters.add(neuerPosTreeKnoten);
//			
////			DefaultTreeModel model = (DefaultTreeModel)getJTree().getModel(); 
////			model.insertNodeInto(neuerPosTreeKnoten, treeNode, treeNode.getChildCount());
////			TreeNode[] path = model.getPathToRoot(treeNode);
////			getJTree().expandPath(new TreePath(path));
//			
//			zeichneKinderImTree(vater.getKinderList().get(i), neuerPosTreeKnoten, angeklickterModelKnotenBean);
//		}
//	}

	@Override
	public DefaultTreeModel getTreeModel(TreeNode root) {
		if (treeModel == null) {
			treeModel = new DefaultTreeModel(root);
		}
		return treeModel;
	}



//	@Override
//	public TreeSelectionListener getTreeSelectionListner() {
//		if(treeSelectionListner==null){
//			//Wenn der Zeiger auf TreeSelectionListner-Interface noch leer ist,
//			//dann wird ein Objekt erzeugt, welches das Interface TreeSelectionListner
//			//implemntiert.
//			//Ein TreeSelectionListner-Objekt ist ein Objekt, 
//			//welches auf den Maus-Klick auf ein JTree-Knoten reagiert .
//			treeSelectionListner = new TreeSelectionListener() {
//				
//				@Override
//				public void valueChanged(TreeSelectionEvent e) {
//					System.out.println("BaugruppeTreePaneView: vor PrüfeFEhler 0");
//					if (prueferFehler()){
//						System.out.println("BaugruppeTreePaneView: nach PrüfeFEhler 1");
//						treeValueChanged(e,BaugruppeTreePaneView.this.getJTree());
//					}else{
//						System.out.println("BaugruppeTreePaneView: nach PrüfeFEhler 2");
//						getTreeController().aktualisiereAnzeige();
//					}
//				}
//			};
//		}
//		return treeSelectionListner;
//	}

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
	        GenericNode node = (GenericNode)value;
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

					if (modelKnotenBean instanceof BaugruppeModelKnotenBean) {
						BaugruppeModelKnotenBean baugruppeModelKnotenBean = (BaugruppeModelKnotenBean)modelKnotenBean;
						String IconNameStatus = BeanDBStatus.JavaToIconName(baugruppeModelKnotenBean.getIBean().getBeanDBStatus());
						Icon icon = Run.createCompoundIcon(Konstanten.ICON_BAUGRUPPE, IconNameStatus,iconError);
						setIcon(icon);
					}
					;
					if (modelKnotenBean instanceof BaugruppeArtikelModelKnotenBean) {
						BaugruppeArtikelModelKnotenBean baugruppeArtikelModelKnotenBean = (BaugruppeArtikelModelKnotenBean)modelKnotenBean;
						String IconNameStatus = BeanDBStatus.JavaToIconName(baugruppeArtikelModelKnotenBean.getIBean().getBeanDBStatus());
						Icon icon = Run.createCompoundIcon(Konstanten.ICON_ARTIKEL, IconNameStatus,iconError);
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
	
	@Override
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		super.zeichneDich(modelKnotenBean, iModel);

		BaugruppeSuchBean baugruppeSuchBean = (BaugruppeSuchBean) iModel.getiSuchBean();
		if (
				baugruppeSuchBean.getArtikelBean().getId().equals(0)
			&& 
				baugruppeSuchBean.getBaugruppeName().length()==0 
		){
			return;
		}
		
		//Positionen zu der SuchBean markieren
	    TreeNode rootNode = (TreeNode) getJTree().getModel().getRoot();
	    expandAnhandSuchBean(getJTree(), new TreePath(rootNode),rootNode,iModel.getiSuchBean());
		
		
		this.repaint();    //alte Komponenten werden gelöscht
		this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
		this.validate();   //werden gezeichnet.
		this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
		
	}	
	
	private void expandAnhandSuchBean(JTree tree, TreePath parent, TreeNode rootNode, ISuchBean iSuchBean) {
		    BaugruppeSuchBean baugruppeSuchBean = (BaugruppeSuchBean) iSuchBean;
		    TreeNode node = (TreeNode) parent.getLastPathComponent();
		    if (node.getChildCount() >= 0) {
		      for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements();) {
		        TreeNode n = (TreeNode) e.nextElement();
		        TreePath path = parent.pathByAddingChild(n);
		        expandAnhandSuchBean(tree, path, rootNode, iSuchBean);
		      }
		    }
		    for(int i = 0; i < node.getChildCount();i++){
		    	TreeNode child = node.getChildAt(i);
		    	if (child.isLeaf()&&child instanceof GenericNode){
		    		Object o = ((GenericNode)child).getUserObject();
		    		if (o instanceof ModelKnotenBean){
			    		IBean iBean = ((ModelKnotenBean)o).getIBean();
			    		if (iBean!=null){
			    			if (iBean instanceof BaugruppeArtikelBean){
			    				BaugruppeArtikelBean artikelBean = (BaugruppeArtikelBean) iBean;
			    				if (artikelBean.getArtikel().equals(baugruppeSuchBean.getArtikelBean())){
							        TreePath path = parent.pathByAddingChild(child);
								    tree.expandPath(parent);
			    				}else{
//					    			Log.log().severe("Artikel uninteressant:"+artikelBean);
			    				}
			    			}else if (iBean instanceof BaugruppeBean){
			    				BaugruppeBean baugruppeBean = (BaugruppeBean) iBean;
			    				if (baugruppeBean.getName().equals(baugruppeSuchBean.getBaugruppeName())){
							        TreePath path = parent.pathByAddingChild(child);
								    tree.expandPath(parent);
			    				}else{
//					    			Log.log().severe("Artikel uninteressant:"+artikelBean);
			    				}
				    		}else{
//				    			Log.log().severe("Objekt uninteressant:"+iBean.getClass().getName());
//				    			Log.log().severe("Objekt uninteressant:"+iBean);
				    		}
			    		}else{
			    			Log.log().severe("Kein Userobjekt:"+iBean);
			    		}
		    		}else{
		    			Log.log().severe("Fehler, falscher Typ:"+o);
		    		}
		    	}
		    }
	}	
	
	
}


