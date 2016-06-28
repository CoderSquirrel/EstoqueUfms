package com.br.ufms.schirrel.panels;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Entrada;
import com.br.ufms.schirrel.classes.ItemTable;
import com.br.ufms.schirrel.classes.Usuario;

public class BuscaConsumo extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField tf_DataInicial, tf_DataFinal;
	private JButton btBuscar, btBuscarData, btBuscarNome;
	private List<Entrada> entradas;
	DAO dao;
	private JTable EntradaTable;
	private Usuario USUARIO_LOGADO;
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	private Month meses[] = { Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE,
			Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER };
	private JTextField tf_nome;

	JPanel PanelBusca;

	public BuscaConsumo(DAO D, Usuario u) {
		USUARIO_LOGADO = u;
		dao = D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Itens", TitledBorder.LEADING, TitledBorder.CENTER, null, null));

		JRadioButton rdbtnBuscaPorNome = new JRadioButton("Busca Por Nome");
		rdbtnBuscaPorNome.setBounds(18, 15, 149, 23);
		add(rdbtnBuscaPorNome);
		rdbtnBuscaPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelBuscaPorNome();
			}
		});
		JRadioButton rdbtnBuscaPorData = new JRadioButton("Busca Por Data");
		rdbtnBuscaPorData.setBounds(171, 15, 149, 23);
		add(rdbtnBuscaPorData);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBuscaPorNome);
		group.add(rdbtnBuscaPorData);
		rdbtnBuscaPorData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelBuscaPorData();
			}
		});

		rdbtnBuscaPorNome.setSelected(true);

		PanelBusca = new JPanel();
		PanelBusca.setBounds(12, 40, 774, 124);
		add(PanelBusca);
	}

	void Busc() {
		DateFormatter formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);

		JLabel lblInicial = new JLabel("Data Inicial:");
		lblInicial.setBounds(10, 25, 100, 20);
		lblInicial.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblInicial);

		JLabel lblFinal = new JLabel("Data Final:");
		lblFinal.setBounds(250, 25, 100, 20);
		lblFinal.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblFinal);

		btBuscar = new JButton("Buscar");
		btBuscar.setBounds(500, 20, 100, 30);
		btBuscar.addActionListener(this);
		add(btBuscar);

	}

	void IniciarTable() {

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

		JScrollPane scrollPane = new JScrollPane(EntradaTable);
		scrollPane.setBounds(10, 120, 780, 200);

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

	void PreencherTabela() {

		ItemTable it = (ItemTable) EntradaTable.getModel();

		it.RemoveAll();
		it.AddList(CarregarLista());

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btBuscarNome){
			if (tf_nome.getText().toString().trim().isEmpty() || tf_nome.getText().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Campo em branco");
			} else {
				entradas = dao.ListarPorNome(tf_nome.getText().toString().trim());
				IniciarTable();
			}
		} else if(e.getSource() == btBuscarData){
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
				IniciarTable();
			}
		}
		
		
		

	}

	void PanelBuscaPorData() {

		DateFormatter formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);

		PanelBusca.removeAll();

		JPanel PanelData = new JPanel();
		PanelData.setBorder(
				new TitledBorder(null, "Buscar Por Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelData.setBounds(0, 0, 755, 75);
		PanelBusca.add(PanelData);
		PanelData.setLayout(null);

		JPanel PanelDataInicial = new JPanel();
		PanelDataInicial.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Data Inicial",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelDataInicial.setBounds(12, 15, 295, 52);
		PanelData.add(PanelDataInicial);
		PanelDataInicial.setLayout(null);

		tf_DataInicial = new JFormattedTextField(formatter);
		tf_DataInicial.setBounds(12, 18, 200, 28);
		PanelDataInicial.add(tf_DataInicial);
		tf_DataInicial.setValue(new Date());
		tf_DataInicial.setColumns(10);

		JPanel PanelDataFinal = new JPanel();
		PanelDataFinal.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Data Final",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelDataFinal.setBounds(315, 15, 295, 52);
		PanelData.add(PanelDataFinal);
		PanelDataFinal.setLayout(null);

		tf_DataFinal = new JFormattedTextField(formatter);
		tf_DataFinal.setBounds(12, 18, 200, 28);
		PanelDataFinal.add(tf_DataFinal);
		tf_DataFinal.setValue(new Date());
		tf_DataFinal.setColumns(10);

		btBuscarData = new JButton("Buscar");
		btBuscarData.setBounds(616, 39, 117, 25);
		btBuscarData.addActionListener(this);
		PanelData.add(btBuscarData);
		PanelBusca.repaint();

	}

	void PanelBuscaPorNome() {

		PanelBusca.removeAll();
		JPanel PanelNome = new JPanel();
		PanelNome.setBorder(
				new TitledBorder(null, "Busca Por Nome", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelNome.setBounds(0, 0, 755, 65);
		PanelBusca.add(PanelNome);
		PanelNome.setLayout(null);

		tf_nome = new JTextField();
		tf_nome.setBounds(12, 22, 602, 31);
		PanelNome.add(tf_nome);
		tf_nome.setColumns(10);

		btBuscarNome = new JButton("Buscar");
		btBuscarNome.addActionListener(this);
		btBuscarNome.setBounds(626, 25, 117, 25);
		PanelNome.add(btBuscarNome);
		PanelBusca.repaint();
	}

}