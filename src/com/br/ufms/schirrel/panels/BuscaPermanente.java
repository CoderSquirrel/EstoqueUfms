package com.br.ufms.schirrel.panels;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.DateFormatter;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.EntradaPermanente;
import com.br.ufms.schirrel.classes.Usuario;
import com.br.ufms.schirrel.tabelas.ItemPermanenteTableModel;

public class BuscaPermanente extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btBuscarPatrimonio, btBuscarNome;
	private List<EntradaPermanente> entradas;
	DAO dao;
	private JTable EntradaTable;
	@SuppressWarnings("unused")
	private Usuario USUARIO_LOGADO;
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	@SuppressWarnings("unused")
	private Month meses[] = { Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE,
			Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER };
	private JTextField tf_nome, tf_patrimonio;

	JPanel PanelBusca;

	public BuscaPermanente(DAO D, Usuario u) {
		USUARIO_LOGADO = u;
		dao = D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Busca Materiais Permanentes", TitledBorder.LEADING, TitledBorder.CENTER, null, null));

		JRadioButton rdbtnBuscaPorNome = new JRadioButton("Busca Por Nome");
		rdbtnBuscaPorNome.setBounds(18, 15, 149, 23);
		add(rdbtnBuscaPorNome);
		rdbtnBuscaPorNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelBuscaPorNome();
			}
		});
		JRadioButton rdbtnBuscaPorData = new JRadioButton("Busca Por Numero de Patrimonio");
		rdbtnBuscaPorData.setBounds(171, 15, 449, 23);
		add(rdbtnBuscaPorData);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBuscaPorNome);
		group.add(rdbtnBuscaPorData);
		rdbtnBuscaPorData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelBuscaPorPatrimonio();
			}
		});

		rdbtnBuscaPorNome.setSelected(true);

		PanelBusca = new JPanel();
		PanelBusca.setBounds(12, 40, 774, 75);
		add(PanelBusca);
	}


	void IniciarTable() {

		List<Object[]> a = CarregarLista();

		EntradaTable = new JTable(new ItemPermanenteTableModel(a));
		PreencherTabela();

		EntradaTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		EntradaTable.getColumnModel().getColumn(1).setPreferredWidth(30);
		EntradaTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		EntradaTable.getColumnModel().getColumn(3).setPreferredWidth(5);
		EntradaTable.getColumnModel().getColumn(4).setPreferredWidth(5);
		EntradaTable.getColumnModel().getColumn(5).setPreferredWidth(5);
		EntradaTable.getColumnModel().getColumn(6).setPreferredWidth(30);
		EntradaTable.getColumnModel().getColumn(7).setPreferredWidth(30);
		EntradaTable.getColumnModel().getColumn(8).setPreferredWidth(100);
		JScrollPane scrollPane = new JScrollPane(EntradaTable);
		scrollPane.setBounds(10, 120, 780, 200);

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

	}

	List<Object[]> CarregarLista() {
		// entradas = dao.ListarEntradasAtivas();
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

		if(e.getSource() == btBuscarNome){
			if (tf_nome.getText().toString().trim().isEmpty() || tf_nome.getText().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Campo em branco");
			} else {
				entradas = dao.ListarPermanentePorNome(tf_nome.getText().toString().trim());
				IniciarTable();
			}
		} else if(e.getSource() == btBuscarPatrimonio){
			if (tf_patrimonio.getText().toString().trim().isEmpty() || tf_patrimonio.getText().toString().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Campo em branco");
			} else {
				entradas = dao.ListarPermanentePorPatrimonio(tf_patrimonio.getText().toString().trim());
				IniciarTable();
			}
			
		}
		
		
		

	}

	void PanelBuscaPorPatrimonio() {
		if(EntradaTable!=null ){
			remove(EntradaTable);
			repaint();
			}
		
		DateFormatter formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);

		PanelBusca.removeAll();

		JPanel PanelPatrimonio = new JPanel();
		PanelPatrimonio.setBorder(
				new TitledBorder(null, "Buscar Por Numero de Patrimonio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelPatrimonio.setBounds(0, 0, 755, 65);
		PanelBusca.add(PanelPatrimonio);
		PanelPatrimonio.setLayout(null);


		tf_patrimonio = new JTextField();
		tf_patrimonio.setBounds(12, 22, 602, 31);
		PanelPatrimonio.add(tf_patrimonio);
		tf_patrimonio.setColumns(10);

		btBuscarPatrimonio = new EUButton("Buscar");
		btBuscarPatrimonio.setBounds(626, 25, 117, 25);
		btBuscarPatrimonio.addActionListener(this);
		PanelPatrimonio.add(btBuscarPatrimonio);
		PanelBusca.repaint();

	}

	void PanelBuscaPorNome() {
		if(EntradaTable!=null ){
			remove(EntradaTable);
			repaint();
			}
		
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

		btBuscarNome = new EUButton("Buscar");
		btBuscarNome.addActionListener(this);
		btBuscarNome.setBounds(626, 25, 117, 25);
		PanelNome.add(btBuscarNome);
		PanelBusca.repaint();
	}

}