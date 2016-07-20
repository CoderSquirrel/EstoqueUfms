package com.br.ufms.schirrel.classes;


public class Usuario{
	int id;
	String usuario;
	long registro;
	String senha;
 
	public Usuario(int i, String u, long r, String s) {
		id = i;
		usuario = u;
		registro = r;
		senha = s;
	}	
	public Usuario(int i, String u, long r) {
		id = i;
		usuario = u;
		registro = r;
	
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public long getRegistro() {
		return registro;
	}
	public void setRegistro(long registro) {
		this.registro = registro;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return usuario;
	}
	
}