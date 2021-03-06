package com.br.ufms.schirrel.tabelas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MyDefaultModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4133677953918985085L;
	public boolean DEBUG = false;
	protected List<Object[]> data;

	

	public int getRowCount() {
		return data.size();
	}


	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}

	/*
	 * JTable uses this method to determine the default renderer/ editor for
	 * each cell. If we didn't implement this method, then the last column would
	 * contain text ("true"/"false"), rather than a check box.
	 */
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		if (DEBUG) {
			System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
					+ value.getClass() + ")");
		}

		data.get(row)[col] = value;
		fireTableCellUpdated(row, col);

		if (DEBUG) {
			System.out.println("New value of data:");
			printDebugData();
		}
	}

	private void printDebugData() {
		int numRows = getRowCount();
		int numCols = getColumnCount();

		for (int i = 0; i < numRows; i++) {
			System.out.print("    row " + i + ":");
			for (int j = 0; j < numCols; j++) {
				System.out.print("  " + data.get(i)[j]);
			}
			System.out.println();
		}
		System.out.println("--------------------------");
	}

	public void addRow(Object[] no) {
		data.add(no);
		this.fireTableDataChanged();
	}

	public void delRow(int row) {
		data.remove(row);
		this.fireTableDataChanged();

	}

	public void AddList(List<Object[]> a) {
		for (Object[] o : a) {
			data.add(o);
		}

	}

	public void RemoveAll() {
		data.clear();
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}