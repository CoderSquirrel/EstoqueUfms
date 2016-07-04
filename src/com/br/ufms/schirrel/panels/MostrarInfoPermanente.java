package com.br.ufms.schirrel.panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.classes.EntradaPermanente;

public class MostrarInfoPermanente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1612517913044249516L;

	/**
	 * Create the panel.
	 */
	public MostrarInfoPermanente(EntradaPermanente ep) {
		JPanel panelItem = new JPanel();
		panelItem.setBorder(new TitledBorder(null, "Item", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelItem.setBounds(12, 12, 235, 49);
		add(panelItem);
		panelItem.setLayout(null);

		JLabel lblItem = new JLabel(ep.getItem().getItem());
		lblItem.setBounds(12, 12, 211, 25);
		panelItem.add(lblItem);

		JPanel panelQtd = new JPanel();
		panelQtd.setBorder(new TitledBorder(null, "Quantidade", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelQtd.setBounds(495, 109, 137, 49);
		panelQtd.setLayout(null);
		add(panelQtd);

		JLabel lblQtd = new JLabel(ep.getQtd() + "");
		lblQtd.setBounds(12, 12, 125, 25);
		panelQtd.add(lblQtd);

		JPanel panelEntrada = new JPanel();
		panelEntrada.setBorder(new TitledBorder(null, "Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEntrada.setBounds(495, 62, 137, 49);
		panelEntrada.setLayout(null);
		add(panelEntrada);

		JLabel lblEntrada = new JLabel(ep.getDataEntrada().toString());
		lblEntrada.setBounds(12, 12, 125, 25);
		panelEntrada.add(lblEntrada);

		JPanel panelDep = new JPanel();
		panelDep.setBorder(new TitledBorder(null, "Dep", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDep.setBounds(562, 160, 72, 49);
		panelDep.setLayout(null);
		add(panelDep);

		JLabel lblDep = new JLabel(ep.getDeposito() + "");
		lblDep.setBounds(12, 12, 60, 25);
		panelDep.add(lblDep);

		JPanel panelLab = new JPanel();
		panelLab.setBorder(new TitledBorder(null, "Lab", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLab.setBounds(562, 210, 72, 49);
		panelLab.setLayout(null);
		add(panelLab);

		JLabel lblLab = new JLabel(ep.getLaboratorio() + "");
		lblLab.setBounds(12, 12, 60, 25);
		panelLab.add(lblLab);

		JPanel panelPatrimonio = new JPanel();
		panelPatrimonio
				.setBorder(new TitledBorder(null, "Patrimonio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPatrimonio.setBounds(246, 12, 239, 49);
		add(panelPatrimonio);
		panelPatrimonio.setLayout(null);

		JLabel lblPatrimonio = new JLabel(ep.getPatrimonio());
		lblPatrimonio.setBounds(12, 12, 227, 25);
		panelPatrimonio.add(lblPatrimonio);

		JPanel panelDescricao = new JPanel();
		panelDescricao.setBorder(
				new TitledBorder(null, "Descri\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDescricao.setBounds(12, 62, 481, 96);
		panelDescricao.setLayout(null);
		add(panelDescricao);

		JLabel lblDescricao = new JLabel(ep.getDescricao());
		lblDescricao.setBounds(12, 12, 457, 72);
		panelDescricao.add(lblDescricao);

		JPanel panelObs = new JPanel();
		panelObs.setBorder(new TitledBorder(null, "Obs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelObs.setBounds(12, 157, 550, 152);
		panelObs.setLayout(null);
		add(panelObs);

		JLabel lblObs = new JLabel(ep.getObs());
		lblObs.setBounds(12, 12, 526, 128);
		panelObs.add(lblObs);

		JPanel panelEstado = new JPanel();
		panelEstado.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Estado", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelEstado.setBounds(485, 12, 146, 49);
		panelEstado.setLayout(null);
		add(panelEstado);

		JLabel lblEstado = new JLabel(ep.getEstadoString());
		lblEstado.setBounds(12, 12, 134, 25);
		panelEstado.add(lblEstado);

		JButton btOk = new JButton("OK");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btOk.setBounds(564, 262, 68, 42);
		btOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		add(btOk);

		setBounds(0, 60, 650, 350);
		setLayout(null);
		setVisible(true);

	}
}
