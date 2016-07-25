package com.br.ufms.schirrel.panels;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Entrada;
import com.br.ufms.schirrel.classes.Usuario;
import com.br.ufms.schirrel.exportar.ExportarRelatorio;
import com.br.ufms.schirrel.tabelas.ItemTableModel;

public class RelatorioAtivos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Entrada> entradas;
	DAO dao;
	private JButton bt;
	private JTable EntradaTable;
	private Usuario USUARIO_LOGADO;
	ExportarRelatorio EXPORTAR;
	public RelatorioAtivos(DAO D, Usuario u) {
		EXPORTAR = new ExportarRelatorio();
		USUARIO_LOGADO = u;
		dao = D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Itens Ativos",
				TitledBorder.LEADING, TitledBorder.CENTER, null, new Color(24, 135, 180)));
 
		List<Object[]> a = CarregarLista();
		EntradaTable = new JTable(new ItemTableModel(a));
		PreencherTabela();
		setBackground(Color.WHITE);
		EntradaTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(1).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(7).setPreferredWidth(15);
		EntradaTable.getTableHeader().setBackground(new Color(24, 135, 180));
		EntradaTable.getTableHeader().setForeground(Color.WHITE);
		EntradaTable.setBackground(Color.WHITE);
		EntradaTable.setFillsViewportHeight(true);
		EntradaTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane scrollPane = new JScrollPane(EntradaTable);
		scrollPane.setBounds(10, 20, 780, 320);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane);

		EntradaTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				JTable table = (JTable) me.getSource();
				Point p = me.getPoint();
				int row = table.rowAtPoint(p);
				if (me.getClickCount() == 2) {
					int disp = ((int) table.getModel().getValueAt(row, 5))
							- ((int) table.getModel().getValueAt(row, 7));

					try {
						int qtd;
						String obj = JOptionPane.showInputDialog("Digite Quantidade da nova retirada: ");
						if (obj != null)
							qtd = Integer.parseInt(obj);
						else
							qtd = 0;

						if (qtd > disp) {
							JOptionPane.showMessageDialog(null, "Quantidade a ser retirada é maior que a de estoque.");
						} else {
							entradas.get(row).setUsuario(USUARIO_LOGADO);
							if (dao.UpdateRetirada(entradas.get(row), qtd)) {
								PreencherTabela();
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	
		
		bt = new EUButton	("Gerar Relatorio");
		bt.setBounds(10, 350, 200, 30);
		add(bt);
		
		
	bt.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
			int op = JOptionPane.showConfirmDialog(null, "Gerar Relatorio de  Materias de Consumo Ativos?");
			if (op == 0) {
				if (EXPORTAR.GerarRelatorioConsumo(entradas, "Relatorio de Materiais de Consumo Ativos", "RelatorioMCAtivos.xls")) {
					JOptionPane.showMessageDialog(null, "Relatorio Gerado Com Sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Problema na Geração do Relatorio");
				}
			} 
			
		}
	});
	
		
		
	}

	List<Object[]> CarregarLista() {
		entradas = dao.ListarEntradasAtivas();
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

		ItemTableModel it = (ItemTableModel) EntradaTable.getModel();

		it.RemoveAll();
		it.AddList(CarregarLista());
		repaint();
	}


}