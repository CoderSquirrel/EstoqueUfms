package com.br.ufms.schirrel.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Fabricante;
import com.br.ufms.schirrel.classes.Item;
import com.br.ufms.schirrel.classes.Unidade;
import com.br.ufms.schirrel.classes.Usuario;

public class Entrada extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfItem, tfQtd;
	private JFormattedTextField tfDataValidade;
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

	public Entrada(DAO D, Usuario u) {
		dao = D;
		USUARIO_LOGADO = u;
		setBounds(0, 60, 800, 280);
		setLayout(null);
		setBorder(new TitledBorder(null, "Entrada de Item", TitledBorder.LEADING, TitledBorder.CENTER, null, null));

		// JLabel label = new JLabel("Entrada de Item");
		// label.setFont(new Font("Arial", Font.BOLD, 20));
		// label.setHorizontalAlignment(SwingConstants.CENTER);
		// label.setBounds(0, 30, 800, 30);
		// add(label);

		JLabel lblItem = new JLabel("Item: ");
		lblItem.setBounds(10, 30, 50, 20);
		lblItem.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblItem);

		cbItens = new JComboBox<>(dao.ListarItens());
		cbItens.setBounds(50, 30, 350, 26);
		cbItens.setFont(new Font("Arial", Font.BOLD, 14));
		add(cbItens);

		JLabel lblUnidade = new JLabel("Unidade: ");
		lblUnidade.setBounds(420, 30, 100, 20);
		lblUnidade.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblUnidade);

		cbUnidades = new JComboBox<>(dao.ListarUnidades());
		cbUnidades.setBounds(490, 30, 300, 26);
		cbUnidades.setFont(new Font("Arial", Font.BOLD, 14));
		add(cbUnidades);

		JLabel lblFornecedor = new JLabel("Fornecedor:");
		lblFornecedor.setBounds(10, 70, 100, 20);
		lblFornecedor.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblFornecedor);

		cbFabricantes = new JComboBox<>(dao.ListarFabricantes());
		cbFabricantes.setBounds(100, 70, 350, 26);
		cbFabricantes.setFont(new Font("Arial", Font.BOLD, 14));
		add(cbFabricantes);

		JLabel lblQtd = new JLabel("Quantidade: ");
		lblQtd.setBounds(10, 110, 120, 20);
		lblQtd.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblQtd);

		tfQtd = new JTextField();
		tfQtd.setBounds(100, 110, 120, 26);
		tfQtd.setColumns(15);
		AbstractDocument document = (AbstractDocument) tfQtd.getDocument();
		document.setDocumentFilter(filter);
		add(tfQtd);

		JLabel lblDataValidade = new JLabel("Validade: ");
		lblDataValidade.setBounds(10, 150, 100, 20);
		lblDataValidade.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblDataValidade);

		DateFormat format= new SimpleDateFormat("MM/dd/yy");
		DateFormatter formatter = new DateFormatter(format);
		format.setLenient(false);
		formatter.setAllowsInvalid(false);
		formatter.setOverwriteMode(true);
		
		tfDataValidade = new JFormattedTextField(formatter);
		tfDataValidade.setBounds(100, 150, 100, 26);
		tfDataValidade.setFont(new Font("Arial", Font.BOLD, 14));
		add(tfDataValidade);
		tfDataValidade.setValue(new Date());
		tfDataValidade.setColumns(10);

		
		
		
		
		JLabel lblDataValida = new JLabel("separada por / ");
		lblDataValida.setBounds(220, 150, 140, 20);
		lblDataValida.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblDataValida);

		
		
		btCadastrar = new JButton("Salvar");
		btCadastrar.setBounds(10, 190, 100, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

		 lblStatus = new JLabel("Status: ");
		 lblStatus.setBounds(10, 220, 400, 20);
		 lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
	//	 lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		 add(lblStatus);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (EmBranco(tfDataValidade) || EmBranco(tfQtd)) {
			lblStatus.setText("Existe campo em branco.");
		} else {
			lblStatus.setText("tudókei.");
		}
		// dao.CadastrarFornecedor(tfFornecedor.getText().toString().trim());

	}

	boolean EmBranco(JTextField campo) {
		return campo.getText().toString().trim() == null || campo.getText().toString().trim().isEmpty()
				|| campo.getText().toString().trim().equals("");

	}
}