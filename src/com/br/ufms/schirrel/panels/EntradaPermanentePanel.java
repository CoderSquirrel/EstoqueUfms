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
import com.br.ufms.schirrel.UI.EUComboBox;
import com.br.ufms.schirrel.UI.EUFormattedTextField;
import com.br.ufms.schirrel.UI.EUTextArea;
import com.br.ufms.schirrel.UI.EUTextField;
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
	DateFormatter formatter;

	public EntradaPermanentePanel(DAO D, Usuario u) {
		dao = D;
		USUARIO_LOGADO = u;
		setBounds(0, 55, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null,
				"Entrada de Item Permanente", 
				TitledBorder.LEADING, TitledBorder.CENTER, null, new Color(24, 135, 180)));
		setBackground(Color.WHITE);
		formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);


		btCadastrar = new EUButton("Salvar");
		btCadastrar.setBounds(680, 350, 100, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

		lblStatus = new JLabel("");
		lblStatus.setForeground(new Color(24, 135, 180));
		lblStatus.setBounds(10, 363, 788, 26);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);
		
		Item();
		Descricao();
		Estado();
		Obs();
		DepositoLaboratorio();
		Entrada();
		Quantidade();
		Patrimonio();
	}

	void Item() {

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Item",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(10, 17, 560, 60);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);

		cbItens = new EUComboBox<>(dao.ListarItens());
		cbItens.setBounds(15, 20, 530, 25);
		cbItens.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(cbItens);
	}

	void Patrimonio() {


		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Nº de Patrimonio",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(570, 17, 220, 60);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);

		
		tfPatrimonio = new EUTextField();
		tfPatrimonio.setBounds(10, 20, 200, 30);
		tfPatrimonio.setColumns(15);
		panel.add(tfPatrimonio);
	}

	void Descricao() {

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Descrição",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(10, 80, 780, 75);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		

		taDescricao = new EUTextArea();
		taDescricao.setBounds(7, 15, 765, 50);
		taDescricao.setLineWrap(true);
		taDescricao.setWrapStyleWord(true);
		panel.add(taDescricao);

	}

	void Entrada() {
		JPanel panelEntrada = new JPanel();
		panelEntrada.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Entrada",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panelEntrada.setBounds(280, 280, 130, 60);
		panelEntrada.setBackground(Color.WHITE);
		panelEntrada.setLayout(null);
		panelEntrada.setBackground(Color.WHITE);
		add(panelEntrada);

		tfDataEntrada = new EUFormattedTextField(formatter);
		tfDataEntrada.setBounds(10, 20, 110, 30);
		tfDataEntrada.setFont(new Font("Arial", Font.BOLD, 14));
		panelEntrada.add(tfDataEntrada);
		tfDataEntrada.setValue(new Date());
		tfDataEntrada.setColumns(10);
	}
	void DepositoLaboratorio() {
		JPanel panelDeposito = new JPanel();
		panelDeposito.setBorder(new TitledBorder(null, "Deposito", TitledBorder.LEADING, TitledBorder.TOP, null, 
				new Color(24, 135, 180)));
		panelDeposito.setBounds(10, 220, 120, 60);
		panelDeposito.setBackground(Color.WHITE);
		panelDeposito.setLayout(null);
		add(panelDeposito);
		
		JPanel panelLaboratorio = new JPanel();
		panelLaboratorio
				.setBorder(new TitledBorder(null, "Laboratorio", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panelLaboratorio.setBounds(10, 280, 120, 60);
		panelLaboratorio.setBackground(Color.WHITE);
		panelLaboratorio.setLayout(null);
		add(panelLaboratorio);
		Integer[] dep = { 1, 2, 3, 4, 5 };
		cbDepositos = new EUComboBox<>(dep);
		cbDepositos.setBounds(10,20, 100, 30);
		
		
		panelDeposito.add(cbDepositos);
		Integer[] lab = { 1, 2, 3, 4, 5, 6, 7, 8 };
		cbLaboratorios = new EUComboBox<>(lab);
		// cbLaboratorios = new JComboBox<>();
		cbLaboratorios.setBounds(10,20, 100, 30);
		panelLaboratorio.add(cbLaboratorios);

	}

	void Quantidade() {
		JPanel panelQuantidade = new JPanel();
		panelQuantidade.setLayout(null);
		panelQuantidade.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Quantidade",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panelQuantidade.setBounds(130, 280, 150, 60);
		panelQuantidade.setBackground(Color.WHITE);
		panelQuantidade.setLayout(null);
		add(panelQuantidade);

		tfQtd = new EUTextField();
		AbstractDocument document = (AbstractDocument) tfQtd.getDocument();
		document.setDocumentFilter(filter);
		tfQtd.setBounds(10,20, 130, 30);
		panelQuantidade.add(tfQtd);

		tfQtd.setColumns(10);
	}

	void Estado() {

		JPanel panelEstado = new JPanel();
		panelEstado.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estado", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(24, 135, 180)));
		panelEstado.setBounds(10, 160, 120, 60);
		panelEstado.setBackground(Color.WHITE);
		panelEstado.setLayout(null);
		add(panelEstado);

		cbEstados = new EUComboBox<>(Estado.values());
		cbEstados.setBounds(10,20, 100, 30);
		panelEstado.add(cbEstados);
	}

	void Obs() {
		taObs = new EUTextArea();
		taObs.setBounds(7, 15, 645, 95);
		taObs.setLineWrap(true);
		taObs.setWrapStyleWord(true);
		taObs.setOpaque(true);
		JPanel panelObs = new JPanel();
		panelObs.setLayout(null);
		panelObs.setBackground(Color.WHITE);
		panelObs.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Obs", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(24, 135, 180)));
		panelObs.setBounds(130, 160, 660, 120);
		add(panelObs);

		panelObs.add(taObs);
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
					tfPatrimonio.getText().trim(), taDescricao.getText().trim(), Estado.bom);
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