package com.br.ufms.schirrel.login;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.app.AppMain;
import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.Usuario;

public class Login implements KeyListener {

	private JFrame frame;
	private JTextField tfUsuario;
	private JPasswordField pfSenha;
	private JButton btEntrar;
	private JLabel lblMsg;

	private DAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		dao = new DAO();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		 if ("Nimbus".equals(info.getName())) {
		 UIManager.setLookAndFeel(info.getClassName());
		 break;
		 }
		 }
		 } catch (Exception e) {

		}
		
		frame = new JFrame("Estoque");
		frame.setBounds(100, 100, 300, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		JPanel PanelUsuario = new JPanel();
		PanelUsuario.setBounds(5, 20, 290, 100);
		PanelUsuario.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelUsuario.setLayout(null);
		frame.getContentPane().add(PanelUsuario);

		JLabel lblUsuario = new JLabel("Registro: ");
		lblUsuario.setBounds(20, 29, 70, 15);
		PanelUsuario.add(lblUsuario);

		tfUsuario = new JTextField();
		tfUsuario.setBounds(95, 29, 150, 25);
		tfUsuario.addKeyListener(this);
		PanelUsuario.add(tfUsuario);

//		JLabel lblSenha = new JLabel("Senha: ");
//		lblSenha.setBounds(20, 60, 70, 15);
//		PanelUsuario.add(lblSenha);
//
//		pfSenha = new JPasswordField();
//		pfSenha.setBounds(95, 60, 150, 25);
//		pfSenha.addKeyListener(this);
//		PanelUsuario.add(pfSenha);

		btEntrar = new JButton("Entrar");
		btEntrar.setBounds(100, 130, 100, 25);
		frame.getContentPane().add(btEntrar);
		btEntrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ValidarLogin();

			}
		});

		lblMsg = new JLabel("");
		lblMsg.setBounds(20, 155, 260, 25);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblMsg);

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == pfSenha) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				ValidarLogin();
			}

		} else if(e.getSource() == tfUsuario) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				//pfSenha.requestFocus();
				ValidarLogin();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	private void ValidarLogin() {
		//String senha = String.valueOf(pfSenha.getPassword()).trim();
		if (tfUsuario.getText().toString().trim().equals("") ) {
			lblMsg.setText("Campo(s) em branco(s).");
		} else {
			Usuario USUARIO = dao.Login(tfUsuario.getText().toString().trim());
			if (USUARIO != null) {
				btEntrar.setEnabled(false);
				lblMsg.setText("Logado");
				new AppMain(USUARIO);
				frame.dispose();
			} else{
			
				if(dao.EncontrarLogin(tfUsuario.getText().toString().trim())){
					lblMsg.setText("Senha incorreta");
				}else
				lblMsg.setText("Problema com login");
				
			}

		}
	}

}
