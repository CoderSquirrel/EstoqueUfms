package com.br.ufms.schirrel.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Entrada;
import com.br.ufms.schirrel.exportar.ExportarRelatorio;
import com.br.ufms.schirrel.tabelas.ItemTableModel;

public class RelatorioInativos extends JPanel implements ActionListener, TableModelListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Entrada> entradas;
	DAO dao;
	private JTable EntradaTable;
	private JButton btGerar, btNova;

	public RelatorioInativos(DAO D) {
		dao = D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Itens Inativos", TitledBorder.LEADING, TitledBorder.CENTER, null, null));
		setBackground(Color.WHITE);
		btNova = new EUButton("Nova Entrada");
		btNova.setBounds(220, 340, 200, 30);
		btNova.addActionListener(this);
		btNova.setEnabled(false);
		add(btNova);

		btGerar = new EUButton("Gerar Relatorio");
		btGerar.setBounds(10, 340, 200, 30);
		btGerar.addActionListener(this);
		add(btGerar);

		List<Object[]> a = CarregarLista();
		EntradaTable = new JTable(new ItemTableModel(a));
		PreencherTabela();

		EntradaTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(1).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(7).setPreferredWidth(15);
		EntradaTable.getModel().addTableModelListener(this);
		JScrollPane scrollPane = new JScrollPane(EntradaTable);
		scrollPane.setBounds(10, 20, 780, 300);

		EntradaTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				btNova.setEnabled(true);
			}
		});

		add(scrollPane);

	}

	List<Object[]> CarregarLista() {
		entradas = dao.ListarEntradasInativas();
		Object[] objs;
		List<Object[]> objetos = new ArrayList<Object[]>();

		for (int i = 0; i < entradas.size(); i++) {
			objs = new Object[8];
			objs[0] = entradas.get(i).getItem().getItem();
			objs[1] = entradas.get(i).getUnidade().getUnidade();
			objs[2] = entradas.get(i).getFabricante().getFabricante();
			objs[3] = entradas.get(i).getDataFabricacao();
			objs[4] = entradas.get(i).getDataValidade();
			objs[5] = entradas.get(i).getQtd();
			objs[6] = entradas.get(i).getDataEntrada();
			objs[7] = entradas.get(i).getRetirada();
			objetos.add(objs);
		}
		btGerar.setEnabled(!(objetos.size() == 0));
		btNova.setEnabled(false);
		return objetos;
	}

	void PreencherTabela() {

		ItemTableModel it = (ItemTableModel) EntradaTable.getModel();

		it.RemoveAll();
		it.AddList(CarregarLista());
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btNova) {
			int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade: "));
			entradas.get(EntradaTable.getSelectedRow()).setQtd(qtd);
			dao.NovaEntrada(entradas.get(EntradaTable.getSelectedRow()));
			PreencherTabela();
		} else if (e.getSource() == btGerar) {
			int op = JOptionPane.showConfirmDialog(null, "Gerar Relatorio de  Materias de Consumo Inativos?");
			if (op == 0) {
				if (new ExportarRelatorio().GerarRelatorioConsumo(entradas,
						"Relatorio de Materiais de Consumo Inativos", "RelatorioMCInativos.xls")) {
					JOptionPane.showMessageDialog(null, "Relatorio Gerado Com Sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Problema na Geração do Relatorio");
				}
			}

		}

	}

	@Override
	public void tableChanged(TableModelEvent e) {

	}

}