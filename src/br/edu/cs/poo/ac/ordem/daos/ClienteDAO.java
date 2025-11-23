package br.edu.cs.poo.ac.ordem.daos;

import br.edu.cs.poo.ac.utils.Registro;

import br.edu.cs.poo.ac.ordem.entidades.Cliente;

public class ClienteDAO extends DAOGenerico {
	public Cliente buscar(String codigo) {
		return (Cliente)super.buscar(codigo);
	}
	
	public boolean incluir(Cliente cliente) {
		return super.incluir(cliente);
	}
	
	public boolean alterar(Cliente cliente) {
		return super.alterar(cliente);
	}
	
	public boolean excluir(String id) {
		return super.excluir(id);
	}
	
	public Cliente[] buscarTodos() {
		Registro[] ret = super.buscarTodos();
		Cliente[] retorno;
		if (ret != null && ret.length > 0) {
			retorno = new Cliente[ret.length];
			for (int i = 0; i < ret.length; i++) {
				retorno[i] = (Cliente) ret[i];
			}
		} else {
			retorno = new Cliente[0];
		}
		return retorno;
	}

	@Override
	public Class<?> getClasseEntidade() {
		return Cliente.class;
	}
	
}
