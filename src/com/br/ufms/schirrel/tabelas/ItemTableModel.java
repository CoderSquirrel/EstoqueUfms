package com.br.ufms.schirrel.tabelas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ItemTableModel extends MyDefaultModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1451953691800449874L;
	private String[] columnNames = { "Item", "Unidade", "Fabricante", "Fabricacao", "Validade", "Q. Rec", "Entrada",
			"Q. Ret" };
	public boolean DEBUG = false;


	public ItemTableModel(List<Object[]> data) {
		this.data = data;

	}
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}


	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
}