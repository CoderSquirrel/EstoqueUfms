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
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import com.br.ufms.schirrel.UI.EUButton;
import com.br.ufms.schirrel.UI.EUTextField;
import com.br.ufms.schirrel.banco.DAO;

public class CadastrarUsuario extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfUsuario, tfRegistro;
	private JButton btCadastrar;
	private JLabel lblStatus;
	DAO dao;
	private DocumentFilter filter = new IntDocumentFilter();

	public CadastrarUsuario(DAO D) {
		dao = D;
		setBounds(0, 60, 795, 300);
		setLayout(null);
		setBorder(new TitledBorder(null, "Cadastrar Item", TitledBorder.LEADING, TitledBorder.CENTER, null, new Color(24, 135, 180)));
	

		setBackground(Color.WHITE);

		JPanel panelu = new JPanel();
		panelu.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Usuario",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panelu.setBounds(10, 17, 780, 60);
		panelu.setLayout(null);
		panelu.setBackground(Color.WHITE);
		add(panelu);

		tfUsuario = new EUTextField();
		tfUsuario.setBounds(15, 20, 750, 25);
		tfUsuario.setFont(new Font("Arial", Font.BOLD, 18));
		panelu.add(tfUsuario);
		tfUsuario.setColumns(10);

		JPanel panelr = new JPanel();
		panelr.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Registro",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(24, 135, 180)));
		panelr.setBounds(10, 90, 780, 60);
		panelr.setLayout(null);
		panelr.setBackground(Color.WHITE);
		add(panelr);

		tfRegistro = new EUTextField();
		tfRegistro.setBounds(15, 20, 750, 25);
		tfRegistro.setFont(new Font("Arial", Font.BOLD, 18));
		AbstractDocument document = (AbstractDocument) tfRegistro.getDocument();
		document.setDocumentFilter(filter);
		tfRegistro.setDocument(document);
		panelr.add(tfRegistro);
		tfRegistro.setColumns(10);

		lblStatus = new JLabel("");
		lblStatus.setBounds(100, 230, 600, 20);
		lblStatus.setForeground(new Color(24, 135, 180));
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);

		btCadastrar = new EUButton("Cadastrar");
		btCadastrar.setBounds(635, 250, 150, 40);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			
			if (EmBranco(tfRegistro) || EmBranco(tfUsuario)) {
				lblStatus.setText("Existe campo em branco.");
			} else {
			if (dao.CadastrarUsuario(tfUsuario.getText().trim(), Long.parseLong(tfRegistro.getText().trim())).getId()!=0) {
					 tfUsuario.setText("");
					 tfRegistro.setText("");
					
					lblStatus.setText("Usuario Cadastrado");
				}
			}
			
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	boolean EmBranco(JTextField campo){
		return campo.getText().toString().trim() == null || campo.getText().toString().trim().isEmpty()
				|| campo.getText().toString().trim().equals("");
		
		
	}

}