package com.br.ufms.schirrel.UI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.text.Document;

public class EUTextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EUTextArea() {
		setBackground(new Color(230, 230, 230));
		setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
	}

	public EUTextArea(String text) {
		super(text);
	}

	public EUTextArea(Document doc) {
		super(doc);
	}

	public EUTextArea(int rows, int columns) {
		super(rows, columns);
	}

	public EUTextArea(String text, int rows, int columns) {
		super(text, rows, columns);
	}

	public EUTextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
	}

}
