package com.br.ufms.schirrel.classes;

import java.util.Date;

public class EntradaPermanente {


	int id;
	int qtd;
	int deposito;
	int laboratorio;
	String patrimonio;
	Item item;
	String descricao;
	Usuario usuario;
	Date  dataEntrada;
	String obs;
	Estado estado;

	
	public EntradaPermanente(int id, Item item, Usuario usuario, Date dataEntrada, int qtd, int dep, int lab, String obs, String patrimonio, String descricao, Estado e) {
	this.id = id;
		this.item = item;
		this.usuario = usuario;
		this.dataEntrada = dataEntrada;
		this.qtd = qtd;
		this.deposito = dep;
		this.laboratorio = lab;
		this.obs = obs;
		this.patrimonio = patrimonio;
		this.descricao = descricao;
		this.estado = e;
	}
	
	public EntradaPermanente(Item item,  Usuario usuario,  Date dataEntrada,int qtd, int dep, int lab, String obs, String patrimonio, String descricao, Estado e) {
		this.item = item;
		this.usuario = usuario;
		this.dataEntrada = dataEntrada;
		this.qtd = qtd;
		this.deposito = dep;
		this.laboratorio = lab;
		this.obs = obs;
		this.patrimonio = patrimonio;
		this.descricao = descricao;
		this.estado = e;
	}
	
	public EntradaPermanente(int id, Item item,  Usuario usuario,  Date dataEntrada,int qtd, int dep, int lab, String obs, String patrimonio, String descricao, int e) {
		this.id = id;
		this.item = item;
		this.usuario = usuario;
		this.dataEntrada = dataEntrada;
		this.qtd = qtd;
		this.deposito = dep;
		this.laboratorio = lab;
		this.obs = obs;
		this.patrimonio = patrimonio;
		this.descricao = descricao;
		this.estado = Estado.values()[e];
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
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Estado getEstado() {
		return estado;
	}
	
	public int getEstadoInt(){
		return estado.ordinal();
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public enum Estado {
		bom, ruim
	}


	public String getEstadoString() {
		// TODO Auto-generated method stub
		return estado.name();
	}
}

