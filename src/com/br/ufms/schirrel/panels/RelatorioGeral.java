package com.br.ufms.schirrel.panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.br.ufms.schirrel.banco.DAO;
import com.br.ufms.schirrel.classes.ItemTable;

public class RelatorioGeral extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField tfFornecedor;
	private JButton btCadastrar;
	private JLabel lblStatus;
	DAO dao;

	public RelatorioGeral(DAO D) {
		dao =  D;
		setBounds(0, 60, 798, 400);
		setLayout(null);
		setBorder(new TitledBorder(null, "Itens", TitledBorder.LEADING, TitledBorder.CENTER, null, null));
	    Object[][] a ={ { "Item", "10", "Fornecedor", new Date(), new Date(),new Integer(5) , new Date(),
	    	new Integer(2) }};
    
		JTable Entrada = new JTable(new ItemTable(a));
	    Entrada.setPreferredScrollableViewportSize(new Dimension(500, 70));

	    //Create the scroll pane and add the table to it.
	    
	    JScrollPane scrollPane = new JScrollPane(Entrada);
	    scrollPane.setBounds(10, 20, 780, 400);
	    Entrada.getColumnModel().getColumn(0).setPreferredWidth(80);
	    Entrada.getColumnModel().getColumn(1).setPreferredWidth(15);
	    Entrada.getColumnModel().getColumn(2).setPreferredWidth(80);
	    Entrada.getColumnModel().getColumn(3).setPreferredWidth(50);
	    Entrada.getColumnModel().getColumn(4).setPreferredWidth(50);
	    Entrada.getColumnModel().getColumn(5).setPreferredWidth(15);
	    Entrada.getColumnModel().getColumn(6).setPreferredWidth(50);
	    Entrada.getColumnModel().getColumn(7).setPreferredWidth(15);
    	
	    	
	    	add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//dao.CadastrarFornecedor(tfFornecedor.getText().toString().trim());

	}

}