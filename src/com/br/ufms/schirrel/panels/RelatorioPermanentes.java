package com.br.ufms.schirrel.panels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.EntradaPermanente;
import com.br.ufms.schirrel.classes.Usuario;
import com.br.ufms.schirrel.exportar.ExportarRelatorio;
import com.br.ufms.schirrel.tabelas.ItemPermanenteTableModel;

public class RelatorioPermanentes extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<EntradaPermanente> entradas;
	DAO dao;
	private JButton btGerar;
	private JTable EntradaTable;
	@SuppressWarnings("unused")
	private Usuario USUARIO_LOGADO;

	public RelatorioPermanentes(DAO D, Usuario u) {
		USUARIO_LOGADO = u;
		dao = D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Relatorio de Material Permanente", TitledBorder.LEADING, TitledBorder.CENTER, null, null));


		List<Object[]> a = CarregarLista();
		EntradaTable = new JTable(new ItemPermanenteTableModel(a));
		PreencherTabela();

		EntradaTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(1).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(7).setPreferredWidth(15);

		JScrollPane scrollPane = new JScrollPane(EntradaTable);
		scrollPane.setBounds(10, 20, 780, 320);

		add(scrollPane);
		
		EntradaTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2) {
		        	new MostrarInfoPermanente(entradas.get(row));
		        	
		        }
		    }
		});

		
		btGerar = new JButton("Gerar Relatorio");
		btGerar.setBounds(10, 350, 200, 30);
		add(btGerar);
		
		
		btGerar.addActionListener(this);
	}

	List<Object[]> CarregarLista() {
		entradas = dao.ListarEntradasPermanentes();
		Object[] objs;
		List<Object[]> objetos = new ArrayList<Object[]>();

		for (int i = 0; i < entradas.size(); i++) {
			objs = new Object[9];
			objs[0] = entradas.get(i).getItem().getItem();
			objs[1] = entradas.get(i).getDescricao();
			objs[2] = entradas.get(i).getDataEntrada();
			objs[3] = entradas.get(i).getQtd();
			objs[4] = entradas.get(i).getDeposito();
			objs[5] = entradas.get(i).getLaboratorio();
			objs[6] = entradas.get(i).getPatrimonio();
			objs[7] = entradas.get(i).getEstadoString();
			objs[8] = entradas.get(i).getObs();

			objetos.add(objs);
		}
		return objetos;
	}

	void PreencherTabela() {

		ItemPermanenteTableModel it = (ItemPermanenteTableModel) EntradaTable.getModel();

		it.RemoveAll();
		it.AddList(CarregarLista());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btGerar){
			int op = JOptionPane.showConfirmDialog(null, "Gerar Relatorio de  Materias de Consumo Ativos?");
			if (op == 0) {
				if (new ExportarRelatorio().GerarRelatorioPermanente(entradas, "Relatorio de Materiais de Consumo Ativos", "RelatorioMPermanente.xls")) {
					JOptionPane.showMessageDialog(null, "Relatorio Gerado Com Sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Problema na Geração do Relatorio");
				}
			} 
		}
		
	}

}