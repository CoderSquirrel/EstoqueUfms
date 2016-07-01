package com.br.ufms.schirrel.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Entrada;
import com.br.ufms.schirrel.tabelas.ItemTable;

public class RelatorioInativos extends JPanel implements ActionListener, TableModelListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Entrada> entradas;
	DAO dao;
	private JTable EntradaTable;

	public RelatorioInativos(DAO D) {
		dao = D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Itens", TitledBorder.LEADING, TitledBorder.CENTER, null, null));

		List<Object[]> a = CarregarLista();
		EntradaTable = new JTable(new ItemTable(a));
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

		add(scrollPane);

		JButton btNova = new JButton("Nova Entrada");
		btNova.setBounds(300, 340, 200, 30);
		btNova.addActionListener(this);
		add(btNova);
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
		return objetos;
	}

	void PreencherTabela() {

		ItemTable it = (ItemTable) EntradaTable.getModel();

		it.RemoveAll();
		it.AddList(CarregarLista());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int qtd = Integer.parseInt(JOptionPane.showInputDialog("Quantidade: "));
		entradas.get(EntradaTable.getSelectedRow()).setQtd(qtd);
		dao.NovaEntrada(entradas.get(EntradaTable.getSelectedRow()));
	}

	@Override
	public void tableChanged(TableModelEvent e) {

	}

}