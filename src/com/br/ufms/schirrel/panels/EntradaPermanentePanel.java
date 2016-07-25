package com.br.ufms.schirrel.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DateFormatter;
import javax.swing.text.DocumentFilter;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.EntradaPermanente;
import com.br.ufms.schirrel.classes.EntradaPermanente.Estado;
import com.br.ufms.schirrel.classes.Item;
import com.br.ufms.schirrel.classes.Usuario;

public class EntradaPermanentePanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfQtd, tfPatrimonio;
	private JFormattedTextField tfDataEntrada;
	private JButton btCadastrar;
	private JLabel lblStatus;
	private JComboBox<Item> cbItens;
	private JComboBox<Integer> cbDepositos, cbLaboratorios;
	private JComboBox<Estado> cbEstados;
	private Usuario USUARIO_LOGADO;
	private JTextArea taObs, taDescricao;
	DAO dao;
	private DocumentFilter filter = new IntDocumentFilter();
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Format shortDate = DateFormat.getDateInstance(DateFormat.SHORT);

	public EntradaPermanentePanel(DAO D, Usuario u) {
		dao = D;
		USUARIO_LOGADO = u;
		setBounds(0, 60, 780, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Entrada de Item", TitledBorder.LEADING, TitledBorder.CENTER, null, null));
		setBackground(Color.WHITE);
		JPanel panelEntrada = new JPanel();
		panelEntrada.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Entrada",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEntrada.setBounds(10, 155, 153, 55);
		panelEntrada.setLayout(null);
		add(panelEntrada);
		setBackground(Color.WHITE);
		JPanel panelDeposito = new JPanel();
		panelDeposito.setBorder(new TitledBorder(null, "Deposito", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDeposito.setBounds(163, 155, 100, 55);
		add(panelDeposito);
		JPanel panelLaboratorio = new JPanel();
		panelLaboratorio
				.setBorder(new TitledBorder(null, "Laboratorio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLaboratorio.setBounds(263, 155, 100, 55);
		add(panelLaboratorio);

		JPanel panelQuantidade = new JPanel();
		panelQuantidade.setLayout(null);
		panelQuantidade.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Quantidade",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelQuantidade.setBounds(363, 155, 153, 55);
		add(panelQuantidade);

		JPanel panelEstado= new JPanel();
		panelEstado.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estado",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEstado.setBounds(518, 155, 120, 55);
		add(panelEstado);
		
		cbItens = new JComboBox<>(dao.ListarItens());
		cbItens.setBounds(50, 22, 350, 26);
		cbItens.setFont(new Font("Arial", Font.BOLD, 14));
		add(cbItens);
		
		cbEstados = new JComboBox<>(Estado.values());
		panelEstado.add(cbEstados);

		Integer[] dep = { 1, 2, 3, 4, 5 };
		cbDepositos = new JComboBox<>(dep);
		// cbDepositos = new JComboBox<>();
		cbDepositos.setBounds(0, 0, 100, 40);
		panelDeposito.add(cbDepositos);
		Integer[] lab = { 1, 2, 3, 4, 5, 6, 7, 8 };
		cbLaboratorios = new JComboBox<>(lab);
		// cbLaboratorios = new JComboBox<>();
		cbLaboratorios.setBounds(0, 0, 100, 40);
		panelLaboratorio.add(cbLaboratorios);

		JLabel lblPatrimonio = new JLabel("Nº de Patrimonio: ");
		lblPatrimonio.setBounds(425, 22, 190, 20);
		lblPatrimonio.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblPatrimonio);

		tfPatrimonio = new JTextField();
		tfPatrimonio.setBounds(570, 22, 200, 26);
		tfPatrimonio.setColumns(15);
		add(tfPatrimonio);

		DateFormatter formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);

		tfDataEntrada = new JFormattedTextField(formatter);
		tfDataEntrada.setBounds(15, 17, 110, 30);
		tfDataEntrada.setFont(new Font("Arial", Font.BOLD, 14));
		panelEntrada.add(tfDataEntrada);
		tfDataEntrada.setValue(new Date());
		tfDataEntrada.setColumns(10);

		tfQtd = new JTextField();
		AbstractDocument document = (AbstractDocument) tfQtd.getDocument();
		document.setDocumentFilter(filter);
		tfQtd.setBounds(33, 18, 90, 30);
		panelQuantidade.add(tfQtd);

		tfQtd.setColumns(10);

		JLabel lblItem = new JLabel("Item: ");
		lblItem.setBounds(10, 22, 50, 20);
		lblItem.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblItem);

		taObs = new JTextArea();
		taObs.setBounds(7, 15, 745, 77);
		taObs.setLineWrap(true);
		taObs.setWrapStyleWord(true);
			taObs.setOpaque(true);
		JPanel panelObs = new JPanel();
		panelObs.setLayout(null);
		panelObs.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Obs", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
		panelObs.setBounds(10, 225, 760, 100);
		add(panelObs);
		
		panelObs.add(taObs);
		//add(taObs);

		
		taDescricao = new JTextArea();
		taDescricao.setBounds(7, 15, 745, 67);
		taDescricao.setLineWrap(true);
		taDescricao.setWrapStyleWord(true);
		//taDescricao
		
		
		JPanel panelDescricao = new JPanel();
		panelDescricao.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Descrição", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelDescricao.setLayout(null);
		panelDescricao.setBounds(10, 60, 760, 90);
		add(panelDescricao);
		
		panelDescricao.add(taDescricao);
		
		
		btCadastrar = new EUButton("Salvar");
		btCadastrar.setBounds(10, 340, 100, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

		lblStatus = new JLabel("");
		lblStatus.setBounds(10, 363, 788, 26);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (EmBranco(tfDataEntrada) || EmBranco(tfQtd)) {
			lblStatus.setText("Existe campo em branco.");
		} else {

			Item i = dao.GetItemPorNome(cbItens.getSelectedItem().toString());
			EntradaPermanente perm;
			perm = new EntradaPermanente(i, USUARIO_LOGADO, new Date(tfDataEntrada.getText().toString()),
					Integer.parseInt(tfQtd.getText().toString().trim()),
					Integer.parseInt(cbDepositos.getSelectedItem().toString().trim()),
					Integer.parseInt(cbLaboratorios.getSelectedItem().toString().trim()), taObs.getText().trim(),
					tfPatrimonio.getText().trim(),taDescricao.getText().trim(), Estado.bom);
			try {
				perm = dao.CadastrarEntradaPermanente(perm);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				lblStatus.setText("Problema com banco de dados");
			}
			if (perm.getId() > 0) {
				lblStatus.setText("Cadastrado");
				tfDataEntrada.setValue(new Date());
				tfQtd.setText("");
				cbItens.setSelectedIndex(0);
				cbDepositos.setSelectedIndex(0);
				cbLaboratorios.setSelectedIndex(0);
				cbEstados.setSelectedIndex(0);
				tfPatrimonio.setText("");
				taObs.setText("");
				taDescricao.setText("");

			} else {
				lblStatus.setText("Problema ao efetuar cadastro");
			}
		}

	}

	boolean EmBranco(JTextField campo) {
		return campo.getText().toString().trim() == null || campo.getText().toString().trim().isEmpty()
				|| campo.getText().toString().trim().equals("");

	}
}