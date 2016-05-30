package com.br.ufms.schirrel.banco;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.ufms.schirrel.classes.Fabricante;
import com.br.ufms.schirrel.classes.Item;
import com.br.ufms.schirrel.classes.Unidade;
import com.br.ufms.schirrel.classes.Usuario;

public class DAO {

	private Connection conn;

	public DAO() {
		CriarConexao connectionFactory = new CriarConexao();
		try {
			conn = connectionFactory.getConexao();
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * LOGIN
	 */
	public Usuario Login(String usuario, String senha) {
		String query = "SELECT * FROM TB_USUARIOS WHERE USUARIO LIKE ? AND SENHA LIKE ? ";
		Usuario USUARIO = null;
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, usuario);
			st.setString(2, senha);
			st.execute();
			ResultSet rs = st.getResultSet();

			if (rs.next())

			{
				USUARIO = new Usuario(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getString(4));
				st.close();
				rs.close();
			} else {
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return USUARIO;

	}

	public boolean EncontrarLogin(String usuario) {
		String query = "SELECT * FROM TB_USUARIOS WHERE USUARIO LIKE ?";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, usuario);
			st.execute();
			ResultSet rs = st.getResultSet();

			if (rs.next())

			{
				
				st.close();
				rs.close();
				return true;
			} 

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	/*
	 * LISTAGENS
	 */
	public Fabricante[] ListarFabricantes() {
		List<Fabricante> list = new ArrayList<Fabricante>();
		String query = "SELECT * FROM TB_FABRICANTES ";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				if (rs != null) {
					list.add(new Fabricante(rs.getInt(1), rs.getString(2)));
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Fabricante[] array = new Fabricante[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	public Unidade[] ListarUnidades() {
		List<Unidade> list = new ArrayList<Unidade>();
		String query = "SELECT * FROM TB_UNIDADES ";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				if (rs != null) {
					list.add(new Unidade(rs.getInt(1), rs.getString(2)));
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Unidade[] array = new Unidade[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	public Item[] ListarItens() {
		List<Item> list = new ArrayList<Item>();
		String query = "SELECT * FROM TB_ITENS ";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				if (rs != null) {
					list.add(new Item(rs.getInt(1), rs.getString(2)));
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Item[] array = new Item[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}

	/*
	 * CADASTROS
	 */
	public Usuario CadastrarUsuario(String usuario, long registro, String senha) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("  INSERT INTO TB_USUARIOS  ");
		query.append("  (usuario, registro, senha) ");
		query.append("  VALUES (? , ? , ?) ");
		PreparedStatement st = conn.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
		Usuario cadastrado = null;
		try {

			st.setString(1, usuario);
			st.setLong(2, registro);
			st.setString(3, senha);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				cadastrado = new Usuario(rs.getInt(1), usuario, registro, senha);
			}
			conn.commit();
			st.close();
			rs.close();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			st.close();
		}
		return cadastrado;

	}

	public Fabricante CadastrarFabricante(String fabricante) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("  INSERT INTO TB_FABRICANTES  ");
		query.append("  (fabricante) ");
		query.append("  VALUES (?) ");
		PreparedStatement st = null;
		Fabricante cadastrado = null;
		try {
			st = conn.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, fabricante);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				cadastrado = new Fabricante(rs.getInt(1), rs.getString(2));
			}

			conn.commit();
			st.close();
			rs.close();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			st.close();
		}
		return cadastrado;

	}

	public Unidade CadastrarUnidade(String trim) throws SQLException {

		StringBuilder query = new StringBuilder();
		query.append("  INSERT INTO TB_UNIDADES  ");
		query.append("  (unidade) ");
		query.append("  VALUES (?) ");
		PreparedStatement st = null;
		Unidade cadastrada = null;
		try {
			st = conn.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, trim);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				cadastrada = new Unidade(rs.getInt(1), rs.getString(2));
			}
			conn.commit();
			st.close();
			rs.close();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			st.close();
		}
		return cadastrada;

	}

	public Item CadastrarItem(String trim) throws SQLException {

		StringBuilder query = new StringBuilder();
		query.append("  INSERT INTO TB_ITENS  ");
		query.append("  (item) ");
		query.append("  VALUES (?) ");
		PreparedStatement st = null;
		Item cadastrao = null;
		try {
			st = conn.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, trim);
			st.execute();
			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				cadastrao = new Item(rs.getInt(1), rs.getString(2));
			}
			conn.commit();
			st.close();
			rs.close();
		} catch (SQLException e) {
			conn.rollback();
			throw e;
		} finally {
			st.close();
		}
		return cadastrao;

	}

}