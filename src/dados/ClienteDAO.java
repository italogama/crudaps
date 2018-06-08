package dados;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexaoBanco.ConexaoMySQL;
import model.Cliente;

public class ClienteDAO {

	private Connection conexao = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	String queryConsultaPelaId = "select * from clientes where id=?";
	String queryAdicionarUsuario = "insert into clientes (nome, cpf, sexo, nascimento) values (?,?,?,?) ";
	String queryDeletarUsuario = "delete from clientes where id=?";
	String queryAlterarUsuario = "update clientes set nome=?, cpf=?, sexo=?, nascimento=? where id=?";

	public ClienteDAO() {
		conexao = ConexaoMySQL.conector();
		pst = null;
		rs = null;
	}

	public Cliente consultar(String id) {

		Cliente cliente = new Cliente();

		try {
			// Executa consulta de login
			pst = conexao.prepareStatement(queryConsultaPelaId);
			pst.setString(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setSexo(rs.getString("sexo"));
				cliente.setDate(rs.getString("nascimento"));
			} else {
				// caso ele nao encontre um usuario ele vai limpar os campos e exibir msg de
				// erro
				cliente = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cliente;

	}

	public int adicionar(Cliente cliente) throws Exception {

		try {
			pst = conexao.prepareStatement(queryAdicionarUsuario);
			// pst.setString(1,txtUsuId.getText());
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getSexo());
			pst.setString(4, cliente.getNascimento());
			return pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public int remover(String id) {

		try {
			pst = conexao.prepareStatement(queryDeletarUsuario);
			pst.setString(1, id);
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int atualizar(Cliente cliente) {

		try {
			pst = conexao.prepareStatement(queryAlterarUsuario);
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getCpf());
			pst.setString(3, cliente.getSexo());
			pst.setDate(4, (Date) cliente.getNascimento());

			return pst.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return 0;
	}
}
