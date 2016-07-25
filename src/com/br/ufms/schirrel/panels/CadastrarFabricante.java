package com.br.ufms.schirrel.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.UI.EUTextField;
import com.br.ufms.schirrel.banco.DAO;

public class CadastrarFabricante extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfFabricante;
	private JButton btCadastrar;
	private JLabel lblStatus;
	DAO dao;

	public CadastrarFabricante(DAO D) {
		dao = D;
		setBounds(0, 60, 800, 180);
		setLayout(null);
		setBorder(
				new TitledBorder(null, "Cadastrar Fabricante", TitledBorder.LEADING, TitledBorder.CENTER, null, new Color(24, 135, 180)));
		setBackground(Color.WHITE);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Fabricante",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panel.setBounds(10, 17, 560, 60);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		add(panel);
		
		tfFabricante = new EUTextField();
		tfFabricante.setBounds	(15, 20, 530, 25);
		tfFabricante.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(tfFabricante);
		tfFabricante.setColumns(10);

		lblStatus = new JLabel("");
		lblStatus.setForeground(new Color(24, 135, 180));
		
		lblStatus.setBounds(100, 120, 500, 20);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);

		btCadastrar = new EUButton("Cadastrar");
		btCadastrar.setBounds(620, 30, 150, 40);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (tfFabricante.getText().toString().trim() == null || tfFabricante.getText().toString().trim().isEmpty()
					|| tfFabricante.getText().toString().trim().equals("")) {
				lblStatus.setText("Campo Fabricante em branco.");
			} else {
				
				if (dao.CadastrarFabricante(tfFabricante.getText().toString().trim()) != null) {
					tfFabricante.setText("");
					lblStatus.setText("Fabricante Cadastrado");
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}