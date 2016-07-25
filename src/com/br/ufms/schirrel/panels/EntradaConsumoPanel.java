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
import com.br.ufms.schirrel.UI.EUTextField;
import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Entrada;
import com.br.ufms.schirrel.classes.Fabricante;
import com.br.ufms.schirrel.classes.Item;
import com.br.ufms.schirrel.classes.Unidade;
import com.br.ufms.schirrel.classes.Usuario;

public class EntradaConsumoPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfQtd;
	private JFormattedTextField tfDataValidade, tfDataFabricacao, tfDataEntrada;
	private JButton btCadastrar;
	private JLabel lblStatus;
	private JComboBox<Fabricante> cbFabricantes;
	private JComboBox<Item> cbItens;
	private JComboBox<Unidade> cbUnidades;
	private Usuario USUARIO_LOGADO;
	DAO dao;
	private DocumentFilter filter = new IntDocumentFilter();
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Format shortDate = DateFormat.getDateInstance(DateFormat.SHORT);
	DateFormatter formatter;

	public EntradaConsumoPanel(DAO D, Usuario u) {
		dao = D;
		USUARIO_LOGADO = u;
		setBounds(0, 55, 798, 400);
		setLayout(null);
		
		setBorder(new TitledBorder(null, "Entrada de Item", TitledBorder.LEADING, TitledBorder.CENTER, null,
				new Color(24, 135, 180)));
		setBackground(Color.WHITE);

	

		formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);

		lblStatus = new JLabel("");
		lblStatus.setBounds(10, 350, 788, 26);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setForeground(new Color(24, 135, 180));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);
		
		Item();
		Unidade();
		Fabricante();
		Quantidade();
		Entrada();
		Fabricacao();
		Validade();
		Botoes();
	}

	void Item() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Item",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(10, 22, 780, 60);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);

		cbItens = new EUComboBox<>(dao.ListarItens());
		cbItens.setBounds(15, 20, 750, 25);
		cbItens.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(cbItens);
	}

	void Unidade() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Unidade",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(10, 85, 780, 60);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		cbUnidades = new EUComboBox<>(dao.ListarUnidades());
		cbUnidades.setBounds(15, 20, 750, 25);
		cbUnidades.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(cbUnidades);

	}

	void Fabricante() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Fabricante",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(10, 150, 780, 60);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		
		cbFabricantes = new EUComboBox<>(dao.ListarFabricantes());
		cbFabricantes.setBounds(15, 20, 750, 25);
		cbFabricantes.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(cbFabricantes);

	}

	void Quantidade() {

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Quantidade",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(10, 230, 130, 50);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		
		tfQtd = new EUTextField();
		tfQtd.setBounds(10,17, 110, 25);
		tfQtd.setColumns(15);
		AbstractDocument document = (AbstractDocument) tfQtd.getDocument();
		document.setDocumentFilter(filter);
		panel.add(tfQtd);
	}

	void Validade() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Validade",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(490, 230, 130, 50);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		
		tfDataValidade = new EUFormattedTextField(formatter);
		tfDataValidade.setBounds(10, 17, 110, 25);

		tfDataValidade.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(tfDataValidade);
		tfDataValidade.setValue(new Date());
		tfDataValidade.setColumns(10);

	}

	void Entrada() {

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Entrada",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(170, 230, 130, 50);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);

		tfDataEntrada = new EUFormattedTextField(formatter);
		tfDataEntrada.setBounds(10, 17, 110, 25);
		tfDataEntrada.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(tfDataEntrada);
		tfDataEntrada.setValue(new Date());
		tfDataEntrada.setColumns(10);
	}

	void Fabricacao() {

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Fabricação",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(330, 230, 130, 50);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		tfDataFabricacao = new EUFormattedTextField(formatter);
		tfDataFabricacao.setBounds(10, 17, 110, 25);
		tfDataFabricacao.setFont(new Font("Arial", Font.BOLD, 14));
		panel.add(tfDataFabricacao);
		tfDataFabricacao.setValue(new Date());
		tfDataFabricacao.setColumns(10);

	}

	void Botoes() {
		btCadastrar = new EUButton("Salvar");
		btCadastrar.setBounds(680, 350, 100, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (EmBranco(tfDataValidade) || EmBranco(tfQtd)) {
			lblStatus.setText("Existe campo em branco.");
		} else {

			Fabricante f = dao.GetFabricantePorNome(cbFabricantes.getSelectedItem().toString());
			Item i = dao.GetItemPorNome(cbItens.getSelectedItem().toString());
			Unidade u = dao.GetUnidadePorNome(cbUnidades.getSelectedItem().toString());
			@SuppressWarnings("deprecation")
			Entrada ent = new Entrada(i, u, f, USUARIO_LOGADO, new Date(tfDataValidade.getText().toString()),
					new Date(tfDataEntrada.getText().toString()), new Date(tfDataFabricacao.getText().toString()),
					Integer.parseInt(tfQtd.getText().toString().trim()));
			try {
				ent = dao.CadastrarEntrada(ent);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				lblStatus.setText("Problema com banco de dados");
			}
			if (ent.getId() > 0) {
				lblStatus.setText("Cadastrado");
				tfDataEntrada.setValue(new Date());
				tfDataValidade.setValue(new Date());
				tfDataFabricacao.setValue(new Date());
				tfQtd.setText("");
				cbFabricantes.setSelectedIndex(0);
				cbItens.setSelectedIndex(0);
				cbUnidades.setSelectedIndex(0);

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