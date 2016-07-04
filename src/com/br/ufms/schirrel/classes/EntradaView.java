package com.br.ufms.schirrel.classes;

import java.util.Date;

public class EntradaView {

	
	int entrada_id;
	String item;
	String fabricante;
	Date fabricacao;
	Date validade;
	String usuario;
	int qtd;
	
	
	
	public EntradaView(int ei, String it, String fa, Date fab, Date va, String us, Date re, int qtd) {
		this.entrada_id = ei;
		this.item = it;
		this.fabricante = fa;
		this.fabricacao = fab;
		this.validade = va;
		this.usuario = us;
		
		this.qtd = qtd;
		
		
	}
	
	
	
	
	public EntradaView(int ei, String it, String fa, Date fab, Date va, int qtd) {
		this.entrada_id = ei;
		this.item = it;
		this.fabricante = fa;
		this.fabricacao = fab;
		this.validade = va;
		this.qtd = qtd;
		
		
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

	public Date getFabricacao() {
		return fabricacao;
	}




	public void setFabricacao(Date fabricacao) {
		this.fabricacao = fabricacao;
	}




	public void setQtd(int qtd) {
		this.qtd = qtd;
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
	
	public int getQtd() {
		return qtd;
	}
	public void setQtd_retirada(int qtd) {
		this.qtd = qtd;
	}
	
	
	
}
