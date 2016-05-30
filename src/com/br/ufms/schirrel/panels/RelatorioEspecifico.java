package com.br.ufms.schirrel.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.br.ufms.schirrel.banco.DAO;

public class RelatorioEspecifico extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfFornecedor;
	private JButton btCadastrar;
	private JLabel lblStatus;
	DAO dao;

	public RelatorioEspecifico(DAO D) {
		dao =  D;
		setBounds(0, 60, 800, 600);
		setLayout(null);
		JLabel label = new JLabel("Relatorio Epecifico");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 800, 30);
		add(label);
//
//		JLabel lblFornecedores = new JLabel("Fornecedor: ");
//		lblFornecedores.setBounds(10, 100, 140, 20);
//		lblFornecedores.setFont(new Font("Arial", Font.BOLD, 18));
//		add(lblFornecedores);
//
//		tfFornecedor = new JTextField();
//		tfFornecedor.setBounds(160, 100, 450, 26);
//		tfFornecedor.setFont(new Font("Arial", Font.BOLD, 18));
//		add(tfFornecedor);
//		tfFornecedor.setColumns(10);
//
//		lblStatus = new JLabel("Status: ");
//		lblStatus.setBounds(100, 180, 140, 20);
//		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
//		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
//		add(lblStatus);
//
//		btCadastrar = new JButton("Cadastrar");
//		btCadastrar.setBounds(160, 140, 200, 30);
//		add(btCadastrar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//dao.CadastrarFornecedor(tfFornecedor.getText().toString().trim());

	}

}