package com.br.ufms.schirrel.UI;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class EUTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9158292096204552670L;

	public EUTextField() {
		setBackground(new Color(230, 230, 230));
		setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
	}
}
