package com.br.ufms.schirrel.app;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.EntradaView;
import com.br.ufms.schirrel.classes.SaidaView;
import com.br.ufms.schirrel.classes.Usuario;
import com.br.ufms.schirrel.exportar.ExportarRelatorio;
import com.br.ufms.schirrel.panels.BuscaConsumo;
import com.br.ufms.schirrel.panels.BuscaPermanente;
import com.br.ufms.schirrel.panels.CadastrarFabricante;
import com.br.ufms.schirrel.panels.CadastrarItem;
import com.br.ufms.schirrel.panels.CadastrarUnidade;
import com.br.ufms.schirrel.panels.CadastrarUsuario;
import com.br.ufms.schirrel.panels.EditarItem;
import com.br.ufms.schirrel.panels.NovaEntrada;
import com.br.ufms.schirrel.panels.NovaEntradaPermanente;
import com.br.ufms.schirrel.panels.RelatorioAtivos;
import com.br.ufms.schirrel.panels.RelatorioInativos;
import com.br.ufms.schirrel.panels.RelatorioPermanentes;

public class AppMain extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenu menuEstoque, menuRelatorio, menuCadastros, menuBuscar;
	private JMenuItem mieEntrada, mieEntradaPermanente, mieCadastrarItem, mieEditar, mieCadastrarUnidade,
			mieCadastrarFabricante, mirAtivo, mirInativo, mirPermanente, mirMaisEntradas, mirMaisSaida,
			mieCadastrarUsuario, mibPermanente, mibConsumo;
	private DAO dao;
	private Usuario USUARIO_LOGADO;
	private ExportarRelatorio EXPORTAR;

	int contRetirada = 0, contEntrada;

	public AppMain(Usuario u) {

		super("Controle de Estoque - UFMS - Laboratorios");
		EXPORTAR = new ExportarRelatorio();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		USUARIO_LOGADO = u;
		dao = new DAO();
		setResizable(false);
		initializeMenu();
		setVisible(true);
		// try {
		// for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		// if ("Nimbus".equals(info.getName())) {
		// UIManager.setLookAndFeel(info.getClassName());
		// break;
		// }
		// }
		// } catch (Exception e) {
		// // If Nimbus is not available, you can set the GUI to another look
		// and feel.
		// }

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
		mieCadastrarUnidade = new JMenuItem("Cadastrar Unidade");
		mieCadastrarFabricante = new JMenuItem("Cadastrar Fabricante");
		mieCadastrarUsuario = new JMenuItem("Cadastrar Usuario");
		mirAtivo = new JMenuItem("Relatorio Consumo Ativo");
		mirInativo = new JMenuItem("Relatorio Consumo Inativo");
		mirPermanente = new JMenuItem("Relatorio de Material Permanente");
		mirMaisEntradas = new JMenuItem("Itens Com Maior Entrada");
		mirMaisSaida = new JMenuItem("Itens Com Maior Retirada");
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
		// menuCadastros.add(mieEditar);

		menuEstoque.add(mieEntrada);
		menuEstoque.add(mieEntradaPermanente);
		menuRelatorio.add(mirAtivo);
		menuRelatorio.add(mirInativo);
		menuRelatorio.add(mirPermanente);
		menuRelatorio.addSeparator();
		menuRelatorio.add(mirMaisEntradas);
		menuRelatorio.add(mirMaisSaida);
		// menuRelatorio.add(mirDataAnteriores);

		menuBuscar.add(mibConsumo);

		menuBuscar.addSeparator();
		menuBuscar.add(mibPermanente);

		menuBar.add(menuEstoque);
		menuBar.add(menuCadastros);
		menuBar.add(menuRelatorio);
		menuBar.add(menuBuscar);
		mirPermanente.addActionListener(this);
		mirAtivo.addActionListener(this);
		mirInativo.addActionListener(this);
		mibConsumo.addActionListener(this);
		mibPermanente.addActionListener(this);
		mirMaisSaida.addActionListener(this);
		mirMaisEntradas.addActionListener(this);

		// getContentPane().add(new RelatorioAtivos(dao, USUARIO_LOGADO));
		// getContentPane().add(new BuscaConsumo(dao, USUARIO_LOGADO));

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
		} else if (e.getSource() == mieCadastrarUnidade) {
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
		} else if (e.getSource() == mirAtivo) {
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
		} else if (e.getSource() == mirPermanente) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new RelatorioPermanentes(dao, USUARIO_LOGADO));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mibConsumo) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new BuscaConsumo(dao, USUARIO_LOGADO));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mibPermanente) {
			getContentPane().removeAll();
			Elementos();
			getContentPane().add(new BuscaPermanente(dao, USUARIO_LOGADO));
			getContentPane().revalidate();
			getContentPane().repaint();
		} else if (e.getSource() == mirMaisSaida) {

			// por algum motivo o clique do menu esta indo duas vezes, isso é
			// para evitar
			if (contRetirada == 0) {
				contRetirada++;

				int op = JOptionPane.showConfirmDialog(this, "Gerar Relatorio de Itens com Maior Saida?");
				if (op == 0) {
					System.out.println("Sim");
					List<SaidaView> saidas = dao.ListarRetiradasTotal();
					if (EXPORTAR.GerarRelatorioTotalDeSaida(saidas)) {
						JOptionPane.showMessageDialog(this, "Relatorio Gerado Com Sucesso");
					} else {
						JOptionPane.showMessageDialog(this, "Problema na Geração do Relatorio");
					}
				} else {
					System.out.println("Não");
				}

			} else {
				contRetirada = 0;
			}
		} else if (e.getSource() == mirMaisEntradas) {

			// por algum motivo o clique do menu esta indo duas vezes, isso é
			// para evitar
			if (contEntrada == 0) {
				contEntrada++;

				int op = JOptionPane.showConfirmDialog(this, "Gerar Relatorio de Itens com Maior Entrada?");
				if (op == 0) {
					System.out.println("Sim");
					List<EntradaView> entradas = dao.ListarEntradaTotal();
					if (EXPORTAR.GerarRelatorioTotalDeEntradda(entradas)) {
						JOptionPane.showMessageDialog(this, "Relatorio Gerado Com Sucesso");
					} else {
						JOptionPane.showMessageDialog(this, "Problema na Geração do Relatorio");
					}
				} else {
					System.out.println("Não");
				}

			} else {
				contEntrada = 0;
			}
		}

	}

	public void Elementos() {
		JPanel PanelUsuario = new JPanel();
		PanelUsuario.setBounds(580, 0, 200, 50);
		PanelUsuario.setBorder(new TitledBorder(null, "Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		PanelUsuario.add(new JLabel(USUARIO_LOGADO.toString()));
		JLabel label = new JLabel("Controle de Estoque");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 10, 600, 30);
		getContentPane().add(label);

		JPanel PanelInferior = new JPanel();
		PanelInferior.setLayout(null);
		PanelInferior.setBounds(0, 460, 798, 90);
		PanelInferior.setBorder(
				new TitledBorder(null, "Desenvolvimento", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel UFMS = new JLabel("");
		UFMS.setIcon(new ImageIcon(getClass().getResource("/img/rodape.png")));
		UFMS.setBounds(0, 0, 798, 90);
		PanelInferior.add(UFMS);

	/*	JLabel CPCX = new JLabel("");
		CPCX.setBounds(300, 0, 256, 122);
		CPCX.setIcon(new ImageIcon(getClass().getResource("/img/cpcx.png")));
		CPCX.setFont(new Font("Arial", Font.PLAIN, 25));
		PanelInferior.add(CPCX);

		JLabel SI = new JLabel("");
		SI.setBounds(500, 0, 122, 58);
		SI.setIcon(new ImageIcon(getClass().getResource("/img/si_p.png")));
		SI.setFont(new Font("Arial", Font.PLAIN, 25));
		PanelInferior.add(SI);
*/
		getContentPane().add(PanelInferior);

		getContentPane().add(PanelUsuario);
	}

}