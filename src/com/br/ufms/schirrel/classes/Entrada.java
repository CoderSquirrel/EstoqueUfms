package com.br.ufms.schirrel.classes;

import java.util.Date;

public class Entrada {
	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	int id;
	int qtd;
	Item item;
	Unidade unidade;
	Fabricante fabricante;
	Usuario usuario;
	Date dataValidade, dataEntrada, dataFabricacao;
int retirada;
	public Entrada() {
		dataEntrada = new Date();
	}

	public Entrada(Item item, Unidade unidade, Fabricante fabricante, Usuario usuario, Date dataValidade, Date dataEntrada,Date dataFabricacao, int qtd) {
		this.item = item;
		this.unidade = unidade;
		this.fabricante = fabricante;
		this.usuario = usuario;
		this.dataValidade = dataValidade;
		this.dataEntrada = dataEntrada;
		this.dataFabricacao = dataFabricacao;
		this.qtd = qtd;
		this.retirada = 0;
	}
	
	public Entrada(int id, Item item, Unidade unidade, Fabricante fabricante, Usuario usuario, Date dataValidade, Date dataEntrada,Date dataFabricacao, int qtd) {
	this.id = id;
		this.item = item;
		this.unidade = unidade;
		this.fabricante = fabricante;
		this.usuario = usuario;
		this.dataValidade = dataValidade;
		this.dataEntrada = dataEntrada;
		this.dataFabricacao = dataFabricacao;
		this.qtd = qtd;
		this.retirada = 0;
	}
	
	public int getRetirada() {
		return retirada;
	}

	public void setRetirada(int retirada) {
		this.retirada = retirada;
	}

	public Entrada(int id, Item item, Unidade unidade, Fabricante fabricante, Usuario usuario, Date dataValidade, Date dataEntrada,Date dataFabricacao, int qtd, int retirado) {
	this.id = id;
		this.item = item;
		this.unidade = unidade;
		this.fabricante = fabricante;
		this.usuario = usuario;
		this.dataValidade = dataValidade;
		this.dataEntrada = dataEntrada;
		this.dataFabricacao = dataFabricacao;
		this.qtd = qtd;
		this.retirada =retirado;
	}
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

}