package com.br.ufms.schirrel.classes;


public class Unidade{
	int id;
	String unidade;
	public Unidade() {
		// TODO Auto-generated constructor stub
	}
	public Unidade(int id, String u) {
		this.id = id;
		unidade = u;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return unidade;
	}
}