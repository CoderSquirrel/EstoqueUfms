package com.br.ufms.schirrel.tabelas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class SaidaTableModel extends MyDefaultModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4133677953918985085L;
	private String[] columnNames = {
			"Item", "Fabricante", "Entrada",
			"Validade",  "Usuario",
			"Retirada", "QTD" };
	public boolean DEBUG = false;

	public SaidaTableModel(List<Object[]> data) {
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