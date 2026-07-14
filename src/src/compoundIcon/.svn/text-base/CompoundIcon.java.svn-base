package compoundIcon;
/*
 * (c)2008 mharrison 
 * This class is released under GPLv3
 */

import java.awt.Component;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.Icon;
 

public class CompoundIcon implements Icon {
	 
    private Vector _icons = new Vector();
    private int _spaceSize = 2;
 
    public CompoundIcon(Icon[] icons) {
        for (int i = 0; i < icons.length; i++) {
            _icons.add(icons[i]);
        }
    }
 
    @Override
    public int getIconHeight() {
        int result = 0;
        for (Icon icon : getIcons()) {
            result = Math.max(result, icon.getIconHeight());
        }
        return result;
    }
 
    @Override
    public int getIconWidth() {
        int result = 0;
        for (Icon icon : getIcons()) {
            result += icon.getIconWidth();
            result += getSpaceSize();
        }
        return result;
    }
 
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        int h = getIconHeight();
        int offset = 0;
 
        for (Icon icon : getIcons()) {
            icon.paintIcon(c, g, x + offset, y + (h - icon.getIconHeight()) / 2);
            offset += icon.getIconWidth();
            offset += getSpaceSize();
        }
    }
 
    public int getSpaceSize() {
        return _spaceSize;
    }
 
    public void setSpaceSize(int spaceSize) {
        this._spaceSize = spaceSize;
    }
 
    public void add(Icon icon) {
        _icons.add(icon);
    }
 
    public Icon[] getIcons() {
    	Icon[] icons = new Icon[_icons.size()];
    	for(int i=0; i<_icons.size();i++){
    		icons[i] = (Icon)_icons.get(i);
    	}
        return icons;
    }
}
