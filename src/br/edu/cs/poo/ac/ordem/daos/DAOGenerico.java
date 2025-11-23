package br.edu.cs.poo.ac.ordem.daos;

import java.io.Serializable;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.edu.cs.poo.ac.ordem.entidades.Cliente;
import br.edu.cs.poo.ac.utils.Registro;

public abstract class DAOGenerico {
	protected CadastroObjetos cadastroObjetos;
	
	public DAOGenerico() {
		cadastroObjetos = new CadastroObjetos(getClasseEntidade());
	}
	
	public abstract Class<?> getClasseEntidade();
	
	
	public Registro buscar(String Id) {
		return (Registro)cadastroObjetos.buscar(Id);
	}
	
	public boolean incluir(Registro registro) {	
		if (registro == null) return false;
		
		String idRetorno = registro.getId();
		if (buscar(idRetorno) == null) {
			cadastroObjetos.incluir(registro, idRetorno);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean alterar(Registro registro) {
		if (registro == null) return false;
		
		String idRetorno = registro.getId();
		if (buscar(idRetorno) != null) {
			cadastroObjetos.alterar(registro, idRetorno);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean excluir(String id) {
		if(buscar(id) != null) {
			cadastroObjetos.excluir(id);
			return true;
		} else {
			return false;
		}
	}
	
	public Registro[] buscarTodos() {
		Serializable[] ret = cadastroObjetos.buscarTodos();
		Registro[] retorno;
		
		if (ret != null && ret.length > 0) {
			retorno = new Registro[ret.length];
			for (int i = 0; i < ret.length; i++) {
				retorno[i] = (Registro)ret[i];
			}
		} else {
			retorno = new Registro[0];
		}
		return retorno;
	}
	
}