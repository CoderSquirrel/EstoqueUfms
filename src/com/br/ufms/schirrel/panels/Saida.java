package com.br.ufms.schirrel.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Fabricante;
import com.br.ufms.schirrel.classes.Item;
import com.br.ufms.schirrel.classes.Unidade;
import com.br.ufms.schirrel.classes.Usuario;

public class Saida extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private JTextField tfItem, tfDataValidade;
	private JTextField tfQtd;
	private JButton btCadastrar;
	private JLabel lblStatus;
	private JComboBox<Fabricante> cbFabricantes;
	private JComboBox<Item> cbItens;
	private JComboBox<Unidade> cbUnidades;
	private Usuario USUARIO_LOGADO;
	DAO dao;
	 private DocumentFilter filter = new IntDocumentFilter();
	public Saida(DAO D, Usuario u) {
		dao = D;
		USUARIO_LOGADO = u;
		setBounds(0, 60, 800, 250);
		setLayout(null);
		setBorder(new TitledBorder(null, "Saida de Item", TitledBorder.LEADING, TitledBorder.CENTER, null, null));
		
//		JLabel label = new JLabel("Entrada de Item");
//		label.setFont(new Font("Arial", Font.BOLD, 20));
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		label.setBounds(0, 30, 800, 30);
//		add(label);
		setBackground(Color.WHITE);
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
		lblItem.setFont(new Font("Arial", Font.BOLD, 14));
		add(lblDataValidade);

		tfDataValidade= new JTextField();
		tfDataValidade.setBounds(100, 150, 100, 26);
		tfDataValidade.setFont(new Font("Arial", Font.BOLD, 14));
		add(tfDataValidade);
		tfDataValidade.setColumns(10);
		
		
	
		btCadastrar = new EUButton("Salvar");
		btCadastrar.setBounds(10, 190, 100, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// dao.CadastrarFornecedor(tfFornecedor.getText().toString().trim());

	}

}