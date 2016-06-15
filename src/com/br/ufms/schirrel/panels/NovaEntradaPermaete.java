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

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Entrada;
import com.br.ufms.schirrel.classes.Fabricante;
import com.br.ufms.schirrel.classes.Item;
import com.br.ufms.schirrel.classes.Unidade;
import com.br.ufms.schirrel.classes.Usuario;

public class NovaEntradaPermaete extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfItem, tfQtd;
	private JFormattedTextField tfDataValidade, tfDataFabricacao, tfDataEntrada;
	private JButton btCadastrar;
	private JLabel lblStatus;
	private JComboBox<Fabricante> cbFabricantes;
	private JComboBox<Item> cbItens;
	private JComboBox<Unidade> cbUnidades;
	private JComboBox<Integer> cbDepositos, cbLaboratorios;
	private Usuario USUARIO_LOGADO;// Dener o mais lindo menino s2
	DAO dao;
	private DocumentFilter filter = new IntDocumentFilter();
	DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Format shortDate = DateFormat.getDateInstance(DateFormat.SHORT);

	public NovaEntradaPermaete(DAO D, Usuario u) {
		dao = D;
		USUARIO_LOGADO = u;
		setBounds(0, 60, 800, 280);
		setLayout(null);
		setBorder(new TitledBorder(null, "Entrada de Item", TitledBorder.LEADING, TitledBorder.CENTER, null, null));

		JPanel panelEntrada = new JPanel();
		panelEntrada.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Entrada",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEntrada.setBounds(10, 135, 153, 55);
		add(panelEntrada);

		JPanel panelDeposito = new JPanel();
		panelDeposito.setBorder(new TitledBorder(null, "Deposito", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDeposito.setBounds(163, 135, 100, 55);
		add(panelDeposito);
		JPanel panelLaboratorio = new JPanel();
		panelLaboratorio
				.setBorder(new TitledBorder(null, "Laboratorio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLaboratorio.setBounds(263, 135, 100, 55);
		add(panelLaboratorio);

		/*
		 * JPanel panelValidade = new JPanel(); panelValidade.setBorder(new
		 * TitledBorder(new LineBorder(new Color(184, 207, 229)), "Validade",
		 * TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 * panelValidade.setBounds(10, 135, 153, 50); add(panelValidade);
		 * 
		 * JPanel panelFabricacao = new JPanel(); panelFabricacao.setBorder(new
		 * TitledBorder(new LineBorder(new Color(184, 207, 229)),
		 * "Fabrica\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null,
		 * null)); panelFabricacao.setBounds(240, 135, 153, 50);
		 * add(panelFabricacao);
		 */
		cbItens = new JComboBox<>(dao.ListarItens());
		cbItens.setBounds(50, 22, 350, 26);
		cbItens.setFont(new Font("Arial", Font.BOLD, 14));
		add(cbItens);

		cbUnidades = new JComboBox<>(dao.ListarUnidades());
		cbUnidades.setBounds(498, 22, 300, 26);
		cbUnidades.setFont(new Font("Arial", Font.BOLD, 14));
		add(cbUnidades);

		cbFabricantes = new JComboBox<>(dao.ListarFabricantes());
		cbFabricantes.setBounds(112, 56, 350, 26);
		cbFabricantes.setFont(new Font("Arial", Font.BOLD, 14));
		add(cbFabricantes);

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

		JLabel lblFornecedor = new JLabel("Fabricante:");
		lblFornecedor.setBounds(12, 56, 100, 20);
		lblFornecedor.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblFornecedor);

		JLabel lblQtd = new JLabel("Quantidade: ");
		lblQtd.setBounds(13, 92, 120, 20);
		lblQtd.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblQtd);

		tfQtd = new JTextField();
		tfQtd.setBounds(123, 94, 120, 26);
		tfQtd.setColumns(15);
		AbstractDocument document = (AbstractDocument) tfQtd.getDocument();
		document.setDocumentFilter(filter);
		add(tfQtd);

		DateFormatter formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);

		tfDataEntrada = new JFormattedTextField(formatter);
		tfDataEntrada.setBounds(0, 0, 100, 40);
		tfDataEntrada.setFont(new Font("Arial", Font.BOLD, 14));
		panelEntrada.add(tfDataEntrada);
		tfDataEntrada.setValue(new Date());
		tfDataEntrada.setColumns(10);
		/*
		 * tfDataValidade = new JFormattedTextField(formatter);
		 * tfDataValidade.setBounds(0, 0, 100, 26);
		 * 
		 * tfDataValidade.setFont(new Font("Arial", Font.BOLD, 14));
		 * panelValidade.add(tfDataValidade); tfDataValidade.setValue(new
		 * Date()); tfDataValidade.setColumns(10);
		 * 
		 * tfDataFabricacao = new JFormattedTextField(formatter);
		 * tfDataFabricacao.setBounds(0, 0, 100, 40);
		 * tfDataFabricacao.setFont(new Font("Arial", Font.BOLD, 14));
		 * panelFabricacao.add(tfDataFabricacao); tfDataFabricacao.setValue(new
		 * Date()); tfDataFabricacao.setColumns(10);
		 * 
		 */
		JLabel lblItem = new JLabel("Item: ");
		lblItem.setBounds(10, 22, 50, 20);
		lblItem.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblItem);

		JLabel lblUnidade = new JLabel("Unidade: ");
		lblUnidade.setBounds(428, 22, 100, 20);
		lblUnidade.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblUnidade);

		btCadastrar = new JButton("Salvar");
		btCadastrar.setBounds(10, 200, 100, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

		lblStatus = new JLabel("");
		lblStatus.setBounds(10, 223, 788, 26);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);

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
					new Date(tfDataValidade.getText().toString()), new Date(tfDataValidade.getText().toString()),
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
		// dao.CadastrarFornecedor(tfFornecedor.getText().toString().trim());

	}

	boolean EmBranco(JTextField campo) {
		return campo.getText().toString().trim() == null || campo.getText().toString().trim().isEmpty()
				|| campo.getText().toString().trim().equals("");

	}
}