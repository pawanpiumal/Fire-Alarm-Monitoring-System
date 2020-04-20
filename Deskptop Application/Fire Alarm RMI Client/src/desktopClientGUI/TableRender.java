package desktopClientGUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

class TableRender extends DefaultTableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableRender() {
		super();
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		float backgroudnColorHSBGreen[] = Color.RGBtoHSB(65, 170, 65, null);
		float backgroudnColorHSBYello[] = Color.RGBtoHSB(168, 170, 65, null);
		float backgroudnColorHSBRed[] = Color.RGBtoHSB(170, 65, 65, null);
		float backgroudnColorHSBPink[] = Color.RGBtoHSB(192, 169, 166, null);
		if(((String)table.getValueAt(row, 1)).equals("Not Active")) {
			setForeground(Color.black);
			setBackground(Color.getHSBColor(backgroudnColorHSBPink[0], backgroudnColorHSBPink[1], backgroudnColorHSBPink[2]));
		}else if ((Integer) table.getValueAt(row, 5) < 5 && (Integer) table.getValueAt(row, 4) < 5) {
			setForeground(Color.black);
			setBackground(Color.getHSBColor(backgroudnColorHSBGreen[0], backgroudnColorHSBGreen[1], backgroudnColorHSBGreen[2]));
		} else if ((Integer) table.getValueAt(row, 5) == 5 || (Integer) table.getValueAt(row, 4) == 5) {
			setForeground(Color.black);
			setBackground(Color.getHSBColor(backgroudnColorHSBYello[0], backgroudnColorHSBYello[1], backgroudnColorHSBYello[2]));
		} else if((Integer) table.getValueAt(row, 5) > 5 || (Integer) table.getValueAt(row, 4) > 5){
			setBackground(Color.getHSBColor(backgroudnColorHSBRed[0], backgroudnColorHSBRed[1], backgroudnColorHSBRed[2]));
			setForeground(Color.black);
		}
		setText(value != null ? value.toString() : "");
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
}