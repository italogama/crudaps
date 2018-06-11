package negocio;

import java.sql.ResultSet;

import dados.ListarDAO;
import dados.ClienteDAO;
import model.Cliente;

public class RegraListar {

	private ListarDAO listarDao;

	public RegraListar() {
		listarDao = new ListarDAO();
	}

	public ResultSet consultar() {

		return listarDao.consultar();

	}
}