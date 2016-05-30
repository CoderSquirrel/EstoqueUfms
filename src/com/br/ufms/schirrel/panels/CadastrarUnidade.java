package com.br.ufms.schirrel.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.banco.DAO;

public class CadastrarUnidade extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfUnidade;
	private JButton btCadastrar;
	private JLabel lblStatus;
	DAO dao;

	public CadastrarUnidade(DAO D) {
		dao =  D;
		setBounds(0, 60, 800, 180);
		setLayout(null);
		setBorder(new TitledBorder(null, "Cadastrar Unidade", TitledBorder.LEADING, TitledBorder.CENTER, null, null));

		JLabel lblUnidades = new JLabel("Unidade: ");
		lblUnidades.setBounds(10, 30, 140, 20);
		lblUnidades.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblUnidades);

		tfUnidade = new JTextField();
		tfUnidade.setBounds(160, 30, 450, 26);
		tfUnidade.setFont(new Font("Arial", Font.BOLD, 18));
		add(tfUnidade);
		tfUnidade.setColumns(10);

		lblStatus = new JLabel("");
		lblStatus.setBounds(100, 120, 600, 20);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);

		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setBounds(160, 70, 150, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
		
			
			
			if (tfUnidade.getText().toString().trim() == null || tfUnidade.getText().toString().trim().isEmpty()
					|| tfUnidade.getText().toString().trim().equals("")) {
				lblStatus.setText("Campo Unidade em branco.");
			} else {
				
				if(dao.CadastrarUnidade(tfUnidade.getText().toString().trim()) != null){
					tfUnidade.setText("");
					lblStatus.setText("Unidade Cadastrada");
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}