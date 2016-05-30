package com.br.ufms.schirrel.classes;

public class Fabricante {
	int id;
	String fabricante;

	public Fabricante() {
		// TODO Auto-generated constructor stub
	}

	public Fabricante(int id, String f) {
		this.id = id;
		fabricante = f;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {

		return fabricante;
	}
}