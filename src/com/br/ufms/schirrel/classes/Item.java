package com.br.ufms.schirrel.classes;


public class Item{
	int id;
	String item;
	public Item() {
		// TODO Auto-generated constructor stub
	}
	public Item(int id, String i) {
		this.id  = id;
		item = i;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return item;
	}
	
}