package halfback.cmpm.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import halfback.cmpm.model.Chronicle;

public class ChronicleTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    
    private List<ChronicleTableEntry> _table;

    public ChronicleTableModel(Iterable<Chronicle> chronicles) {
		_table = new ArrayList<ChronicleTableEntry>();
		for (Chronicle chronicle : chronicles) {
			_table.add(new ChronicleTableEntry(chronicle));
		}
    }

	@Override
	public int getRowCount() {
		return _table.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return rowIndex;
			case 1:
				return _table.get(rowIndex).toString();
			case 2:
				return _table.get(rowIndex).getSupport();
			case 3:
				return _table.get(rowIndex).getSize();
			default:
				return null;
		}
	}

	@Override
	public String getColumnName(int col) {
		switch (col) {
			case 1:
				return "Motif";
			case 2:
				return "Support";
			case 3:
				return "Taille";
			default:
				return "";
		}
	}

	public ChronicleTableEntry getEntry(int row) {
		return _table.get(row);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (_table.isEmpty()) {
			return Object.class;
		}
		return getValueAt(0, columnIndex).getClass();
	}
}