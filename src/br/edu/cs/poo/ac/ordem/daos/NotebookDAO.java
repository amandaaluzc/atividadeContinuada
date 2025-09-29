package br.edu.cs.poo.ac.ordem.daos;

import java.io.Serializable;

import br.edu.cs.poo.ac.ordem.entidades.Notebook;

//O identificador único, por objeto, de Notebook é a concatenação do retorno 
//do método getTipo com o atributo serial.  


public class NotebookDAO extends DAOGenerico {
	
	public NotebookDAO() {
		super(Notebook.class);
	}
	
	private String getCodigo(Notebook note) {
		return note.getIdTipo() + note.getSerial(); 
	}
	
	public Notebook buscar(String codigo) {
		return (Notebook)cadastroObjetos.buscar(codigo);
	}
	
	public boolean incluir(Notebook Notebook) {
		if (buscar(getCodigo(Notebook)) == null) {
			cadastroObjetos.incluir(Notebook, getCodigo(Notebook));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean alterar(Notebook Notebook) {
		if (buscar(getCodigo(Notebook)) != null) {
			cadastroObjetos.alterar(Notebook, getCodigo(Notebook));
			return true;
		} else {
			return false;
		}
	}

	public boolean excluir (String codigo) {
		if (buscar(codigo) != null) {
			cadastroObjetos.excluir(codigo);
			return true;
		} else {
			return false;
		}
	}
	
	public Notebook[] buscarTodos() {
		Serializable[] ret = cadastroObjetos.buscarTodos();
		Notebook[] retorno;
		if (ret != null && ret.length > 0) {
			retorno = new Notebook[ret.length];
			for (int i = 0; i < ret.length; i++) {
				retorno[i] = (Notebook)ret[i];
			}
		} else {
			retorno = new Notebook[0];
		}
		return retorno;
	}
}