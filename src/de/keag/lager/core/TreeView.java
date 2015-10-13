package de.keag.lager.core;

import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import de.keag.lager.core.fehler.Fehler;
import de.keag.lager.core.fehler.Log;
import de.keag.lager.core.fehler.dialog.JFehlerDialogWechselController;

abstract public class TreeView extends View implements ITreeBeobachter{
	private TreeSelectionListener treeSelectionListner = null;  //  @jve:decl-index=0:
	private DefaultTreeModel treeModel = null; // @jve:decl-index=0:
	protected TreePath angeklickterKnotenTreePath = null;
	private ArrayList<TreePath> offenePfade;
	
	public TreeView() {
		super();
	}
	
//	@Override
//	public String getToolTipText(MouseEvent evt) {
//        if (getJTree().getRowForLocation(evt.getX(), evt.getY()) == -1)
//          return null;
//        TreePath curPath = getJTree().getPathForLocation(evt.getX(), evt.getY());
//        return ((GenericNode) curPath.getLastPathComponent())
//            .getToolTipText();
//      }	

	public void zeichneKinderImTree(ModelKnotenBean vater,	
			GenericNode treeNodeDesVaters, 
			ModelKnotenBean angeklickterModelKnotenBean) {
		treeNodeDesVaters.setUserObject(vater);
		if (vater.equals(angeklickterModelKnotenBean)){
			angeklickterKnotenTreePath = new TreePath(treeNodeDesVaters.getPath());
//			angeklickterKnotenTreePath = new LagerTreePath(treeNodeDesVaters.getPath());
		}
		for(int i = 0; i< vater.getKinderList().size();i++){
			GenericNode neuerPosTreeKnoten = new GenericNode(vater.getKinderList().get(i));
			neuerPosTreeKnoten.setToolTipText(vater.getKinderList().get(i).toString());
			treeNodeDesVaters.add(neuerPosTreeKnoten);
			zeichneKinderImTree(vater.getKinderList().get(i), neuerPosTreeKnoten, angeklickterModelKnotenBean);
		}
	}
	
	
	@Override
	/**
	 * Der gewählt Knoten (Anforderung, oder die Anforderungsposition wird gewählt)
	 */
	public void zeichneDich(ModelKnotenBean modelKnotenBean, IModel iModel) {
		
		SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" );
		
//		this.setToolTipText("Tip");
		
		Log.log().finest("--------------- zeichneDich Beginn -----------------"  + df.format(Bean.getAktuellesDatum())) ;
		
		//Während wir zeichnen, soll JTree nicht reagieren.(z.B. auf Auswahl eines Knotens)
		getJTree().removeTreeSelectionListener(getTreeSelectionListner());
		//Wir holen den Zeiger auf den aktuellen root-Knoten
//		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getJTree().getModel().getRoot();
		GenericNode root = (GenericNode) getJTree().getModel().getRoot();

		Log.log().finest("--------------- zeichneDich After removeTreeSelectionListener -----------------"+ df.format(Bean.getAktuellesDatum())) ;
		
		//Alle aktuell gewählte Pfade werden gemerkt.
		ArrayList<TreePath> offenePfade = getOffenePfade();
		offenePfade.clear();
//		ArrayList<TreePath> blaetter = new ArrayList<TreePath>();
		if (root!=null){
			merkeAlleOffenenPfade(getJTree(), offenePfade, null);
//			merkeAlleOffenenPfade(getJTree(), offenePfade, blaetter);
		}
		
		Log.log().finest("--------------- zeichneDich After merkeAlleOffenenPfade -----------------"+ df.format(Bean.getAktuellesDatum())) ;
		
		angeklickterKnotenTreePath = null;
		//aktuellen Inhalt des Trees löschen.
		root.removeAllChildren();
		if (modelKnotenBean != null) {
			ModelKnotenBean urvater = modelKnotenBean; //Annahme!
			while (urvater.getVater()!=null){
				urvater = urvater.getVater(); 
			}
			//Alle Kinder
			zeichneKinderImTree(urvater,root,modelKnotenBean);
		}else{
			root.setUserObject(null);
		}

		Log.log().finest("--------------- zeichneDich After zeichneKinderImTree -----------------"+ df.format(Bean.getAktuellesDatum())) ;
		
		//neu Zeichnen (danach die pfade wieder öffnen)
		((DefaultTreeModel) getJTree().getModel()).reload();
//		this.repaint();    //alte Komponenten werden gelöscht
//		this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
//		this.validate();   //werden gezeichnet.
//		this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
		
		Log.log().finest("--------------- zeichneDich After Reload -----------------"+ df.format(Bean.getAktuellesDatum())) ;

		

		//alte Pfade nach möglichkeit öffenen (expandieren)
		for (TreePath tp: offenePfade ){
			LagerTreePath ltp = new LagerTreePath(tp.getPath());
			if (!getJTree().isExpanded(ltp)){
				getJTree().expandPath(ltp);
				Log.log().finest("Expand:tp"+tp);
			}
			
			
//			getJTree().expandPath(tp);
		}

		Log.log().finest("--------------- zeichneDich After Expand -----------------"+ df.format(Bean.getAktuellesDatum())) ;
		
//		//alte Pfade nach möglichkeit öffenen (expandieren)
//		for (TreePath bl: blaetter ){
//			LagerTreePath btp = new LagerTreePath(bl.getParentPath().getPath());
//			if (!getJTree().isExpanded(btp)){
//				getJTree().expandPath(btp);
//				Log.log().finest("Blatt:btp"+btp);
//			}
////			getJTree().expandPath(tp);
////			getJTree().expandPath(new LagerTreePath(bl.getPath()));
//		}
		

//		expandAll(getJTree(),true, angeklickterKnotenTreePath); //Alle Zweige aufklappen		
		

		//angeklickten Knoten neu markieren
		if(angeklickterKnotenTreePath!=null){
			if (angeklickterKnotenTreePath.getParentPath()!=null){
				  LagerTreePath ktp = new LagerTreePath(angeklickterKnotenTreePath.getParentPath().getPath());
				  if (!getJTree().isExpanded(ktp)){
					  getJTree().expandPath(ktp);
					  expandAll(getJTree(),true,ktp);
					  Log.log().finest("Expand Selection:Lief"+ktp);
				  }
					getJTree().setSelectionPath(angeklickterKnotenTreePath);
					Log.log().finest("Select Selection:Lief"+angeklickterKnotenTreePath);
			}
//			getJTree().setSelectionPath(angeklickterKnotenTreePath);
			Log.log().finest("Select Selection:Lief"+angeklickterKnotenTreePath);
//			System.out.println("Select Selection:Lief"+angeklickterKnotenTreePath);
			
//			  getJTree().expandPath(angeklickterKnotenTreePath.getParentPath());
//			  Log.log().severe("Expand Selection:Lief"+angeklickterKnotenTreePath.getParentPath());
//			  getJTree().setSelectionPath(angeklickterKnotenTreePath);
//			  Log.log().severe("Select Selection:Lief"+angeklickterKnotenTreePath);
		}

		
		this.repaint();    //alte Komponenten werden gelöscht
		this.invalidate(); //alle bis zu dem obersten Kontainer auf ungültig
		this.validate();   //werden gezeichnet.
		this.revalidate(); //Layout-Manager tut seinen JOB, und richtet die Komponenten auf
		
		Log.log().finest("--------------- zeichneDich After Selection -----------------"+ df.format(Bean.getAktuellesDatum())) ;
		
		//Nachdem der JTree neu gezeichnet ist, darf er wieder auf die Maus reagieren.
		getJTree().addTreeSelectionListener(getTreeSelectionListner());
		
		Log.log().finest("--------------- zeichneDich ende -----------------"+ df.format(Bean.getAktuellesDatum())) ;
		
	}

	
	
	private ArrayList<TreePath> getOffenePfade() {
		if (offenePfade==null){
			offenePfade = 
				new ArrayList<TreePath>();
		}
		return offenePfade;
	}

	abstract public TreeController getTreeController() ;

	
	/**
	 * Achtung ! Aufruf direkt aus dem jTree.Listener aus this.getTreeSelectionListner()
	 * @param e
	 * @param jTree
	 */
	protected void treeValueChanged(TreeSelectionEvent e, JTree jTree) {

		//Returns the last path element of the selection.
		//This method is useful only when the selection model allows a single selection.
//		if (jTree.isFocusOwner()||jTree.requestFocusInWindow()){
//			System.out.println("Fokus 005 Tree vor");
		    DefaultMutableTreeNode node = (DefaultMutableTreeNode)jTree.getLastSelectedPathComponent();
		    if (node==null){
		    	//nix ist gewählt
		    	Log.log().finest("TreeView:no node");
		    }else{
		    	ModelKnotenBean selectedModelKnotenBean = (ModelKnotenBean) node.getUserObject();
		    	//ausgewählten Knoten an Kontroller weitergeben.
		    	Log.log().finest("TreeView:" + selectedModelKnotenBean.toString());
		    	getTreeController().selectKnoten(selectedModelKnotenBean);
		    }
//			System.out.println("Fokus 005 Tree nach");
			
//		}else{
//			System.out.println("kein Fokus 004 Tree");
//		}
	}
	
	public boolean prueferFehler() {
		Log.log().finest("TreeView: prüfeFehler vor");
		ArrayList<Fehler> errors = getTreeController().setzeNeuenAktivenController();
		for(int i=0; i<errors.size();i++){
			Log.log().finest("Kontroller-Umschalten ist nicht möglich:" + errors.get(i).getMessage());
		}
		Boolean result = errors.size()==0;
		if(!result){
			JFehlerDialogWechselController.getOneIntstance().showView(errors);
			//Neues zeichnen des Trees(eher nicht nötig)
//			getBaTreePaneController().setStandardFocusPosition();
			
		}
		Log.log().finest("TreeView: prüfeFehler nach");
		return errors.size()==0;
	}

	protected TreeSelectionListener getTreeSelectionListner() {
		if(treeSelectionListner==null){
			//Wenn der Zeiger auf TreeSelectionListner-Interface noch leer ist,
			//dann wird ein Objekt erzeugt, welches das Interface TreeSelectionListner
			//implemntiert.
			//Ein TreeSelectionListner-Objekt ist ein Objekt, 
			//welches auf den Maus-Klick auf ein JTree-Knoten reagiert .
			treeSelectionListner = new TreeSelectionListener() {
				
				@Override
				public void valueChanged(TreeSelectionEvent e) {
					Log.log().finest("TreeView: valueChanged: prüfeFehler vor");
					if (prueferFehler()){
						Log.log().finest("TreeView: valueChanged: prüfeFehler nach");
						Log.log().finest("TreeView: valueChanged: treeValueCanged vor");
						treeValueChanged(e,TreeView.this.getJTree());
						Log.log().finest("TreeView: valueChanged: treeValueCanged nach");
					}else{
						Log.log().finest("TreeView: valueChanged: aktualisiereAnzeige vor");
						getTreeController().aktualisiereAnzeige();
						Log.log().finest("TreeView: valueChanged: aktualisiereAnzeige nach");
					}
				}
			};
		}
		return treeSelectionListner;
	}

	abstract public JTree getJTree();
	
//	@Override
//	public String getToolTipText(MouseEvent evt) {
//        if (getJTree().getRowForLocation(evt.getX(), evt.getY()) == -1)
//          return null;
//        TreePath curPath = getJTree().getPathForLocation(evt.getX(), evt.getY());
//        return ((GenericNode) curPath.getLastPathComponent())
//            .getToolTipText();
//    }	

	
	public DefaultTreeModel getTreeModel(TreeNode root) {
		if (treeModel == null) {
			treeModel = new DefaultTreeModel(root);
		}
		return treeModel;
	}

	abstract public void setTreeController(TreeController treePaneController);

	
	public void merkeAlleOffenenPfade(JTree tree, ArrayList<TreePath> offenePfade, ArrayList<TreePath> blaetter){
	    TreeNode root = (TreeNode) tree.getModel().getRoot();
	    merkeAlleOffenenPfade(tree, new TreePath(root), offenePfade,blaetter);
//	    if (blaetter!=null){
//		    merkeAlleBlaetter(tree, new TreePath(root),blaetter);
//	    }
//	    merkeAlleOffenenPfade(tree, new LagerTreePath(root), offenePfade);
	  }

	private void merkeAlleBlaetter(JTree tree, TreePath parent,
			ArrayList<TreePath> blaetter) {
		//Recurstion
	    TreeNode node = (TreeNode) parent.getLastPathComponent(); //eigener Node wird ermittelt(erster ist root-Node)
	    if (node.getChildCount() >= 0) {
	      for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements();) {
	        TreeNode n = (TreeNode) e.nextElement(); //Child-Node wird ermittelt
	        TreePath path = parent.pathByAddingChild(n); //Pfad zu dem Child wird erstellt
	        merkeAlleBlaetter(tree, path, blaetter); //Rekursion über alle Kinder
	      }
	    }
//	    //Eigentlicher Ablauf
//	    if (node.getChildCount() >= 0) {
//		      for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements();) {
//		        TreeNode n = (TreeNode) e.nextElement(); //Child-Node wird ermittelt
//		        TreePath path = parent.pathByAddingChild(n); //Pfad zu dem Child wird erstellt
//		        merkeAlleBlaetter(tree, path, blaetter); //Rekursion über alle Kinder
//		      }
//	    }
	}

	private void merkeAlleOffenenPfade(JTree tree, TreePath parent, ArrayList<TreePath> offenePfade, ArrayList<TreePath> blaetter) {
	    TreeNode node = (TreeNode) parent.getLastPathComponent(); //eigener Node wird ermittelt(erster ist root-Node)
	    if (node.getChildCount() >= 0) {
	      for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements();) {
	        TreeNode n = (TreeNode) e.nextElement(); //Child-Node wird ermittelt
	        TreePath path = parent.pathByAddingChild(n); //Pfad zu dem Child wird erstellt
	        merkeAlleOffenenPfade(tree, path, offenePfade,blaetter); //Rekursion über alle Kinder
	      }
	    }
	    Enumeration<TreePath> neueOffenePfade = tree.getExpandedDescendants(parent); //Hier beginnt die eigentliche Arbeit der Funtion
	    while(neueOffenePfade!=null &&	neueOffenePfade.hasMoreElements()){
	    	//Wenn noch nicht im Array, dann hinzufügen.
	    	TreePath offenerTreePath = neueOffenePfade.nextElement();
	    	if (offenePfade.indexOf(offenerTreePath)==-1){
			    	offenePfade.add(offenerTreePath);
			    	//Blätter - Pfade sammeln
			    	if (blaetter!=null){
					    TreeNode offenerTreeNode = (TreeNode) offenerTreePath.getLastPathComponent(); //eigener Node wird ermittelt(erster ist root-Node)
					    for(int i = 0; i < offenerTreeNode.getChildCount();i++){
					    	TreeNode child =   	offenerTreeNode.getChildAt(i);
					    	if (child.isLeaf()){
					    		TreePath childPath = offenerTreePath.pathByAddingChild(child); //Pfad zu dem Child wird erstellt
					    		if (blaetter.indexOf(childPath)==-1){
					    			blaetter.add(childPath);
					    		}
					    	}
					    }
			    	}
	    	}
	    }
	}	
	
		
	
	public void expandAll(JTree tree, boolean expand, TreePath nurDiesenTreePath) {
	    TreeNode root = (TreeNode) tree.getModel().getRoot();
	    expandAll(tree, new TreePath(root), expand, nurDiesenTreePath);
	  }

	  private void expandAll(JTree tree, TreePath parent, boolean expand, TreePath nurDiesenTreePath) {
	    TreeNode node = (TreeNode) parent.getLastPathComponent();
	    if (node.getChildCount() >= 0) {
	      for (Enumeration<TreeNode> e = node.children(); e.hasMoreElements();) {
	        TreeNode n = (TreeNode) e.nextElement();
	        TreePath path = parent.pathByAddingChild(n);
	        expandAll(tree, path, expand, nurDiesenTreePath);
	      }
	    }
	    if (parent.equals(nurDiesenTreePath) || nurDiesenTreePath==null){
		    if (expand){
			    tree.expandPath(parent);
		    }else{
			    tree.collapsePath(parent);
		    }
	    }
	    
	  }	
	
}
