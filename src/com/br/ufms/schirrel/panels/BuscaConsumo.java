package com.br.ufms.schirrel.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.UI.EUFormattedTextField;
import com.br.ufms.schirrel.UI.EUTextField;
import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Entrada;
import com.br.ufms.schirrel.classes.SaidaView;
import com.br.ufms.schirrel.classes.Usuario;
import com.br.ufms.schirrel.exportar.ExportarRelatorio;
import com.br.ufms.schirrel.tabelas.ItemTableModel;
import com.br.ufms.schirrel.tabelas.MyDefaultModel;
import com.br.ufms.schirrel.tabelas.SaidaTableModel;

public class BuscaConsumo extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField tf_DataInicial, tf_DataFinal;
	private JButton btBuscarData, btGerarD, btBuscarNome, btBuscarRetirada;
	private List<Entrada> entradas;
	private List<SaidaView> saidas;
	DAO dao;
	private JTable EntradaTable;
	@SuppressWarnings("unused")
	private Usuario USUARIO_LOGADO;
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private Month meses[] = { Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE,
			Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER };
	private JTextField tf_nome;

	JScrollPane scrollPane;
	JPanel PanelBusca;
	ExportarRelatorio EXPORTAR;

	public BuscaConsumo(DAO D, Usuario u) {
		EXPORTAR = new ExportarRelatorio();
		USUARIO_LOGADO = u;
		dao = D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Busca Materiais de Consumo", TitledBorder.LEADING, TitledBorder.CENTER, null,
				new Color(24, 135, 180)));
		setBackground(Color.WHITE);
		JRadioButton rdbtnBuscaPorNome = new JRadioButton("Busca Por Nome");
		rdbtnBuscaPorNome.setBounds(18, 15, 149, 23);
		rdbtnBuscaPorNome.setBackground(Color.WHITE);
		rdbtnBuscaPorNome.setForeground(new Color(24, 135, 180));
		add(rdbtnBuscaPorNome);
		rdbtnBuscaPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelBuscaPorNome();
			}
		});
		JRadioButton rdbtnBuscaPorData = new JRadioButton("Busca Por Data de Entrada");
		rdbtnBuscaPorData.setBounds(171, 15, 229, 23);
		rdbtnBuscaPorData.setBackground(Color.WHITE);
		add(rdbtnBuscaPorData);
		rdbtnBuscaPorData.setForeground(new Color(24, 135, 180));
		rdbtnBuscaPorData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelBuscaPorData();
			}
		});

		JRadioButton rdbtnBuscaPorRetirada = new JRadioButton("Busca Por Data de Retirada");
		rdbtnBuscaPorRetirada.setBounds(400, 15, 249, 23);
		add(rdbtnBuscaPorRetirada);
		rdbtnBuscaPorRetirada.setBackground(Color.WHITE);
		rdbtnBuscaPorRetirada.setForeground(new Color(24, 135, 180));
		rdbtnBuscaPorRetirada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelBuscaPorDataRetirada();
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBuscaPorNome);
		group.add(rdbtnBuscaPorData);
		group.add(rdbtnBuscaPorRetirada);

		rdbtnBuscaPorNome.setSelected(true);

		PanelBusca = new JPanel();
		PanelBusca.setBounds(12, 40, 774, 80);
		PanelBusca.setBackground(Color.WHITE);
		add(PanelBusca);
	}

	void IniciarTable() {

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

		scrollPane = new JScrollPane(EntradaTable);
		scrollPane.setBounds(10, 120, 780, 300);

		add(scrollPane);

	}

	void IniciarTableSaida() {

		List<Object[]> a = CarregarListaSaida();

		EntradaTable = new JTable(new SaidaTableModel(a));
		PreencherTabelaSaida();

		EntradaTable.getColumnModel().getColumn(0).setPreferredWidth(120);
		EntradaTable.getColumnModel().getColumn(1).setPreferredWidth(120);
		EntradaTable.getColumnModel().getColumn(2).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(3).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		EntradaTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		EntradaTable.getColumnModel().getColumn(6).setPreferredWidth(10);

		JScrollPane scrollPane = new JScrollPane(EntradaTable);
		scrollPane.setBounds(10, 120, 780, 270);

		add(scrollPane);

	}

	List<Object[]> CarregarLista() {
		// entradas = dao.ListarEntradasAtivas();
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

	List<Object[]> CarregarListaSaida() {
		Object[] objs;
		List<Object[]> objetos = new ArrayList<Object[]>();

		for (int i = 0; i < saidas.size(); i++) {
			objs = new Object[7];

			objs[0] = saidas.get(i).getItem();
			objs[1] = saidas.get(i).getFabricante();
			objs[2] = saidas.get(i).getEntrada();
			objs[3] = saidas.get(i).getValidade();
			objs[4] = saidas.get(i).getUsuario();
			objs[5] = saidas.get(i).getRetirada();
			objs[6] = saidas.get(i).getQtd_retirada();
			objetos.add(objs);
		}
		return objetos;

	}

	void PreencherTabela() {

		for (Component c : getComponents()) {
			if (c == scrollPane) {
				remove(scrollPane);
			}
		}

		ItemTableModel it = (ItemTableModel) EntradaTable.getModel();
		EntradaTable.getTableHeader().setReorderingAllowed(false);
		it.RemoveAll();
		it.AddList(CarregarLista());
		repaint();
		PanelBusca.repaint();

	}

	void PreencherTabelaSaida() {
		for (Component c : getComponents()) {
			if (c == scrollPane) {
				remove(scrollPane);
			}
		}
		SaidaTableModel it = (SaidaTableModel) EntradaTable.getModel();

		it.RemoveAll();
		it.AddList(CarregarListaSaida());
		repaint();
		PanelBusca.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btBuscarNome) {
			if (tf_nome.getText().toString().trim().isEmpty() || tf_nome.getText().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Campo em branco");
			} else {
				entradas = dao.ListarPorNome(tf_nome.getText().toString().trim());
				IniciarTable();
			}
		} else if (e.getSource() == btBuscarData) {
			LocalDate date = LocalDate.of(Integer.parseInt(tf_DataInicial.getText().split("/")[2]),
					meses[(Integer.parseInt(tf_DataInicial.getText().split("/")[1]) - 1)],
					Integer.parseInt(tf_DataInicial.getText().split("/")[0]));
			java.sql.Date i = java.sql.Date.valueOf(date);
			LocalDate dateF = LocalDate.of(Integer.parseInt(tf_DataFinal.getText().split("/")[2]),
					meses[(Integer.parseInt(tf_DataFinal.getText().split("/")[1]) - 1)],
					Integer.parseInt(tf_DataFinal.getText().split("/")[0]));
			java.sql.Date f = java.sql.Date.valueOf(dateF);

			if (f.before(i)) {
				JOptionPane.showMessageDialog(this, "Data final é anterior a data inicial");
			} else {
				entradas = dao.ListarPorData(i, f);
				// EXPORTAR.GerarRelatorioDatasDeEntrada(entradas,
				// tf_DataInicial.getText(), tf_DataFinal.getText());
				IniciarTable();
			}
		} else if (e.getSource() == btBuscarRetirada) {

			LocalDate date = LocalDate.of(Integer.parseInt(tf_DataInicial.getText().split("/")[2]),
					meses[(Integer.parseInt(tf_DataInicial.getText().split("/")[1]) - 1)],
					Integer.parseInt(tf_DataInicial.getText().split("/")[0]));
			java.sql.Date i = java.sql.Date.valueOf(date);
			LocalDate dateF = LocalDate.of(Integer.parseInt(tf_DataFinal.getText().split("/")[2]),
					meses[(Integer.parseInt(tf_DataFinal.getText().split("/")[1]) - 1)],
					Integer.parseInt(tf_DataFinal.getText().split("/")[0]));
			java.sql.Date f = java.sql.Date.valueOf(dateF);

			if (f.before(i)) {
				JOptionPane.showMessageDialog(this, "Data final é anterior a data inicial");
			} else {
				saidas = dao.ListarRetiradasPorData(i, f);
				IniciarTableSaida();
				// try {
				// //EXPORTAR.GerarRelatorioDatasDeSaida(saidas,
				// tf_DataInicial.getText(), tf_DataFinal.getText());
				// } catch (DocumentException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

			}
		}

	}

	void PanelBuscaPorData() {

		DateFormatter formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		RemoverItensTable();
		PanelBusca.removeAll();
		PanelBusca.repaint();
		JPanel PanelData = new JPanel();
		PanelData.setBorder(new TitledBorder(null, "Buscar Por Data de Entrada", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(24, 135, 180)));
		PanelData.setBounds(0, 0, 755, 75);
		PanelBusca.add(PanelData);
		PanelData.setLayout(null);
		PanelData.setBackground(Color.WHITE);
		JPanel PanelDataInicial = new JPanel();
		PanelDataInicial.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Data Inicial",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		PanelDataInicial.setBounds(12, 15, 295, 52);
		PanelDataInicial.setBackground(Color.WHITE);
		PanelData.add(PanelDataInicial);
		PanelDataInicial.setLayout(null);

		tf_DataInicial = new EUFormattedTextField(formatter);
		tf_DataInicial.setBounds(12, 18, 200, 28);
		PanelDataInicial.add(tf_DataInicial);
		tf_DataInicial.setValue(new Date());
		tf_DataInicial.setColumns(10);

		JPanel PanelDataFinal = new JPanel();
		PanelDataFinal.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Data Final",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		PanelDataFinal.setBounds(315, 15, 295, 52);
		PanelDataFinal.setBackground(Color.WHITE);
		PanelData.add(PanelDataFinal);
		PanelData.setBackground(Color.WHITE);
		PanelDataFinal.setLayout(null);

		tf_DataFinal = new EUFormattedTextField(formatter);
		tf_DataFinal.setBounds(12, 18, 200, 28);
		PanelDataFinal.add(tf_DataFinal);
		tf_DataFinal.setValue(new Date());
		tf_DataFinal.setColumns(10);

		btBuscarData = new EUButton("Buscar");
		btBuscarData.setBounds(626, 30, 117, 25);
		btBuscarData.addActionListener(this);
		PanelData.add(btBuscarData);
		PanelBusca.repaint();
		repaint();

	}

	void PanelBuscaPorNome() {
		RemoverItensTable();
		PanelBusca.removeAll();
		PanelBusca.repaint();
		JPanel PanelNome = new JPanel();
		PanelNome.setBorder(
				new TitledBorder(null, "Busca Por Nome", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		PanelNome.setBounds(0, 0, 755, 65);
		PanelNome.setBackground(Color.WHITE);
		PanelBusca.add(PanelNome);
		PanelNome.setLayout(null);

		tf_nome = new EUTextField();
		tf_nome.setBounds(12, 22, 602, 31);
		PanelNome.add(tf_nome);
		tf_nome.setColumns(10);

		btBuscarNome = new EUButton("Buscar");
		btBuscarNome.addActionListener(this);
		btBuscarNome.setBounds(626, 25, 117, 25);
		PanelNome.add(btBuscarNome);
		PanelBusca.repaint();
		repaint();
	}

	void PanelBuscaPorDataRetirada() {
		RemoverItensTable();
		DateFormatter formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);

		PanelBusca.removeAll();
		PanelBusca.repaint();
		JPanel PanelRetirada = new JPanel();
		PanelRetirada.setBorder(new TitledBorder(null, "Buscar Por Data de Retirada", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(24, 135, 180)));
		PanelRetirada.setBounds(0, 0, 755, 75);
		PanelRetirada.setBackground(Color.WHITE);
		PanelBusca.add(PanelRetirada);
		PanelRetirada.setLayout(null);

		JPanel PanelDataInicial = new JPanel();
		PanelDataInicial.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Data Inicial",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		PanelDataInicial.setBounds(12, 15, 295, 52);
		PanelRetirada.add(PanelDataInicial);
		PanelDataInicial.setBackground(Color.WHITE);
		PanelDataInicial.setLayout(null);

		tf_DataInicial = new EUFormattedTextField(formatter);
		tf_DataInicial.setBounds(12, 18, 200, 28);
		PanelDataInicial.add(tf_DataInicial);
		tf_DataInicial.setValue(new Date());
		tf_DataInicial.setColumns(10);

		JPanel PanelDataFinal = new JPanel();
		PanelDataFinal.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Data Final",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		PanelDataFinal.setBounds(315, 15, 295, 52);
		PanelRetirada.add(PanelDataFinal);
		PanelDataFinal.setBackground(Color.WHITE);
		PanelDataFinal.setLayout(null);

		tf_DataFinal = new EUFormattedTextField(formatter);
		tf_DataFinal.setBounds(12, 18, 200, 28);
		PanelDataFinal.add(tf_DataFinal);
		tf_DataFinal.setValue(new Date());
		tf_DataFinal.setColumns(10);

		btBuscarRetirada = new EUButton("Buscar");
		btBuscarRetirada.setBounds(626, 30, 117, 25);
		btBuscarRetirada.addActionListener(this);
		PanelRetirada.add(btBuscarRetirada);
		PanelBusca.repaint();
		repaint();

	}

	void RemoverItensTable() {
		if (EntradaTable != null) {
			MyDefaultModel model = (MyDefaultModel) EntradaTable.getModel();
			model.RemoveAll();
			scrollPane.

					repaint();
			PanelBusca.repaint();
		}

	}
}