package de.keag.lager.core;

import java.awt.event.MouseEvent;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class JLagerTree extends JTree {

	@Override
	public String getToolTipText(MouseEvent evt) {
        if (this.getRowForLocation(evt.getX(), evt.getY()) == -1)
          return null;
        TreePath curPath = this.getPathForLocation(evt.getX(), evt.getY());
        if (curPath.getLastPathComponent() instanceof  GenericNode){
        	String s = ((GenericNode) curPath.getLastPathComponent())
            .getToolTipText().trim();
        	if (s.isEmpty()){
        		return null;
        	}else{
        		return s;
        	}
        }else{
        	return null;
        }
    }	
	
	
}
