package com.br.ufms.schirrel.UI;

import java.awt.Color;
import java.text.Format;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;

public class EUFormattedTextField extends JFormattedTextField {

	public EUFormattedTextField() {
		// TODO Auto-generated constructor stub
	}

	public EUFormattedTextField(Object value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	public EUFormattedTextField(Format format) {
		super(format);
		// TODO Auto-generated constructor stub
	}

	public EUFormattedTextField(AbstractFormatter formatter) {
		super(formatter);
		setBackground(new Color(230, 230, 230));
		setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
	}

	public EUFormattedTextField(AbstractFormatterFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}

	public EUFormattedTextField(AbstractFormatterFactory factory, Object currentValue) {
		super(factory, currentValue);
		// TODO Auto-generated constructor stub
	}

}
