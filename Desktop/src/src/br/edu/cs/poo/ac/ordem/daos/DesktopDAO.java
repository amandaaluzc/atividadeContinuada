package br.edu.cs.poo.ac.ordem.daos;

import java.io.Serializable;

import br.edu.cs.poo.ac.ordem.entidades.Desktop;


//O identificador único, por objeto, de Desktop é a concatenação do retorno 
//do método getTipo com o atributo serial.   
public class DesktopDAO extends DAOGenerico {
	public DesktopDAO() {
		super(Desktop.class); 
	}
	public Desktop buscar(String codigo) {
		return (Desktop)cadastroObjetos.buscar(codigo);		
	}
	private String getId(Desktop notebook) {
		return notebook.getIdTipo() + notebook.getSerial();
	}
	public boolean incluir(Desktop notebook) {		
		String id = getId(notebook);
		if (buscar(id) == null) {
			cadastroObjetos.incluir(notebook, id);
			return true;
		} else {
			return false;
		}
	}
	public boolean alterar(Desktop notebook) {
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
	public Desktop[] buscarTodos() {
		Serializable[] ret = cadastroObjetos.buscarTodos();
		Desktop[] retorno;
		if (ret != null && ret.length > 0) {
			retorno = new Desktop[ret.length];
			for (int i=0; i<ret.length; i++) {
				retorno[i] = (Desktop)ret[i];
			}
		} else {
			retorno = new Desktop[0]; 
		}
		return retorno;
	}

}
