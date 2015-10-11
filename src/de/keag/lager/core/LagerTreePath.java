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
