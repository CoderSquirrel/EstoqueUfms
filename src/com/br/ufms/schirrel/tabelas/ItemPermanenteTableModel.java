package com.br.ufms.schirrel.tabelas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ItemPermanenteTableModel extends MyDefaultModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -594384802321424004L;
	private String[] columnNames = { "Item", "Descrição", "Entrada", "Qtd", "Dep",  "Lab",
			"Patrimonio", "Estado", "OBS" };
	public boolean DEBUG = false;


	public ItemPermanenteTableModel(List<Object[]> data) {
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