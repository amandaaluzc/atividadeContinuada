package br.edu.cs.poo.ac.ordem.daos;

import java.io.Serializable;

import br.edu.cs.poo.ac.ordem.entidades.Desktop;
//O identificador único, por objeto, de Desktop é a concatenação do retorno 
//do método getTipo com o atributo serial.   


public class DesktopDAO extends DAOGenerico {
	
	public DesktopDAO () {
		super(Desktop.class);
	}
		
	
	private String getCodigo (Desktop desk) {
		return desk.getIdTipo() + desk.getSerial();
	}
	
	public Desktop buscar(String codigo) {
		return (Desktop)cadastroObjetos.buscar(codigo);
	}
	
	public boolean incluir (Desktop desk) {
		if (buscar(getCodigo(desk)) == null) {
			cadastroObjetos.incluir(desk, getCodigo(desk));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean alterar (Desktop desk) {
		if (buscar(getCodigo(desk)) != null) {
			cadastroObjetos.alterar(desk, getCodigo(desk));
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
	
	public Desktop[] buscarTodos() {
		Serializable[] ret = cadastroObjetos.buscarTodos();
		Desktop[] retorno;
		if (ret != null && ret.length > 0) {
			retorno = new Desktop[ret.length];
			for (int i = 0; i < ret.length; i++) {
				retorno[i] = (Desktop)ret[i]; 
			}
		} else {
			retorno = new Desktop[0];
		}
		return retorno;		
	}
}
