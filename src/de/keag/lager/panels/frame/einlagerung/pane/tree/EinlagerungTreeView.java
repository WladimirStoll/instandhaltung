package de.keag.lager.panels.frame.einlagerung.pane.tree;

import javax.swing.Icon;
import javax.swing.JTree;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
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
import de.keag.lager.core.ITreeBeobachter;
import de.keag.lager.core.JLagerTree;
import de.keag.lager.core.ModelKnotenBean;
import de.keag.lager.core.TreeController;
import de.keag.lager.core.TreeView;
import de.keag.lager.core.enums.ModelKnotenTyp;
import de.keag.lager.core.fehler.BenutzerOberflacheLagerException;
import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.FehlerTyp;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;
import de.keag.lager.core.main.Konstanten;
import de.keag.lager.core.main.Run;
import de.keag.lager.db.connection.BeanDBStatus;
//import de.keag.lager.panels.frame.einlagerung.model.EinlagerungModelKnotenBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosBean;
import de.keag.lager.panels.frame.einlagerung.model.EinlagerungPosModelKnotenBean;

import java.awt.Color;

public class EinlagerungTreeView extends TreeView implements ITreeBeobachter {

	private JTree jTree = null;
	private TreeController treeController = null;  //  @jve:decl-index=0:

	/**
	 * This method initializes
	 * @param einlagerungTreePaneController 
	 * 
	 */
	protected EinlagerungTreeView(EinlagerungTreeController einlagerungTreePaneController) {
		super();
		setTreeController(einlagerungTreePaneController);
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
			jTree.getSelectionModel().setSelectionMode(
					TreeSelectionModel.SINGLE_TREE_SELECTION);
			// DefaultMutableTreeNode root = new DefaultMutableTreeNode(null);
			GenericNode root;
			try {
				root = new GenericNode(new EinlagerungPosModelKnotenBean(new EinlagerungPosBean()));
			} catch (BenutzerOberflacheLagerException e) {
				root = new GenericNode(null);
				e.printStackTrace();
			}
			jTree.setModel(getTreeModel(root));
			jTree.setLargeModel(true);
			jTree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			// jTree.addTreeSelectionListener(new
			// javax.swing.event.TreeSelectionListener() {
			// public void valueChanged(javax.swing.event.TreeSelectionEvent e)
			// {
			// treeValueChanged(e,ArtikelTreePaneView.this.getJTree());
			// // System.out.println("valueChanged()"); // TODO Auto-generated
			// Event stub valueChanged()
			// }
			// });
			// jTree.addTreeSelectionListener(getTreeSelectionListner());
			// Aussehen von JTree
			jTree.setCellRenderer(new EinlagerungPosTreeRenderer());
//			jTree = new JLagerTree();
//			jTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION );
//			DefaultMutableTreeNode root = new DefaultMutableTreeNode(null);
//			jTree.setModel(getTreeModel(root));
//			jTree.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
//			jTree.setCellRenderer(new AnfoderungTreeRenderer());
			jTree.setToolTipText("");

		}
		return jTree;
	}



//	@Override
//	/**
//	 * Der gewählt Knoten (Anforderung, oder die Anforderungsposition wird gewählt)
//	 */
//	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
//		//Während wir zeichnen, soll JTree nicht reagieren.(z.B. auf Auswahl eines Knotens)
//		jTree.removeTreeSelectionListener(getTreeSelectionListner());
//		//Wir holen den Zeiger auf den aktuellen root-Knoten
//		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getJTree().getModel().getRoot();
//		//aktuellen Inhalt des Trees löschen.
//		root.removeAllChildren();
//		
//		//neuen Root-Knoten setzen.
//		if (modelKnotenBean != null) {
//			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.ENTNAHME) {
//				root.setUserObject(modelKnotenBean); // Anforderung wurde
//														// angeklickt oder
//														// gewählt.
//			} else {
//				if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.EINLAGERUNGPOSITION) {
//					// Der Anforderungsknoten ist der root-Knoten
//					// Aber der User hat eine Anforderungsposition gewählt.
//					// root-Anforderung wird gesetzt.
//					root.setUserObject(modelKnotenBean.getVater());
//				} else {
//					root.setUserObject(new Fehler(19, FehlerTyp.FEHLER,
//							modelKnotenBean.toString()));
//				}
//			}
//		}
//
//		
//		//der neu gesetzte Root-Knoten wird geholt.
//		root = (DefaultMutableTreeNode) getJTree().getModel().getRoot();
//		if (root.getUserObject() instanceof EinlagerungModelKnotenBean){
//			//Anforderung wir im Tree gezeichnet.
//			TreePath angeklickterKnoten = null;
//			EinlagerungModelKnotenBean einlagerungModelKnotenBean = (EinlagerungModelKnotenBean)root.getUserObject();
//			for (ModelKnotenBean modelKnotenBeanKind : einlagerungModelKnotenBean.getKinderList()) {
//				if (modelKnotenBeanKind instanceof EinlagerungPosModelKnotenBean){
//					DefaultMutableTreeNode neuerPosTreeKnoten = new DefaultMutableTreeNode(modelKnotenBeanKind);
//					root.add(neuerPosTreeKnoten);
//					if (modelKnotenBean==modelKnotenBeanKind){
//						angeklickterKnoten = new TreePath(neuerPosTreeKnoten.getPath());
////						angeklickterKnoten = new TreePath(((DefaultTreeModel)jTree.getModel()).getPathToRoot( neuerPosTreeKnoten ) );
//					}
//				}
//			}
//			
//			//neu Zeichnen
//			((DefaultTreeModel) getJTree().getModel()).reload();
//			
//			//angeklickten Knoten setzen
//			if (modelKnotenBean==einlagerungModelKnotenBean){
//				angeklickterKnoten = new TreePath(root.getPath	());
//			}
//			if(angeklickterKnoten!=null){
//				  getJTree().setSelectionPath(angeklickterKnoten);
//			}
//		}else{
////			Log.log().severe("Fehler! Im Tree konnte das UserObjekt nicht korrekt gesetzt werden.");
//		}
//		
//		
//		
//		//Nachdem der JTree neu gezeichnet ist, darf er wieder auf die Maus reagieren.
//		jTree.addTreeSelectionListener(getTreeSelectionListner());
//	}

	@Override
	public TreeController getTreeController() {
		return treeController;
	}

	@Override
	public void setTreeController(TreeController treePaneController) {
		this.treeController = treePaneController;
	}


	
	//Symbole im JTree werden hier gesetzt
	//http://java.sun.com/docs/books/tutorial/uiswing/components/tree.html
	private class EinlagerungPosTreeRenderer extends DefaultTreeCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Icon[] icons;

//	    public AnfoderungTreeRenderer() {
//	    }
	    
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

//					if (modelKnotenBean instanceof EinlagerungModelKnotenBean) {
//						EinlagerungModelKnotenBean einlagerungModelKnotenBean = (EinlagerungModelKnotenBean)modelKnotenBean;
//						String IconNameStatus = BeanDBStatus.JavaToIconName(einlagerungModelKnotenBean.getIBean().getBeanDBStatus());
//						Icon icon = Run.createCompoundIcon(Konstanten.ICON_ENTNAHME, IconNameStatus,iconError);
//						setIcon(icon);
//					}
//					;
					if (modelKnotenBean instanceof EinlagerungPosModelKnotenBean) {
						EinlagerungPosModelKnotenBean einlagerungPosModelKnotenBean = (EinlagerungPosModelKnotenBean)modelKnotenBean;
						String IconNameStatus = BeanDBStatus.JavaToIconName(einlagerungPosModelKnotenBean.getIBean().getBeanDBStatus());
						Icon icon = Run.createCompoundIcon(Konstanten.ICON_EINLAGERUNGPOSITION, IconNameStatus,iconError);
						setIcon(icon);
					}
					;
					
				}
			}
	        return this;
	    }


	}


	
}


