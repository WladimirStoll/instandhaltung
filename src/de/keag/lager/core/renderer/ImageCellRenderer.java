package de.keag.lager.core.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

//http://java.sun.com/docs/books/tutorial/uiswing/components/table.html#renderer
public class ImageCellRenderer extends JLabel implements TableCellRenderer{
	
	private boolean isBordered;

	public ImageCellRenderer(boolean isBordered) {
		super();
        this.isBordered = isBordered;
        setOpaque(true); //MUST do this for background to show up.
    }
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return (JLabel)this;
	}

}
