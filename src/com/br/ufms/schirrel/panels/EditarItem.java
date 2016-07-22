package com.br.ufms.schirrel.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.banco.DAO;

public class EditarItem extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfItem;
	private JButton btEditar, btExcluir;
	private JLabel lblStatus;
	DAO dao;

	public EditarItem(DAO D) {
		dao =  D;
		setBounds(0, 60, 800, 600);
		setLayout(null);
		JLabel label = new JLabel("Editar Itens");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 800, 30);
		add(label);

//		JLabel lblItems = new JLabel("Item: ");
//		lblItems.setBounds(10, 100, 140, 20);
//		lblItems.setFont(new Font("Arial", Font.BOLD, 18));
//		add(lblItems);

		tfItem = new JTextField();
		tfItem.setBounds(160, 100, 450, 26);
		tfItem.setFont(new Font("Arial", Font.BOLD, 18));
		add(tfItem);
		tfItem.setColumns(10);

		lblStatus = new JLabel("Status: ");
		lblStatus.setBounds(100, 180, 140, 20);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);

		btEditar = new EUButton("Editar");
		btEditar.setBounds(160, 140, 100, 30);
		add(btEditar);
		
		btExcluir = new EUButton("Exluir");
		btExcluir.setBounds(280, 140, 100, 30);
		add(btExcluir);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}