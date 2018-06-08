package negocio;

import dados.ClienteDAO;
import model.Cliente;

public class RegraCliente {

	private ClienteDAO clienteDao;

	public RegraCliente() {
		clienteDao = new ClienteDAO();
	}

	public Cliente consultaCliente(String id) {

		return clienteDao.consultar(id);

	}

	/**
	 * Método responsável por alterar ou inserir um usuário Caso o ID esteja
	 * preenchido considero que o usuário já existe então só faço alterar utilizando
	 * o código informado
	 * 
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	public int adicionarOrAlterar(Cliente cliente) throws Exception {

		if (cliente.getNome().isEmpty()) {
			throw new Exception("Nome está vazio");
		} else if (cliente.getCpf().isEmpty()) {
			throw new Exception("CPF está vazio");
		} else if (cliente.getSexo().isEmpty()) {
			throw new Exception("Preencha o sexo");
		} else if (cliente.getCpf().length() != 11) {
			throw new Exception("CPF deve conter 11 Digitos!");
		} else if (!cliente.getCpf().matches("[0-9]*")) {
			throw new Exception("CPF Invalido, deve conter apenas numeros!");
		}
		if (cliente.getNome() != null && !cliente.getNome().isEmpty()) {
			return clienteDao.atualizar(cliente);
		} else {
			return clienteDao.adicionar(cliente);
		}
	}

	public int remover(String id) {
		return clienteDao.remover(id);

	}

}
