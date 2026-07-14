package de.keag.lager.core;

import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import de.keag.lager.core.fehler.Log;

public class LagerTreePath extends TreePath {

	public LagerTreePath(Object[] path){
		super(path);
	}

	public LagerTreePath(TreeNode root) {
		super(root);
	}

	/**
	 * Zwei Pfade werden anhand der User-Objeckte vergliechen, aber nur dann, falls
	 * ModelKnotenBeans in beiden Pfade sind.
	 */
	@Override
    public boolean equals(Object o) {
		if (o!=null && o instanceof TreePath){
			GenericNode node = (GenericNode) ((TreePath)o).getLastPathComponent();
			Object userObject = node.getUserObject();
			GenericNode this_node = (GenericNode)this.getLastPathComponent();
			Object this_userObject = this_node.getUserObject();
			if (userObject!=null && this_userObject!=null){
				if (userObject instanceof ModelKnotenBean && this_userObject instanceof ModelKnotenBean){
					Boolean vergleich = ((ModelKnotenBean)this_userObject).equals((ModelKnotenBean)userObject);
//					System.out.println("LagerTreePath-equals(o/o/ergebnis :"+userObject + "/"+ this_userObject + "/"+vergleich);
					Log.log().finest("LagerTreePath-equals(o/o/ergebnis :"+userObject + "/"+ this_userObject + "/"+vergleich);
					return vergleich;
				}else{
					Log.log().severe("keine ModelknotenBeans:"+ userObject + ","+this_userObject);
				}
			}
		}
		return super.equals(o);
		
	}
		
	
}
