package de.keag.lager.panels.frame.lieferanthersteller.pane.tree;

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
import de.keag.lager.panels.frame.lieferanthersteller.model.LhKatalogModelKnotenBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhModelKnotenBean;
import de.keag.lager.panels.frame.lieferanthersteller.model.LhZugriffsrechtModelKnotenBean;

import java.awt.Color;

public class LhTreePaneView extends TreeView{

	private JTree jTree = null;
	private DefaultTreeModel treeModel = null; // @jve:decl-index=0:
	private TreeController treeController = null;  //  @jve:decl-index=0:
	private TreeSelectionListener treeSelectionListner = null;  //  @jve:decl-index=0:

	/**
	 * This method initializes
	 * @param treeController 
	 * 
	 */
	protected LhTreePaneView(TreeController treeController) {
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
//			jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
//				public void valueChanged(javax.swing.event.TreeSelectionEvent e) {
//					treeValueChanged(e,LhTreePaneView.this.getJTree());
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

//	@Override
//	public void treeValueChanged(TreeSelectionEvent e, JTree jTree) {
//		//Returns the last path element of the selection.
//		//This method is useful only when the selection model allows a single selection.
////		if (jTree.isFocusOwner()||jTree.requestFocusInWindow()){
////			System.out.println("Fokus 005 Tree vor");
//		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
//		    if (node==null){
//		    	//nix ist gewählt
//		    }else{
//		    	if (node.getUserObject()!=null){
//			    	ModelKnotenBean selectedModelKnotenBean = (ModelKnotenBean) node.getUserObject();
//			    	//ausgewählten Knoten an Kontroller weitergeben.
//			    	getTreeController().selectKnoten(selectedModelKnotenBean);
//		    	}
//		    }
////			System.out.println("Fokus 005 Tree nach");
//			
////		}else{
////			System.out.println("kein Fokus 004 Tree");
////		}
//	}


	@Override
	/**
	 * Der gewählt Knoten (Anforderung, oder die Anforderungsposition wird gewählt)
	 */
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		//Während wir zeichnen, soll JTree nicht reagieren.(z.B. auf Auswahl eines Knotens)
		getJTree().removeTreeSelectionListener(getTreeSelectionListner());
		//Wir holen den Zeiger auf den aktuellen root-Knoten
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getJTree().getModel().getRoot();
		//aktuellen Inhalt des Trees löschen.
		root.removeAllChildren();
		
		//neuen Root-Knoten setzen.
		if (modelKnotenBean != null) {
			root.setUserObject(null);
			if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_HERSTELLER) {
				root.setUserObject(modelKnotenBean); // Anforderung wurde
				// angeklickt oder
				// gewählt.
			}else{
				if (modelKnotenBean.getSammelKnotenTypENUM() == ModelKnotenTyp.LIEFERANT_HERSTELLER_KATALOG
						) {
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
		if (root.getUserObject() instanceof LhModelKnotenBean){
			//Anforderung wir im Tree gezeichnet.
			TreePath angeklickterKnoten = null;
			LhModelKnotenBean lhModelKnotenBean = (LhModelKnotenBean)root.getUserObject();
			for (ModelKnotenBean modelKnotenBeanKind : lhModelKnotenBean
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
			if (modelKnotenBean==lhModelKnotenBean){
				angeklickterKnoten = new TreePath(root.getPath());
			}
			if(angeklickterKnoten!=null){
				  getJTree().setSelectionPath(angeklickterKnoten);
			}
		}else{
			//Log.log().severe("Fehler! Im Tree konnte das UserObjekt nicht korrekt gesetzt werden.");
		}
		
		
		
		//Nachdem der JTree neu gezeichnet ist, darf er wieder auf die Maus reagieren.
		getJTree().addTreeSelectionListener(getTreeSelectionListner());
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
						treeValueChanged(e,LhTreePaneView.this.getJTree());
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
	    	super();
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

					if (modelKnotenBean instanceof LhModelKnotenBean) {
						LhModelKnotenBean lhModelKnotenBean = (LhModelKnotenBean)modelKnotenBean;
						String IconNameStatus = BeanDBStatus.JavaToIconName(lhModelKnotenBean.getIBean().getBeanDBStatus());
						Icon icon = Run.createCompoundIcon(Konstanten.ICON_LIEFERANT_HERSTELLER, IconNameStatus,iconError);
						setIcon(icon);
					}
					;
					if (modelKnotenBean instanceof LhKatalogModelKnotenBean) {
						LhKatalogModelKnotenBean lhKatalogModelKnotenBean = (LhKatalogModelKnotenBean)modelKnotenBean;
						String IconNameStatus = BeanDBStatus.JavaToIconName(lhKatalogModelKnotenBean.getIBean().getBeanDBStatus());
						Icon icon = Run.createCompoundIcon(Konstanten.ICON_LIEFERANT_HERSTELLER_KATALOG, IconNameStatus,iconError);
						setIcon(icon);
					}
					;
					
//					if (modelKnotenBean instanceof LhZugriffsrechtModelKnotenBean) {
//						LhZugriffsrechtModelKnotenBean lhZugriffsrechtModelKnotenBean = (LhZugriffsrechtModelKnotenBean)modelKnotenBean;
//						String IconNameStatus = BeanDBStatus.JavaToIconName(lhZugriffsrechtModelKnotenBean.getIBean().getBeanDBStatus());
//						Icon icon = Run.createCompoundIcon(Konstanten.ICON_LIEFERANT_HERSTELLER_KATALOG, IconNameStatus,iconError);
//						setIcon(icon);
//					}
//					;
					
					
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


