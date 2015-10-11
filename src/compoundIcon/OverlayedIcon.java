package compoundIcon;
/*
 * (c)2008 mharrison 
 * This class is released under GPLv3
 */
import java.awt.Component;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.Icon;

public class OverlayedIcon implements Icon {
	 
    private Vector _icons = new Vector();
    private int _spaceSize = 2;
 
    public OverlayedIcon(Icon[] icons) {
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
            result = Math.max(result, icon.getIconWidth());
        }
        return result;
    }
 
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        int h = getIconHeight();
        int w = getIconWidth();
 
        for (Icon icon : getIcons()) {
            icon.paintIcon(c, g, x + (w - icon.getIconWidth()) / 2, y + (h - icon.getIconHeight()) / 2);
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
 
//    public Vector getIcons() {
//        return _icons;
//    }
    public Icon[] getIcons() {
    	Icon[] icons = new Icon[_icons.size()];
    	for(int i=0; i<_icons.size();i++){
    		icons[i] = (Icon)_icons.get(i);
    	}
        return icons;
    }}