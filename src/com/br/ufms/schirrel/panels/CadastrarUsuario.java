package com.br.ufms.schirrel.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import com.br.ufms.schirrel.banco.DAO;

public class CadastrarUsuario extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfUsuario, tfRegistro;
	private JPasswordField pfSenha, pfConfirmacao;
	private JButton btCadastrar;
	private JLabel lblStatus;
	DAO dao;
	private DocumentFilter filter = new IntDocumentFilter();

	public CadastrarUsuario(DAO D) {
		dao = D;
		setBounds(0, 60, 800, 300);
		setLayout(null);
		setBorder(new TitledBorder(null, "Cadastrar Item", TitledBorder.LEADING, TitledBorder.CENTER, null, null));
	

	

		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(10, 30, 140, 20);
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblUsuario);

		tfUsuario = new JTextField();
		tfUsuario.setBounds(160, 30, 140, 26);
		tfUsuario.setFont(new Font("Arial", Font.BOLD, 18));
		add(tfUsuario);
		tfUsuario.setColumns(10);

		JLabel lblRegistro = new JLabel("Registro: ");
		lblRegistro.setBounds(10, 70, 140, 20);
		lblRegistro.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblRegistro);

		tfRegistro = new JTextField();
		tfRegistro.setBounds(160, 70, 140, 26);
		tfRegistro.setFont(new Font("Arial", Font.BOLD, 18));
		AbstractDocument document = (AbstractDocument) tfRegistro.getDocument();
		document.setDocumentFilter(filter);
		tfRegistro.setDocument(document);
		add(tfRegistro);
		tfRegistro.setColumns(10);

		JLabel lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(10, 110, 140, 20);
		lblSenha.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblSenha);

		pfSenha = new JPasswordField();
		pfSenha.setBounds(160, 110, 150, 25);
		add(pfSenha);

		JLabel lblConfirmacao = new JLabel("Confirmacao: ");
		lblConfirmacao.setBounds(10, 150, 140, 20);
		lblConfirmacao.setFont(new Font("Arial", Font.BOLD, 18));
		add(lblConfirmacao);

		pfConfirmacao = new JPasswordField();
		pfConfirmacao.setBounds(160, 150, 150, 25);
		add(pfConfirmacao);

		lblStatus = new JLabel("");
		lblStatus.setBounds(100, 230, 600, 20);
		lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblStatus);

		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setBounds(160, 190, 130, 30);
		btCadastrar.addActionListener(this);
		add(btCadastrar);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			
			
			if (EmBranco(tfRegistro) || EmBranco(tfUsuario) || EmBranco(pfConfirmacao)|| EmBranco(pfSenha)) {
				lblStatus.setText("Existe campo em branco.");
			} else if (!pfConfirmacao.getText().toString().trim().equals(pfSenha.getText().toString().trim())){
			lblStatus.setText("Senhas não identicas.");
			}else {
				
				if (dao.CadastrarUsuario(tfUsuario.getText().trim(), Long.parseLong(tfRegistro.getText().trim()),
						pfSenha.getText().trim()) != null) {
					 tfUsuario.setText("");
					 tfRegistro.setText("");
					 pfSenha.setText("");
					 pfConfirmacao.setText("");
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