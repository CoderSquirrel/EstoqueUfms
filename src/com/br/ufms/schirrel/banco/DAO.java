package com.br.ufms.schirrel.banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.br.ufms.schirrel.classes.Entrada;
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

	public List<Entrada> ListarEntradasAtivas(){
		List<Entrada> entradas = new ArrayList<Entrada>();
		String query = "SELECT e.id, item_id, item, unidade_id, unidade, fabricante_id, fabricante, validade, fabricacao, entrada, qtd, qtd_retirada FROM TB_ENTRADAS e INNER JOIN TB_ITENS i on e.item_id = i.id INNER JOIN TB_FABRICANTES f on e.fabricante_id = f.id  INNER JOIN TB_UNIDADES u on e.unidade_id = u.id WHERE  qtd_retirada > 0 order by e.id ASC";
		
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.execute();
			ResultSet rs = st.getResultSet();
			
			while(rs.next()) {
				if(rs != null){
					entradas.add(new Entrada(rs.getInt(1), new Item(rs.getInt(2), rs.getString(3)), new Unidade(rs.getInt(4), rs.getString(5)), new Fabricante(rs.getInt(6),rs.getString(7)), null, rs.getDate(8), rs.getDate(10), rs.getDate(9), rs.getInt(11), rs.getInt(12)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return entradas;
	}

	
	public List<Entrada> ListarEntradasInativas(){
		List<Entrada> entradas = new ArrayList<Entrada>();
		String query = "SELECT e.id, item_id, item, unidade_id, unidade, fabricante_id, fabricante, validade, fabricacao, entrada, qtd, qtd_retirada FROM TB_ENTRADAS e INNER JOIN TB_ITENS i on e.item_id = i.id INNER JOIN TB_FABRICANTES f on e.fabricante_id = f.id  INNER JOIN TB_UNIDADES u on e.unidade_id = u.id WHERE  qtd_retirada < 1 order by e.id ASC";
		
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.execute();
			ResultSet rs = st.getResultSet();
			
			while(rs.next()) {
				if(rs != null){
					entradas.add(new Entrada(rs.getInt(1), new Item(rs.getInt(2), rs.getString(3)), new Unidade(rs.getInt(4), rs.getString(5)), new Fabricante(rs.getInt(6),rs.getString(7)), null, rs.getDate(8), rs.getDate(10), rs.getDate(9), rs.getInt(11), rs.getInt(12)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return entradas;
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

	public Entrada CadastrarEntrada(Entrada e) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("  INSERT INTO TB_ENTRADAS  ");
		query.append("  (item_id, unidade_id, fabricante_id, validade, fabricacao, entrada, qtd ) ");
		query.append("  VALUES (? , ? , ? , ? , ? , ? , ?) ");
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

			st.setInt(1, e.getItem().getId());
			st.setInt(2, e.getUnidade().getId());
			st.setInt(3, e.getFabricante().getId());
			st.setDate(4, new java.sql.Date(e.getDataValidade().getTime()));
			st.setDate(5, new java.sql.Date(e.getDataFabricacao().getTime()));
			st.setDate(6, new java.sql.Date(e.getDataEntrada().getTime()));
			st.setInt(7, e.getQtd());
			st.execute();
			ResultSet rs = st.getGeneratedKeys();

			if (rs.next()) {
				e.setId(rs.getInt(1));
				// cadastrao = new Item(rs.getInt(1), rs.getString(2));
			}
			conn.commit();
			st.close();
			rs.close();
		} catch (SQLException e1) {
			conn.rollback();
			throw e1;
		} finally {
			st.close();
		}
		return e;

	}
	/*
	 * GETS
	 */

	public Item GetItemPorNome(String nome) {
		Item f = null;
		String query = "SELECT * FROM TB_ITENS WHERE item = ? ";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nome);
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				if (rs != null) {
					f = new Item(rs.getInt(1), rs.getString(2));
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public Fabricante GetFabricantePorNome(String nome) {
		Fabricante f = null;
		String query = "SELECT * FROM TB_FABRICANTES WHERE fabricante = ? ";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nome);
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				if (rs != null) {
					f = new Fabricante(rs.getInt(1), rs.getString(2));
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public Unidade GetUnidadePorNome(String nome) {
		Unidade f = null;
		String query = "SELECT * FROM TB_UNIDADES WHERE unidade = ? ";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, nome);
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				if (rs != null) {
					f = new Unidade(rs.getInt(1), rs.getString(2));
				}
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	
	/*
	 * UPDATE
	 */
	
	public boolean UpdateRetirada(Entrada e) throws SQLException {
		StringBuilder query = new StringBuilder();
		query.append("  UPDATE TB_ENTRADAS  ");
		query.append(" SET qtd_retirada = ?  ");
		query.append("  WHERE id = ? ");
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(query.toString(), PreparedStatement.RETURN_GENERATED_KEYS);

			st.setInt(1, e.getRetirada());
			st.setInt(2, e.getId());
			st.executeUpdate();
			
			conn.commit();
			st.close();
			System.out.println("ok");
			return true;
		} catch (SQLException e1) {
			conn.rollback();
			
			throw e1;
			
	}
	}
}