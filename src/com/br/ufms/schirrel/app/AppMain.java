package com.br.ufms.schirrel.app;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.EntradaPermanente;
import com.br.ufms.schirrel.classes.Usuario;
import com.br.ufms.schirrel.panels.CadastrarFabricante;
import com.br.ufms.schirrel.panels.CadastrarItem;
import com.br.ufms.schirrel.panels.CadastrarUnidade;
import com.br.ufms.schirrel.panels.CadastrarUsuario;
import com.br.ufms.schirrel.panels.EditarItem;
import com.br.ufms.schirrel.panels.NovaEntrada;
import com.br.ufms.schirrel.panels.NovaEntradaPermanente;
import com.br.ufms.schirrel.panels.RelatorioInativos;
import com.br.ufms.schirrel.panels.RelatorioAtivos;
import com.br.ufms.schirrel.panels.BuscaConsumo;
import com.br.ufms.schirrel.panels.Saida;

public class AppMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenu menuEstoque, menuRelatorio, menuCadastros, menuBuscar;
	private JMenuItem mieEntrada,mieEntradaPermanente, mieCadastrarItem, mieEditar, mieCadastrarUnidade, mieCadastrarFabricante,
			mirAtivo, mirInativo, mirSaidaData, mirEntradaData, mieCadastrarUsuario, mibPermanente, 
			 mibConsumo  ;
	private DAO dao;
	private Usuario USUARIO_LOGADO;
	public AppMain(Usuario u) {
		super("Controle de Estoque - UFMS - Laboratorios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		USUARIO_LOGADO = u;
		dao =new DAO();
		setResizable(false);
		initializeMenu();
		setVisible(true);
	}

	private void initializeMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuEstoque = new JMenu("Estoque");
		menuCadastros = new JMenu("Cadastro");
		menuRelatorio = new JMenu("Relatorio");
		menuBuscar = new JMenu("Busca");

		mieCadastrarItem = new JMenuItem("Cadastrar Item");
		mieEditar = new JMenuItem("Editar Item");
		mieEntrada = new JMenuItem("Entrada de Item");
		mieEntradaPermanente = new JMenuItem("Entrada de Item Permanente");
//		mieSaida = new JMenuItem("Saida de Item");
		mieCadastrarUnidade = new JMenuItem("Cadastrar Unidade");
		mieCadastrarFabricante = new JMenuItem("Cadastrar Fabricante");
		mieCadastrarUsuario = new JMenuItem("Cadastrar Usuario");
		mirAtivo = new JMenuItem("Relatorio Ativo");
		mirInativo = new JMenuItem("Relatorio Inativo");
	
		
		mibPermanente = new JMenuItem("Material Permanente");
	
		mibConsumo = new JMenuItem("Material de Consumo");
	
		mieCadastrarItem.addActionListener(this);
		mieEditar.addActionListener(this);
		mieEntrada.addActionListener(this);
		mieEntradaPermanente.addActionListener(this);
	
		
		mieCadastrarUnidade.addActionListener(this);
		mieCadastrarFabricante.addActionListener(this);
		mieCadastrarUsuario.addActionListener(this);
		menuCadastros.add(mieCadastrarItem);
		menuCadastros.add(mieCadastrarFabricante);
		menuCadastros.add(mieCadastrarUnidade);	
		menuCadastros.add(mieCadastrarUsuario);
		menuCadastros.addSeparator();
	//	menuCadastros.add(mieEditar);

		menuEstoque.add(mieEntrada);
		menuEstoque.add(mieEntradaPermanente);
		menuRelatorio.add(mirAtivo);
		menuRelatorio.add(mirInativo);
	//	menuRelatorio.add(mirDataAnteriores);
		
		menuBuscar.add(mibConsumo);

		menuBuscar.addSeparator();
		menuBuscar.add(mibPermanente);
	
		menuBar.add(menuEstoque);
		menuBar.add(menuCadastros);
		menuBar.add(menuRelatorio);
		menuBar.add(menuBuscar);
		
		mirAtivo.addActionListener(this);
		mirInativo.addActionListener(this);
	//	getContentPane().add(new RelatorioAtivos(dao,  USUARIO_LOGADO));
		getContentPane().add(new BuscaConsumo(dao,  USUARIO_LOGADO));
		
		Elementos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mieCadastrarItem) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new CadastrarItem(dao));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mieEditar) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new EditarItem(dao));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mieEntrada) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new NovaEntrada(dao, USUARIO_LOGADO));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mieEntradaPermanente) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new NovaEntradaPermanente(dao, USUARIO_LOGADO));
			getContentPane().revalidate();
			getContentPane().repaint();
		}else if (e.getSource() == mieCadastrarUnidade) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new CadastrarUnidade(dao));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mieCadastrarFabricante) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new CadastrarFabricante(dao));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mieCadastrarUsuario) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new CadastrarUsuario(dao));
			getContentPane().revalidate();
			getContentPane().repaint();
		}else if (e.getSource() == mirAtivo) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new RelatorioAtivos(dao, USUARIO_LOGADO));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mirInativo) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new RelatorioInativos(dao));
			getContentPane().revalidate();
			getContentPane().repaint();
		}else if (e.getSource() == mibConsumo) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new BuscaConsumo(dao, USUARIO_LOGADO));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mibPermanente) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new BuscaConsumo(dao, USUARIO_LOGADO));
			getContentPane().revalidate();
			getContentPane().repaint();
		} 


	}
	
	public void Elementos(){
		JPanel PanelUsuario = new JPanel();
		PanelUsuario.setBounds(580,0, 200, 50);
		PanelUsuario.setBorder(new TitledBorder(null, "Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelUsuario.add(new JLabel(USUARIO_LOGADO.toString()));
		JLabel label = new JLabel("Controle de Estoque");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 10, 600, 30);
		getContentPane().add(label);
		
		JPanel PanelInferior = new JPanel();
		PanelInferior.setBounds(0,460, 798, 90);
		PanelInferior.setBorder(new TitledBorder(null, "Desenvolvimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	
		
		JLabel UFMS = new JLabel("");
		UFMS.setIcon(new ImageIcon(getClass().getResource("/img/ufms_p.png")));
		UFMS.setBounds(0, 0, 122, 58);
		PanelInferior.add(UFMS);
		
		JLabel CPCX = new JLabel("");
		CPCX.setBounds(300, 0, 256, 122);
		CPCX.setIcon(new ImageIcon(getClass().getResource("/img/cpcx.png")));
		CPCX.setFont(new Font("Arial", Font.PLAIN, 25));
		PanelInferior.add(CPCX);
		
		JLabel SI = new JLabel("");
		SI.setBounds(500, 0, 122, 58);
		SI.setIcon(new ImageIcon(getClass().getResource("/img/si_p.png")));
		SI.setFont(new Font("Arial", Font.PLAIN, 25));
		PanelInferior.add(SI);
		
		getContentPane().add(PanelInferior);
		
		getContentPane().add(PanelUsuario);
	}

}