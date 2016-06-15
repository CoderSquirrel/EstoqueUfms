package com.br.ufms.schirrel.classes;

import java.util.Date;

public class EntradaPermanente {
	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	int id;
	int qtd;
	int deposito;
	int laboratorio;
	
	public int getDeposito() {
		return deposito;
	}

	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}

	public int getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(int laboratorio) {
		this.laboratorio = laboratorio;
	}

	Item item;
	Unidade unidade;
	Fabricante fabricante;
	Usuario usuario;
	Date dataValidade, dataEntrada, dataFabricacao;
	public EntradaPermanente() {
		dataEntrada = new Date();
	}
	
	public EntradaPermanente(int id, Item item, Unidade unidade, Fabricante fabricante, Usuario usuario, Date dataValidade, Date dataEntrada,Date dataFabricacao, int qtd, int dep, int lab) {
	this.id = id;
		this.item = item;
		this.unidade = unidade;
		this.fabricante = fabricante;
		this.usuario = usuario;
		this.dataValidade = dataValidade;
		this.dataEntrada = dataEntrada;
		this.dataFabricacao = dataFabricacao;
		this.qtd = qtd;
		this.deposito = dep;
		this.laboratorio = lab;
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