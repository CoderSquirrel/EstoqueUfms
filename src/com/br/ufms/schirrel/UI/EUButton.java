package com.br.ufms.schirrel.UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class EUButton extends JButton {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2692519808528264797L;

	public EUButton (String label){
		this.setText(label);
		this.setBackground(new Color(24, 135, 180));
		//this.setBackground(new Color(184, 207, 229));
		//this.setForeground(new Color(238, 238, 238));
		//this.setForeground(new Color(24, 135, 1));
		this.setForeground(Color.WHITE);
		this.setFocusPainted(false);
		this.setFont(new Font("Tahoma", Font.BOLD, 12));//http://answers.yahoo.com/question/index?qid=20070906133202AAOvnIP

	}
}
