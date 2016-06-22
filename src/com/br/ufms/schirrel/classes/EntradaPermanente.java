package com.br.ufms.schirrel.classes;

import java.util.Date;

public class EntradaPermanente {


	int id;
	int qtd;
	int deposito;
	int laboratorio;
	String patrimonio;
	Item item;
	Usuario usuario;
	Date  dataEntrada;
	String obs;

	
	public EntradaPermanente(int id, Item item, Usuario usuario, Date dataEntrada, int qtd, int dep, int lab, String obs, String patrimonio) {
	this.id = id;
		this.item = item;
		this.usuario = usuario;
		this.dataEntrada = dataEntrada;
		this.qtd = qtd;
		this.deposito = dep;
		this.laboratorio = lab;
		this.obs = obs;
		this.patrimonio = patrimonio;
	}
	
	public EntradaPermanente(Item item,  Usuario usuario,  Date dataEntrada,int qtd, int dep, int lab, String obs, String patrimonio) {
		this.item = item;
		this.usuario = usuario;
		this.dataEntrada = dataEntrada;
		this.qtd = qtd;
		this.deposito = dep;
		this.laboratorio = lab;
		this.obs = obs;
		this.patrimonio = patrimonio;
	}
	
	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}



	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
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


	public EntradaPermanente() {
		dataEntrada = new Date();
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}


}