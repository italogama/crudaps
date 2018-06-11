package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import conexaoBanco.ConexaoMySQL;
import model.Listar;

public class ListarDAO {

	private Connection conexao = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	String queryConsulta = "select * from clientes";
	// ORDER BY hotel_diaria";

	public ListarDAO() {
		conexao = ConexaoMySQL.conector();
		pst = null;
		rs = null;
	}

	public ResultSet consultar() {

		try {
			// Executa consulta de hotel
			pst = conexao.prepareStatement(queryConsulta);
			rs = pst.executeQuery();

		} catch (Exception e) {

		}

		return rs;
	}


}
