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
