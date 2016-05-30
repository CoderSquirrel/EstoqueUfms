package com.br.ufms.schirrel.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CriarConexao {
	public Connection getConexao() throws SQLException {
		return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
	}
}
