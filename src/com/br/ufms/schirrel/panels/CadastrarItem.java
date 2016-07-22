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

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.banco.DAO;

public class CadastrarItem extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfItem;
	private JButton btCadastrar;
	private JLabel lblStatus;
	DAO dao;

	public CadastrarItem(DAO D) {
		dao =  D;
		setBounds(0, 60, 800, 180);
		setLayout(null);
		setBorder(new TitledBorder(null, "Cadastrar Item", TitledBorder.LEADING, TitledBorder.CENTER, null, null));
	

		JLabel lblItems = new JLabel("Item: ");
		lblItems.setBounds(10, 30, 140, 20);
		lblItems.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblItems);

		tfItem = new JTextField();
		tfItem.setBounds(160, 30, 450, 26);
		tfItem.setFont(new Font("Arial", Font.BOLD, 18));
		add(tfItem);
		tfItem.setColumns(10);

		lblStatus = new JLabel("");
		lblStatus.setBounds(100, 120, 600, 20);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);

		btCadastrar = new EUButton("Cadastrar");
		btCadastrar.setBounds(160, 70, 150, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			
			if (tfItem.getText().toString().trim() == null || tfItem.getText().toString().trim().isEmpty()
					|| tfItem.getText().toString().trim().equals("")) {
				lblStatus.setText("Campo Item em branco.");
			} else {
				
				if(dao.CadastrarItem(tfItem.getText().toString().trim()) != null){
					tfItem.setText("");
					lblStatus.setText("Item Cadastrado");
				}
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}