package br.edu.cs.poo.ac.ordem.daos;

import java.io.Serializable;

import br.edu.cs.poo.ac.ordem.entidades.Notebook;

//O identificador único, por objeto, de Notebook é a concatenação do retorno 
//do método getTipo com o atributo serial.   
public class NotebookDAO extends DAOGenerico {
	public NotebookDAO() {
		super(Notebook.class);
	}
	public Notebook buscar(String codigo) {
		return (Notebook)cadastroObjetos.buscar(codigo);		
	}
	private String getId(Notebook notebook) {
		return notebook.getIdTipo() + notebook.getSerial();
	}
	public boolean incluir(Notebook notebook) {		
		String id = getId(notebook);
		if (buscar(id) == null) {
			cadastroObjetos.incluir(notebook, id);
			return true;
		} else {
			return false;
		}
	}
	public boolean alterar(Notebook notebook) {
		String id = getId(notebook);
		if (buscar(id) != null) {
			cadastroObjetos.alterar(notebook, id);
			return true;
		} else {
			return false;
		}
	}
	public boolean excluir(String id) {
		if (buscar(id) != null) {
			cadastroObjetos.excluir(id);
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
			for (int i=0; i<ret.length; i++) {
				retorno[i] = (Notebook)ret[i];
			}
		} else {
			retorno = new Notebook[0]; 
		}
		return retorno;
	}

}
