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
package de.keag.lager.core;

import javax.swing.tree.DefaultMutableTreeNode;

import de.keag.lager.core.fehler.Log;

public class GenericNode extends DefaultMutableTreeNode {

	private String toolTipText;
	
	public String getToolTipText() {
		if (toolTipText==null){
			toolTipText = "";
		}
		return toolTipText;
	}	
	
//	@Override
	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}	
	
//	http://www.java2s.com/Code/Java/Swing-Components/ToolTipTreeExample.htm
//	  public ToolTipTreeNode(String str, String toolTipText) {
//	    super(str);
//	    this.toolTipText = toolTipText;
//	  }

	
    public GenericNode(Object userObject) {
    	super(userObject, true);
    }
	
	@Override
	 public boolean equals(Object o)
	 {
	   if (o instanceof DefaultMutableTreeNode){
		   DefaultMutableTreeNode n = (DefaultMutableTreeNode)o;
		   if (this.getUserObject()!=null && n.getUserObject()!=null){
			   Boolean vergleich;
			   if (this.getUserObject() instanceof ModelKnotenBean && n.getUserObject() instanceof ModelKnotenBean){
				   IBean this_ibean = ((ModelKnotenBean)this.getUserObject()).getIBean();
				   IBean ibean = ((ModelKnotenBean)n.getUserObject()).getIBean();
				   if (this_ibean!=null && ibean!=null){
					   vergleich = ((Bean)this_ibean).equals((Bean)ibean);
				   }else{
					   vergleich = this.getUserObject().equals(n.getUserObject()); 
//					   System.out.println("FEHler? CenericNode-equals(o/o/erg):"+this.getUserObject() + "/" + n.getUserObject() + "/" + vergleich.toString());
//					   Log.log().severe("FEHler? CenericNode-equals(o/o/erg):"+this.getUserObject() + "/" + n.getUserObject() + "/" + vergleich.toString());
				   }
			   }else{
				   vergleich = this.getUserObject().equals(n.getUserObject()); 
//				   System.out.println("Fehler? CenericNode-equals(o/o/erg):"+this.getUserObject() + "/" + n.getUserObject() + "/" + vergleich.toString());
				   Log.log().finest("Fehler? CenericNode-equals(o/o/erg):"+this.getUserObject() + "/" + n.getUserObject() + "/" + vergleich.toString());
			   }
//			   System.out.println("CenericNode-equals(o/o/erg):"+this.getUserObject() + "/" + n.getUserObject() + "/" + vergleich.toString());
			   return vergleich;
		   }
	   }
	   Log.log().finest("CenericNode-equals, Default:"+super.equals(o));
	   return super.equals(o);
	 }
	 
	@Override
	 public int hashCode()
	 {
       if (this.getUserObject()==null){
    	   Log.log().finest("CenericNode-hashCode, Default:"+super.hashCode());
    	   return super.hashCode();
       }else{
    	   if (this.getUserObject() instanceof ModelKnotenBean){
    		   int erg = ((ModelKnotenBean)this.getUserObject()).hashCodeForTreeView();
//			   System.out.println("CenericNode-hashCode(o/):"+((ModelKnotenBean)this.getUserObject()) + "/"+erg);
    		   return erg;
    	   }
    	   Log.log().finest("CenericNode-hashCode, Kein ModelknotenBean:"+this.getUserObject().hashCode());
    	   return this.getUserObject().hashCode();
       }
	 }	
	
	
//    /**
//     * Kopiert aus der Klasse DefaultMutableTreeNode
//     * Builds the parents of node up to and including the root node,
//     * where the original node is the last element in the returned array.
//     * The length of the returned array gives the node's depth in the
//     * tree.
//     * 
//     * @param aNode  the TreeNode to get the path for
//     * @param depth  an int giving the number of steps already taken towards
//     *        the root (on recursive calls), used to size the returned array
//     * @return an array of TreeNodes giving the path from the root to the
//     *         specified node 
//     */
//	protected TreeNode[] getPathToRoot(TreeNode aNode, int depth) {
//		TreeNode[] retNodes;
//
//		/*
//		 * Check for null, in case someone passed in a null node, or they passed
//		 * in an element that isn't rooted at root.
//		 */
//		if (aNode == null) {
//			if (depth == 0)
//				return null;
//			else
//				retNodes = new TreeNode[depth];
//		} else {
//			depth++;
//			retNodes = getPathToRoot(aNode.getParent(), depth);
//			retNodes[retNodes.length - depth] = aNode;
//		}
//		return retNodes;
//	}

}
