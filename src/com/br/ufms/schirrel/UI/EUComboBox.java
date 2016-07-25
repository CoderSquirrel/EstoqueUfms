package com.br.ufms.schirrel.UI;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboPopup;

public class EUComboBox<E> extends JComboBox<E> {

	public EUComboBox(E[] items) {
		super(items);
	//	maximumRowCount = items.length;
	
		setBackground(Color.WHITE);

		 setForeground(new Color(24, 135, 180));
		 setBorder(new LineBorder(new Color(24, 135, 180)));
		 UIManager.put("ScrollBar.thumbHighlight", Color.WHITE);
		 UIManager.put("ScrollBar.thumbDarkShadow", Color.WHITE);
		 UIManager.put("ScrollBar.highlight", Color.WHITE);
		 UIManager.put("ScrollBar.trackHighlight", Color.WHITE);
		 //cbItens.setFocusPainted(false);
		setUI(ColorArrowUI.createUI(this));
		 Object child = getAccessibleContext().getAccessibleChild(0);
		 BasicComboPopup popup = (BasicComboPopup)child;
		 JList list = popup.getList();
		 list.setSelectionBackground(new Color(24, 135, 180));
		 list.setSelectionForeground(Color.WHITE);
	}
}
