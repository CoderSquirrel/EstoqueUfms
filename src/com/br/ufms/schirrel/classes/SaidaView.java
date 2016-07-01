package com.br.ufms.schirrel.classes;

import java.util.Date;

public class SaidaView {

	
	int entrada_id;
	String item;
	String fabricante;
	Date entrada;
	Date validade;
	String usuario;
	Date retirada;
	int qtd_retirada;
	
	
	
	public SaidaView(int ei, String it, String fa, Date en, Date va, String us, Date re, int qtd) {
		this.entrada_id = ei;
		this.item = it;
		this.fabricante = fa;
		this.entrada = en;
		this.validade = va;
		this.usuario = us;
		this.retirada = re;
		this.qtd_retirada = qtd;
		
		
	}
	
	
	public int getEntrada_id() {
		return entrada_id;
	}
	public void setEntrada_id(int entrada_id) {
		this.entrada_id = entrada_id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public Date getEntrada() {
		return entrada;
	}
	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getRetirada() {
		return retirada;
	}
	public void setRetirada(Date retirada) {
		this.retirada = retirada;
	}
	public int getQtd_retirada() {
		return qtd_retirada;
	}
	public void setQtd_retirada(int qtd_retirada) {
		this.qtd_retirada = qtd_retirada;
	}
	
	
	
}
